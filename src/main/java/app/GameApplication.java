package app;

import data_access.DBSearchDataAccessObject;
import interface_adapter.play_game_aspects.PlayGameController;
import use_case.search.SearchViewModel;
import use_case.search.SearchInteractor;
import view.MainFrame;

/**
 * The GameApplication class is the entry point of the application.
 * It initializes and starts the main components of the application.
 */
public class GameApplication {

    /**
     * The main method to run the application.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        // Step 1: Initialize dependencies for SearchInteractor
        final DBSearchDataAccessObject searchDataAccess = new DBSearchDataAccessObject();
        final SearchViewModel searchViewModel = new SearchViewModel();
        final SearchInteractor searchInteractor = new SearchInteractor(searchDataAccess, searchViewModel);

        // Step 2: Create PlayGameController
        final PlayGameController playGameController = new PlayGameUseCaseFactory().createController();

        // Step 3: Initialize MainFrame with both dependencies
        final MainFrame mainFrame = new MainFrame(playGameController, searchInteractor);

        // Step 4: Start MainFrame
        mainFrame.start();
    }
}
