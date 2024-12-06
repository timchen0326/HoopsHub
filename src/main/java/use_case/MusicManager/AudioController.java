package use_case.MusicManager;

public interface AudioController {
    /**
     * Mute the audio.
     */
    void mute();
    /**
     * Mute the audio.
     */

    void unmute();
    /**
     * Check if audio is muted.
     */

    boolean isMuted();
    /**
     * Play a specific music file.
     */

    void playMusic(String filePath);
    /**
     * Stop the music playback.
     */

    void stopMusic();
}
