package view;

import java.util.Scanner;

import use_case.search.SearchInteractor;
import use_case.search.SearchRequestModel;

/**
 * View for searching user data and displaying results via the console.
 */
public class SearchView {

    private final SearchInteractor interactor;

    /**
     * Constructs a SearchView with the specified interactor.
     *
     * @param interactor the interactor responsible for executing searches; must not be null
     */
    public SearchView(SearchInteractor interactor) {
        this.interactor = interactor;
    }

    /**
     * Runs the search view, allowing the user to input a username and view search results.
     */
    public void run() {
        final Scanner scanner = new Scanner(System.in);
        System.out.print("Enter username to search: ");
        final String username = scanner.nextLine();

        // Create a SearchRequestModel using the entered username
        final SearchRequestModel requestModel = new SearchRequestModel(username);

        // Execute the search using the interactor
        interactor.executeSearch(requestModel);
    }
}
