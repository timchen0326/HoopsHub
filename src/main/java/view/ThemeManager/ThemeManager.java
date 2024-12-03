package view.ThemeManager;

import java.awt.Color;

/**
 * Singleton class for managing theme settings in the application.
 * Implements the {@link ThemeController} interface to provide functionality
 * for toggling themes and retrieving theme-specific colors.
 */
public class ThemeManager implements ThemeController {

    private boolean darkMode;
    private static final ThemeManager INSTANCE = new ThemeManager();

    /**
     * Private constructor to enforce the singleton pattern.
     * Initializes the theme to light mode by default.
     */
    private ThemeManager() {
        this.darkMode = false;
    }

    /**
     * Retrieves the singleton instance of the ThemeManager.
     *
     * @return the singleton instance of ThemeManager
     */
    public static ThemeManager getInstance() {
        return INSTANCE;
    }

    /**
     * Toggles the theme between dark mode and light mode.
     */
    @Override
    public void toggleDarkMode() {
        darkMode = !darkMode;
        System.out.println(darkMode ? "Switched to Dark Mode." : "Switched to Light Mode.");
    }

    /**
     * Checks if the current theme is set to dark mode.
     *
     * @return {@code true} if the theme is dark, {@code false} otherwise
     */
    @Override
    public boolean isDarkMode() {
        return darkMode;
    }

    /**
     * Gets the background color for the current theme.
     *
     * @return the background color based on the current theme
     */
    @Override
    public Color getBackgroundColor() {
        return darkMode ? Color.BLACK : Color.WHITE;
    }

    /**
     * Gets the text color for the current theme.
     *
     * @return the text color based on the current theme
     */
    @Override
    public Color getTextColor() {
        return darkMode ? Color.WHITE : Color.BLACK;
    }
}
