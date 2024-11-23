package view;

import java.awt.Color;

public class ThemeManager {
    private static ThemeManager instance;
    private boolean isDarkMode = false;

    private ThemeManager() {

    }

    public static ThemeManager getInstance() {
        if (instance == null) {
            instance = new ThemeManager();
        }
        return instance;
    }

    public boolean isDarkMode() {
        return isDarkMode;
    }

    public void setDarkMode(boolean darkMode) {
        isDarkMode = darkMode;
    }

    public Color getBackgroundColor() {
        return isDarkMode ? Color.DARK_GRAY : Color.WHITE;
    }

    public Color getTextColor() {
        return isDarkMode ? Color.WHITE : Color.BLACK;
    }
}