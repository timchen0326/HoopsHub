package view.MusicManager;

public interface AudioController {
    void mute();              // Mute the audio
    void unmute();            // Unmute the audio
    boolean isMuted();        // Check if audio is muted
    void playMusic(String filePath); // Play a specific music file
    void stopMusic();       // Stop the music playback
}
