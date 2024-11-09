package interface_adapter.account;

import use_case.account.AccountInteractor;

public class AccountController {

    private final AccountInteractor interactor;

    public AccountController(AccountInteractor interactor) {
        this.interactor = interactor;
    }

    public void createAccount(String username, String password) {
        interactor.createAccount(username, password);
    }

    public void loginUser(String username, String password) {
        interactor.loginUser(username, password);
    }
}
