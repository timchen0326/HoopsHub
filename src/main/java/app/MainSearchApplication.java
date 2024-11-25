package app;

import data_access.DBSearchDataAccessObject;
import data_access.PlayerStatisticsRepositoryImpl;
import interface_adapter.PlayGameAspects.PlayGameController;
import interface_adapter.search.SearchViewModel;
import use_case.playgame.FetchPlayerStatisticsInteractor;
import use_case.playgame.FetchPlayerStatsUseCase;
import use_case.playgame.FetchPlayerYearsUseCase;
import use_case.playgame.GetAverageStatUseCase;
import use_case.search.SearchInteractor;
import view.MainFrame;

public class MainSearchApplication {

    public static void main(String[] args) {
        // Step 1: Initialize dependencies for SearchInteractor
        DBSearchDataAccessObject dataAccess = new DBSearchDataAccessObject();
        SearchViewModel viewModel = new SearchViewModel();
        SearchInteractor searchInteractor = new SearchInteractor(dataAccess, viewModel);

        // Step 2: Initialize Player Statistics Repository and Interactor
        PlayerStatisticsRepositoryImpl playerStatisticsRepository = new PlayerStatisticsRepositoryImpl();
        FetchPlayerStatisticsInteractor interactor = new FetchPlayerStatisticsInteractor(playerStatisticsRepository);

        // Step 3: Initialize Use Cases for PlayGameController
        FetchPlayerYearsUseCase fetchPlayerYearsUseCase = new FetchPlayerYearsUseCase(interactor);
        FetchPlayerStatsUseCase fetchPlayerStatsUseCase = new FetchPlayerStatsUseCase(interactor);
        GetAverageStatUseCase getAverageStatUseCase = new GetAverageStatUseCase(interactor);

        // Step 4: Initialize PlayGameController with the use cases
        PlayGameController playGameController = new PlayGameController(
                fetchPlayerYearsUseCase,
                fetchPlayerStatsUseCase,
                getAverageStatUseCase
        );

        // Step 3: Initialize MainFrame
        MainFrame mainFrame = new MainFrame(playGameController, searchInteractor);

        // Step 4: Start the MainFrame
        mainFrame.start();
    }
}