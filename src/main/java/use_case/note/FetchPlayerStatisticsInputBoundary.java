package use_case.note;

import java.util.List;

public interface FetchPlayerStatisticsInputBoundary {
    String fetchPlayerStatistics(String playerName); // General stats fetcher
    List<String> getAvailableYears(String playerName); // Available years for player
    String fetchPlayerStatisticsByYear(String playerName, int year); // Stats by year
}
