package interface_adapter.game_history;

import use_case.game_history.GameHistoryInteractor;

import java.util.List;

public class GameHistoryController {
    private final GameHistoryInteractor interactor;

    public GameHistoryController(GameHistoryInteractor interactor) {
        this.interactor = interactor;
    }

    public List<String> fetchGameHistory(String username) {
        return interactor.fetchGameHistory(username);
    }
}
