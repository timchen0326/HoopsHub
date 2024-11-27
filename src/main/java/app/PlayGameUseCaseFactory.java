package app;

import data_access.PlayerStatisticsRepositoryImpl;
import interface_adapter.play_game_aspects.FetchPlayerStatisticsPresenter;
import interface_adapter.play_game_aspects.PlayGameController;
import use_case.playgame.FetchPlayerStatisticsInputBoundary;
import use_case.playgame.FetchPlayerStatisticsInteractor;
import use_case.playgame.FetchPlayerStatsUseCase;
import use_case.playgame.FetchPlayerYearsUseCase;
import use_case.playgame.GetAverageStatUseCase;

/**
 * Factory class for creating instances of PlayGameController.
 */
public class PlayGameUseCaseFactory {

    /**
     * Creates and returns an instance of PlayGameController.
     *
     * @return a new instance of PlayGameController
     */
    public static PlayGameController createController() {
        // Step 1: Initialize the repository
        final PlayerStatisticsRepositoryImpl repository = new PlayerStatisticsRepositoryImpl();

        // Step 2: Initialize the presenter (OutputBoundary)
        final FetchPlayerStatisticsPresenter presenter = new FetchPlayerStatisticsPresenter();

        // Step 3: Initialize the interactor (FetchPlayerStatisticsInputBoundary)
        final FetchPlayerStatisticsInputBoundary interactor = new FetchPlayerStatisticsInteractor(
                repository, presenter);

        // Step 4: Create use cases
        final FetchPlayerYearsUseCase fetchPlayerYearsUseCase = new FetchPlayerYearsUseCase(interactor);
        final FetchPlayerStatsUseCase fetchPlayerStatsUseCase = new FetchPlayerStatsUseCase(interactor);
        final GetAverageStatUseCase getAverageStatUseCase = new GetAverageStatUseCase(interactor);

        // Step 5: Initialize and return the PlayGameController
        return new PlayGameController(fetchPlayerYearsUseCase, fetchPlayerStatsUseCase, getAverageStatUseCase);
    }
}
