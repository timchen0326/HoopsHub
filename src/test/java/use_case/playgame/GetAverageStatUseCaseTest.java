package use_case.playgame;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class GetAverageStatUseCaseTest {

    @Test
    public void testExecute() {
        FetchPlayerStatisticsInputBoundary mockInteractor = mock(FetchPlayerStatisticsInputBoundary.class);

        when(mockInteractor.getAverageStat("LeBron James", "2023", "Average Points")).thenReturn(30.5);

        GetAverageStatUseCase useCase = new GetAverageStatUseCase(mockInteractor);

        double result = useCase.execute("Average Points", "LeBron James", "2023");

        assertEquals(30.5, result, 0.001);
    }
}
