package interface_adapter.account;

import javax.swing.JFrame;

import use_case.account.AccountOutputBoundary;
import view.MainFrame;

/**
 * The AccountPresenter class is responsible for handling the presentation logic
 * for account-related operations, such as displaying success or error messages
 * and managing the visibility of the main application frame.
 */
public class AccountPresenter implements AccountOutputBoundary {

    private final MainFrame mainFrame;
    private final JFrame loginFrame;

    /**
     * Constructs an AccountPresenter with the specified main frame and login frame.
     *
     * @param mainFrame  the main frame of the application, must not be null
     * @param loginFrame the login frame of the application, must not be null
     */
    public AccountPresenter(MainFrame mainFrame, JFrame loginFrame) {
        this.mainFrame = mainFrame;
        this.loginFrame = loginFrame;
    }

    /**
     * Handles the presentation of a success message.
     * This will dispose of the login frame and switch to the main frame's home panel.
     *
     * @param message the success message to display, must not be null
     */
    @Override
    public void prepareSuccessView(String message) {
        System.out.println("SUCCESS: " + message);

        // Hide login frame and show main frame with Home panel
        loginFrame.dispose();
        mainFrame.setVisible(true);
        mainFrame.switchTo("Home");
    }

    /**
     * Handles the presentation of an error message.
     * This will display the error in the console.
     *
     * @param errorMessage the error message to display, must not be null
     */
    @Override
    public void prepareFailView(String errorMessage) {
        System.err.println("ERROR: " + errorMessage);
    }
}
