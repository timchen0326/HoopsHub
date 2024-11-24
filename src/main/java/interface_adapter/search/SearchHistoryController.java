package interface_adapter.search;

import use_case.search.SearchHistoryInteractor;
import entity.SearchHistoryRecord;

import java.util.List;

/**
 * Controller for handling search history interactions.
 */
public class SearchHistoryController {
    private final SearchHistoryInteractor interactor;

    public SearchHistoryController(SearchHistoryInteractor interactor) {
        this.interactor = interactor;
    }

    /**
     * Adds a search query to the history.
     */
    public void addSearchQuery(String query) {
        interactor.addSearchQuery(query);
    }

    /**
     * Retrieves the search history.
     */
    public List<SearchHistoryRecord> getSearchHistory() {
        return interactor.getSearchHistory();
    }
}
