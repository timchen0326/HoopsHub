package use_case.account;

import org.json.JSONObject;

import entity.User;

/**
 * Interface for account data access operations.
 * Provides methods for saving users, logging in users, and loading user details.
 */
public interface AccountDataAccessInterface {

    /**
     * Saves the user data.
     *
     * @param user the user entity to save, must not be null
     * @throws Exception if an error occurs while saving the user
     */
    void saveUser(User user) throws Exception;

    /**
     * Logs in a user with the given username and password.
     *
     * @param username the username of the user, must not be null
     * @param password the password of the user, must not be null
     * @return true if the login is successful, false otherwise
     * @throws Exception if an error occurs during the login process
     */
    boolean loginUser(String username, String password) throws Exception;

    /**
     * Loads the user details for the specified username.
     *
     * @param username the username of the user, must not be null
     * @return a JSON object containing the user's details
     * @throws Exception if an error occurs while loading user details
     */
    JSONObject loadUserDetails(String username) throws Exception;
}
