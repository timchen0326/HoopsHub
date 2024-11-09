package interface_adapter.account;

import use_case.account.AccountOutputBoundary;

public class AccountPresenter implements AccountOutputBoundary {

    @Override
    public void prepareSuccessView(String message) {
        System.out.println("SUCCESS: " + message);  // Replace with actual UI logic
    }

    @Override
    public void prepareFailView(String errorMessage) {
        System.err.println("ERROR: " + errorMessage);  // Replace with actual UI logic
    }
}
