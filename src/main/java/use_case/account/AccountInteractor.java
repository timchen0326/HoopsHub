package use_case.account;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import app.Session;
import entity.User;

/**
 * The AccountInteractor class handles the core business logic for user account management,
 * implementing the AccountInputBoundary.
 */
public class AccountInteractor implements AccountInputBoundary {

    private static final String INFO = "info";

    private final AccountDataAccessInterface accountDataAccess;
    private final AccountOutputBoundary outputBoundary;

    public AccountInteractor(AccountDataAccessInterface accountDataAccess, AccountOutputBoundary outputBoundary) {
        this.accountDataAccess = accountDataAccess;
        this.outputBoundary = outputBoundary;
    }

    @Override
    public void createAccount(String name, String password) throws Exception {
        final User newUser = new User(name, password);
        accountDataAccess.saveUser(newUser);

        // Initialize session
        Session.getInstance().setUsername(name);
        Session.getInstance().setPassword(password);

        // Notify the presenter
        outputBoundary.prepareSuccessView("Account created successfully.");
    }

    @Override
    public void loginUser(String username, String password) throws Exception {
        final boolean loginSuccess = accountDataAccess.loginUser(username, password);
        if (loginSuccess) {
            final JSONObject userData = accountDataAccess.loadUserDetails(username);

            // Update session
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

            // Notify the presenter
            outputBoundary.prepareSuccessView("Login successful.");
        }
        else {
            outputBoundary.prepareFailView("Login failed. Invalid username or password.");
        }
    }
}
