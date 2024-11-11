package interface_adapter;

import java.util.List;

public interface PlayerStatisticsRepository {
    String fetchAllStatisticsForPlayer(String playerName);
    List<String> fetchAvailableYearsForPlayer(String playerName);
    String fetchStatsForPlayerByYear(String playerName, int year);
}
