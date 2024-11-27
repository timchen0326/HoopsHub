package interface_adapter.play_game_aspects;

import java.util.List;
import use_case.playgame.FetchPlayerStatisticsOutputBoundary;
import use_case.playgame.FetchPlayerStatisticsResponseModel;

public class FetchPlayerStatisticsPresenter implements FetchPlayerStatisticsOutputBoundary {

    @Override
    public void presentPlayerStatistics(FetchPlayerStatisticsResponseModel responseModel) {
        System.out.println("Statistics for " + responseModel.getPlayerName() + ": " + responseModel.getPlayerStatistics());
    }

    @Override
    public void presentAvailableYears(List<String> years) {
        System.out.println("Available years: " + String.join(", ", years));
    }

    @Override
    public void presentAverageStat(double average, String playerName, String year, String statType) {
        System.out.println("Average " + statType + " for " + playerName + " in " + year + ": " + average);
    }

    @Override
    public void presentError(String message) {
        System.err.println("Error: " + message);
    }
}

