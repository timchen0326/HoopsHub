package view;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import app.Session;
import use_case.ThemeManager.ThemeManager;

/**
 * The HomePanel class represents the main navigation panel of the application,
 * allowing the user to switch between different sections such as "Play", "Search",
 * and "Settings".
 */
public class HomePanel extends JPanel {

    private static final int GRID_COLUMNS = 5;
    private final ThemeManager themeManager = ThemeManager.getInstance();

    /**
     * Constructs a HomePanel with navigation buttons and a welcome label.
     *
     * @param frame the main application frame, must not be null
     */
    public HomePanel(MainFrame frame) {
        setLayout(new GridLayout(1, GRID_COLUMNS));

        // Play Game Button
        final JButton playButton = new JButton("Play Game");
        playButton.addActionListener(event -> frame.switchTo("Play"));
        add(playButton);

        // Search Button
        final JButton searchButton = new JButton("Search");
        searchButton.addActionListener(event -> frame.switchTo("Search"));
        add(searchButton);

        // Search History Button (New Feature)
        final JButton searchHistoryButton = new JButton("Search History");
        searchHistoryButton.addActionListener(event -> frame.switchTo("SearchHistory"));
        add(searchHistoryButton);

        // Welcome Label
        add(new JLabel("Welcome to the Game App"));

        // Settings Button
        final JButton settingsButton = new JButton("Settings");
        settingsButton.addActionListener(event -> frame.switchTo("Settings"));
        add(settingsButton);

        // Logout Button
        final JButton logoutButton = new JButton("Logout");
        logoutButton.addActionListener(event -> {
            // Clear session data
            Session.getInstance().clear();

            // Navigate to LoginView
            frame.switchTo("Login");
        });
        add(logoutButton);

        updateTheme();
    }

    /**
     * Updates the panel's theme colors based on the ThemeManager settings.
     */
    private void updateTheme() {
        setBackground(themeManager.getBackgroundColor());
        setForeground(themeManager.getTextColor());
    }
}
