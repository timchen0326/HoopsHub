package app;

import data_access.PlayerStatisticsRepositoryImpl;
import interface_adapter.PlayGameAspects.PlayGameController;
import use_case.playgame.FetchPlayerStatisticsInteractor;
import use_case.playgame.FetchPlayerStatisticsInputBoundary;
import use_case.playgame.FetchPlayerYearsUseCase;
import use_case.playgame.FetchPlayerStatsUseCase;
import use_case.playgame.GetAverageStatUseCase;

public class PlayGameUseCaseFactory {
    public static PlayGameController createController() {
        // Step 1: Initialize the repository
        PlayerStatisticsRepositoryImpl repository = new PlayerStatisticsRepositoryImpl();

        // Step 2: Initialize the interactor (FetchPlayerStatisticsInputBoundary)
        FetchPlayerStatisticsInputBoundary interactor = new FetchPlayerStatisticsInteractor(repository);

        // Step 3: Create use cases
        FetchPlayerYearsUseCase fetchPlayerYearsUseCase = new FetchPlayerYearsUseCase(interactor);
        FetchPlayerStatsUseCase fetchPlayerStatsUseCase = new FetchPlayerStatsUseCase(interactor);
        GetAverageStatUseCase getAverageStatUseCase = new GetAverageStatUseCase(interactor);

        // Step 4: Initialize and return the PlayGameController
        return new PlayGameController(fetchPlayerYearsUseCase, fetchPlayerStatsUseCase, getAverageStatUseCase);
    }
}
