package interface_adapter.account;

import use_case.account.AccountInteractor;

/**
 * Controller class for managing account-related operations.
 * This class interacts with the AccountInteractor to perform account actions.
 */
public class AccountController {

    /**
     * The AccountInteractor instance used for account operations.
     */
    private final AccountInteractor interactor;

    /**
     * Constructs an AccountController with the specified AccountInteractor.
     *
     * @param interactor the AccountInteractor instance, must not be null
     */
    public AccountController(AccountInteractor interactor) {
        this.interactor = interactor;
    }

    /**
     * Creates a new account with the specified username and password.
     *
     * @param username the username for the new account, must not be null
     * @param password the password for the new account, must not be null
     */
    public void createAccount(String username, String password) {
        interactor.createAccount(username, password);
    }

    /**
     * Logs in a user with the specified username and password.
     *
     * @param username the username for the user, must not be null
     * @param password the password for the user, must not be null
     */
    public void loginUser(String username, String password) {
        interactor.loginUser(username, password);
    }
}
