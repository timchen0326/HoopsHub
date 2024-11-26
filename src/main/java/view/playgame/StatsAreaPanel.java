package view.playgame;

import java.awt.BorderLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * Panel for displaying player statistics in a non-editable text area.
 * Includes a scroll pane for easier navigation.
 */
public class StatsAreaPanel extends JPanel {
    private final JTextArea statsArea = new JTextArea(12, 40);

    /**
     * Constructs the StatsAreaPanel with a scrollable text area.
     */
    public StatsAreaPanel() {
        statsArea.setEditable(false);
        statsArea.setLineWrap(true);
        statsArea.setWrapStyleWord(true);
        final JScrollPane scrollPane = new JScrollPane(
                statsArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Player Statistics"));
        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
    }

    /**
     * Returns the text area for displaying player statistics.
     *
     * @return the stats text area
     */
    public JTextArea getStatsArea() {

        return statsArea;
    }
}
