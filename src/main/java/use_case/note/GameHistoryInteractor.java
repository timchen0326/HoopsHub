package use_case.game_history;

import data_access.GameHistoryDataAccessObject;

import java.util.List;

public class GameHistoryInteractor {
    private final GameHistoryDataAccessObject dataAccess;

    public GameHistoryInteractor(GameHistoryDataAccessObject dataAccess) {
        this.dataAccess = dataAccess;
    }

    public List<String> fetchGameHistory(String username) {
        return dataAccess.getGameHistory(username);
    }
}
