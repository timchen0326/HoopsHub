package view;

import use_case.note.search.SearchInteractor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SearchPanel {
    private final SearchInteractor interactor;

    public SearchPanel(SearchInteractor interactor) {
        this.interactor = interactor;
        createAndShowGUI();
    }

    private void createAndShowGUI() {
        // Create the main frame
        JFrame frame = new JFrame("Search Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        // Create the input panel
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());

        JLabel label = new JLabel("Enter username:");
        JTextField textField = new JTextField(20);
        JButton searchButton = new JButton("Search");

        inputPanel.add(label);
        inputPanel.add(textField);
        inputPanel.add(searchButton);

        // Create the results area
        JTextArea resultsArea = new JTextArea();
        resultsArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultsArea);

        // Add components to the frame
        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Add action listener for the search button
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = textField.getText();
                String result = interactor.executeSearch(username);

                // Extract and calculate winrate (assuming the result contains "Wins" and "Losses" lines)
                int wins = extractValue(result, "Wins:");
                int losses = extractValue(result, "Losses:");
                double winrate = calculateWinrate(wins, losses);

                // Filter out "UserID" and "Username" lines and append winrate
                String filteredResult = result.replaceAll("(?m)^Username:.*\\n?", "")
                        .replaceAll("(?m)^UserID:.*\\n?", "");
                filteredResult += "\nWinrate: " + String.format("%.2f", winrate) + "%";

                resultsArea.setText(filteredResult);
            }
        });

        // Display the frame
        frame.setVisible(true);
    }

    private int extractValue(String result, String key) {
        String[] lines = result.split("\\n");
        for (String line : lines) {
            if (line.startsWith(key)) {
                try {
                    return Integer.parseInt(line.replaceAll("[^0-9]", ""));
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
            }
        }
        return 0;
    }

    private double calculateWinrate(int wins, int losses) {
        if (wins + losses == 0) {
            return 0.0;
        }
        return ((double) wins / (wins + losses)) * 100;
    }
}
