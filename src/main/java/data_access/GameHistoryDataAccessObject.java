package data_access;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

public class GameHistoryDataAccessObject {
    private static final String API_URL = "http://vm003.teach.cs.toronto.edu:20112/user?username=";

    public List<String> getGameHistory(String username) {
        List<String> history = new ArrayList<>();
        try {
            // Build the request URL
            URL url = new URL(API_URL + username);

            // Create HTTP connection
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            // Check the response code
            int responseCode = conn.getResponseCode();
            if (responseCode != 200) {
                throw new IOException("Failed to fetch data: HTTP response code " + responseCode);
            }

            // Read the response
            StringBuilder response = new StringBuilder();
            try (Scanner scanner = new Scanner(conn.getInputStream())) {
                while (scanner.hasNext()) {
                    response.append(scanner.nextLine());
                }
            }

            // Parse the JSON response
            JSONObject jsonResponse = new JSONObject(response.toString());
            JSONObject userInfo = jsonResponse.getJSONObject("user").getJSONObject("info");
            JSONArray gameHistoryArray = userInfo.getJSONArray("history");

            // Extract game details
            for (int i = 0; i < gameHistoryArray.length(); i++) {
                JSONObject game = gameHistoryArray.getJSONObject(i);
                String player = game.getString("player");
                String stats = game.getString("stats");
                String year = game.getString("year");

                String record = String.format("Player: %s | Stats: %s | Year: %s", player, stats, year);
                history.add(record);
            }
        } catch (Exception e) {
            history.add("Error: Unable to fetch game history. " + e.getMessage());
        }

        return history;
    }
}
