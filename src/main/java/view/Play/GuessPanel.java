package view.Play;

import javax.swing.*;
import java.awt.*;

public class GuessPanel extends JPanel {
    private final JComboBox<String> guessComboBox = new JComboBox<>(new String[]{"Average Rebounds", "Average Points", "Average Assists"});
    private final JLabel randomGuessLabel = new JLabel("Generated Value: --");
    private final JButton overButton = new JButton("Over");
    private final JButton underButton = new JButton("Under");

    public GuessPanel() {
        setLayout(new GridBagLayout());
        setBorder(BorderFactory.createTitledBorder("Make a Guess"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0; gbc.gridy = 0;
        add(new JLabel("Which one do you want to guess?"), gbc);

        gbc.gridx = 1; gbc.gridy = 0;
        add(guessComboBox, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        add(randomGuessLabel, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        add(overButton, gbc);

        gbc.gridx = 1; gbc.gridy = 2;
        add(underButton, gbc);
    }

    public JComboBox<String> getGuessComboBox() {
        return guessComboBox;
    }

    public JLabel getRandomGuessLabel() {
        return randomGuessLabel;
    }

    public JButton getOverButton() {
        return overButton;
    }

    public JButton getUnderButton() {
        return underButton;
    }
}