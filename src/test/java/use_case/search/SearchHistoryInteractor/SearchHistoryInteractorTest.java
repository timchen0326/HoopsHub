import data_access.SearchHistoryDataAccessObject;
import entity.SearchHistoryRecord;
import org.junit.jupiter.api.Test;
import use_case.search.SearchHistoryInteractor;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.ArrayList;

class SearchHistoryInteractorTest {

    // In-memory implementation of SearchHistoryDataAccessObject
    static class InMemorySearchHistoryDataAccessObject extends SearchHistoryDataAccessObject {
        private final List<SearchHistoryRecord> history = new ArrayList<>();

        @Override
        public void saveSearchHistory(SearchHistoryRecord record) {
            history.add(record);
        }

        @Override
        public List<SearchHistoryRecord> getSearchHistory() {
            return history;
        }
    }

    @Test
    void testAddSearchQuery() {
        // Given: Create the in-memory DAO and the interactor
        InMemorySearchHistoryDataAccessObject dataAccess = new InMemorySearchHistoryDataAccessObject();
        SearchHistoryInteractor interactor = new SearchHistoryInteractor(dataAccess);
        String query = "Win the game";

        // Act: Add a search query
        interactor.addSearchQuery(query);

        // Assert: Verify that the search query has been saved in the in-memory DAO
        List<SearchHistoryRecord> history = dataAccess.getSearchHistory();
        assertEquals(1, history.size());  // Check that one record was added
        assertEquals("Win the game", history.get(0).getQuery());  // Check that the query is correct
    }

    @Test
    void testGetSearchHistory() {
        // Given: Create the in-memory DAO and the interactor, and add a search query
        InMemorySearchHistoryDataAccessObject dataAccess = new InMemorySearchHistoryDataAccessObject();
        SearchHistoryInteractor interactor = new SearchHistoryInteractor(dataAccess);
        interactor.addSearchQuery("Win the game");

        // Act: Retrieve the search history
        List<SearchHistoryRecord> history = interactor.getSearchHistory();

        // Assert: Verify that the history contains the correct record
        assertEquals(1, history.size());  // Check that there is one record
        assertEquals("Win the game", history.get(0).getQuery());  // Check that the query matches the added search
    }
}
