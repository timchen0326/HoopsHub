package data_access;

import entity.PlayerStatistic;
import entity.PlayerStatisticFactory;
import interface_adapter.PlayGameAspects.PlayerStatisticsRepository;

import java.util.List;
import java.util.stream.Collectors;

public class PlayerStatisticsRepositoryImpl implements PlayerStatisticsRepository {
    private final PlayerStatisticsAPI api;

    public PlayerStatisticsRepositoryImpl() {
        this.api = new PlayerStatisticsAPI();
    }

    @Override
    public List<PlayerStatistic> fetchAllStatisticsForPlayer(String playerName) {
        try {
            String rawData = api.fetchPlayerData(playerName);
            return PlayerStatisticFactory.fromJson(rawData);
        } catch (Exception e) {
            throw new RuntimeException("Error fetching all statistics for player: " + playerName, e);
        }
    }

    @Override
    public PlayerStatistic fetchStatsForPlayerByYear(String playerName, int year) {
        try {
            List<PlayerStatistic> stats = fetchAllStatisticsForPlayer(playerName);
            return stats.stream()
                    .filter(stat -> stat.getSeason() == year)
                    .findFirst()
                    .orElse(null); // Return null if no matching year
        } catch (Exception e) {
            throw new RuntimeException("Error fetching statistics for player by year: " + playerName + ", year: " + year, e);
        }
    }

    @Override
    public List<Integer> fetchAvailableYearsForPlayer(String playerName) {
        try {
            List<PlayerStatistic> stats = fetchAllStatisticsForPlayer(playerName);
            return stats.stream()
                    .map(PlayerStatistic::getSeason)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Error fetching available years for player: " + playerName, e);
        }
    }
}
