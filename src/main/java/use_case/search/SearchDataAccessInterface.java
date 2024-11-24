package use_case.search;

import entity.SearchResult;
import java.util.List;

public interface SearchDataAccessInterface {
    List<SearchResult> fetchData(String username);
}
