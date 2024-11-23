package use_case.account;

import app.Session;
import entity.User;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AccountInteractor {

    private final AccountDataAccessInterface accountDataAccess;
    private final AccountOutputBoundary outputBoundary;

    public AccountInteractor(AccountDataAccessInterface accountDataAccess, AccountOutputBoundary outputBoundary) {
        this.accountDataAccess = accountDataAccess;
        this.outputBoundary = outputBoundary;
    }

    public void createAccount(String name, String password) {
        try {
            User newUser = new User(name, password);
            accountDataAccess.saveUser(newUser);

            // Optionally set session for the new user
            Session.getInstance().setUsername(name);
            Session.getInstance().setPassword(password);

            outputBoundary.prepareSuccessView("Account created successfully.");
        } catch (Exception e) {
            outputBoundary.prepareFailView("Failed to create account: " + e.getMessage());
        }
    }

    public void loginUser(String username, String password) {
        try {
            boolean loginSuccess = accountDataAccess.loginUser(username, password);
            if (loginSuccess) {
                // Retrieve additional user details
                JSONObject userData = accountDataAccess.loadUserDetails(username);

                Session session = Session.getInstance();
                session.setUsername(username);
                session.setPassword(password);
                session.setWin(userData.getJSONObject("info").getInt("win"));
                session.setLose(userData.getJSONObject("info").getInt("lose"));

                // Convert the history array to a list of JSONObjects
                JSONArray historyArray = userData.getJSONObject("info").getJSONArray("history");
                List<JSONObject> historyList = new ArrayList<>();
                for (int i = 0; i < historyArray.length(); i++) {
                    historyList.add(historyArray.getJSONObject(i));
                }
                session.setHistory(historyList);

                outputBoundary.prepareSuccessView("Login successful.");
            } else {
                outputBoundary.prepareFailView("Login failed. Invalid username or password.");
            }
        } catch (Exception e) {
            outputBoundary.prepareFailView("Error during login: " + e.getMessage());
        }
    }


}

