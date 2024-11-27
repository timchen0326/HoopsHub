package view;

import java.util.Scanner;

import use_case.search.SearchInteractor;

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
        final String result = interactor.executeSearch(username);
        System.out.println(result);
    }
}
