package util;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class MusicManager {
    private static MusicManager instance;
    private Clip musicClip;
    private boolean isMuted = false;

    private MusicManager() {}

    public static MusicManager getInstance() {
        if (instance == null) {
            instance = new MusicManager();
        }
        return instance;
    }

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
            }
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void stopMusic() {
        if (musicClip != null && musicClip.isRunning()) {
            musicClip.stop();
        }
    }

    public void muteMusic() {
        isMuted = true;
        if (musicClip != null && musicClip.isRunning()) {
            musicClip.stop();
        }
    }

    public void unmuteMusic() {
        isMuted = false;
        if (musicClip != null && !musicClip.isRunning()) {
            musicClip.setFramePosition(0); // Restart from the beginning
            musicClip.start();
        }
    }

    public boolean isMuted() {
        return isMuted;
    }
}
