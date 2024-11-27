package view.playgame;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Panel for making guesses about player statistics.
 * Provides options for guessing and buttons for interaction.
 */
public class GuessPanel extends JPanel {
    private static final int INSET_SIZE = 10;
    private final JComboBox<String> guessComboBox = new JComboBox<>(
            new String[]{"Average Rebounds", "Average Points", "Average Assists"});
    private final JLabel randomGuessLabel = new JLabel("Generated Value: --");
    private final JButton overButton = new JButton("Over");
    private final JButton underButton = new JButton("Under");

    /**
     * Constructs the GuessPanel with all its components.
     */
    public GuessPanel() {
        setLayout(new GridBagLayout());
        setBorder(BorderFactory.createTitledBorder("Make a Guess"));
        final GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(INSET_SIZE, INSET_SIZE, INSET_SIZE, INSET_SIZE);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Which one do you want to guess?"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        add(guessComboBox, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(randomGuessLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(overButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        add(underButton, gbc);
    }

    /**
     * Returns the combo box for selecting guess options.
     *
     * @return the guess combo box
     */
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
