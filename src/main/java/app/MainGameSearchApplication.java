package app;

import data_access.DBSearchDataAccessObject;
import data_access.SearchHistoryDataAccessObject;
import data_access.PlayerStatisticsRepositoryImpl;
import interface_adapter.PlayGameAspects.PlayGameController;
import interface_adapter.search.SearchHistoryController;
import interface_adapter.search.SearchViewModel;
import use_case.playgame.FetchPlayerStatisticsInteractor;
import use_case.search.SearchHistoryInteractor;
import use_case.search.SearchInteractor;
import view.MainFrame;

public class MainGameSearchApplication {
    public static void main(String[] args) {
        // Initialize dependencies for FetchPlayerStatisticsInteractor
        PlayerStatisticsRepositoryImpl playerStatisticsRepository = new PlayerStatisticsRepositoryImpl(); // Replace with actual implementation
        FetchPlayerStatisticsInteractor fetchStatsBoundary = new FetchPlayerStatisticsInteractor(playerStatisticsRepository);
        PlayGameController playGameController = PlayGameUseCaseFactory.createController();

        // Initialize dependencies for SearchInteractor
        DBSearchDataAccessObject dbSearchDataAccess = new DBSearchDataAccessObject(); // Implements SearchDataAccessInterface
        SearchViewModel searchViewModel = new SearchViewModel(); // Create an instance of SearchViewModel
        SearchInteractor searchInteractor = new SearchInteractor(dbSearchDataAccess, searchViewModel); // Pass both dependencies

        // Initialize dependencies for SearchHistoryController
        SearchHistoryDataAccessObject searchHistoryDataAccess = new SearchHistoryDataAccessObject();
        SearchHistoryInteractor searchHistoryInteractor = new SearchHistoryInteractor(searchHistoryDataAccess);
        SearchHistoryController searchHistoryController = new SearchHistoryController(searchHistoryInteractor);

        // Pass the correct arguments to MainFrame
        MainFrame mainFrame = new MainFrame(playGameController, searchInteractor);
        mainFrame.start();
    }
}
