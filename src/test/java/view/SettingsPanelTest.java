package view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.MusicManager.AudioController;
import use_case.ThemeManager.ThemeController;
import javax.swing.*;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class SettingsPanelTest {

    private SettingsPanel settingsPanel;
    private JButton muteButton;
    private JButton themeButton;
    private AudioController mockAudioController;
    private ThemeController mockThemeController;

    @BeforeEach
    public void setup() {
        // Create mock dependencies
        mockAudioController = mock(AudioController.class);
        mockThemeController = mock(ThemeController.class);

        // Set default behavior for mocks
        when(mockAudioController.isMuted()).thenReturn(false);
        when(mockThemeController.isDarkMode()).thenReturn(false);

        // Initialize MainFrame and SettingsPanel
        MainFrame mockFrame = mock(MainFrame.class);
        settingsPanel = new SettingsPanel(mockFrame, mockAudioController, mockThemeController);

        // Access buttons via the UI components
        muteButton = new JButton(mockAudioController.isMuted() ? "Unmute Music" : "Mute Music");
        themeButton = new JButton(mockThemeController.isDarkMode() ? "Switch to Light Mode" : "Switch to Dark Mode");
    }

    @Test
    public void testMuteButtonTogglesAudioController() {
        // Extract the real mute button from SettingsPanel
        JButton muteButton = (JButton) settingsPanel.getComponent(1); // Adjust index based on layout

        // Simulate a click to mute
        when(mockAudioController.isMuted()).thenReturn(false);
        muteButton.doClick();
        verify(mockAudioController, times(1)).mute();

        // Simulate a click to unmute
        when(mockAudioController.isMuted()).thenReturn(true);
        muteButton.doClick();
        verify(mockAudioController, times(1)).unmute();
    }

    @Test
    public void testThemeButtonTogglesThemeController() {
        JButton themeButton = (JButton) settingsPanel.getComponent(0); // Adjust index based on layout

        // Simulate a click to toggle theme
        themeButton.doClick();
        verify(mockThemeController, times(1)).toggleDarkMode();

        // Simulate another click
        themeButton.doClick();
        verify(mockThemeController, times(2)).toggleDarkMode();
    }

    @Test
    public void testPanelInitialization() {
        // Verify that the SettingsPanel is not null
        assertNotNull(settingsPanel);

        // Verify the buttons have correct initial text
        assertEquals("Mute Music", muteButton.getText());
        assertEquals("Switch to Dark Mode", themeButton.getText());
    }
}
