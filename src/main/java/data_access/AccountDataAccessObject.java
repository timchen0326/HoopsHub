package data_access;

import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONObject;

import entity.User;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import use_case.account.AccountDataAccessInterface;

/**
 * Implementation of the AccountDataAccessInterface.
 * Handles user-related data access operations by interacting with an external server.
 */

public class AccountDataAccessObject implements AccountDataAccessInterface {
    private static final String PASSWORD_KEY = "password";
    private static final String APPLICATION_JSON = "application/json; charset=utf-8";
    private static final int HTTP_CONFLICT = 409;
    private static final String CREATE_USER_URL = "http://vm003.teach.cs.toronto.edu:20112/user";
    private static final String MODIFY_USER_INFO_URL = "http://vm003.teach.cs.toronto.edu:20112/modifyUserInfo";
    private static final String LOAD_USER_INFO_URL = "http://vm003.teach.cs.toronto.edu:20112/user?username=%s";
    private final OkHttpClient client = new OkHttpClient();

    @Override
    public void saveUser(User user) throws Exception {
        // Step 1: Create user with basic details
        final JSONObject jsonObject = new JSONObject();
        jsonObject.put("username", user.getName());
        jsonObject.put(PASSWORD_KEY, user.getPassword());

        final RequestBody body = RequestBody.create(
                jsonObject.toString(),
                MediaType.parse(APPLICATION_JSON)
        );

        final Request request = new Request.Builder()
                .url(CREATE_USER_URL)
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                System.out.println("User " + user.getName() + " created successfully.");
                initializeUserInfo(user);
            }
            else {
                // Check if user already exists and handle it by updating instead
                if (response.code() == HTTP_CONFLICT) {
                    throw new Exception("User already exists");
                }
                else {
                    throw new IOException("Unexpected response: " + response);
                }
            }
        }
    }

    private void initializeUserInfo(User user) throws Exception {
        // Step 2a: Prepare JSON with username, password, info, and history fields
        final JSONObject infoObject = new JSONObject();
        infoObject.put(PASSWORD_KEY, user.getPassword());
        infoObject.put("win", user.getWin());
        infoObject.put("lose", user.getLose());
        infoObject.put("history", new JSONArray());

        final JSONObject jsonObject = new JSONObject();
        jsonObject.put("username", user.getName());
        jsonObject.put(PASSWORD_KEY, user.getPassword());
        jsonObject.put("info", infoObject);

        final RequestBody body = RequestBody.create(
                jsonObject.toString(),
                MediaType.parse(APPLICATION_JSON)
        );

        final Request request = new Request.Builder()
                .url(MODIFY_USER_INFO_URL)
                .put(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Failed to initialize user info: " + response);
            }
            System.out.println("Initialized user info for user " + user.getName());
        }
    }

    @Override
    public boolean loginUser(String username, String password) throws Exception {
        final boolean result;
        // Attempt to load user info and compare the password
        final String storedPassword = loadUserPassword(username);
        if (storedPassword != null && storedPassword.equals(password)) {
            System.out.println("Login successful for user: " + username);
            result = true;
        }
        else {
            System.out.println("Login failed for user: " + username);
            result = false;
        }
        return result;
    }

    private String loadUserPassword(String username) throws Exception {
        final String url = String.format(LOAD_USER_INFO_URL, username);
        final Request request = new Request.Builder()
                .url(url)
                .get()
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Failed to load user info: " + response);
            }

            // Parse the JSON response to extract the password from "info"
            final JSONObject responseBody = new JSONObject(response.body().string());
            final JSONObject userInfo = responseBody.getJSONObject("user").getJSONObject("info");
            return userInfo.getString(PASSWORD_KEY);
        }
    }

    /**
     * Loads detailed information about a user.
     *
     * @param username the username of the user whose details are to be loaded, must not be null
     * @return a JSON object containing the user's details
     * @throws IOException if the server returns an unsuccessful response or a network error occurs
     * @throws Exception for any other general exceptions that occur during the operation
     */

    public JSONObject loadUserDetails(String username) throws Exception {
        final String url = String.format(LOAD_USER_INFO_URL, username);
        final Request request = new Request.Builder()
                .url(url)
                .get()
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Failed to load user info: " + response);
            }

            // Parse and return the JSON response
            return new JSONObject(response.body().string()).getJSONObject("user");
        }
    }

    /**
     * Updates the user's information in the system.
     *
     * @param userInfo a JSON object containing the user's updated information, must not be null
     * @throws IOException if the server returns an unsuccessful response or a network error occurs
     * @throws Exception for any other general exceptions that occur during the operation
     */

    public void updateUserInfo(JSONObject userInfo) throws Exception {
        final String url = "http://vm003.teach.cs.toronto.edu:20112/modifyUserInfo";

        final RequestBody body = RequestBody.create(
                userInfo.toString(),
                MediaType.parse(APPLICATION_JSON)
        );

        final Request request = new Request.Builder()
                .url(url)
                .put(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Failed to update user info: " + response);
            }
        }
    }

}
