package view;

import use_case.search.SearchInteractor;

import javax.swing.*;
import java.awt.*;

public class SearchPanel extends JPanel {
    private final SearchInteractor interactor;

    public SearchPanel(MainFrame frame, SearchInteractor interactor) {
        this.interactor = interactor;

        // Set layout for SearchPanel
        setLayout(new BorderLayout(20, 20));
        setBackground(Color.WHITE);

        // Title Label
        JLabel titleLabel = new JLabel("Search User");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(titleLabel, BorderLayout.NORTH);

        // Input Section
        JPanel inputPanel = new JPanel(new GridBagLayout());
        inputPanel.setBackground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel label = new JLabel("Enter Username:");
        label.setFont(new Font("Arial", Font.PLAIN, 18));

        JTextField textField = new JTextField(20);
        textField.setFont(new Font("Arial", Font.PLAIN, 18));

        JButton searchButton = new JButton("Search");
        searchButton.setFont(new Font("Arial", Font.PLAIN, 18));

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

        add(inputPanel, BorderLayout.NORTH);

        // Results Section
        JPanel resultsPanel = new JPanel(new GridBagLayout());
        resultsPanel.setBackground(Color.WHITE);
        GridBagConstraints resultsGbc = new GridBagConstraints();
        resultsGbc.insets = new Insets(5, 5, 5, 5);
        resultsGbc.anchor = GridBagConstraints.WEST;

        JLabel winsLabel = new JLabel("Wins: ");
        winsLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        resultsGbc.gridx = 0;
        resultsGbc.gridy = 0;
        resultsPanel.add(winsLabel, resultsGbc);

        JLabel winsValue = new JLabel("--");
        winsValue.setFont(new Font("Arial", Font.PLAIN, 18));
        resultsGbc.gridx = 1;
        resultsPanel.add(winsValue, resultsGbc);

        JLabel lossesLabel = new JLabel("Losses: ");
        lossesLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        resultsGbc.gridx = 0;
        resultsGbc.gridy = 1;
        resultsPanel.add(lossesLabel, resultsGbc);

        JLabel lossesValue = new JLabel("--");
        lossesValue.setFont(new Font("Arial", Font.PLAIN, 18));
        resultsGbc.gridx = 1;
        resultsPanel.add(lossesValue, resultsGbc);

        JLabel ratioLabel = new JLabel("Win Ratio: ");
        ratioLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        resultsGbc.gridx = 0;
        resultsGbc.gridy = 2;
        resultsPanel.add(ratioLabel, resultsGbc);

        JLabel ratioValue = new JLabel("--");
        ratioValue.setFont(new Font("Arial", Font.PLAIN, 18));
        resultsGbc.gridx = 1;
        resultsPanel.add(ratioValue, resultsGbc);

        add(resultsPanel, BorderLayout.CENTER);

        // Back Button
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.setBackground(Color.WHITE);

        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.PLAIN, 18));

        // Back Button Action Listener
        backButton.addActionListener(e -> {
            // Clear the search bar
            textField.setText("");

            // Reset result values
            winsValue.setText("--");
            lossesValue.setText("--");
            ratioValue.setText("--");

            // Switch back to Home Panel
            frame.switchTo("Home");
        });

        bottomPanel.add(backButton);
        add(bottomPanel, BorderLayout.SOUTH);

        // Action Listener for Search
        searchButton.addActionListener(e -> {
            String username = textField.getText().trim();

            // Check if the username field is empty
            if (username.isEmpty()) {
                winsValue.setText("--");
                lossesValue.setText("--");
                ratioValue.setText("--");
                JOptionPane.showMessageDialog(this, "Please enter a username to search.", "Input Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Call the interactor to fetch results
            String result = interactor.executeSearch(username);

            if (result == null || result.isEmpty()) {
                // Display "no results" message
                winsValue.setText("--");
                lossesValue.setText("--");
                ratioValue.setText("--");
                JOptionPane.showMessageDialog(this, "No results found for the username: " + username, "No Results", JOptionPane.INFORMATION_MESSAGE);
            } else {
                // Extract values and update labels
                int wins = extractValue(result, "Wins:");
                int losses = extractValue(result, "Losses:");
                double winRatio = calculateWinLossRatio(wins, losses);

                winsValue.setText(String.valueOf(wins));
                lossesValue.setText(String.valueOf(losses));
                ratioValue.setText(String.format("%.2f%%", winRatio));
            }
        });
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

    private double calculateWinLossRatio(int wins, int losses) {
        int totalGames = wins + losses;
        if (totalGames == 0) {
            return 0.0;
        }
        return ((double) wins / totalGames) * 100;
    }
}
