package view;

import use_case.search.SearchInteractor;
import java.util.Scanner;

public class SearchView {
    private final SearchInteractor interactor;

    public SearchView(SearchInteractor interactor) {
        this.interactor = interactor;
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter username to search: ");
        String username = scanner.nextLine();
        String result = interactor.executeSearch(username);
        System.out.println(result);
    }
}
