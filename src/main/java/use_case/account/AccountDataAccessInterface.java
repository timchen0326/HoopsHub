package use_case.account;

import entity.User;
import org.json.JSONObject;

public interface AccountDataAccessInterface {
    void saveUser(User user) throws Exception;
    boolean loginUser(String username, String password) throws Exception;
    JSONObject loadUserDetails(String username) throws Exception; // Add this method
}
