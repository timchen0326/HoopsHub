package view;

import interface_adapter.PlayGameController;
import use_case.note.search.SearchInteractor;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private final CardLayout cardLayout = new CardLayout();
    private final JPanel mainPanel = new JPanel(cardLayout);

    public MainFrame(PlayGameController controller, SearchInteractor searchInteractor) {
        setTitle("Game App");
        setSize(800, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Add panels to the CardLayout
        mainPanel.add(new HomePanel(this), "Home");       // Home panel
        mainPanel.add(new PlayGamePanel(this, controller), "Play"); // PlayGame panel
        mainPanel.add(new SearchPanel(this, searchInteractor), "Search"); // Search panel

        add(mainPanel);
    }

    public void switchTo(String panelName) {
        cardLayout.show(mainPanel, panelName);
    }

    public void start() {
        setVisible(true);
        switchTo("Home"); // Start with the Home panel
    }
}
