package app;

import data_access.PlayerStatisticsRepositoryImpl;
import interface_adapter.PlayGameAspects.PlayGameController;
import use_case.playgame.FetchPlayerStatisticsInteractor;
import use_case.playgame.FetchPlayerStatisticsInputBoundary;

public class PlayGameUseCaseFactory {
    public static PlayGameController createController() {
        PlayerStatisticsRepositoryImpl repository = new PlayerStatisticsRepositoryImpl();
        FetchPlayerStatisticsInputBoundary interactor = new FetchPlayerStatisticsInteractor(repository);
        return new PlayGameController(interactor);

    }
}