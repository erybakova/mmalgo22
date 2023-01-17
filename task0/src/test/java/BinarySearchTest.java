import java.io.*;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Assertions;

public class BinarySearchTest {
    @Test
    @DisplayName("Test BinarySearch.find for array of size 10")
    public void testBinarySearchInEvenSizeArray() {
        int[] a = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };

        for (int i = 1; i <= a.length; ++i) {
            int res = BinarySearch.find(a, i);
            Assertions.assertTrue(res >= 0);
            Assertions.assertEquals(res, i - 1);
        }
    }

    @Test
    @DisplayName("Test BinarySearch.find for array of size 11")
    public void testBinarySearchInOddSizeArray() {
        int[] a = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 };

        for (int i = 1; i <= a.length; ++i) {
            int res = BinarySearch.find(a, i);
            Assertions.assertTrue(res >= 0);
            Assertions.assertEquals(i - 1, res);
        }
    }

    @Test
    @DisplayName("Test BinarySearch.find for array with repeating items")
    public void testBinarySearchOnRepeatingItems() {
        int[] a = { 1, 1, 3, 3, 5, 5, 7, 7, 9, 9 };

        for (int i = 1; i <= 5; i += 2) {
            int res = BinarySearch.find(a, i);
            Assertions.assertTrue(res >= i - 1 && res <= i);
        }
    }

    @Test
    @DisplayName("Test BinarySearch.find on missing items")
    public void testBinarySearchOnMissingItems() {
        int[] a = { 1, 2, 3, 4, 5, 6, 7, 9, 10, 11 };
        int res;

        res = BinarySearch.find(a, 0);
        Assertions.assertTrue(res < 0);

        res = BinarySearch.find(a, 12);
        Assertions.assertTrue(res < 0);

        res = BinarySearch.find(a, 8);
        Assertions.assertTrue(res < 0);
    }
}