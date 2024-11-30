package use_case.search;

/**
 * Request model for search operations.
 */
public class SearchRequestModel {
    private final String username;

    /**
     * Constructs a SearchRequestModel with the specified username.
     *
     * @param username the username to search for
     */
    public SearchRequestModel(String username) {
        this.username = username;
    }

    /**
     * Returns the username for the search operation.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }
}

