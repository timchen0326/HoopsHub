package view;

import interface_adapter.search.SearchHistoryController;
import app.Session;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import org.json.JSONObject;

public class SearchHistoryPanel extends JPanel {
    public SearchHistoryPanel(SearchHistoryController controller, MainFrame frame) {
        setLayout(new BorderLayout());

        // Search History Display
        JTextArea historyArea = new JTextArea(15, 40);
        historyArea.setEditable(false);

        // Set Comic Sans MS font for JTextArea to make the text bigger
        historyArea.setFont(new Font("Comic Sans MS", Font.PLAIN, 17));  // Change font to Comic Sans MS

        JScrollPane scrollPane = new JScrollPane(historyArea);

        // Top Panel: Back Button and Show History Button
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> frame.switchTo("Home")); // Switch back to HomePanel
        topPanel.add(backButton);

        JButton showHistoryButton = new JButton("Show History");
        showHistoryButton.addActionListener(e -> updateHistory(historyArea));
        topPanel.add(showHistoryButton);

        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void updateHistory(JTextArea historyArea) {
        // Fetch data from the Session
        Session session = Session.getInstance();

        // Check if there is any history data
        List<JSONObject> historyList = session.getHistory();
        if (historyList == null || historyList.isEmpty()) {
            historyArea.setText("No game history available.");
            return;
        }

        // Build the history display text
        StringBuilder displayText = new StringBuilder("Game History:\n\n");
        int gameNumber = 1;

        for (JSONObject history : historyList) {
            String player = history.optString("player", "Unknown Player");
            String stats = history.optString("stats", "Unknown Stats");
            String year = history.optString("year", "Unknown Year");
            String result = determineResult(history.optString("result", ""));

            displayText.append(String.format(
                    "Game %d:\nPlayer: %s | Stats: %s | Year: %s | Result: %s\n\n",
                    gameNumber++, player, stats, year, result
            ));
        }

        // Append win/loss summary
        displayText.append("\nSummary:\n");
        displayText.append(String.format("Wins: %d | Losses: %d\n",
                session.getWin(), session.getLose()));

        historyArea.setText(displayText.toString());
    }

    private String determineResult(String result) {
        // Return "Win" or "Lose" based on the provided result
        return result.equalsIgnoreCase("win") ? "Win" : "Lose";
    }
}
