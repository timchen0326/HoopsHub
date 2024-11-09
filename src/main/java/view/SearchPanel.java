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
        JFrame frame = new JFrame("User Search Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 350);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null); // Center the window on the screen

        // Create the input panel
        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel label = new JLabel("Enter username:");
        JTextField textField = new JTextField(20);
        JButton searchButton = new JButton("Search");

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        inputPanel.add(label, gbc);

        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        inputPanel.add(textField, gbc);

        gbc.gridx = 2;
        gbc.fill = GridBagConstraints.NONE;
        inputPanel.add(searchButton, gbc);

        // Create the results area
        JTextArea resultsArea = new JTextArea(10, 30);
        resultsArea.setEditable(false);
        resultsArea.setLineWrap(true);
        resultsArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(resultsArea);

        // Add components to the frame
        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Action listener for searching
        ActionListener searchAction = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = textField.getText().trim();
                if (username.isEmpty()) {
                    resultsArea.setText("Please enter a username to search.");
                    return;
                }
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
        };

        // Add action listener for the search button
        searchButton.addActionListener(searchAction);

        // Add key listener for the Enter key on the text field
        textField.addActionListener(searchAction);

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
