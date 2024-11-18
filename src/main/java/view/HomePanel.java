package view;

import javax.swing.*;
import java.awt.*;

public class HomePanel extends JPanel {
    public HomePanel(MainFrame frame) {
        setLayout(new GridLayout(1, 2));

        JButton gameHistoryButton = new JButton("Game History");
        gameHistoryButton.addActionListener(e -> frame.switchTo("GameHistory"));
        add(gameHistoryButton);

        add(new JLabel("Welcome to the Game App!"));
    }
}
