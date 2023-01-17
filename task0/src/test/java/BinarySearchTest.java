import java.io.*;
import java.util.Arrays;

import static org.junit.Assert.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

public class BinarySearchTest {
    @Test
    @DisplayName("Test BinarySearch.find for array of size 10")
    public void testBinarySearchInEvenSizeArray() {
        int a[] = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };

        for (int i = 1; i <= a.length; ++i) {
            int res = BinarySearch.find(a, i);
            assertTrue(res >= 0);
            assertEquals(res, i - 1);
        }
    }

    @Test
    @DisplayName("Test BinarySearch.find for array of size 11")
    public void testBinarySearchInOddSizeArray() {
        int a[] = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 };

        for (int i = 1; i <= a.length; ++i) {
            int res = BinarySearch.find(a, i);
            assertTrue(res >= 0);
            assertEquals(res, i - 1);
        }
    }

    @Test
    @DisplayName("Test BinarySearch.find on missing items")
    public void testBinarySearchOnMissingItems() {
        int a[] = { 1, 2, 3, 4, 5, 6, 7, 9, 10, 11 };
        int res;

        res = BinarySearch.find(a, 0);
        assertTrue(res < 0);

        res = BinarySearch.find(a, 12);
        assertTrue(res < 0);

        res = BinarySearch.find(a, 8);
        assertTrue(res < 0);
    }
}