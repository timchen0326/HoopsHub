import entity.SearchResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import use_case.search.*;

class SearchInteractorTest {

    private SearchDataAccessInterface mockDataAccess;
    private SearchOutputBoundary mockOutputBoundary;
    private SearchInteractor interactor;

    @BeforeEach
    void setUp() {
        mockDataAccess = Mockito.mock(SearchDataAccessInterface.class);
        mockOutputBoundary = Mockito.mock(SearchOutputBoundary.class);
        interactor = new SearchInteractor(mockDataAccess, mockOutputBoundary);
    }

    @Test
    void testExecuteSearch_WithValidSearchResult() {
        // Arrange
        String username = "john_doe";
        SearchRequestModel requestModel = new SearchRequestModel(username);
        SearchResult validResult = new SearchResult("john_doe", "1234", 10, 2);
        List<SearchResult> mockResults = List.of(validResult);
        when(mockDataAccess.fetchData(username)).thenReturn(mockResults);

        // Act
        interactor.executeSearch(requestModel);

        // Assert
        verify(mockDataAccess).fetchData(username);
        verify(mockOutputBoundary).presentResults(argThat(outputData -> {
            assertNotNull(outputData);
            assertEquals(1, outputData.getResults().size());
            assertEquals(validResult.toString(), outputData.getResults().get(0));
            return true;
        }));
    }

    @Test
    void testExecuteSearch_NoResults() {
        // Arrange
        String username = "unknown_user";
        SearchRequestModel requestModel = new SearchRequestModel(username);
        when(mockDataAccess.fetchData(username)).thenReturn(List.of());

        // Act
        interactor.executeSearch(requestModel);

        // Assert
        verify(mockDataAccess).fetchData(username);
        verify(mockOutputBoundary).presentResults(argThat(outputData -> {
            assertNotNull(outputData);
            assertTrue(outputData.getResults().isEmpty());
            return true;
        }));
    }
}
