package view.playgame;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ItemEvent;
import java.util.List;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import org.json.JSONArray;
import org.json.JSONObject;

import app.Session;
import data_access.AccountDataAccessObject;
import entity.PlayerStatistic;
import interface_adapter.play_game_aspects.PlayGameController;
import view.MainFrame;
import view.ThemeManager;

/**
 * Panel for playing the game. Contains input fields for player name and year, a text area for displaying player stats,
 * a combo box for selecting a stat to guess, and buttons for guessing over or under the generated value.
 */
public class PlayGamePanel extends JPanel {
    private static final int BORDER_GAP = 20;
    private static final double RANDOM_OFFSET_MULTIPLIER = 3.0;
    private final InputPanel inputPanel = new InputPanel();
    private final YearSelectionPanel yearPanel = new YearSelectionPanel();
    private final StatsAreaPanel statsAreaPanel = new StatsAreaPanel();
    private final GuessPanel guessPanel = new GuessPanel();
    private final ThemeManager themeManager = ThemeManager.getInstance();

    private double currentTrueAverage;

    public PlayGamePanel(MainFrame frame, PlayGameController controller) {
        setLayout(new BorderLayout(BORDER_GAP, BORDER_GAP));
        updateTheme();

        // Back Button
        final JButton backButton = new JButton("Back");
        backButton.addActionListener(event -> {
            frame.switchTo("Home");
            reset();
        });
        final JPanel backPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        backPanel.add(backButton);

        // Add Listeners
        inputPanel.getFetchButton().addActionListener(event -> fetchPlayerYears(controller));
        yearPanel.getFetchYearButton().addActionListener(event -> fetchPlayerStats(controller));
        guessPanel.getGuessComboBox().addItemListener(event -> {
            if (event.getStateChange() == ItemEvent.SELECTED) {
                updateGeneratedValue(controller);
            }
        });

        guessPanel.getOverButton().addActionListener(event -> handleOverUnderGuess(true));
        guessPanel.getUnderButton().addActionListener(event -> handleOverUnderGuess(false));

        // Layout
        add(inputPanel, BorderLayout.NORTH);
        add(yearPanel, BorderLayout.WEST);
        add(statsAreaPanel, BorderLayout.CENTER);
        add(guessPanel, BorderLayout.EAST);
        add(backPanel, BorderLayout.SOUTH);
    }

    private void fetchPlayerYears(PlayGameController controller) {
        final String playerName = inputPanel.getPlayerNameField().getText();
        final List<String> availableYears = controller.getAvailableYears(playerName);

        if (!availableYears.isEmpty()) {
            yearPanel.getYearComboBox().removeAllItems();
            for (String year : availableYears) {
                yearPanel.getYearComboBox().addItem(year);
            }
            yearPanel.setVisible(true);
        }
        else {
            JOptionPane.showMessageDialog(this, "No data available for this player.");
        }
    }

    private void fetchPlayerStats(PlayGameController controller) {
        final String playerName = inputPanel.getPlayerNameField().getText();
        final String selectedYear = (String) yearPanel.getYearComboBox().getSelectedItem();

        System.out.println("Player: " + playerName + ", Selected Year: " + selectedYear);
        if (selectedYear != null) {
            final PlayerStatistic stats = controller.fetchPlayerStatisticsByYear(playerName, selectedYear);
            final String formattedStats = stats.formatPlayerStats();
            statsAreaPanel.getStatsArea().setText(formattedStats);

            // Automatically update generated value
            updateGeneratedValue(controller);
        }
        else {
            JOptionPane.showMessageDialog(this, "Please select a valid year.");
        }
    }

    private void updateGeneratedValue(PlayGameController controller) {
        final String selectedStat = (String) guessPanel.getGuessComboBox().getSelectedItem();
        final String playerName = inputPanel.getPlayerNameField().getText();
        final String selectedYear = (String) yearPanel.getYearComboBox().getSelectedItem();

        if (selectedYear != null && selectedStat != null) {
            currentTrueAverage = controller.getAverageStatistic(selectedStat, playerName, selectedYear);
            final double randomValue = generateRandomOffset(currentTrueAverage);
            guessPanel.getRandomGuessLabel().setText("Generated Value: " + String.format("%.2f", randomValue));
        }
    }

    private void handleOverUnderGuess(boolean isOver) {
        final double randomValue = getRandomValue();
        final String result = determineResult(isOver, randomValue);
        showResultDialog(result, randomValue);
        updateSessionInfo(isOver, randomValue);
    }

    private double getRandomValue() {
        return Double.parseDouble(guessPanel.getRandomGuessLabel().getText().split(": ")[1]);
    }

    private String determineResult(boolean isOver, double randomValue) {
        final boolean isCorrect;
        final String result;

        if (isOver) {
            isCorrect = currentTrueAverage > randomValue;
            if (isCorrect) {
                result = "You WON! It's Over!";
            }
            else {
                result = "You Lose! It's Under!";
            }
        }
        else {
            isCorrect = currentTrueAverage < randomValue;
            if (isCorrect) {
                result = "You WON! It's Under!";
            }
            else {
                result = "You Lose! It's Over!";
            }
        }

        updateWinLossStats(isCorrect);
        return result;
    }

    private void showResultDialog(String result, double randomValue) {
        JOptionPane.showMessageDialog(this, result + "\nTrue Average: "
                + currentTrueAverage + "\nRandom Value: " + randomValue);
    }

    private void updateWinLossStats(boolean isCorrect) {
        final Session session = Session.getInstance();
        if (isCorrect) {
            session.setWin(session.getWin() + 1);
        }
        else {
            session.setLose(session.getLose() + 1);
        }
    }

    private void updateSessionInfo(boolean isOver, double randomValue) {
        final Session session = Session.getInstance();

        // Determine if the guess was correct
        final boolean isCorrect = isGuessCorrect(isOver, randomValue);
        final String result;
        if (isCorrect) {
            result = "Win";
        }
        else {
            result = "Lose";
        }

        // Create history entry with result
        final JSONObject newHistoryEntry = createHistoryEntry(result);
        session.addHistoryEntry(newHistoryEntry);

        // Create and push updated info
        final JSONObject updatedInfo = createUpdatedInfo(session);
        pushUpdatedInfoToApi(updatedInfo);
    }

    private boolean isGuessCorrect(boolean isOver, double randomValue) {
        final boolean result;
        if (isOver) {
            result = currentTrueAverage > randomValue;
        }
        else {
            result = currentTrueAverage < randomValue;
        }
        return result;
    }

    private JSONObject createHistoryEntry(String result) {
        final JSONObject historyEntry = new JSONObject();
        historyEntry.put("player", inputPanel.getPlayerNameField().getText());
        historyEntry.put("stats", (String) guessPanel.getGuessComboBox().getSelectedItem());
        historyEntry.put("year", (String) yearPanel.getYearComboBox().getSelectedItem());
        historyEntry.put("result", result);
        return historyEntry;
    }

    private JSONObject createUpdatedInfo(Session session) {
        final JSONObject updatedInfo = new JSONObject();
        updatedInfo.put("username", session.getUsername());
        updatedInfo.put("password", session.getPassword());

        final JSONObject info = new JSONObject();
        info.put("win", session.getWin());
        info.put("lose", session.getLose());
        info.put("history", new JSONArray(session.getHistory()));
        info.put("password", session.getPassword());

        updatedInfo.put("info", info);
        return updatedInfo;
    }

    private void pushUpdatedInfoToApi(JSONObject updatedInfo) {
        try {
            final AccountDataAccessObject accountDataAccess = new AccountDataAccessObject();
            accountDataAccess.updateUserInfo(updatedInfo);
        }
        catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Failed to update user info: " + ex.getMessage());
        }
    }

    private double generateRandomOffset(double trueAverage) {
        final Random random = new Random();
        final double offsetMultiplier;

        if (random.nextBoolean()) {
            offsetMultiplier = 1;
        }
        else {
            offsetMultiplier = -1;
        }

        return trueAverage + (random.nextDouble() * RANDOM_OFFSET_MULTIPLIER) * offsetMultiplier;
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

    private void updateTheme() {
        setBackground(themeManager.getBackgroundColor());
        setForeground(themeManager.getTextColor());

        for (Component comp : getComponents()) {
            if (comp instanceof JLabel) {
                comp.setForeground(themeManager.getTextColor());
            }
            else if (comp instanceof JTextArea) {
                comp.setBackground(themeManager.getBackgroundColor());
                comp.setForeground(themeManager.getTextColor());
            }
        }
    }
}
