package util;

import view.MusicManager.AudioController;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class MusicManager implements AudioController {
    private static MusicManager instance;
    private Clip musicClip;
    private boolean isMuted = false;

    // Private constructor to enforce singleton pattern
    private MusicManager() {}

    // Static method to get the singleton instance
    public static MusicManager getInstance() {
        if (instance == null) {
            instance = new MusicManager();
        }
        return instance;
    }

    // Implementation of AudioController interface methods
    @Override
    public void mute() {
        isMuted = true;
        if (musicClip != null && musicClip.isRunning()) {
            musicClip.stop();
        }
        System.out.println("Music muted.");
    }

    @Override
    public void unmute() {
        isMuted = false;
        if (musicClip != null && !musicClip.isRunning()) {
            musicClip.setFramePosition(0); // Restart from the beginning
            musicClip.start();
        }
        System.out.println("Music unmuted.");
    }

    @Override
    public boolean isMuted() {
        return isMuted;
    }

    // Play music file
    public void playMusic(String filePath) {
        try {
            if (musicClip != null && musicClip.isOpen()) {
                musicClip.stop(); // Stop any existing playback
                musicClip.close();
            }

            File musicFile = new File(filePath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(musicFile);
            musicClip = AudioSystem.getClip();
            musicClip.open(audioStream);

            // Add a listener to restart the music when it finishes
            musicClip.addLineListener(event -> {
                if (event.getType() == LineEvent.Type.STOP && !isMuted) {
                    musicClip.setFramePosition(0); // Restart from the beginning
                    musicClip.start();
                }
            });

            if (!isMuted) {
                musicClip.start();
                System.out.println("Playing music: " + filePath);
            }
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.err.println("Error playing music: " + e.getMessage());
        }
    }

    // Stop music playback
    public void stopMusic() {
        if (musicClip != null && musicClip.isRunning()) {
            musicClip.stop();
            System.out.println("Music stopped.");
        }
    }
}
