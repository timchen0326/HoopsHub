package app;

import data_access.PlayerStatisticsAPI;
import interface_adapter.PlayGameController;
import use_case.play_game.FetchPlayerStatisticsInteractor;

public class PlayGameUseCaseFactory {
    public PlayGameController createController() {
        PlayerStatisticsAPI api = new PlayerStatisticsAPI();
        FetchPlayerStatisticsInteractor interactor = new FetchPlayerStatisticsInteractor(api);
        return new PlayGameController(interactor);
    }
}
