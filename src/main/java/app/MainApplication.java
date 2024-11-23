package app;

import data_access.AccountDataAccessObject;
import data_access.DBSearchDataAccessObject;
import interface_adapter.account.AccountController;
import interface_adapter.account.AccountPresenter;
import interface_adapter.PlayGameController;
import interface_adapter.search.SearchViewModel;
import use_case.account.AccountDataAccessInterface;
import use_case.account.AccountInteractor;
import use_case.account.AccountOutputBoundary;
import use_case.note.search.SearchInteractor;
import view.AccountCreationView;
import view.LoginView;
import view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionListener;

public class MainApplication {

    public static void main(String[] args) {

        SessionMonitor monitor = new SessionMonitor();
        monitor.start();
        // Step 1: Initialize dependencies for SearchInteractor
        DBSearchDataAccessObject searchDataAccess = new DBSearchDataAccessObject();
        SearchViewModel searchViewModel = new SearchViewModel();
        SearchInteractor searchInteractor = new SearchInteractor(searchDataAccess, searchViewModel);

        // Step 2: Initialize PlayGameController
        PlayGameController playGameController = new PlayGameUseCaseFactory().createController();

        // Step 3: Initialize MainFrame with PlayGameController and SearchInteractor
        MainFrame mainFrame = new MainFrame(playGameController, searchInteractor);
        mainFrame.setVisible(false); // Initially hidden until login is successful

        // Step 4: Setup the login frame
        JFrame loginFrame = new JFrame("User Authentication");
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setSize(400, 200);

        // Step 5: Initialize account-related components
        AccountDataAccessInterface accountDataAccess = new AccountDataAccessObject();
        AccountOutputBoundary accountPresenter = new AccountPresenter(mainFrame, loginFrame);
        AccountInteractor accountInteractor = new AccountInteractor(accountDataAccess, accountPresenter);
        AccountController accountController = new AccountController(accountInteractor);

        // Step 6: Define behavior for switching to sign-up view
        ActionListener switchToSignUp = e -> {
            loginFrame.setContentPane(new AccountCreationView(accountController));
            loginFrame.pack();
        };

        // Step 7: Set up login view with sign-up functionality
        loginFrame.setContentPane(new LoginView(accountController, switchToSignUp));
        loginFrame.pack();
        loginFrame.setVisible(true);
    }
}
