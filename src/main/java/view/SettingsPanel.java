package view;

import view.MusicManager.AudioController;
import view.ThemeManager.ThemeController;

import javax.swing.*;
import java.awt.*;

public class SettingsPanel extends JPanel {
    private final ThemeController themeController;
    private final AudioController audioController;

    public SettingsPanel(MainFrame frame, AudioController audioController, ThemeController themeController) {
        this.audioController = audioController;
        this.themeController = themeController;
        setLayout(new BorderLayout());

        JButton toggleButton = new JButton(themeController.isDarkMode() ? "Switch to Light Mode" : "Switch to Dark Mode");
        toggleButton.addActionListener(e -> {
            themeController.toggleDarkMode();
            updateTheme(); // Update the panel's theme
            toggleButton.setText(themeController.isDarkMode() ? "Switch to Light Mode" : "Switch to Dark Mode");
        });

        JButton muteButton = new JButton(audioController.isMuted() ? "Unmute Music" : "Mute Music");
        muteButton.addActionListener(e -> {
            if (audioController.isMuted()) {
                audioController.unmute();
            } else {
                audioController.mute();
            }
            muteButton.setText(audioController.isMuted() ? "Unmute Music" : "Mute Music");
        });

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> frame.switchTo("Home"));

        add(toggleButton, BorderLayout.NORTH);
        add(muteButton, BorderLayout.CENTER);
        add(backButton, BorderLayout.SOUTH);
    }

    private void updateTheme() {
        setBackground(themeController.getBackgroundColor());
        setForeground(themeController.getTextColor());
        repaint();
    }
}
