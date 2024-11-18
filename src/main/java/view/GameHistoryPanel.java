package view;

import interface_adapter.game_history.GameHistoryController;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class GameHistoryPanel extends JPanel {
    public GameHistoryPanel(MainFrame frame, GameHistoryController controller) {
        setLayout(new BorderLayout());

        // Input panel
        JLabel label = new JLabel("Enter username:");
        JTextField usernameField = new JTextField(20);
        JButton fetchButton = new JButton("Fetch Game History");
        JTextArea resultsArea = new JTextArea(15, 40);
        resultsArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(resultsArea);

        // Back button to navigate to the home panel
        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> frame.switchTo("Home"));

        // ActionListener for the fetch button
        fetchButton.addActionListener(e -> {
            String username = usernameField.getText().trim();
            if (username.isEmpty()) {
                resultsArea.setText("Please enter a username.");
                return;
            }
            List<String> history = controller.fetchGameHistory(username);
            if (history.isEmpty()) {
                resultsArea.setText("No history found for the given username.");
            } else {
                StringBuilder historyText = new StringBuilder("Game History:\n");
                for (String record : history) {
                    historyText.append(record).append("\n");
                }
                resultsArea.setText(historyText.toString());
            }
        });

        // Layout setup
        JPanel inputPanel = new JPanel(new FlowLayout());
        inputPanel.add(label);
        inputPanel.add(usernameField);
        inputPanel.add(fetchButton);

        JPanel bottomPanel = new JPanel(new FlowLayout());
        bottomPanel.add(backButton);

        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }
}
