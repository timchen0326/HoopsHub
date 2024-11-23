package view;

import interface_adapter.search.SearchHistoryController;
import app.Session;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;
import org.json.JSONObject;

public class SearchHistoryPanel extends JPanel {
    public SearchHistoryPanel(SearchHistoryController controller, MainFrame frame) {
        setLayout(new BorderLayout());

        // Search History Display
        JTextArea historyArea = new JTextArea(15, 40);
        historyArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(historyArea);

        // Filter Panel
        JPanel filterPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel filterLabel = new JLabel("Filter by:");
        String[] filterOptions = {"All", "Wins", "Losses"};
        JComboBox<String> filterDropdown = new JComboBox<>(filterOptions);

        filterPanel.add(filterLabel);
        filterPanel.add(filterDropdown);

        // Back Button
        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> frame.switchTo("Home")); // Switch back to HomePanel
        filterPanel.add(backButton);

        add(filterPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        // Load and display history initially
        updateHistory(historyArea, "All");

        // Add ActionListener to filter dropdown
        filterDropdown.addActionListener(e -> {
            String selectedFilter = (String) filterDropdown.getSelectedItem();
            updateHistory(historyArea, selectedFilter);
        });
    }

    private void updateHistory(JTextArea historyArea, String filter) {
        // Fetch data from the Session
        Session session = Session.getInstance();
        List<JSONObject> historyList = session.getHistory();

        StringBuilder displayText = new StringBuilder("Game History:\n\n");
        int gameNumber = 1;

        for (JSONObject history : historyList) {
            String player = history.getString("player");
            String stats = history.getString("stats");
            String year = history.getString("year");
            String result = determineResult(stats); // Logic to determine "Win" or "Lose"

            // Apply filter logic
            if (filter.equals("Wins") && !result.equals("Win")) continue;
            if (filter.equals("Losses") && !result.equals("Lose")) continue;

            displayText.append(String.format(
                    "Game %d:\nPlayer: %s | Stats: %s | Year: %s | Result: %s\n\n",
                    gameNumber++, player, stats, year, result
            ));
        }

        // Append win/loss summary
        displayText.append("\nSummary:\n");
        displayText.append(String.format("Wins: %d | Losses: %d\n",
                session.getWin(), session.getLose()));

        // Update the history area
        if (displayText.length() == "Game History:\n\n\nSummary:\n".length()) {
            historyArea.setText("No results found for the selected filter.");
        } else {
            historyArea.setText(displayText.toString());
        }
    }

    private String determineResult(String stats) {
        // Placeholder logic to determine the result based on stats
        return stats.toLowerCase().contains("win") ? "Win" : "Lose";
    }
}
