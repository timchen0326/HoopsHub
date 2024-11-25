package use_case.playgame;

import entity.PlayerStatistic;
import java.util.List;

public interface FetchPlayerStatisticsInputBoundary {
    List<PlayerStatistic> fetchPlayerStatistics(String playerName);
    PlayerStatistic fetchPlayerStatisticsByYear(String playerName, int year);
    List<String> getAvailableYears(String playerName);
    double getAverageStat(String playerName, String year, String statType);
}