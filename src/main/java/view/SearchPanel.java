package view;

import use_case.search.SearchInteractor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SearchPanel extends JPanel {
    private final SearchInteractor interactor;
    private final ThemeManager themeManager = ThemeManager.getInstance();

    public SearchPanel(MainFrame frame, SearchInteractor interactor) {
        this.interactor = interactor;

        // Set layout for SearchPanel
        setLayout(new BorderLayout());
        updateTheme();

        // Create Input Section
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

        // Results Section
        JTextArea resultsArea = new JTextArea(10, 30);
        resultsArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultsArea);

        // Back Button
        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> frame.switchTo("Home")); // Switch back to HomePanel

        // Add Components to the Panel
        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(backButton, BorderLayout.SOUTH);

        // Action Listener for Search
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = textField.getText().trim();
                if (username.isEmpty()) {
                    resultsArea.setText("Please enter a username to search.");
                    return;
                }

                // Call interactor to fetch results
                String result = interactor.executeSearch(username);

                // Parse results to extract wins, losses, and calculate win/loss ratio
                int wins = extractValue(result, "Wins:");
                int losses = extractValue(result, "Losses:");
                double winLossRatio = calculateWinLossRatio(wins, losses);

                // Display the results
                resultsArea.setText(String.format("Wins: %d\nLosses: %d\nWin Ratio: %.2f%%",
                        wins, losses, winLossRatio));
            }
        });
    }

    private void updateTheme() {
        setBackground(themeManager.getBackgroundColor());
        setForeground(themeManager.getTextColor());

        for (Component comp : getComponents()) {
            if (comp instanceof JLabel) {
                comp.setForeground(themeManager.getTextColor());
            } else if (comp instanceof JTextArea) {
                comp.setBackground(themeManager.getBackgroundColor());
                comp.setForeground(themeManager.getTextColor());
            }
        }
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
