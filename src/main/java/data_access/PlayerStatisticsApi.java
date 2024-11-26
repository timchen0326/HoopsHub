package data_access;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * A class that fetches player statistics data from an API.
 */
public class PlayerStatisticsApi {
    private static final String BASE_URL = "https://samplejson.onrender.com/api/PlayerDataTotals/name/";

    /**
     * Fetches player data from the API.
     *
     * @param playerName the name of the player whose data is to be fetched
     * @return a JSON string containing the player's statistics
     * @throws Exception if an error occurs during the API request
     * @throws RuntimeException if the GET request fails with a non-OK response code
     */

    public String fetchPlayerData(String playerName) throws Exception {
        final String encodedName = URLEncoder.encode(playerName, "UTF-8").replace("+", "%20");
        final String urlString = BASE_URL + encodedName;

        final URL url = new URL(urlString);
        final HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        final int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            final BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            final StringBuilder response = new StringBuilder();
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            return response.toString();
        }
        else {
            throw new RuntimeException("GET request failed with response code: " + responseCode);
        }
    }
}
