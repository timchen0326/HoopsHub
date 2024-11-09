package use_case.account;

import entity.User;

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
            outputBoundary.prepareSuccessView("Account created successfully.");
        } catch (Exception e) {
            outputBoundary.prepareFailView("Failed to create account: " + e.getMessage());
        }
    }

    public void loginUser(String username, String password) {
        try {
            boolean loginSuccess = accountDataAccess.loginUser(username, password);
            if (loginSuccess) {
                outputBoundary.prepareSuccessView("Login successful.");
            } else {
                outputBoundary.prepareFailView("Login failed. Invalid username or password.");
            }
        } catch (Exception e) {
            outputBoundary.prepareFailView("Error during login: " + e.getMessage());
        }
    }
}
