package use_case.playgame;

import entity.PlayerStatistic;
import interface_adapter.PlayGameAspects.PlayerStatisticsRepository;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class FetchPlayerStatisticsInteractorTest {

    @Test
    public void testFetchPlayerStatistics() {
        PlayerStatisticsRepository mockRepo = mock(PlayerStatisticsRepository.class);
        FetchPlayerStatisticsInteractor interactor = new FetchPlayerStatisticsInteractor(mockRepo);

        PlayerStatistic stat1 = new PlayerStatistic(25, 5, 2, 6, 0.56, 15, 8, 0.53,
                4, 5, 0.8, 82, 82, 1, 2400, 2, 3,
                "123", "LeBron James", 2000, "SF", 2023, 2,
                "Lakers", 100, 40, 0.4, 600, 30, 200, 100, 0.5);

        PlayerStatistic stat2 = new PlayerStatistic(26, 6, 3, 7, 0.58, 18, 10, 0.55,
                5, 6, 0.83, 80, 80, 2, 2450, 3, 4,
                "124", "Anthony Davis", 2200, "PF", 2023, 3,
                "Lakers", 120, 50, 0.42, 700, 40, 250, 120, 0.48);

        when(mockRepo.fetchAllStatisticsForPlayer("LeBron James")).thenReturn(Arrays.asList(stat1, stat2));

        List<PlayerStatistic> result = interactor.fetchPlayerStatistics("LeBron James");

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("LeBron James", result.get(0).getPlayerName());
    }

    @Test
    public void testFetchPlayerStatisticsByYear() {
        PlayerStatisticsRepository mockRepo = mock(PlayerStatisticsRepository.class);
        FetchPlayerStatisticsInteractor interactor = new FetchPlayerStatisticsInteractor(mockRepo);

        PlayerStatistic stat = new PlayerStatistic(25, 5, 2, 6, 0.56, 15, 8, 0.53,
                4, 5, 0.8, 82, 82, 1, 2400, 2, 3,
                "123", "LeBron James", 2000, "SF", 2023, 2,
                "Lakers", 100, 40, 0.4, 600, 30, 200, 100, 0.5);

        when(mockRepo.fetchStatsForPlayerByYear("LeBron James", 2023)).thenReturn(stat);

        PlayerStatistic result = interactor.fetchPlayerStatisticsByYear("LeBron James", 2023);

        assertNotNull(result);
        assertEquals("LeBron James", result.getPlayerName());
        assertEquals(2023, result.getSeason());
    }

    @Test
    public void testFetchPlayerStatisticsByYearNullReturn() {
        PlayerStatisticsRepository mockRepo = mock(PlayerStatisticsRepository.class);
        FetchPlayerStatisticsInteractor interactor = new FetchPlayerStatisticsInteractor(mockRepo);

        when(mockRepo.fetchStatsForPlayerByYear("Unknown Player", 2023)).thenReturn(null);

        PlayerStatistic result = interactor.fetchPlayerStatisticsByYear("Unknown Player", 2023);

        assertNull(result);
    }

    @Test
    public void testGetAvailableYears() {
        PlayerStatisticsRepository mockRepo = mock(PlayerStatisticsRepository.class);
        FetchPlayerStatisticsInteractor interactor = new FetchPlayerStatisticsInteractor(mockRepo);

        when(mockRepo.fetchAvailableYearsForPlayer("LeBron James")).thenReturn(Arrays.asList(2020, 2021, 2022));

        List<String> result = interactor.getAvailableYears("LeBron James");

        assertNotNull(result);
        assertEquals(3, result.size());
        assertEquals("2020", result.get(0));
        assertEquals("2021", result.get(1));
    }

    @Test
    public void testGetAvailableYearsEmptyList() {
        PlayerStatisticsRepository mockRepo = mock(PlayerStatisticsRepository.class);
        FetchPlayerStatisticsInteractor interactor = new FetchPlayerStatisticsInteractor(mockRepo);

        when(mockRepo.fetchAvailableYearsForPlayer("Unknown Player")).thenReturn(Collections.emptyList());

        List<String> result = interactor.getAvailableYears("Unknown Player");

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetAverageStatInvalidYear() {
        PlayerStatisticsRepository mockRepo = mock(PlayerStatisticsRepository.class);
        FetchPlayerStatisticsInteractor interactor = new FetchPlayerStatisticsInteractor(mockRepo);

        when(mockRepo.fetchStatsForPlayerByYear("LeBron James", 2023)).thenReturn(null);

        interactor.getAverageStat("LeBron James", "2023", "Average Points");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetAverageStatInvalidStatType() {
        PlayerStatisticsRepository mockRepo = mock(PlayerStatisticsRepository.class);
        FetchPlayerStatisticsInteractor interactor = new FetchPlayerStatisticsInteractor(mockRepo);

        PlayerStatistic stat = new PlayerStatistic(25, 5, 2, 6, 0.56, 15, 8, 0.53,
                4, 5, 0.8, 82, 82, 1, 2400, 2, 3,
                "123", "LeBron James", 2000, "SF", 2023, 2,
                "Lakers", 100, 40, 0.4, 600, 30, 200, 100, 0.5);

        when(mockRepo.fetchStatsForPlayerByYear("LeBron James", 2023)).thenReturn(stat);

        interactor.getAverageStat("LeBron James", "2023", "InvalidStatType");
    }

    @Test
    public void testCalculateAveragePoints() {
        PlayerStatisticsRepository mockRepo = mock(PlayerStatisticsRepository.class);
        FetchPlayerStatisticsInteractor interactor = new FetchPlayerStatisticsInteractor(mockRepo);

        PlayerStatistic stat = new PlayerStatistic(25, 5, 2, 6, 0.56, 15, 8, 0.53,
                4, 5, 0.8, 10, 10, 1, 2400, 2, 3,
                "123", "LeBron James", 2000, "SF", 2023, 2,
                "Lakers", 100, 40, 0.4, 600, 30, 200, 100, 0.5);

        when(mockRepo.fetchStatsForPlayerByYear("LeBron James", 2023)).thenReturn(stat);

        double avgPoints = interactor.getAverageStat("LeBron James", "2023", "Average Points");

        assertEquals(200.0, avgPoints, 0.001);
    }

    @Test
    public void testCalculateAverageRebounds() {
        PlayerStatisticsRepository mockRepo = mock(PlayerStatisticsRepository.class);
        FetchPlayerStatisticsInteractor interactor = new FetchPlayerStatisticsInteractor(mockRepo);

        PlayerStatistic stat = new PlayerStatistic(25, 5, 2, 6, 0.56, 15, 8, 0.53,
                4, 5, 0.8, 10, 10, 1, 2400, 2, 3,
                "123", "LeBron James", 2000, "SF", 2023, 2,
                "Lakers", 100, 40, 0.4, 600, 30, 200, 100, 0.5);

        when(mockRepo.fetchStatsForPlayerByYear("LeBron James", 2023)).thenReturn(stat);

        double avgRebounds = interactor.getAverageStat("LeBron James", "2023", "Average Rebounds");

        assertEquals(60.0, avgRebounds, 0.001);
    }

    @Test
    public void testCalculateAverageAssists() {
        PlayerStatisticsRepository mockRepo = mock(PlayerStatisticsRepository.class);
        FetchPlayerStatisticsInteractor interactor = new FetchPlayerStatisticsInteractor(mockRepo);

        PlayerStatistic stat = new PlayerStatistic(25, 50, 2, 6, 0.56, 15, 8, 0.53,
                4, 5, 0.8, 10, 10, 1, 2400, 2, 3,
                "123", "LeBron James", 2000, "SF", 2023, 2,
                "Lakers", 100, 40, 0.4, 600, 30, 200, 100, 0.5);

        when(mockRepo.fetchStatsForPlayerByYear("LeBron James", 2023)).thenReturn(stat);

        double avgAssists = interactor.getAverageStat("LeBron James", "2023", "Average Assists");

        assertEquals(5.0, avgAssists, 0.001);
    }
}
