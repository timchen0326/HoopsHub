package view;

import data_access.AccountDataAccessObject;
import interface_adapter.PlayGameController;
import interface_adapter.account.AccountController;
import interface_adapter.account.AccountPresenter;
import use_case.account.AccountInteractor;
import use_case.search.SearchInteractor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {
    private final CardLayout cardLayout = new CardLayout();
    private final JPanel mainPanel = new JPanel(cardLayout);
    private final ThemeManager themeManager = ThemeManager.getInstance();

    public MainFrame(PlayGameController controller, SearchInteractor searchInteractor) {
        setTitle("Game App");
        setSize(800, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        util.MusicManager.getInstance().playMusic("/Users/mohitbendale/HoopsHub/src/main/java/music/369920__mrthenoronha__cartoon-game-theme-loop.wav");


        // Add panels to the CardLayout
        mainPanel.add(new HomePanel(this), "Home");        // Home panel
        mainPanel.add(new PlayGamePanel(this, controller), "Play"); // PlayGame panel
        mainPanel.add(new SearchPanel(this, searchInteractor), "Search"); // Search panel
        mainPanel.add(new SettingsPanel(this), "Settings");

        // Add LoginView
        AccountController accountController = initializeAccountController();
        ActionListener switchToSignUp = e -> switchTo("SignUp"); // Stub for switching to sign-up
        LoginView loginView = new LoginView(accountController, switchToSignUp);
        mainPanel.add(loginView, "Login");

        add(mainPanel);
    }

    /**
     * Switch to a specific panel by name.
     */
    public void switchTo(String panelName) {
        cardLayout.show(mainPanel, panelName);
        applyTheme();
    }

    private void applyTheme() {
        for (Component comp : mainPanel.getComponents()) {
            if (comp instanceof JPanel) {
                comp.setBackground(themeManager.getBackgroundColor());
                comp.setForeground(themeManager.getTextColor());
            }
        }
    }

    /**
     * Initialize the main frame with the Login panel visible.
     */
    public void start() {
        setVisible(true);
        switchTo("Login"); // Start with the Login panel
    }

    /**
     * Initializes the AccountController for managing login and account creation.
     */
    private AccountController initializeAccountController() {
        AccountDataAccessObject accountDataAccess = new AccountDataAccessObject();
        AccountPresenter accountPresenter = new AccountPresenter(this, new JFrame("Login")); // Pass MainFrame and login JFrame
        AccountInteractor accountInteractor = new AccountInteractor(accountDataAccess, accountPresenter);
        return new AccountController(accountInteractor);
    }

}