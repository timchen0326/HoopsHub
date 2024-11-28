package interface_adapter.search;

import use_case.search.SearchHistoryOutputBoundary;
import use_case.search.SearchHistoryOutputData;

import java.util.List;

/**
 * Presents the search history data to the view.
 */
public class SearchHistoryPresenter implements SearchHistoryOutputBoundary {

    private final SearchHistoryView view;

    /**
     * Constructs a SearchHistoryPresenter with the given view.
     *
     * @param view the view for displaying the search history
     */
    public SearchHistoryPresenter(SearchHistoryView view) {
        this.view = view;
    }

    @Override
    public void presentSearchHistory(List<SearchHistoryOutputData> history) {
        if (history == null || history.isEmpty()) {
            view.updateHistoryView("No search history available.");
            return;
        }

        // Convert output data into a viewable format and pass it to the view
        StringBuilder displayText = new StringBuilder("Search History:\n");
        for (SearchHistoryOutputData record : history) {
            if (record != null) {
                displayText.append(record.getQuery()).append(" | ")
                        .append(record.getTimestamp()).append("\n");
            }
        }

        // Check if the list was empty after filtering out null records
        if (displayText.length() == "Search History:\n".length()) {
            view.updateHistoryView("No valid search history available.");
        } else {
            view.updateHistoryView(displayText.toString());
        }
    }
}
