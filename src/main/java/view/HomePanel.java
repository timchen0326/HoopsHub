package view;

import javax.swing.*;
import java.awt.*;

public class HomePanel extends JPanel {
    public HomePanel(MainFrame frame) {
        setLayout(new GridLayout(1, 2));

        JButton playButton = new JButton("Play Game");
        playButton.addActionListener(e -> frame.switchTo("Play"));

        add(new JLabel("Welcome to the Game App"));
        add(playButton);
    }
}
