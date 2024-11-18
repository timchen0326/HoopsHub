package view;

import interface_adapter.game_history.GameHistoryController;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    private final CardLayout cardLayout = new CardLayout();
    private final JPanel mainPanel = new JPanel(cardLayout);

    public MainFrame(GameHistoryController gameHistoryController) {
        setTitle("Game App");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Adding panels to MainFrame
        mainPanel.add(new HomePanel(this), "Home");
        mainPanel.add(new GameHistoryPanel(this, gameHistoryController), "GameHistory");

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
