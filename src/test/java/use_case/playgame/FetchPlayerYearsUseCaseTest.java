package use_case.playgame;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class FetchPlayerYearsUseCaseTest {

    @Test
    public void testExecute() {
        FetchPlayerStatisticsInputBoundary mockInteractor = mock(FetchPlayerStatisticsInputBoundary.class);

        List<String> mockYears = Arrays.asList("2020", "2021", "2022");
        when(mockInteractor.getAvailableYears("LeBron James")).thenReturn(mockYears);

        FetchPlayerYearsUseCase useCase = new FetchPlayerYearsUseCase(mockInteractor);

        List<String> result = useCase.execute("LeBron James");

        assertNotNull(result);
        assertEquals(3, result.size());
        assertEquals("2020", result.get(0));
    }
}
