package use_case.play_game;

import data_access.PlayerStatisticsAPI;
import entity.PlayerStatistics;

public class FetchPlayerStatisticsInteractor implements FetchPlayerStatisticsInputBoundary {
    private final PlayerStatisticsAPI api;

    public FetchPlayerStatisticsInteractor(PlayerStatisticsAPI api) {
        this.api = api;
    }

    @Override
    public String fetchPlayerStatistics(String playerName) {
        try {
            String statsData = api.fetchPlayerData(playerName);
            PlayerStatistics playerStats = new PlayerStatistics(playerName, statsData);
            return playerStats.getStats();
        } catch (Exception e) {
            return "Error fetching stats: " + e.getMessage();
        }
    }
}
