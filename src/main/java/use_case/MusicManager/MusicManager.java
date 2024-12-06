package use_case.MusicManager;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;

/**
 * Singleton class for managing background music in the application.
 * Implements the {@link AudioController} interface to provide mute/unmute functionality.
 */

public class MusicManager implements AudioController {
    private static MusicManager instance;
    private Clip musicClip;
    private boolean isMuted;

    /**
     * Private constructor to enforce the singleton pattern.
     */
    private MusicManager() {

    }
    /**
     * Gets the singleton instance of the MusicManager.
     *
     * @return the singleton instance of MusicManager
     */

    public static MusicManager getInstance() {
        if (instance == null) {
            instance = new MusicManager();
        }
        return instance;
    }

    /**
     * Mutes the background music.
     * If music is currently playing, it will be stopped.
     */
    @Override
    public void mute() {
        isMuted = true;
        if (musicClip != null && musicClip.isRunning()) {
            musicClip.stop();
        }
        System.out.println("Music muted.");
    }
    /**
     * Unmutes the background music.
     * If music was previously stopped, it will restart playback from the beginning.
     */

    @Override
    public void unmute() {
        isMuted = false;
        if (musicClip != null && !musicClip.isRunning()) {
            musicClip.setFramePosition(0);
            musicClip.start();
        }
        System.out.println("Music unmuted.");
    }
    /**
     * Checks whether the background music is muted.
     *
     * @return {@code true} if music is muted, {@code false} otherwise
     */

    @Override
    public boolean isMuted() {
        return isMuted;
    }

    /**
     * Plays the specified music file in the background.
     * If a music file is already playing, it will stop and restart with the new file.
     *
     * @param filePath the path to the music file to play
     */

    public void playMusic(String filePath) {
        try {
            if (musicClip != null && musicClip.isOpen()) {
                musicClip.stop();
                musicClip.close();
            }

            final File musicFile = new File(filePath);
            final AudioInputStream audioStream = AudioSystem.getAudioInputStream(musicFile);
            musicClip = AudioSystem.getClip();
            musicClip.open(audioStream);

            // Add a listener to restart the music when it finishes
            musicClip.addLineListener(event -> {
                if (event.getType() == LineEvent.Type.STOP && !isMuted) {
                    musicClip.setFramePosition(0);
                    musicClip.start();
                }
            });

            if (!isMuted) {
                musicClip.start();
                System.out.println("Playing music: " + filePath);
            }
        }
        catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.err.println("Error playing music: " + e.getMessage());
        }
    }

    /**
     * Stops the current music playback, if music is playing.
     */
    public void stopMusic() {
        if (musicClip != null && musicClip.isRunning()) {
            musicClip.stop();
            System.out.println("Music stopped.");
        }
    }
}
