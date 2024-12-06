package use_case.ThemeManager;

import java.awt.Color;

/**
 * Interface for managing theme settings in the application.
 * Provides methods for toggling between dark and light modes,
 * and retrieving theme-specific colors.
 */
public interface ThemeController {

    /**
     * Toggles the application theme between dark mode and light mode.
     */
    void toggleDarkMode();

    /**
     * Checks if the current theme is set to dark mode.
     *
     * @return {@code true} if the theme is dark, {@code false} otherwise
     */
    boolean isDarkMode();

    /**
     * Gets the current background color based on the theme.
     *
     * @return the background color
     */
    Color getBackgroundColor();

    /**
     * Gets the current text color based on the theme.
     *
     * @return the text color
     */
    Color getTextColor();
}

