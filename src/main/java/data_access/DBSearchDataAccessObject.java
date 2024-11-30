package data_access;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import entity.SearchResult;
import use_case.search.SearchDataAccessInterface;

/**
 * Handles data access for fetching search results.
 */
public class DBSearchDataAccessObject implements SearchDataAccessInterface {

    private static final String API_URL = "http://vm003.teach.cs.toronto.edu:20112/user?username=";
    private static final int HTTP_OK = 200;

    /**
     * Fetches search results for a given username.
     *
     * @param username the username to search for
     * @return a list of search results
     */
    @Override
    public List<SearchResult> fetchData(String username) {
        final List<SearchResult> results = new ArrayList<>();
        try {
            final URL url = new URL(API_URL + username);
            final HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/json");

            if (connection.getResponseCode() == HTTP_OK) {
                final BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                final StringBuilder response = new StringBuilder();
                String line;
                while ((line = in.readLine()) != null) {
                    response.append(line);
                }
                in.close();

                // Parse the JSON response
                final JSONObject jsonResponse = new JSONObject(response.toString());
                if (jsonResponse.has("user")) {
                    final JSONObject userJson = jsonResponse.getJSONObject("user");
                    final String userId = userJson.getJSONObject("_id").getString("$oid");
                    final int wins = userJson.getJSONObject("info").getInt("win");
                    final int losses = userJson.getJSONObject("info").getInt("lose");

                    results.add(new SearchResult(username, userId, wins, losses));
                }
            }
            else {
                System.out.println("Error: " + connection.getResponseCode() + " - " + connection.getResponseMessage());
            }
            connection.disconnect();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return results;
    }
}
