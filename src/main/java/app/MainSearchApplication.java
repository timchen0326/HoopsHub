package app;

import data_access.DBSearchDataAccessObject;
import data_access.PlayerStatisticsRepositoryImpl;
import interface_adapter.play_game_aspects.PlayGameController;
import interface_adapter.search.SearchViewModel;
import use_case.playgame.FetchPlayerStatisticsInteractor;
import use_case.playgame.FetchPlayerStatsUseCase;
import use_case.playgame.FetchPlayerYearsUseCase;
import use_case.playgame.GetAverageStatUseCase;
import use_case.search.SearchInteractor;
import view.MainFrame;

/**
 * The MainSearchApplication class is the entry point for the application.
 * It initializes all the components and starts the main frame.
 */
public class MainSearchApplication {

    /**
     * The main method to start the application.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        // Step 1: Initialize dependencies for SearchInteractor
        final DBSearchDataAccessObject dataAccess = new DBSearchDataAccessObject();
        final SearchViewModel viewModel = new SearchViewModel();
        final SearchInteractor searchInteractor = new SearchInteractor(dataAccess, viewModel);

        // Step 2: Initialize Player Statistics Repository and Interactor
        final PlayerStatisticsRepositoryImpl playerStatisticsRepository = new PlayerStatisticsRepositoryImpl();
        final FetchPlayerStatisticsInteractor interactor =
                new FetchPlayerStatisticsInteractor(playerStatisticsRepository);

        // Step 3: Initialize Use Cases for PlayGameController
        final FetchPlayerYearsUseCase fetchPlayerYearsUseCase = new FetchPlayerYearsUseCase(interactor);
        final FetchPlayerStatsUseCase fetchPlayerStatsUseCase = new FetchPlayerStatsUseCase(interactor);
        final GetAverageStatUseCase getAverageStatUseCase = new GetAverageStatUseCase(interactor);

        // Step 4: Initialize PlayGameController with the use cases
        final PlayGameController playGameController = new PlayGameController(
                fetchPlayerYearsUseCase,
                fetchPlayerStatsUseCase,
                getAverageStatUseCase
        );

        // Step 5: Initialize MainFrame
        final MainFrame mainFrame = new MainFrame(playGameController, searchInteractor);

        // Step 6: Start the MainFrame
        mainFrame.start();
    }
}
