
import org.junit.jupiter.api.Test;
import use_case.search.SearchHistoryInputData;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for SearchHistoryInputData.
 */
public class SearchHistoryInputDataTest {

    @Test
    public void testConstructorAndGetters() {
        // Arrange
        String expectedQuery = "example search";
        LocalDateTime expectedTimestamp = LocalDateTime.of(2024, 11, 28, 14, 30);

        // Act
        SearchHistoryInputData inputData = new SearchHistoryInputData(expectedQuery, expectedTimestamp);

        // Assert
        assertEquals(expectedQuery, inputData.getQuery(), "The query should match the input value.");
        assertEquals(expectedTimestamp, inputData.getTimestamp(), "The timestamp should match the input value.");
    }

    @Test
    public void testNullQuery() {
        // Arrange
        String nullQuery = null;
        LocalDateTime timestamp = LocalDateTime.now();

        // Act
        SearchHistoryInputData inputData = new SearchHistoryInputData(nullQuery, timestamp);

        // Assert
        assertNull(inputData.getQuery(), "The query should be null.");
        assertNotNull(inputData.getTimestamp(), "The timestamp should not be null.");
    }

    @Test
    public void testNullTimestamp() {
        // Arrange
        String query = "example search";
        LocalDateTime nullTimestamp = null;

        // Act
        SearchHistoryInputData inputData = new SearchHistoryInputData(query, nullTimestamp);

        // Assert
        assertNotNull(inputData.getQuery(), "The query should not be null.");
        assertNull(inputData.getTimestamp(), "The timestamp should be null.");
    }

    @Test
    public void testEmptyQuery() {
        // Arrange
        String emptyQuery = "";
        LocalDateTime timestamp = LocalDateTime.now();

        // Act
        SearchHistoryInputData inputData = new SearchHistoryInputData(emptyQuery, timestamp);

        // Assert
        assertEquals(emptyQuery, inputData.getQuery(), "The query should be empty.");
        assertNotNull(inputData.getTimestamp(), "The timestamp should not be null.");
    }
}
