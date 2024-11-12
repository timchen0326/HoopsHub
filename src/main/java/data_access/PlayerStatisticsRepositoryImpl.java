package data_access;

import interface_adapter.PlayerStatisticsRepository;
import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class PlayerStatisticsRepositoryImpl implements PlayerStatisticsRepository {
    private final PlayerStatisticsAPI api;

    public PlayerStatisticsRepositoryImpl() {
        this.api = new PlayerStatisticsAPI();
    }

    @Override
    public String fetchAllStatisticsForPlayer(String playerName) {
        try {
            return api.fetchPlayerData(playerName); // Fetch all stats
        } catch (Exception e) {
            throw new RuntimeException("Error fetching all statistics for player: " + playerName, e);
        }
    }

    @Override
    public String fetchStatsForPlayerByYear(String playerName, int year) {
        try {
            String data = api.fetchPlayerData(playerName);
            JSONArray jsonArray = new JSONArray(data);

            for (int i = 0; i < jsonArray.length(); i++) {
                if (jsonArray.getJSONObject(i).getInt("season") == year) {
                    return jsonArray.getJSONObject(i).toString();
                }
            }
            return "{}"; // Return empty JSON if not found
        } catch (Exception e) {
            throw new RuntimeException("Error fetching statistics for player by year: " + playerName + ", year: " + year, e);
        }
    }

    @Override
    public List<Integer> fetchAvailableYearsForPlayer(String playerName) {
        try {
            String data = api.fetchPlayerData(playerName);
            JSONArray jsonArray = new JSONArray(data);
            List<Integer> years = new ArrayList<>();

            for (int i = 0; i < jsonArray.length(); i++) {
                int season = jsonArray.getJSONObject(i).getInt("season");
                years.add(season);
            }

            return years;
        } catch (Exception e) {
            throw new RuntimeException("Error fetching available years for player: " + playerName, e);
        }
    }
}
