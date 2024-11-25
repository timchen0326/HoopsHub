package interface_adapter.PlayGameAspects;

import entity.PlayerStatistic;
import java.util.List;

public interface PlayerStatisticsRepository {
    List<PlayerStatistic> fetchAllStatisticsForPlayer(String playerName);
    PlayerStatistic fetchStatsForPlayerByYear(String playerName, int year);
    List<Integer> fetchAvailableYearsForPlayer(String playerName);
}
