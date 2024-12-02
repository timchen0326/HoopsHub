package view.ThemeManager;

import java.awt.*;

public interface ThemeController {
    void toggleDarkMode();       // Toggle between dark and light modes

    boolean isDarkMode();        // Check if the current mode is dark
    Color getBackgroundColor();  // Get the current background color
    Color getTextColor();
}
