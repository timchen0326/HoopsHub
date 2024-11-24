package view.Play;

import javax.swing.*;
import java.awt.*;

public class StatsAreaPanel extends JPanel {
    private final JTextArea statsArea = new JTextArea(12, 40);

    public StatsAreaPanel() {
        statsArea.setEditable(false);
        statsArea.setLineWrap(true);
        statsArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(statsArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Player Statistics"));
        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
    }

    public JTextArea getStatsArea() {
        return statsArea;
    }
}