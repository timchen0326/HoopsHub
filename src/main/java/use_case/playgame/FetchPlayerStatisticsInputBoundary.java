package use_case.playgame;

import java.util.List;

public interface FetchPlayerStatisticsInputBoundary {
    String fetchPlayerStatistics(String playerName);
    String fetchPlayerStatisticsByYear(String playerName, int year);
    List<String> getAvailableYears(String playerName); // Changed to match the implementation's List<String>
    double getAverageStat(String playerName, String year, String statType);
}
