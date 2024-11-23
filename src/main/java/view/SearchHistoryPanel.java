package view;

import interface_adapter.search.SearchHistoryController;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

public class SearchHistoryPanel extends JPanel {
    public SearchHistoryPanel(SearchHistoryController controller) {
        setLayout(new BorderLayout());

        // Search History Display
        JTextArea historyArea = new JTextArea(15, 40);
        historyArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(historyArea);

        // Input Panel
        JTextField queryField = new JTextField(20);
        JButton addButton = new JButton("Add Search Query");

        // ActionListener for adding a search query
        addButton.addActionListener(e -> {
            String query = queryField.getText().trim();
            if (!query.isEmpty()) {
                controller.addSearchQuery(query);
                queryField.setText(""); // Clear the input field
                updateHistory(controller, historyArea);
            }
        });

        // Layout setup
        JPanel inputPanel = new JPanel(new FlowLayout());
        inputPanel.add(new JLabel("Enter search query:"));
        inputPanel.add(queryField);
        inputPanel.add(addButton);

        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        // Load existing history on initialization
        updateHistory(controller, historyArea);
    }

    private void updateHistory(SearchHistoryController controller, JTextArea historyArea) {
        List<String> history = controller.getSearchHistory().stream()
                .map(Object::toString)
                .collect(Collectors.toList()); // Use Collectors.toList() instead of .toList()

        historyArea.setText(String.join("\n", history));
    }

}
