package data_access;

import entity.User;
import okhttp3.*;
import org.json.JSONObject;
import use_case.account.AccountDataAccessInterface;

import java.io.IOException;

public class AccountDataAccessObject implements AccountDataAccessInterface {

    private static final String CREATE_USER_URL = "http://vm003.teach.cs.toronto.edu:20112/user";
    private static final String MODIFY_USER_INFO_URL = "http://vm003.teach.cs.toronto.edu:20112/modifyUserInfo";
    private static final String LOAD_USER_INFO_URL = "http://vm003.teach.cs.toronto.edu:20112/user?username=%s";
    private final OkHttpClient client = new OkHttpClient();

    @Override
    public void saveUser(User user) throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username", user.getName());
        jsonObject.put("password", user.getPassword());

        RequestBody body = RequestBody.create(
                jsonObject.toString(),
                MediaType.parse("application/json; charset=utf-8")
        );

        Request request = new Request.Builder()
                .url(CREATE_USER_URL)
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                System.out.println("User " + user.getName() + " created successfully.");
            } else {
                // Check the response for "user already exists" error
                if (response.code() == 409) {  // Assuming 409 Conflict is returned if the user already exists
                    throw new Exception("User already exists");
                } else {
                    throw new IOException("Unexpected response: " + response);
                }
            }
        }
    }


    private void createUser(User user) throws Exception {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username", user.getName());
        jsonObject.put("password", user.getPassword());

        RequestBody body = RequestBody.create(
                jsonObject.toString(),
                MediaType.parse("application/json; charset=utf-8")
        );

        Request request = new Request.Builder()
                .url(CREATE_USER_URL)
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected code " + response);
            }
            System.out.println("User " + user.getName() + " created successfully with status code: " + response.code());
        }
    }

    private void initializeUserInfo(User user) throws Exception {
        JSONObject infoObject = new JSONObject();
        infoObject.put("password", user.getPassword());  // Store password in the info field
        infoObject.put("win", user.getWin());
        infoObject.put("lose", user.getLose());

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("username", user.getName());
        jsonObject.put("password", user.getPassword());
        jsonObject.put("info", infoObject);

        RequestBody body = RequestBody.create(
                jsonObject.toString(),
                MediaType.parse("application/json; charset=utf-8")
        );

        Request request = new Request.Builder()
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
        // Attempt to load user info and compare the password
        String storedPassword = loadUserPassword(username);
        if (storedPassword != null && storedPassword.equals(password)) {
            System.out.println("Login successful for user: " + username);
            return true;
        } else {
            System.out.println("Login failed for user: " + username);
            return false;
        }
    }

    private String loadUserPassword(String username) throws Exception {
        String url = String.format(LOAD_USER_INFO_URL, username);
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Failed to load user info: " + response);
            }

            // Parse the JSON response to extract the password from "info"
            JSONObject responseBody = new JSONObject(response.body().string());
            JSONObject userInfo = responseBody.getJSONObject("user").getJSONObject("info");
            return userInfo.getString("password");
        }
    }
}
