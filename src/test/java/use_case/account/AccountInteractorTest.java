package use_case.account;

import entity.User;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class AccountInteractorTest {

    @Test
    public void testCreateAccountSuccess() {
        AccountDataAccessInterface mockDataAccess = new AccountDataAccessInterface() {
            @Override
            public void saveUser(User user) {
                // Simulate successful user saving
            }

            @Override
            public boolean loginUser(String username, String password) {
                return false;
            }

            @Override
            public JSONObject loadUserDetails(String username) {
                return null;
            }
        };

        AccountOutputBoundary mockOutputBoundary = new AccountOutputBoundary() {
            @Override
            public void prepareSuccessView(String message) {
                assertEquals("Account created successfully.", message);
            }

            @Override
            public void prepareFailView(String errorMessage) {
                fail(errorMessage);
            }
        };

        AccountInteractor interactor = new AccountInteractor(mockDataAccess, mockOutputBoundary);
        interactor.createAccount("testUser", "testPassword");
    }

    @Test
    public void testCreateAccountFailure() {
        AccountDataAccessInterface mockDataAccess = new AccountDataAccessInterface() {
            @Override
            public void saveUser(User user) throws Exception {
                throw new Exception("Save failed");
            }

            @Override
            public boolean loginUser(String username, String password) {
                return false;
            }

            @Override
            public JSONObject loadUserDetails(String username) {
                return null;
            }
        };

        AccountOutputBoundary mockOutputBoundary = new AccountOutputBoundary() {
            @Override
            public void prepareSuccessView(String message) {
                fail(message);
            }

            @Override
            public void prepareFailView(String errorMessage) {
                assertEquals("Failed to create account: Save failed", errorMessage);
            }
        };

        AccountInteractor interactor = new AccountInteractor(mockDataAccess, mockOutputBoundary);
        interactor.createAccount("testUser", "testPassword");
    }

    @Test
    public void testLoginUserSuccess() {
        AccountDataAccessInterface mockDataAccess = new AccountDataAccessInterface() {
            @Override
            public void saveUser(User user) {
                // Not needed for this test
            }

            @Override
            public boolean loginUser(String username, String password) {
                return true;
            }

            @Override
            public JSONObject loadUserDetails(String username) {
                JSONObject mockUserDetails = new JSONObject();
                JSONObject info = new JSONObject();
                info.put("win", 5);
                info.put("lose", 3);
                JSONArray history = new JSONArray();
                history.put(new JSONObject().put("action", "won"));
                history.put(new JSONObject().put("action", "lost"));
                info.put("history", history);
                mockUserDetails.put("info", info);
                return mockUserDetails;
            }
        };

        AccountOutputBoundary mockOutputBoundary = new AccountOutputBoundary() {
            @Override
            public void prepareSuccessView(String message) {
                assertEquals("Login successful.", message);
            }

            @Override
            public void prepareFailView(String errorMessage) {
                fail(errorMessage);
            }
        };

        AccountInteractor interactor = new AccountInteractor(mockDataAccess, mockOutputBoundary);
        interactor.loginUser("testUser", "testPassword");
    }

    @Test
    public void testLoginUserFailureInvalidCredentials() {
        AccountDataAccessInterface mockDataAccess = new AccountDataAccessInterface() {
            @Override
            public void saveUser(User user) {
                // Not needed for this test
            }

            @Override
            public boolean loginUser(String username, String password) {
                return false;
            }

            @Override
            public JSONObject loadUserDetails(String username) {
                return null;
            }
        };

        AccountOutputBoundary mockOutputBoundary = new AccountOutputBoundary() {
            @Override
            public void prepareSuccessView(String message) {
                fail(message);
            }

            @Override
            public void prepareFailView(String errorMessage) {
                assertEquals("Login failed. Invalid username or password.", errorMessage);
            }
        };

        AccountInteractor interactor = new AccountInteractor(mockDataAccess, mockOutputBoundary);
        interactor.loginUser("testUser", "wrongPassword");
    }

    @Test
    public void testLoginUserFailureException() {
        AccountDataAccessInterface mockDataAccess = new AccountDataAccessInterface() {
            @Override
            public void saveUser(User user) {
                // Not needed for this test
            }

            @Override
            public boolean loginUser(String username, String password) throws Exception {
                throw new Exception("Data access error");
            }

            @Override
            public JSONObject loadUserDetails(String username) {
                return null;
            }
        };

        AccountOutputBoundary mockOutputBoundary = new AccountOutputBoundary() {
            @Override
            public void prepareSuccessView(String message) {
                fail(message);
            }

            @Override
            public void prepareFailView(String errorMessage) {
                assertEquals("Error during login: Data access error", errorMessage);
            }
        };

        AccountInteractor interactor = new AccountInteractor(mockDataAccess, mockOutputBoundary);
        interactor.loginUser("testUser", "testPassword");
    }
}
