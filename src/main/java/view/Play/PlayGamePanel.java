package view.Play;

import app.Session;
import data_access.AccountDataAccessObject;
import interface_adapter.PlayGameAspects.PlayGameController;
import interface_adapter.PlayGameAspects.PlayerStatsFormatter;
import org.json.JSONArray;
import org.json.JSONObject;
import view.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.util.List;
import java.util.Random;

public class PlayGamePanel extends JPanel {
    private final InputPanel inputPanel = new InputPanel();
    private final YearSelectionPanel yearPanel = new YearSelectionPanel();
    private final StatsAreaPanel statsAreaPanel = new StatsAreaPanel();
    private final GuessPanel guessPanel = new GuessPanel();

    private double currentTrueAverage; // Store the true average of the current stat

    public PlayGamePanel(MainFrame frame, PlayGameController controller) {
        setLayout(new BorderLayout(20, 20));

        // Back Button
        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            frame.switchTo("Home");
            reset(); // Reset the panel when the user goes back
        });
        JPanel backPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        backPanel.add(backButton);

        // Add Listeners
        inputPanel.getFetchButton().addActionListener(e -> fetchPlayerYears(controller));
        yearPanel.getFetchYearButton().addActionListener(e -> fetchPlayerStats(controller));
        guessPanel.getGuessComboBox().addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                updateGeneratedValue(controller); // Update the generated value based on the selected stat
            }
        });

        guessPanel.getOverButton().addActionListener(e -> handleOverUnderGuess(true));
        guessPanel.getUnderButton().addActionListener(e -> handleOverUnderGuess(false));

        // Layout
        add(inputPanel, BorderLayout.NORTH);
        add(yearPanel, BorderLayout.WEST);
        add(statsAreaPanel, BorderLayout.CENTER);
        add(guessPanel, BorderLayout.EAST);
        add(backPanel, BorderLayout.SOUTH);
    }

    private void fetchPlayerYears(PlayGameController controller) {
        String playerName = inputPanel.getPlayerNameField().getText();
        List<String> availableYears = controller.getAvailableYears(playerName);

        if (!availableYears.isEmpty()) {
            yearPanel.getYearComboBox().removeAllItems();
            for (String year : availableYears) {
                yearPanel.getYearComboBox().addItem(year);
            }
            yearPanel.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "No data available for this player.");
        }
    }

    private void fetchPlayerStats(PlayGameController controller) {
        String playerName = inputPanel.getPlayerNameField().getText();
        String selectedYear = (String) yearPanel.getYearComboBox().getSelectedItem();

        if (selectedYear != null) {
            String statsJson = controller.fetchPlayerStatisticsByYear(playerName, selectedYear);
            String formattedStats = PlayerStatsFormatter.formatPlayerStats(statsJson);
            statsAreaPanel.getStatsArea().setText(formattedStats);

            // Automatically update generated value
            updateGeneratedValue(controller);
        } else {
            JOptionPane.showMessageDialog(this, "Please select a valid year.");
        }
    }

    private void updateGeneratedValue(PlayGameController controller) {
        String selectedStat = (String) guessPanel.getGuessComboBox().getSelectedItem();
        String playerName = inputPanel.getPlayerNameField().getText();
        String selectedYear = (String) yearPanel.getYearComboBox().getSelectedItem();

        if (selectedYear == null || selectedStat == null) return;

        currentTrueAverage = controller.getAverageStatistic(selectedStat, playerName, selectedYear);
        double randomValue = generateRandomOffset(currentTrueAverage);
        guessPanel.getRandomGuessLabel().setText("Generated Value: " + String.format("%.2f", randomValue));
    }

    private void handleOverUnderGuess(boolean isOver) {
        double randomValue = Double.parseDouble(guessPanel.getRandomGuessLabel().getText().split(": ")[1]);
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
        newHistoryEntry.put("player", inputPanel.getPlayerNameField().getText());
        newHistoryEntry.put("stats", (String) guessPanel.getGuessComboBox().getSelectedItem());
        newHistoryEntry.put("year", (String) yearPanel.getYearComboBox().getSelectedItem());

        session.addHistoryEntry(newHistoryEntry); // Add to session's history

        // Convert updated session info to JSON
        JSONObject updatedInfo = new JSONObject();
        updatedInfo.put("username", session.getUsername());
        updatedInfo.put("password", session.getPassword());

        JSONObject info = new JSONObject();
        info.put("win", session.getWin());
        info.put("lose", session.getLose());
        info.put("history", new JSONArray(session.getHistory()));
        info.put("password", session.getPassword());// Convert list to JSONArray

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
        inputPanel.getPlayerNameField().setText("");
        yearPanel.getYearComboBox().removeAllItems();
        yearPanel.setVisible(false);
        statsAreaPanel.getStatsArea().setText("");
        guessPanel.getGuessComboBox().setSelectedIndex(0);
        guessPanel.getRandomGuessLabel().setText("Generated Value: --");
    }
}