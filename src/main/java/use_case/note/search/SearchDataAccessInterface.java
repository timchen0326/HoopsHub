package use_case.note.search;

import entity.SearchResult;
import java.util.List;

public interface SearchDataAccessInterface {
    List<SearchResult> fetchData(String username);
}
