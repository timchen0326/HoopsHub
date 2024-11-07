package app.gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

public class GameApplication {
    static final int WIDTH = 800;
    static final int HEIGHT = 400;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Game App");
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.setSize(WIDTH, HEIGHT);

            CardLayout cardLayout = new CardLayout();
            JPanel cardPanel = new JPanel(cardLayout);

            JPanel playCard = createPlayCard();
            JPanel myRecordCard = createMyRecordCard();
            JPanel allRecordsCard = createAllRecordsCard();
            JPanel leaderboardCard = createLeaderboardCard();

            cardPanel.add(playCard, "PlayCard");
            cardPanel.add(myRecordCard, "MyRecordCard");
            cardPanel.add(allRecordsCard, "AllRecordsCard");
            cardPanel.add(leaderboardCard, "LeaderboardCard");

            JButton playButton = new JButton("Play");
            playButton.addActionListener(e -> cardLayout.show(cardPanel, "PlayCard"));

            JButton myRecordButton = new JButton("My Previous Record");
            myRecordButton.addActionListener(e -> cardLayout.show(cardPanel, "MyRecordCard"));

            JButton allRecordsButton = new JButton("All Records");
            allRecordsButton.addActionListener(e -> cardLayout.show(cardPanel, "AllRecordsCard"));

            JButton leaderboardButton = new JButton("Leaderboard");
            leaderboardButton.addActionListener(e -> cardLayout.show(cardPanel, "LeaderboardCard"));

            JPanel buttonPanel = new JPanel();
            buttonPanel.add(playButton);
            buttonPanel.add(myRecordButton);
            buttonPanel.add(allRecordsButton);
            buttonPanel.add(leaderboardButton);

            frame.getContentPane().add(cardPanel, BorderLayout.CENTER);
            frame.getContentPane().add(buttonPanel, BorderLayout.SOUTH);

            frame.setVisible(true);
        });
    }

    private static JPanel createPlayCard() {
        JPanel playCard = new JPanel(new GridLayout(2, 1));
        JLabel label = new JLabel("Game On! Click Play to start your game.");
        JButton playGameButton = new JButton("Play Game");

        playGameButton.addActionListener(e -> JOptionPane.showMessageDialog(null, "Game Started!"));

        playCard.add(label);
        playCard.add(playGameButton);
        return playCard;
    }

    private static JPanel createMyRecordCard() {
        JPanel recordCard = new JPanel(new GridLayout(2, 1));
        JLabel label = new JLabel("Your Previous Game Record:");
        JButton showRecordButton = new JButton("Show My Record");

        showRecordButton.addActionListener(e -> {
            // Placeholder logic for showing user's record
            JOptionPane.showMessageDialog(null, "Your Record: 50 points.");
        });

        recordCard.add(label);
        recordCard.add(showRecordButton);
        return recordCard;
    }

    private static JPanel createAllRecordsCard() {
        JPanel allRecordsCard = new JPanel(new GridLayout(2, 1));
        JLabel label = new JLabel("All Players' Records:");
        JButton showAllRecordsButton = new JButton("Show All Records");

        showAllRecordsButton.addActionListener(e -> {
            // Placeholder logic for showing all records
            JOptionPane.showMessageDialog(null, "Player1: 50 points\nPlayer2: 45 points");
        });

        allRecordsCard.add(label);
        allRecordsCard.add(showAllRecordsButton);
        return allRecordsCard;
    }

    private static JPanel createLeaderboardCard() {
        JPanel leaderboardCard = new JPanel(new GridLayout(2, 1));
        JLabel label = new JLabel("Leaderboard:");
        JButton showLeaderboardButton = new JButton("Show Leaderboard");

        showLeaderboardButton.addActionListener(e -> {
            // Placeholder logic for showing leaderboard
            JOptionPane.showMessageDialog(null, "1. Player1 - 50 points\n2. Player2 - 45 points");
        });

        leaderboardCard.add(label);
        leaderboardCard.add(showLeaderboardButton);
        return leaderboardCard;
    }
}
