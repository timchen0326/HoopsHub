package use_case.note;

import java.util.List;
import interface_adapter.PlayerStatisticsRepository;


public class FetchPlayerStatisticsInteractor implements use_case.note.FetchPlayerStatisticsInputBoundary {
    private final PlayerStatisticsRepository repository;

    public FetchPlayerStatisticsInteractor(PlayerStatisticsRepository repository) {
        this.repository = repository;
    }

    @Override
    public String fetchPlayerStatistics(String playerName) {
        return repository.fetchAllStatisticsForPlayer(playerName);
    }

    @Override
    public List<String> getAvailableYears(String playerName) {
        return repository.fetchAvailableYearsForPlayer(playerName);
    }

    @Override
    public String fetchPlayerStatisticsByYear(String playerName, int year) {
        return repository.fetchStatsForPlayerByYear(playerName, year);
    }
}
