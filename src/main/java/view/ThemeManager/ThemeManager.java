package view.ThemeManager;

import java.awt.*;

public class ThemeManager implements ThemeController {
    private boolean darkMode; // Tracks current theme state
    private static final ThemeManager instance = new ThemeManager();

    // Private constructor for singleton pattern
    private ThemeManager() {
        this.darkMode = false; // Default to light mode
    }

    public static ThemeManager getInstance() {
        return instance;
    }

    // Implementation of ThemeController methods
    @Override
    public void toggleDarkMode() {
        darkMode = !darkMode;
        System.out.println(darkMode ? "Switched to Dark Mode." : "Switched to Light Mode.");
    }

    @Override
    public boolean isDarkMode() {
        return darkMode;
    }

    @Override
    public Color getBackgroundColor() {
        return darkMode ? Color.BLACK : Color.WHITE;
    }

    @Override
    public Color getTextColor() {
        return darkMode ? Color.WHITE : Color.BLACK;
    }
}
