package use_case.account;

public interface AccountOutputBoundary {
    void prepareSuccessView(String message);
    void prepareFailView(String errorMessage);
}
