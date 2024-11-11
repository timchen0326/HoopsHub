package data_access;

import interface_adapter.PlayerStatisticsRepository;

import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PlayerStatisticsRepositoryImpl implements PlayerStatisticsRepository {
    private final PlayerStatisticsAPI api;

    // Constructor initializes PlayerStatisticsAPI internally
    public PlayerStatisticsRepositoryImpl() {
        this.api = new PlayerStatisticsAPI();
    }

    @Override
    public String fetchAllStatisticsForPlayer(String playerName) {
        try {
            return api.fetchPlayerData(playerName); // Returns raw JSON as a string
        } catch (Exception e) {
            throw new RuntimeException("Error fetching player statistics: " + e.getMessage(), e);
        }
    }

    @Override
    public List<String> fetchAvailableYearsForPlayer(String playerName) {
        try {
            String data = api.fetchPlayerData(playerName);
            JSONArray jsonArray = new JSONArray(data);

            return IntStream.range(0, jsonArray.length())
                    .mapToObj(i -> jsonArray.getJSONObject(i).getInt("season"))
                    .distinct()
                    .sorted()
                    .map(String::valueOf)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Error fetching available years: " + e.getMessage(), e);
        }
    }

    @Override
    public String fetchStatsForPlayerByYear(String playerName, int year) {
        try {
            String data = api.fetchPlayerData(playerName);
            JSONArray jsonArray = new JSONArray(data);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject record = jsonArray.getJSONObject(i);
                if (record.getInt("season") == year) {
                    return record.toString(); // Return stats for the specific year as JSON string
                }
            }
            return "No statistics found for year: " + year;
        } catch (Exception e) {
            throw new RuntimeException("Error fetching player statistics for year " + year + ": " + e.getMessage(), e);
        }
    }
}
