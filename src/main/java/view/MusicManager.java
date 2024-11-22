package util;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class MusicManager {
    private static MusicManager instance;
    private Clip musicClip;
    private boolean isMuted = false;

    private MusicManager() {

    }

    public static MusicManager getInstance() {
        if (instance == null) {
            instance = new MusicManager();
        }
        return instance;
    }

    public void playMusic(String filePath) {
        try {
            if (musicClip != null && musicClip.isRunning()) {
                return; // Prevent restarting if already playing
            }

            File musicFile = new File(filePath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(musicFile);
            musicClip = AudioSystem.getClip();
            musicClip.open(audioStream);
            musicClip.loop(Clip.LOOP_CONTINUOUSLY); // Loop the music
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
            musicClip.start();
        }
    }

    public boolean isMuted() {
        return isMuted;
    }
}
