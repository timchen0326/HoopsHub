package data_access;

import entity.SearchResult;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

public class DBSearchDataAccessObject {
    private static final String API_URL = "http://vm003.teach.cs.toronto.edu:20112/user?username=";

    public List<SearchResult> fetchData(String username) {
        List<SearchResult> results = new ArrayList<>();
        try {
            URL url = new URL(API_URL + username);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");

            if (connection.getResponseCode() == 200) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = in.readLine()) != null) {
                    response.append(line);
                }
                in.close();

                // Parse the JSON response
                JSONObject jsonResponse = new JSONObject(response.toString());
                if (jsonResponse.has("user")) {
                    JSONObject userJson = jsonResponse.getJSONObject("user");
                    String userId = userJson.getJSONObject("_id").getString("$oid");
                    int wins = userJson.getJSONObject("info").getInt("win");
                    int losses = userJson.getJSONObject("info").getInt("lose");

                    results.add(new SearchResult(username, userId, wins, losses));
                }
            } else {
                System.out.println("Error: " + connection.getResponseCode() + " - " + connection.getResponseMessage());
            }
            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return results;
    }
}
