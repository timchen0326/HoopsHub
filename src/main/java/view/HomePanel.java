package view;

import app.Session;

import javax.swing.*;
import java.awt.*;

public class HomePanel extends JPanel {
    public HomePanel(MainFrame frame) {
        setLayout(new GridLayout(1, 4)); // Adjusted layout to fit an extra button

        // Play Game Button
        JButton playButton = new JButton("Play Game");
        playButton.addActionListener(e -> frame.switchTo("Play")); // Switch to PlayGame panel
        add(playButton);

        // Search Button
        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(e -> frame.switchTo("Search")); // Switch to Search panel
        add(searchButton);

        // Welcome Label
        add(new JLabel("Welcome to the Game App"));

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
}