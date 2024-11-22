package view;

import javax.swing.*;
import java.awt.*;

public class HomePanel extends JPanel {
    private final ThemeManager themeManager = ThemeManager.getInstance();

    public HomePanel(MainFrame frame) {
        setLayout(new GridLayout(1, 3));

        // Play Game Button
        JButton playButton = new JButton("Play Game");
        playButton.addActionListener(e -> frame.switchTo("Play")); // Switch to PlayGame panel
        add(playButton);

        // Search Button
        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(e -> frame.switchTo("Search")); // Switch to Search panel
        add(searchButton);

        // Settings Button
        JButton settingsButton = new JButton("Settings");
        settingsButton.addActionListener(e -> frame.switchTo("Settings"));
        add(settingsButton);

        updateTheme();

        // Welcome Label
        add(new JLabel("Welcome to the Game App"));
    }
    private void updateTheme() {
        setBackground(themeManager.getBackgroundColor());
        setForeground(themeManager.getTextColor());
    }
}
