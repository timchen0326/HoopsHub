package view.Play;

import javax.swing.*;
import java.awt.*;

public class InputPanel extends JPanel {
    private final JTextField playerNameField = new JTextField(20);
    private final JButton fetchButton = new JButton("Fetch Stats");

    public InputPanel() {
        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        add(new JLabel("Enter Player Name:"));
        add(playerNameField);
        add(fetchButton);
    }

    public JTextField getPlayerNameField() {
        return playerNameField;
    }

    public JButton getFetchButton() {
        return fetchButton;
    }
}