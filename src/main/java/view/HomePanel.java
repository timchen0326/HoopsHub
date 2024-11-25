package view;

import app.Session;

import javax.swing.*;
import java.awt.*;

public class HomePanel extends JPanel {
    private final ThemeManager themeManager = ThemeManager.getInstance();

    public HomePanel(MainFrame frame) {
        setLayout(new GridLayout(1, 5)); // Adjusted layout to fit the new Search History button

        // Play Game Button
        JButton playButton = new JButton("Play Game");
        playButton.addActionListener(e -> frame.switchTo("Play")); // Switch to PlayGame panel
        add(playButton);

        // Search Button
        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(e -> frame.switchTo("Search")); // Switch to Search panel
        add(searchButton);

        // Search History Button (New Feature)
        JButton searchHistoryButton = new JButton("Search History");
        searchHistoryButton.addActionListener(e -> frame.switchTo("SearchHistory")); // Switch to SearchHistory panel
        add(searchHistoryButton);

        // Welcome Label
        add(new JLabel("Welcome to the Game App"));

        // Settings Button
        JButton settingsButton = new JButton("Settings");
        settingsButton.addActionListener(e -> frame.switchTo("Settings"));
        add(settingsButton);

        updateTheme();

        // Logout Button
        JButton logoutButton = new JButton("Logout");
        logoutButton.addActionListener(e -> {
            // Clear session data
            Session.getInstance().clear();

            // Navigate to LoginView
            frame.switchTo("Login");
        });
        add(logoutButton);
    }

    private void updateTheme() {
        setBackground(themeManager.getBackgroundColor());
        setForeground(themeManager.getTextColor());
    }
}
