package app;

import data_access.PlayerStatisticsRepositoryImpl;
import interface_adapter.PlayGameController;
import use_case.note.FetchPlayerStatisticsInteractor;
import use_case.note.FetchPlayerStatisticsInputBoundary;

public class PlayGameUseCaseFactory {
    public static PlayGameController createController() {
        PlayerStatisticsRepositoryImpl repository = new PlayerStatisticsRepositoryImpl();
        FetchPlayerStatisticsInputBoundary interactor = new FetchPlayerStatisticsInteractor(repository);
        return new PlayGameController(interactor);

    }
}