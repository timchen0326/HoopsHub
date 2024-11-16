package app;

import data_access.DBSearchDataAccessObject;
import interface_adapter.PlayGameController;
import interface_adapter.search.SearchViewModel;
import use_case.note.search.SearchInteractor;
import view.MainFrame;

public class MainSearchApplication {

    public static void main(String[] args) {
        // Step 1: Initialize dependencies for SearchInteractor
        DBSearchDataAccessObject dataAccess = new DBSearchDataAccessObject();
        SearchViewModel viewModel = new SearchViewModel();
        SearchInteractor searchInteractor = new SearchInteractor(dataAccess, viewModel);

        // Step 2: Initialize PlayGameController
        PlayGameController playGameController = new PlayGameController(null); // Pass required dependencies

        // Step 3: Initialize MainFrame
        MainFrame mainFrame = new MainFrame(playGameController, searchInteractor);

        // Step 4: Start the MainFrame
        mainFrame.start();
    }
}
