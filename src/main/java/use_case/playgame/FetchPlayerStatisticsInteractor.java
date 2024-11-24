package use_case.playgame;

import interface_adapter.PlayerStatisticsRepository;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class FetchPlayerStatisticsInteractor implements FetchPlayerStatisticsInputBoundary {
    private final PlayerStatisticsRepository repository;

    public FetchPlayerStatisticsInteractor(PlayerStatisticsRepository repository) {
        this.repository = repository;
    }

    @Override
    public String fetchPlayerStatistics(String playerName) {
        return repository.fetchAllStatisticsForPlayer(playerName);
    }

    @Override
    public String fetchPlayerStatisticsByYear(String playerName, int year) {
        return repository.fetchStatsForPlayerByYear(playerName, year);
    }

    @Override
    public List<String> getAvailableYears(String playerName) {
        String rawData = repository.fetchAllStatisticsForPlayer(playerName);
        JSONArray jsonArray = new JSONArray(rawData);
        List<String> years = new ArrayList<>();

        for (int i = 0; i < jsonArray.length(); i++) {
            String seasonYear = String.valueOf(jsonArray.getJSONObject(i).getInt("season"));
            years.add(seasonYear);
        }

        return years;
    }

    public double getAverageStat(String playerName, String year, String statType) {
        String rawData = repository.fetchStatsForPlayerByYear(playerName, Integer.parseInt(year));
        return calculateAverage(rawData, statType);
    }

    private double calculateAverage(String rawData, String statType) {
        JSONObject stats = new JSONObject(rawData);
        switch (statType) {
            case "Average Rebounds":
                return stats.getDouble("totalRb") / stats.getDouble("games");
            case "Average Points":
                return stats.getDouble("points") / stats.getDouble("games");
            case "Average Assists":
                return stats.getDouble("assists") / stats.getDouble("games");
            default:
                throw new IllegalArgumentException("Invalid statistic type.");
        }
    }
}
