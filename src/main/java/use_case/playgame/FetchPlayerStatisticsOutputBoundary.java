package use_case.playgame;

import java.util.List;

/**
 * Output boundary interface for presenting player statistics.
 * Defines methods for sending responses to the presenter.
 */
public interface FetchPlayerStatisticsOutputBoundary {

    void presentPlayerStatistics(FetchPlayerStatisticsResponseModel responseModel);

    void presentAvailableYears(List<String> years);

    void presentAverageStat(double average, String playerName, String year, String statType);

    void presentError(String message);
}
