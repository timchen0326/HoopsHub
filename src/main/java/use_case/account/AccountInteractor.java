package use_case.account;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import app.Session;
import entity.User;

/**
 * The AccountInteractor class handles the core business logic for user account management,
 * including creating new accounts and logging in existing users.
 */

public class AccountInteractor {

    private static final String INFO = "info";

    private final AccountDataAccessInterface accountDataAccess;
    private final AccountOutputBoundary outputBoundary;

    public AccountInteractor(AccountDataAccessInterface accountDataAccess, AccountOutputBoundary outputBoundary) {
        this.accountDataAccess = accountDataAccess;
        this.outputBoundary = outputBoundary;
    }

    /**
     * Creates a new user account and initializes the session with the provided username and password.
     *
     * @param name the username of the new account
     * @param password the password of the new account
     * @throws Exception if there is an issue saving the user data
     */

    public void createAccount(String name, String password) throws Exception {
        final User newUser = new User(name, password);
        accountDataAccess.saveUser(newUser);

        // Optionally set session for the new user
        Session.getInstance().setUsername(name);
        Session.getInstance().setPassword(password);

        outputBoundary.prepareSuccessView("Account created successfully.");
    }

    /**
     * Logs in a user and sets their session data if the login is successful.
     *
     * @param username the username of the account
     * @param password the password of the account
     * @throws Exception if there is an issue accessing the account data
     */

    public void loginUser(String username, String password) throws Exception {
        final boolean loginSuccess = accountDataAccess.loginUser(username, password);
        if (loginSuccess) {
            // Retrieve additional user details
            final JSONObject userData = accountDataAccess.loadUserDetails(username);

            final Session session = Session.getInstance();
            session.setUsername(username);
            session.setPassword(password);
            session.setWin(userData.getJSONObject(INFO).getInt("win"));
            session.setLose(userData.getJSONObject(INFO).getInt("lose"));

            final JSONArray historyArray = userData.getJSONObject(INFO).getJSONArray("history");
            final List<JSONObject> historyList = new ArrayList<>();
            for (int i = 0; i < historyArray.length(); i++) {
                historyList.add(historyArray.getJSONObject(i));
            }
            session.setHistory(historyList);

            outputBoundary.prepareSuccessView("Login successful.");
        }
        else {
            outputBoundary.prepareFailView("Login failed. Invalid username or password.");
        }
    }

}

