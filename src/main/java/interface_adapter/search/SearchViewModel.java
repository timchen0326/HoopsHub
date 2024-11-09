package interface_adapter.search;

import entity.SearchResult;
import java.util.List;

public class SearchViewModel {
    public String formatResults(List<SearchResult> results) {
        if (results.isEmpty()) {
            return "No results found.";
        }
        StringBuilder builder = new StringBuilder();
        for (SearchResult result : results) {
            builder.append(result.toString()).append("\n\n");
        }
        return builder.toString();
    }
}
