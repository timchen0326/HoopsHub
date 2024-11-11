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

        mainPanel.add(new HomePanel(this), "Home");
        mainPanel.add(new PlayGamePanel(this, controller), "Play");
        add(mainPanel);
    }

    public void switchTo(String panelName) {
        cardLayout.show(mainPanel, panelName);
    }

    public void start() {
        setVisible(true);
        switchTo("Home");
    }
}

