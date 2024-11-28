package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import org.json.JSONObject;

import app.Session;
import interface_adapter.search.SearchHistoryController;

/**
 * Panel for displaying and managing search history with stat filter.
 */
public class SearchHistoryPanel extends JPanel {

    private static final int TEXT_AREA_ROWS = 15;
    private static final int TEXT_AREA_COLUMNS = 40;

    private JComboBox<String> statFilterComboBox;

    /**
     * Constructs a SearchHistoryPanel with the given controller and frame.
     *
     * @param controller the search history controller for fetching history data
     * @param frame      the main frame of the application for navigation
     */
    public SearchHistoryPanel(SearchHistoryController controller, MainFrame frame) {
        setLayout(new BorderLayout());

        // Search History Display
        final JTextArea historyArea = new JTextArea(TEXT_AREA_ROWS, TEXT_AREA_COLUMNS);
        historyArea.setEditable(false);
        final JScrollPane scrollPane = new JScrollPane(historyArea);

        // Top Panel: Back Button and Show History Button
        final JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        final JButton backButton = new JButton("Back");
        backButton.addActionListener(event -> frame.switchTo("Home"));
        topPanel.add(backButton);

        final JButton showHistoryButton = new JButton("Show History");
        showHistoryButton.addActionListener(event -> updateHistory(historyArea));
        topPanel.add(showHistoryButton);

        // ComboBox to select stat filter (e.g., Rebounds, Points, Assists)
        statFilterComboBox = new JComboBox<>(new String[] {"All", "Points", "Rebounds", "Assists"});
        topPanel.add(statFilterComboBox);

        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }

    /**
     * Updates the history area with game history data, filtered by the selected stat.
     *
     * @param historyArea the text area to display history data
     */
    private void updateHistory(JTextArea historyArea) {
        final Session session = Session.getInstance();
        String selectedStat = (String) statFilterComboBox.getSelectedItem();

        // Check if there is any history data
        final List<JSONObject> historyList = session.getHistory();
        if (historyList == null || historyList.isEmpty()) {
            historyArea.setText("No game history available.");
            return;
        }

        // Build the history display text
        final StringBuilder displayText = new StringBuilder("Game History (" + selectedStat + "):\n\n");
        int gameNumber = 1;
        int winCount = 0, loseCount = 0;

        for (JSONObject history : historyList) {
            final String player = history.optString("player", "Unknown Player");
            final String stats = history.optString("stats", "Unknown Stats");
            final String year = history.optString("year", "Unknown Year");
            final String result = determineResult(history.optString("result", ""));

            // If the selected stat is "All" or the current stat matches the selected filter
            if ("All".equals(selectedStat) || stats.contains(selectedStat)) {
                displayText.append(String.format(
                        "Game %d:\nPlayer: %s | Stats: %s | Year: %s | Result: %s\n\n",
                        gameNumber++, player, stats, year, result
                ));

                // Update win/loss summary based on the filtered results
                if ("win".equalsIgnoreCase(result)) {
                    winCount++;
                } else {
                    loseCount++;
                }
            }
        }

        // Append the filtered win/loss summary
        displayText.append("\nSummary:\n");
        displayText.append(String.format("Wins: %d | Losses: %d\n", winCount, loseCount));

        historyArea.setText(displayText.toString());
    }

    /**
     * Determines the result of a game based on the given result string.
     *
     * @param result the result string from history data
     * @return "Win" if the result is "win" (case insensitive), otherwise "Lose"
     */
    private String determineResult(String result) {
        return "win".equalsIgnoreCase(result) ? "Win" : "Lose";
    }
}
