package view.playgame;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Panel for selecting a year from a dropdown menu and confirming the selection.
 * Provides a combo box for year selection and a button for submission.
 */
public class YearSelectionPanel extends JPanel {
    private static final int HORIZONTAL_GAP = 10;
    private static final int VERTICAL_GAP = 10;
    private final JComboBox<String> yearComboBox = new JComboBox<>();
    private final JButton fetchYearButton = new JButton("Select Year");

    /**
     * Constructs the YearSelectionPanel with a combo box and a button.
     */
    public YearSelectionPanel() {
        setLayout(new FlowLayout(FlowLayout.CENTER, HORIZONTAL_GAP, VERTICAL_GAP));
        add(new JLabel("Select Year:"));
        add(yearComboBox);
        add(fetchYearButton);
        setVisible(false);
    }

    /**
     * Returns the combo box for selecting a year.
     *
     * @return the year combo box
     */
    public JComboBox<String> getYearComboBox() {

        return yearComboBox;
    }

    /**
     * Returns the button for confirming the year selection.
     *
     * @return the fetch year button
     */
    public JButton getFetchYearButton() {

        return fetchYearButton;
    }
}
