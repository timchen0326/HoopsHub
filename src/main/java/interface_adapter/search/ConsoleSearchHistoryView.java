package interface_adapter.search;

/**
 * Concrete implementation of SearchHistoryView for a console-based application.
 */
public class ConsoleSearchHistoryView implements SearchHistoryView {

    @Override
    public void updateHistoryView(String text) {
        // For now, we are simply printing the text to the console.
        System.out.println(text);
    }
}
