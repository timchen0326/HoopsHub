package view;

import interface_adapter.PlayGameController;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private final CardLayout cardLayout = new CardLayout();
    private final JPanel mainPanel = new JPanel(cardLayout);

    public MainFrame(PlayGameController controller) {
        setTitle("Game App");
        setSize(800, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Add panels to the card layout
        mainPanel.add(new HomePanel(this), "Home");       // Set HomePanel with the "Home" card name
        mainPanel.add(new PlayGamePanel(this, controller), "Play");
        add(mainPanel);
    }

    public void switchTo(String panelName) {
        cardLayout.show(mainPanel, panelName);
    }

    public void start() {
        setVisible(true);
        switchTo("Home");  // Start with the Home panel
    }
}
