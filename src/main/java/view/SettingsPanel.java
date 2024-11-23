package view;

import util.MusicManager;
import javax.swing.*;
import java.awt.*;

public class SettingsPanel extends JPanel {
    private final ThemeManager themeManager = ThemeManager.getInstance();
    private final MusicManager musicManager = MusicManager.getInstance();

    public SettingsPanel(MainFrame frame) {
        setLayout(new BorderLayout());

        JButton toggleButton = new JButton("Switch to Dark Mode");
        updateButtonLabel(toggleButton);

        toggleButton.addActionListener(e -> {
            themeManager.setDarkMode(!themeManager.isDarkMode());
            updateTheme();
            updateButtonLabel(toggleButton);
        });

        JButton muteButton = new JButton("Mute Music");
        updateMuteButton(muteButton);

        muteButton.addActionListener(e -> {
            if (musicManager.isMuted()) {
                musicManager.unmuteMusic();
            } else {
                musicManager.muteMusic();
            }
            updateMuteButton(muteButton);
        });

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> frame.switchTo("Home"));

        add(toggleButton, BorderLayout.NORTH);
        add(muteButton, BorderLayout.CENTER);
        add(backButton, BorderLayout.SOUTH);
    }

    private void updateTheme() {
        setBackground(themeManager.getBackgroundColor());
        setForeground(themeManager.getTextColor());
    }

    private void updateButtonLabel(JButton button) {
        button.setText(themeManager.isDarkMode() ? "Switch to Light Mode" : "Switch to Dark Mode");
    }

    private void updateMuteButton(JButton button) {
        button.setText(musicManager.isMuted() ? "Unmute Music" : "Mute Music");
    }
}
