package interface_adapter.game_history;

import java.util.List;

public class GameHistoryPresenter implements interface_adapter.game_history.GameHistoryOutputBoundary {
    @Override
    public void presentGameHistory(List<String> history) {
        history.forEach(System.out::println); // Simple display for now
    }
}
