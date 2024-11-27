package view;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

import data_access.AccountDataAccessObject;
import data_access.SearchHistoryDataAccessObject;
import interface_adapter.account.AccountController;
import interface_adapter.account.AccountPresenter;
import interface_adapter.play_game_aspects.PlayGameController;
import interface_adapter.search.SearchHistoryController;
import use_case.account.AccountInteractor;
import use_case.search.SearchHistoryInteractor;
import view.playgame.PlayGamePanel;
import view.MusicManager.*;
import view.ThemeManager.ThemeController;
import view.ThemeManager.ThemeManager;

/**
 * MainFrame class represents the main application frame with various panels.
 */
public class MainFrame extends JFrame {
    private static final int FRAME_WIDTH = 800;
    private static final int FRAME_HEIGHT = 400;
    private static final String MUSIC_PATH =
            "/Users/mohitbendale/HoopsHub/src/main/java/music/369920__mrthenoronha__cartoon-game-theme-loop.wav";

    private final CardLayout cardLayout = new CardLayout();
    private final JPanel mainPanel = new JPanel(cardLayout);
    private final ThemeManager themeManager = ThemeManager.getInstance();

    /**
     * Constructs the MainFrame with the necessary controllers and interactors.
     *
     * @param controller        the PlayGameController instance
     * @param searchInteractor  the SearchInteractor instance
     */
    public MainFrame(PlayGameController controller, SearchInteractor searchInteractor) {
        setTitle("Game App");
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        util.MusicManager.getInstance().playMusic(MUSIC_PATH);
        AudioController audioController = util.MusicManager.getInstance();
        ThemeController themeController = ThemeManager.getInstance();


        // Add panels to the CardLayout
        mainPanel.add(new HomePanel(this), "Home");                        // Home panel
        mainPanel.add(new PlayGamePanel(this, controller), "Play");        // PlayGame panel
        mainPanel.add(new SearchPanel(this, searchInteractor), "Search"); // Search panel
        mainPanel.add(new SettingsPanel(this, audioController, themeController), "Settings");

        final SearchHistoryController searchHistoryController = initializeSearchHistoryController();
        mainPanel.add(new SearchHistoryPanel(searchHistoryController, this), "SearchHistory");

        // Add LoginView
        final AccountController accountController = initializeAccountController();
        final ActionListener switchToSignUp = event -> switchTo("SignUp");
        final LoginView loginView = new LoginView(accountController, switchToSignUp);
        mainPanel.add(loginView, "Login");

        add(mainPanel);
    }

    /**
     * Switches to a specific panel by name.
     *
     * @param panelName the name of the panel to switch to
     */
    public void switchTo(String panelName) {
        cardLayout.show(mainPanel, panelName);
        applyTheme();
    }

    /**
     * Applies the current theme to all components.
     */
    private void applyTheme() {
        for (Component comp : mainPanel.getComponents()) {
            if (comp instanceof JPanel) {
                comp.setBackground(themeManager.getBackgroundColor());
                comp.setForeground(themeManager.getTextColor());
            }
        }
    }

    /**
     * Starts the MainFrame with the Login panel visible.
     */
    public void start() {
        setVisible(true);
        switchTo("Login");
    }

    /**
     * Initializes the AccountController for managing login and account creation.
     *
     * @return an AccountController instance
     */
    private AccountController initializeAccountController() {
        final AccountDataAccessObject accountDataAccess = new AccountDataAccessObject();
        final AccountPresenter accountPresenter = new AccountPresenter(this, new JFrame("Login"));
        final AccountInteractor accountInteractor = new AccountInteractor(accountDataAccess, accountPresenter);
        return new AccountController(accountInteractor);
    }

    /**
     * Initializes the SearchHistoryController for managing the search history feature.
     *
     * @return a SearchHistoryController instance
     */
    private SearchHistoryController initializeSearchHistoryController() {
        final SearchHistoryDataAccessObject searchHistoryDataAccess = new SearchHistoryDataAccessObject();
        final SearchHistoryInteractor searchHistoryInteractor = new SearchHistoryInteractor(searchHistoryDataAccess);
        return new SearchHistoryController(searchHistoryInteractor);
    }
}
