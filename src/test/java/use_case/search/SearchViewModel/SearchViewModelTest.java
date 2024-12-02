import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import use_case.search.SearchOutputData;
import use_case.search.SearchViewModel;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SearchViewModelTest {

    private SearchViewModel viewModel;

    @BeforeEach
    void setUp() {
        viewModel = new SearchViewModel();
    }

    @Test
    void testPresentResults_WithEmptyResults() {
        // Arrange
        SearchOutputData outputData = new SearchOutputData(List.of());

        // Act
        viewModel.presentResults(outputData);

        // Assert
        assertEquals("No results found.", viewModel.getFormattedResults());
    }

    @Test
    void testPresentResults_WithMultipleResults() {
        // Arrange
        List<String> results = List.of(
                "Username: john_doe\nUserID: 1234\nWins: 10\nLosses: 5",
                "Username: jane_doe\nUserID: 5678\nWins: 15\nLosses: 3"
        );
        SearchOutputData outputData = new SearchOutputData(results);

        // Act
        viewModel.presentResults(outputData);

        // Assert
        String expectedFormattedResults =
                "Username: john_doe\nUserID: 1234\nWins: 10\nLosses: 5\\n\\n" +
                        "Username: jane_doe\nUserID: 5678\nWins: 15\nLosses: 3\\n\\n";

        assertEquals(expectedFormattedResults, viewModel.getFormattedResults());
    }

    @Test
    void testGetFormattedResults_BeforePresentResultsCalled() {
        // Assert
        assertNull(viewModel.getFormattedResults(),
                "Formatted results should initially be null before presentResults is called.");
    }
}
