package app;

import data_access.GameHistoryDataAccessObject;
import interface_adapter.game_history.GameHistoryController;
import interface_adapter.game_history.GameHistoryPresenter;
import use_case.game_history.GameHistoryInteractor;
import view.GameHistoryPanel;
import view.HomePanel;
import view.MainFrame;

public class MainApplication {
    public static void main(String[] args) {
        // Initialize GameHistory components
        GameHistoryDataAccessObject dataAccess = new GameHistoryDataAccessObject();
        GameHistoryPresenter presenter = new GameHistoryPresenter();
        GameHistoryInteractor interactor = new GameHistoryInteractor(dataAccess);
        GameHistoryController controller = new GameHistoryController(interactor);

        // Initialize MainFrame and start the application
        MainFrame mainFrame = new MainFrame(controller);
        mainFrame.start();
    }
}
