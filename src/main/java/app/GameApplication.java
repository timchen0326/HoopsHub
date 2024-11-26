package app;

import data_access.DBSearchDataAccessObject;
import interface_adapter.play_game_aspects.PlayGameController;
import interface_adapter.search.SearchViewModel;
import use_case.search.SearchInteractor;
import view.MainFrame;

public class GameApplication {
    public static void main(String[] args) {
        // Step 1: Initialize dependencies for SearchInteractor
        DBSearchDataAccessObject searchDataAccess = new DBSearchDataAccessObject();
        SearchViewModel searchViewModel = new SearchViewModel();
        SearchInteractor searchInteractor = new SearchInteractor(searchDataAccess, searchViewModel);

        // Step 2: Create PlayGameController
        PlayGameController playGameController = new PlayGameUseCaseFactory().createController();

        // Step 3: Initialize MainFrame with both dependencies
        MainFrame mainFrame = new MainFrame(playGameController, searchInteractor);

        // Step 4: Start MainFrame
        mainFrame.start();
    }
}
