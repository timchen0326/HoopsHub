package view.Play;

import javax.swing.*;
import java.awt.*;

public class YearSelectionPanel extends JPanel {
    private final JComboBox<String> yearComboBox = new JComboBox<>();
    private final JButton fetchYearButton = new JButton("Select Year");

    public YearSelectionPanel() {
        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        add(new JLabel("Select Year:"));
        add(yearComboBox);
        add(fetchYearButton);
        setVisible(false);
    }

    public JComboBox<String> getYearComboBox() {
        return yearComboBox;
    }

    public JButton getFetchYearButton() {
        return fetchYearButton;
    }
}