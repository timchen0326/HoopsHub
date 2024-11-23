package view;

import interface_adapter.search.SearchHistoryController;

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

        add(scrollPane, BorderLayout.CENTER);

        // Back Button
        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> frame.switchTo("Home")); // Switch back to HomePanel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonPanel.add(backButton);

        add(buttonPanel, BorderLayout.SOUTH);

        // Load existing history for the logged-in user
        updateHistory(controller, historyArea);
    }

    private void updateHistory(SearchHistoryController controller, JTextArea historyArea) {
        // Fetch and display the search history for the logged-in user
        List<String> history = controller.getSearchHistory().stream()
                .map(Object::toString)
                .collect(Collectors.toList());

        if (history.isEmpty()) {
            historyArea.setText("No search history found for this user.");
        } else {
            historyArea.setText(String.join("\n", history));
        }
    }
}
