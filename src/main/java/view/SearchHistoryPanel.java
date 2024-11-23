package view;

import interface_adapter.search.SearchHistoryController;
import entity.SearchHistoryRecord;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

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
        updateHistory(controller, historyArea, "All");

        // Add ActionListener to filter dropdown
        filterDropdown.addActionListener(e -> {
            String selectedFilter = (String) filterDropdown.getSelectedItem();
            updateHistory(controller, historyArea, selectedFilter);
        });
    }

    private void updateHistory(SearchHistoryController controller, JTextArea historyArea, String filter) {
        // Fetch search history as List<SearchHistoryRecord>
        List<SearchHistoryRecord> fullHistoryRecords = controller.getSearchHistory();

        // Filter the history based on the selected filter
        List<SearchHistoryRecord> filteredHistory = fullHistoryRecords.stream()
                .filter(record -> {
                    String result = record.getResult();
                    if ("Wins".equals(filter)) {
                        return "Win".equalsIgnoreCase(result);
                    } else if ("Losses".equals(filter)) {
                        return "Lose".equalsIgnoreCase(result);
                    } else {
                        return true; // "All" shows all entries
                    }
                })
                .collect(Collectors.toList());

        // Update the history area
        if (filteredHistory.isEmpty()) {
            historyArea.setText("No results found for the selected filter.");
        } else {
            // Generate game numbers and format the display
            StringBuilder displayText = new StringBuilder();
            for (int i = 0; i < filteredHistory.size(); i++) {
                SearchHistoryRecord record = filteredHistory.get(i);
                displayText.append(String.format(
                        "Game %d: %s | Time: %s\n",
                        i + 1, // Game number
                        record.getResult(), // Win or Lose
                        record.getTimestamp().toString() // Timestamp
                ));
            }
            historyArea.setText(displayText.toString());
        }
    }
}
