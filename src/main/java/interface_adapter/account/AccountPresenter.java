package interface_adapter.account;

import use_case.account.AccountOutputBoundary;
import view.MainFrame;

import javax.swing.*;

public class AccountPresenter implements AccountOutputBoundary {

    private final MainFrame mainFrame;
    private final JFrame loginFrame;  // Reference to login frame to close it after login

    public AccountPresenter(MainFrame mainFrame, JFrame loginFrame) {
        this.mainFrame = mainFrame;
        this.loginFrame = loginFrame;
    }

    @Override
    public void prepareSuccessView(String message) {
        System.out.println("SUCCESS: " + message);

        // Hide login frame and show main frame with Home panel
        loginFrame.dispose();  // Close login window
        mainFrame.setVisible(true);
        mainFrame.switchTo("Home");  // Switch to Home panel
    }

    @Override
    public void prepareFailView(String errorMessage) {
        System.err.println("ERROR: " + errorMessage);
    }
}
