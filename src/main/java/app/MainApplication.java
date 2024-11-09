package app;

import data_access.AccountDataAccessObject;
import interface_adapter.account.AccountController;
import interface_adapter.account.AccountPresenter;
import use_case.account.AccountDataAccessInterface;
import use_case.account.AccountInteractor;
import use_case.account.AccountOutputBoundary;
import view.AccountCreationView;
import view.LoginView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainApplication {

    public static void main(String[] args) {
        // Initialize application components
        AccountDataAccessInterface dataAccess = new AccountDataAccessObject();
        AccountOutputBoundary presenter = new AccountPresenter();
        AccountInteractor interactor = new AccountInteractor(dataAccess, presenter);
        AccountController controller = new AccountController(interactor);

        // Setup main frame
        JFrame frame = new JFrame("User Authentication");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);

        // ActionListener to switch to AccountCreationView
        ActionListener switchToSignUp = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setContentPane(new AccountCreationView(controller));
                frame.pack();
            }
        };

        // Set initial view to LoginView with switch to sign up functionality
        frame.setContentPane(new LoginView(controller, switchToSignUp));
        frame.pack();
        frame.setVisible(true);
    }
}
