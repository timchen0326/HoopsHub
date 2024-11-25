package view;

import data_access.AccountDataAccessObject;
import data_access.SearchHistoryDataAccessObject;
import interface_adapter.PlayGameAspects.PlayGameController;
import interface_adapter.account.AccountController;
import interface_adapter.account.AccountPresenter;
import interface_adapter.search.SearchHistoryController;
import use_case.account.AccountInteractor;
import use_case.search.SearchInteractor;
import use_case.search.SearchHistoryInteractor;
import view.Play.PlayGamePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {
    private final CardLayout cardLayout = new CardLayout();
    private final JPanel mainPanel = new JPanel(cardLayout);

    public MainFrame(PlayGameController controller, SearchInteractor searchInteractor) {
        setTitle("Game App");
        setSize(800, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // Add panels to the CardLayout
        mainPanel.add(new HomePanel(this), "Home");                        // Home panel
        mainPanel.add(new PlayGamePanel(this, controller), "Play");        // PlayGame panel
        mainPanel.add(new SearchPanel(this, searchInteractor), "Search"); // Search panel

        SearchHistoryController searchHistoryController = initializeSearchHistoryController();
        mainPanel.add(new SearchHistoryPanel(searchHistoryController, this), "SearchHistory"); // Pass both arguments


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

    /**
     * Initializes the SearchHistoryController for managing the search history feature.
     */
    private SearchHistoryController initializeSearchHistoryController() {
        SearchHistoryDataAccessObject searchHistoryDataAccess = new SearchHistoryDataAccessObject();
        SearchHistoryInteractor searchHistoryInteractor = new SearchHistoryInteractor(searchHistoryDataAccess);
        return new SearchHistoryController(searchHistoryInteractor);
    }
}