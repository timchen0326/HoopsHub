package view;

import app.Session;
import data_access.AccountDataAccessObject;
import interface_adapter.PlayGameController;
import interface_adapter.PlayerStatsFormatter;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.util.List;
import java.util.Random;

public class PlayGamePanel extends JPanel {
    private final JTextField playerNameField = new JTextField(20);
    private final JComboBox<String> yearComboBox = new JComboBox<>();
    private final JTextArea statsArea = new JTextArea(12, 40);
    private final JComboBox<String> guessComboBox = new JComboBox<>(new String[]{"Average Rebounds", "Average Points", "Average Assists"});
    private final JLabel randomGuessLabel = new JLabel("Generated Value: --");
    private final JButton fetchButton = new JButton("Fetch Stats");
    private final JButton fetchYearButton = new JButton("Select Year");
    private final JButton overButton = new JButton("Over");
    private final JButton underButton = new JButton("Under");
    private final JPanel yearPanel = new JPanel();

    private double currentTrueAverage; // Store the true average of the current stat

    public PlayGamePanel(MainFrame frame, PlayGameController controller) {
        setLayout(new BorderLayout(20, 20));

        // Input Panel
        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        inputPanel.add(new JLabel("Enter Player Name:"));
        inputPanel.add(playerNameField);
        inputPanel.add(fetchButton);

        // Year Selection Panel
        yearPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        yearPanel.add(new JLabel("Select Year:"));
        yearPanel.add(yearComboBox);
        yearPanel.add(fetchYearButton);
        yearPanel.setVisible(false);

        // Stats Area Panel
        statsArea.setEditable(false);
        statsArea.setLineWrap(true);
        statsArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(statsArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Player Statistics"));

        // Guess Panel
        JPanel guessPanel = new JPanel(new GridBagLayout());
        guessPanel.setBorder(BorderFactory.createTitledBorder("Make a Guess"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0; gbc.gridy = 0;
        guessPanel.add(new JLabel("Which one do you want to guess?"), gbc);

        gbc.gridx = 1; gbc.gridy = 0;
        guessPanel.add(guessComboBox, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        guessPanel.add(randomGuessLabel, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        guessPanel.add(overButton, gbc);

        gbc.gridx = 1; gbc.gridy = 2;
        guessPanel.add(underButton, gbc);

        // Back Button
        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            frame.switchTo("Home");
            reset(); // Reset the panel when the user goes back
        });
        JPanel backPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        backPanel.add(backButton);

        // Add Listeners
        fetchButton.addActionListener(e -> fetchPlayerYears(controller));
        fetchYearButton.addActionListener(e -> fetchPlayerStats(controller));
        guessComboBox.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                updateGeneratedValue(controller); // Update the generated value based on the selected stat
            }
        });

        overButton.addActionListener(e -> handleOverUnderGuess(true));
        underButton.addActionListener(e -> handleOverUnderGuess(false));

        // Layout
        add(inputPanel, BorderLayout.NORTH);
        add(yearPanel, BorderLayout.WEST);
        add(scrollPane, BorderLayout.CENTER);
        add(guessPanel, BorderLayout.EAST);
        add(backPanel, BorderLayout.SOUTH);
    }

    private void fetchPlayerYears(PlayGameController controller) {
        String playerName = playerNameField.getText();
        List<String> availableYears = controller.getAvailableYears(playerName);

        if (!availableYears.isEmpty()) {
            yearComboBox.removeAllItems();
            for (String year : availableYears) {
                yearComboBox.addItem(year);
            }
            yearPanel.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "No data available for this player.");
        }
    }

    private void fetchPlayerStats(PlayGameController controller) {
        String playerName = playerNameField.getText();
        String selectedYear = (String) yearComboBox.getSelectedItem();

        if (selectedYear != null) {
            String statsJson = controller.fetchPlayerStatisticsByYear(playerName, selectedYear);
            String formattedStats = PlayerStatsFormatter.formatPlayerStats(statsJson);
            statsArea.setText(formattedStats);

            // Automatically update generated value
            updateGeneratedValue(controller);
        } else {
            JOptionPane.showMessageDialog(this, "Please select a valid year.");
        }
    }

    private void updateGeneratedValue(PlayGameController controller) {
        String selectedStat = (String) guessComboBox.getSelectedItem();
        String playerName = playerNameField.getText();
        String selectedYear = (String) yearComboBox.getSelectedItem();

        if (selectedYear == null || selectedStat == null) return;

        currentTrueAverage = controller.getAverageStatistic(selectedStat, playerName, selectedYear);
        double randomValue = generateRandomOffset(currentTrueAverage);
        randomGuessLabel.setText("Generated Value: " + String.format("%.2f", randomValue));
    }

    private void handleOverUnderGuess(boolean isOver) {
        double randomValue = Double.parseDouble(randomGuessLabel.getText().split(": ")[1]);
        String result;
        boolean isCorrect;

        if (isOver) {
            isCorrect = currentTrueAverage > randomValue;
            result = isCorrect ? "Correct! It's Over!" : "Incorrect! It's Under!";
        } else {
            isCorrect = currentTrueAverage < randomValue;
            result = isCorrect ? "Correct! It's Under!" : "Incorrect! It's Over!";
        }

        JOptionPane.showMessageDialog(this, result + "\nTrue Average: " + currentTrueAverage + "\nRandom Value: " + randomValue);

        // Update session info
        Session session = Session.getInstance();
        if (isCorrect) {
            session.setWin(session.getWin() + 1);
        } else {
            session.setLose(session.getLose() + 1);
        }

        // Update history
        JSONObject newHistoryEntry = new JSONObject();
        newHistoryEntry.put("player", playerNameField.getText());
        newHistoryEntry.put("stats", (String) guessComboBox.getSelectedItem());
        newHistoryEntry.put("year", (String) yearComboBox.getSelectedItem());

        session.addHistoryEntry(newHistoryEntry); // Add to session's history

        // Convert updated session info to JSON
        JSONObject updatedInfo = new JSONObject();
        updatedInfo.put("username", session.getUsername());
        updatedInfo.put("password", session.getPassword());

        JSONObject info = new JSONObject();
        info.put("win", session.getWin());
        info.put("lose", session.getLose());
        info.put("history", new JSONArray(session.getHistory())); // Convert list to JSONArray

        updatedInfo.put("info", info);

        // Push updated info to API
        try {
            AccountDataAccessObject accountDataAccess = new AccountDataAccessObject();
            accountDataAccess.updateUserInfo(updatedInfo);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Failed to update user info: " + e.getMessage());
        }
    }



    private double generateRandomOffset(double trueAverage) {
        Random random = new Random();
        return trueAverage + (random.nextDouble() * 3) * (random.nextBoolean() ? 1 : -1);
    }

    /**
     * Reset the PlayGamePanel components to their default state.
     */
    private void reset() {
        playerNameField.setText("");
        yearComboBox.removeAllItems();
        yearPanel.setVisible(false);
        statsArea.setText("");
        guessComboBox.setSelectedIndex(0);
        randomGuessLabel.setText("Generated Value: --");
    }
}
