package use_case.account;

import entity.User;

public interface AccountDataAccessInterface {
    void saveUser(User user) throws Exception;
    boolean loginUser(String username, String password) throws Exception;
}
