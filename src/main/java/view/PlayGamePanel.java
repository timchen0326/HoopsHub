package view;

import interface_adapter.PlayGameController;
import interface_adapter.PlayerStatsFormatter;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PlayGamePanel extends JPanel {
    private final JTextField playerNameField = new JTextField(20);
    private final JComboBox<String> yearComboBox = new JComboBox<>();
    private final JTextArea statsArea = new JTextArea(10, 30);
    private final JButton fetchButton = new JButton("Fetch Stats");
    private final JButton fetchYearButton = new JButton("Select Year");
    private final JPanel yearPanel = new JPanel(); // Correct use of class-level yearPanel

    public PlayGamePanel(MainFrame frame, PlayGameController controller) {
        setLayout(new BorderLayout(15, 15));

        // Input Panel
        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        inputPanel.add(new JLabel("Enter Player Name:"));
        inputPanel.add(playerNameField);
        inputPanel.add(fetchButton);

        // Year Selection Panel
        yearPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        yearPanel.add(new JLabel("Select Year:"));
        yearPanel.add(yearComboBox);
        yearPanel.add(fetchYearButton);
        yearPanel.setVisible(false); // Initially hidden

        // Stats Area Panel
        statsArea.setEditable(false);
        statsArea.setLineWrap(true);
        statsArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(statsArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setPreferredSize(new Dimension(400, 200));

        JPanel statsPanel = new JPanel(new BorderLayout());
        statsPanel.setBorder(BorderFactory.createTitledBorder("Player Statistics"));
        statsPanel.add(scrollPane, BorderLayout.CENTER);

        // Back Button
        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> frame.switchTo("Home"));
        JPanel backPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        backPanel.add(backButton);

        // Action Listeners
        fetchButton.addActionListener(e -> {
            String playerName = playerNameField.getText();
            List<String> availableYears = controller.getAvailableYears(playerName);

            if (!availableYears.isEmpty()) {
                yearComboBox.removeAllItems();
                for (String year : availableYears) {
                    yearComboBox.addItem(year);
                }
                yearPanel.setVisible(true); // Show year selection when valid data exists
            } else {
                JOptionPane.showMessageDialog(this, "No data available for this player.");
            }
        });

        fetchYearButton.addActionListener(e -> {
            String playerName = playerNameField.getText();
            String selectedYear = (String) yearComboBox.getSelectedItem();

            if (selectedYear != null) {
                String statsJson = controller.fetchPlayerStatisticsByYear(playerName, selectedYear);
                String formattedStats = PlayerStatsFormatter.formatPlayerStats(statsJson);
                statsArea.setText(formattedStats); // Display formatted stats in the text area
            } else {
                JOptionPane.showMessageDialog(this, "Please select a valid year.");
            }
        });


        // Add components to main panel
        add(inputPanel, BorderLayout.NORTH);     // Input panel at the top
        add(yearPanel, BorderLayout.EAST);
        add(statsPanel, BorderLayout.CENTER);     // Stats output centered
        add(backPanel, BorderLayout.PAGE_END);   // Back button at the bottom
    }
}