package view;

import javax.swing.*;
import java.awt.*;

public class SettingsPanel extends JPanel {
    private final ThemeManager themeManager = ThemeManager.getInstance();

    public SettingsPanel(MainFrame frame) {
        setLayout(new BorderLayout());

        JButton toggleButton = new JButton("Switch to Dark Mode");
        updateButtonLabel(toggleButton);

        toggleButton.addActionListener(e -> {
            themeManager.setDarkMode(!themeManager.isDarkMode());
            updateTheme();
            updateButtonLabel(toggleButton);
        });

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> frame.switchTo("Home"));

        add(toggleButton, BorderLayout.CENTER);
        add(backButton, BorderLayout.SOUTH);
    }

    private void updateTheme() {
        setBackground(themeManager.getBackgroundColor());
        setForeground(themeManager.getTextColor());
    }

    private void updateButtonLabel(JButton button) {
        button.setText(themeManager.isDarkMode() ? "Switch to Light Mode" : "Switch to Dark Mode");
    }
}
