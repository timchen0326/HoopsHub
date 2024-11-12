package app;

import data_access.AccountDataAccessObject;
import interface_adapter.account.AccountController;
import interface_adapter.account.AccountPresenter;
import use_case.account.AccountDataAccessInterface;
import use_case.account.AccountInteractor;
import use_case.account.AccountOutputBoundary;
import view.AccountCreationView;
import view.LoginView;
import view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainApplication {

    public static void main(String[] args) {
        // Initialize MainFrame for game UI
        MainFrame mainFrame = new MainFrame(new PlayGameUseCaseFactory().createController());
        mainFrame.setVisible(false);  // Hide MainFrame initially

        // Setup the login frame first, so we can pass it to the presenter
        JFrame frame = new JFrame("User Authentication");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);

        // Initialize application components
        AccountDataAccessInterface dataAccess = new AccountDataAccessObject();
        AccountOutputBoundary presenter = new AccountPresenter(mainFrame, frame);  // Pass frame here
        AccountInteractor interactor = new AccountInteractor(dataAccess, presenter);
        AccountController controller = new AccountController(interactor);

        // Setup login view with switch to sign-up functionality
        ActionListener switchToSignUp = e -> {
            frame.setContentPane(new AccountCreationView(controller));
            frame.pack();
        };

        frame.setContentPane(new LoginView(controller, switchToSignUp));
        frame.pack();
        frame.setVisible(true);
    }
}
