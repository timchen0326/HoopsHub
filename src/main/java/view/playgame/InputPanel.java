package view.playgame;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Panel for entering a player's name and fetching their statistics.
 * Provides a text field for input and a button for submission.
 */
public class InputPanel extends JPanel {
    private static final int HORIZONTAL_GAP = 10;
    private static final int VERTICAL_GAP = 10;
    private final JTextField playerNameField = new JTextField(20);
    private final JButton fetchButton = new JButton("Fetch Stats");

    /**
     * Constructs the InputPanel with a text field and a button.
     */
    public InputPanel() {
        setLayout(new FlowLayout(FlowLayout.CENTER, HORIZONTAL_GAP, VERTICAL_GAP));
        add(new JLabel("Enter Player Name:"));
        add(playerNameField);
        add(fetchButton);
    }

    /**
     * Returns the text field for entering the player's name.
     *
     * @return the player name text field
     */
    public JTextField getPlayerNameField() {

        return playerNameField;
    }

    /**
     * Returns the button for fetching player statistics.
     *
     * @return the fetch button
     */
    public JButton getFetchButton() {

        return fetchButton;
    }
}
