package view;

import interface_adapter.PlayGameController;

import javax.swing.*;
import java.awt.*;

public class PlayGamePanel extends JPanel {
    private final JTextField playerNameField = new JTextField(20);
    private final JTextArea statsArea = new JTextArea(10, 30);

    public PlayGamePanel(MainFrame frame, PlayGameController controller) {
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel();
        JButton fetchButton = new JButton("Fetch Stats");
        JButton backButton = new JButton("Back");

        inputPanel.add(new JLabel("Enter Player Name:"));
        inputPanel.add(playerNameField);
        inputPanel.add(fetchButton);

        fetchButton.addActionListener(e -> {
            String playerName = playerNameField.getText();
            String stats = controller.fetchPlayerStatistics(playerName);
            statsArea.setText(stats);
        });

        backButton.addActionListener(e -> frame.switchTo("Home"));

        add(inputPanel, BorderLayout.NORTH);
        add(new JScrollPane(statsArea), BorderLayout.CENTER);
        add(backButton, BorderLayout.SOUTH);
    }
}
