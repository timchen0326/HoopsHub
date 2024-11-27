package use_case.account;

/**
 * The AccountInputBoundary defines the methods for handling input
 * related to account operations such as creating an account or logging in a user.
 */
public interface AccountInputBoundary {

    /**
     * Creates a new user account.
     *
     * @param username the username of the account to create, must not be null
     * @param password the password of the account to create, must not be null
     * @throws Exception if an error occurs during the account creation
     */
    void createAccount(String username, String password) throws Exception;

    /**
     * Logs in a user with the given credentials.
     *
     * @param username the username of the user to log in, must not be null
     * @param password the password of the user to log in, must not be null
     * @throws Exception if an error occurs during the login process
     */
    void loginUser(String username, String password) throws Exception;
}
