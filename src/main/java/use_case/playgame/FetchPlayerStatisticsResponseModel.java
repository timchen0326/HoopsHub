package use_case.playgame;

import java.util.List;
import entity.PlayerStatistic;

/**
 * Response model for fetching player statistics.
 * Encapsulates the data to be passed from interactor to presenter.
 */
public class FetchPlayerStatisticsResponseModel {
    private final List<PlayerStatistic> playerStatistics;
    private final String playerName;

    public FetchPlayerStatisticsResponseModel(String playerName, List<PlayerStatistic> playerStatistics) {
        this.playerName = playerName;
        this.playerStatistics = playerStatistics;
    }

    public String getPlayerName() {
        return playerName;
    }

    public List<PlayerStatistic> getPlayerStatistics() {
        return playerStatistics;
    }
}