package interface_adapter.search;

import java.util.List;

import entity.SearchHistoryRecord;
import use_case.search.SearchHistoryInteractor;

/**
 * Controller for handling search history interactions.
 */
public class SearchHistoryController {
    private final SearchHistoryInteractor interactor;

    /**
     * Constructs a SearchHistoryController with the given interactor.
     *
     * @param interactor the interactor responsible for managing search history
     */
    public SearchHistoryController(SearchHistoryInteractor interactor) {
        this.interactor = interactor;
    }

    /**
     * Adds a search query to the history.
     *
     * @param query the search query to add; must not be null
     */
    public void addSearchQuery(String query) {
        interactor.addSearchQuery(query);
    }

    /**
     * Retrieves the search history.
     *
     * @return a list of search history records
     */
    public List<SearchHistoryRecord> getSearchHistory() {
        return interactor.getSearchHistory();
    }
}
