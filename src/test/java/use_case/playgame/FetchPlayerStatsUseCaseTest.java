package use_case.playgame;

import entity.PlayerStatistic;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class FetchPlayerStatsUseCaseTest {

    @Test
    public void testExecute() {
        FetchPlayerStatisticsInputBoundary mockInteractor = mock(FetchPlayerStatisticsInputBoundary.class);

        PlayerStatistic stat = new PlayerStatistic(25, 5, 2, 6, 0.56, 15, 8, 0.53,
                4, 5, 0.8, 82, 82, 1, 2400, 2, 3,
                "123", "LeBron James", 2000, "SF", 2023, 2,
                "Lakers", 100, 40, 0.4, 600, 30, 200, 100, 0.5);

        when(mockInteractor.fetchPlayerStatisticsByYear("LeBron James", 2023)).thenReturn(stat);

        FetchPlayerStatsUseCase useCase = new FetchPlayerStatsUseCase(mockInteractor);

        PlayerStatistic result = useCase.execute("LeBron James", "2023");
        assertNotNull(result);
        assertEquals("LeBron James", result.getPlayerName());
        assertEquals(2023, result.getSeason());
    }
}
