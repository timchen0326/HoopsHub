package app;

import java.awt.event.ActionListener;

import javax.swing.JFrame;

import data_access.AccountDataAccessObject;
import data_access.DBSearchDataAccessObject;
import interface_adapter.account.AccountController;
import interface_adapter.account.AccountPresenter;
import interface_adapter.play_game_aspects.PlayGameController;
import interface_adapter.search.SearchViewModel;
import use_case.account.AccountDataAccessInterface;
import use_case.account.AccountInteractor;
import use_case.account.AccountOutputBoundary;
import use_case.search.SearchInteractor;
import view.AccountCreationView;
import view.LoginView;
import view.MainFrame;

/**
 * MainApplication class initializes and starts the main components of the application.
 */
public class MainApplication {

    /**
     * The main method to run the application.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        // Step 1: Initialize the session monitor
        final SessionMonitor monitor = new SessionMonitor();
        monitor.start();

        // Step 2: Initialize dependencies for SearchInteractor
        final DBSearchDataAccessObject searchDataAccess = new DBSearchDataAccessObject();
        final SearchViewModel searchViewModel = new SearchViewModel();
        final SearchInteractor searchInteractor = new SearchInteractor(searchDataAccess, searchViewModel);

        // Step 3: Initialize PlayGameController
        final PlayGameController playGameController = new PlayGameUseCaseFactory().createController();

        // Step 4: Initialize MainFrame with PlayGameController and SearchInteractor
        final MainFrame mainFrame = new MainFrame(playGameController, searchInteractor);
        mainFrame.setVisible(false);

        // Step 5: Setup the login frame
        final JFrame loginFrame = new JFrame("User Authentication");
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setSize(400, 200);

        // Step 6: Initialize account-related components
        final AccountDataAccessInterface accountDataAccess = new AccountDataAccessObject();
        final AccountOutputBoundary accountPresenter = new AccountPresenter(mainFrame, loginFrame);
        final AccountInteractor accountInteractor = new AccountInteractor(accountDataAccess, accountPresenter);
        final AccountController accountController = new AccountController(accountInteractor);

        // Step 7: Define behavior for switching to sign-up view
        final ActionListener switchToSignUp = event -> {
            loginFrame.setContentPane(new AccountCreationView(accountController));
            loginFrame.pack();
        };

        // Step 8: Set up login view with sign-up functionality
        loginFrame.setContentPane(new LoginView(accountController, switchToSignUp));
        loginFrame.pack();
        loginFrame.setVisible(true);
    }
}
