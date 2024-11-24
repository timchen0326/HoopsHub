package interface_adapter.PlayGameAspects;

import java.util.List;

public interface PlayerStatisticsRepository {
    String fetchAllStatisticsForPlayer(String playerName);

    String fetchStatsForPlayerByYear(String playerName, int year);

    List<Integer> fetchAvailableYearsForPlayer(String playerName); // Ensure this is defined
}
