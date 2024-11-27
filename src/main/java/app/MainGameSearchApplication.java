package app;

import data_access.DBSearchDataAccessObject;
import interface_adapter.play_game_aspects.PlayGameController;
import interface_adapter.search.SearchViewModel;
import use_case.search.SearchInteractor;
import view.MainFrame;

/**
 * MainGameSearchApplication is the entry point of the application.
 * It initializes all dependencies and starts the main frame.
 */
public class MainGameSearchApplication {

    /**
     * The main method to start the application.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {

        // Step 2: Initialize PlayGameController using PlayGameUseCaseFactory
        final PlayGameController playGameController = PlayGameUseCaseFactory.createController();

        // Step 3: Initialize dependencies for SearchInteractor
        final DBSearchDataAccessObject dbSearchDataAccess = new DBSearchDataAccessObject();
        final SearchViewModel searchViewModel = new SearchViewModel();
        final SearchInteractor searchInteractor = new SearchInteractor(dbSearchDataAccess, searchViewModel);

        // Step 5: Pass the correct arguments to MainFrame and start the application
        final MainFrame mainFrame = new MainFrame(playGameController, searchInteractor);
        mainFrame.start();
    }
}