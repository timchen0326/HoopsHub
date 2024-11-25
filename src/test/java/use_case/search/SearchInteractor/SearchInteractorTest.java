import static org.junit.jupiter.api.Assertions.assertEquals;

import data_access.DBSearchDataAccessObject;
import entity.SearchResult;
import interface_adapter.search.SearchViewModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.search.SearchInteractor;

import java.util.ArrayList;
import java.util.List;

public class SearchInteractorTest {
    private SearchInteractor searchInteractor;
    private StubDBSearchDataAccessObject stubDataAccess;
    private StubSearchViewModel stubViewModel;

    @BeforeEach
    public void setUp() {
        // Initialize stub implementations
        stubDataAccess = new StubDBSearchDataAccessObject();
        stubViewModel = new StubSearchViewModel();

        // Initialize the interactor with the stubs
        searchInteractor = new SearchInteractor(stubDataAccess, stubViewModel);
    }

    @Test
    public void testExecuteSearch_ReturnsFormattedResults() {
        // Add test data to the stub data access
        stubDataAccess.addSearchResult(new SearchResult("testUser", "1", 9, 7));
        stubDataAccess.addSearchResult(new SearchResult("anotherUser", "2", 15, 5));

        // Call the method under test
        String result = searchInteractor.executeSearch("testUser");

        // Verify the result
        String expectedResult = "Results:\nUsername: testUser\nUserID: 1\nWins: 9\nLosses: 7\n";
        assertEquals(expectedResult, result);
    }

    @Test
    public void testExecuteSearch_NoResults() {
        // Ensure no data is added to the stub

        // Call the method under test
        String result = searchInteractor.executeSearch("nonExistentUser");

        // Verify the result
        String expectedResult = "No results found.";
        assertEquals(expectedResult, result);
    }

    // Stub for DBSearchDataAccessObject
    private static class StubDBSearchDataAccessObject extends DBSearchDataAccessObject {
        private final List<SearchResult> database = new ArrayList<>();

        @Override
        public List<SearchResult> fetchData(String username) {
            List<SearchResult> results = new ArrayList<>();
            for (SearchResult result : database) {
                if (result.getUsername().equals(username)) {
                    results.add(result);
                }
            }
            return results;
        }

        public void addSearchResult(SearchResult result) {
            database.add(result);
        }
    }

    // Stub for SearchViewModel
    private static class StubSearchViewModel extends SearchViewModel {
        @Override
        public String formatResults(List<SearchResult> results) {
            if (results.isEmpty()) {
                return "No results found.";
            }
            StringBuilder sb = new StringBuilder("Results:\n");
            for (SearchResult result : results) {
                sb.append(result.toString()).append("\n");
            }
            return sb.toString();
        }
    }
}
