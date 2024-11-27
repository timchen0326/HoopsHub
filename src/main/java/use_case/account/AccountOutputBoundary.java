package use_case.account;

/**
 * The AccountOutputBoundary defines the interface for handling outputs
 * related to account operations, such as success or failure messages.
 */

public interface AccountOutputBoundary {

    /**
     * Prepares the view to display a success message.
     *
     * @param message the success message to display, must not be null
     */
    void prepareSuccessView(String message);

    /**
     * Prepares the view to display an error message.
     *
     * @param errorMessage the error message to display, must not be null
     */
    void prepareFailView(String errorMessage);
}
