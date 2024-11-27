package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import use_case.search.SearchInteractor;

/**
 * Panel for searching user data and displaying results.
 */
public class SearchPanel extends JPanel {

    private static final int TITLE_FONT_SIZE = 24;
    private static final int LABEL_FONT_SIZE = 18;
    private static final int TEXT_FIELD_COLUMNS = 20;
    private static final int PADDING = 10;
    private static final int RESULT_PADDING = 5;

    private final SearchInteractor interactor;

    private final JLabel winsValue = new JLabel("--");
    private final JLabel lossesValue = new JLabel("--");
    private final JLabel ratioValue = new JLabel("--");

    /**
     * Constructs a SearchPanel with the given frame and search interactor.
     *
     * @param frame      the main application frame
     * @param interactor the interactor responsible for executing searches
     */
    public SearchPanel(MainFrame frame, SearchInteractor interactor) {
        this.interactor = interactor;

        // Set layout for SearchPanel
        setLayout(new BorderLayout(PADDING, PADDING));
        setBackground(Color.WHITE);

        // Title Label
        final JLabel titleLabel = new JLabel("Search User");
        titleLabel.setFont(new Font("Arial", Font.BOLD, TITLE_FONT_SIZE));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        add(titleLabel, BorderLayout.NORTH);

        // Input Section
        final JPanel inputPanel = createInputPanel();
        add(inputPanel, BorderLayout.NORTH);

        // Results Section
        final JPanel resultsPanel = createResultsPanel();
        add(resultsPanel, BorderLayout.CENTER);

        // Back Button
        final JPanel bottomPanel = createBottomPanel(frame);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private JPanel createInputPanel() {
        final JPanel inputPanel = new JPanel(new GridBagLayout());
        inputPanel.setBackground(Color.WHITE);
        final GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(PADDING, PADDING, PADDING, PADDING);

        final JLabel label = new JLabel("Enter Username:");
        label.setFont(new Font("Arial", Font.PLAIN, LABEL_FONT_SIZE));

        final JTextField textField = new JTextField(TEXT_FIELD_COLUMNS);
        textField.setFont(new Font("Arial", Font.PLAIN, LABEL_FONT_SIZE));

        final JButton searchButton = new JButton("Search");
        searchButton.setFont(new Font("Arial", Font.PLAIN, LABEL_FONT_SIZE));

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

        // Action Listener for Search Button
        searchButton.addActionListener(event -> handleSearch(textField));

        return inputPanel;
    }

    private JPanel createResultsPanel() {
        final JPanel resultsPanel = new JPanel(new GridBagLayout());
        resultsPanel.setBackground(Color.WHITE);
        final GridBagConstraints resultsGbc = new GridBagConstraints();
        resultsGbc.insets = new Insets(RESULT_PADDING, RESULT_PADDING, RESULT_PADDING, RESULT_PADDING);
        resultsGbc.anchor = GridBagConstraints.WEST;

        // Results Labels
        addResultRow(resultsPanel, resultsGbc, 0, "Wins: ", winsValue);
        addResultRow(resultsPanel, resultsGbc, 1, "Losses: ", lossesValue);
        addResultRow(resultsPanel, resultsGbc, 2, "Win Ratio: ", ratioValue);

        return resultsPanel;
    }

    private void addResultRow(JPanel panel, GridBagConstraints gbc, int row, String labelText, JLabel valueLabel) {
        gbc.gridx = 0;
        gbc.gridy = row;

        final JLabel label = new JLabel(labelText);
        label.setFont(new Font("Arial", Font.PLAIN, LABEL_FONT_SIZE));
        panel.add(label, gbc);

        gbc.gridx = 1;

        valueLabel.setFont(new Font("Arial", Font.PLAIN, LABEL_FONT_SIZE));
        panel.add(valueLabel, gbc);
    }

    private JPanel createBottomPanel(MainFrame frame) {
        final JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bottomPanel.setBackground(Color.WHITE);

        final JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.PLAIN, LABEL_FONT_SIZE));

        // Back Button Action Listener
        backButton.addActionListener(event -> {
            clearResults();
            frame.switchTo("Home");
        });

        bottomPanel.add(backButton);
        return bottomPanel;
    }

    private void handleSearch(JTextField textField) {
        final String username = textField.getText().trim();

        if (username.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a username to search.", "Input Error",
                    JOptionPane.ERROR_MESSAGE);
            clearResults();
            return;
        }

        final String result = interactor.executeSearch(username);

        if (result == null || result.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No results found for the username: " + username, "No Results",
                    JOptionPane.INFORMATION_MESSAGE);
            clearResults();
        }
        else {
            updateResults(result);
        }
    }

    private void clearResults() {
        winsValue.setText("--");
        lossesValue.setText("--");
        ratioValue.setText("--");
    }

    private void updateResults(String result) {
        // Parse and display results
        final int wins = extractValue(result, "Wins:");
        final int losses = extractValue(result, "Losses:");
        final double winRatio = calculateWinLossRatio(wins, losses);

        winsValue.setText(String.valueOf(wins));
        lossesValue.setText(String.valueOf(losses));
        ratioValue.setText(String.format("%.2f%%", winRatio));
    }

    private int extractValue(String result, String key) {
        final String[] lines = result.split("\\n");
        for (String line : lines) {
            if (line.startsWith(key)) {
                try {
                    return Integer.parseInt(line.replaceAll("[^0-9]", ""));
                }
                catch (NumberFormatException ex) {
                    ex.printStackTrace();
                }
            }
        }
        return 0;
    }

    private double calculateWinLossRatio(int wins, int losses) {
        final int totalGames = wins + losses;
        if (totalGames == 0) {
            return 0.0;
        }
        return ((double) wins / totalGames) * 100;
    }
}
