import java.util.Arrays;
import java.util.Random;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

public class QuickSortTest {
    @Test
    @DisplayName("Test QuickSort.sort for 10 Integer()'s")
    public void testQuickSortInteger10() {
        Integer [] a = { 10, 10, 4, 3, 2, 1, 0, -2, 125, -10 };

        SuperComparator<Integer> cmp = new SuperComparator<Integer>();
        QuickSort.sort(a, cmp);

        Assertions.assertTrue(QuickSort.isSorted(a, cmp));
    }

    @Test
    @DisplayName("Test QuickSort.sort for 100 equal Integer()'s")
    public void testQuickSortIntegerEqual() {
        Integer [] a = new Integer[100];

        for (int i = 0; i < 100; ++i)
            a[i] = 5;

        SuperComparator<Integer> cmp = new SuperComparator<Integer>();
        QuickSort.sort(a, cmp);

        Assertions.assertTrue(QuickSort.isSorted(a, cmp));
    }

    @Test
    @DisplayName("Test QuickSort.sort for 100 sorted Integer()'s")
    public void testQuickSortIntegerSorted() {
        Integer [] a = new Integer[100];

        for (int i = 0; i < 100; ++i)
            a[i] = i + 1;

        SuperComparator<Integer> cmp = new SuperComparator<Integer>();
        QuickSort.sort(a, cmp);

        Assertions.assertTrue(QuickSort.isSorted(a, cmp));
    }

    @Test
    @DisplayName("Test QuickSort.sort for 100 inverse sorted Integer()'s")
    public void testQuickSortIntegerInvSorted() {
        Integer [] a = new Integer[100];

        for (int i = 0; i < 100; ++i)
            a[i] = 100 - i;

        SuperComparator<Integer> cmp = new SuperComparator<Integer>();
        QuickSort.sort(a, cmp);

        Assertions.assertTrue(QuickSort.isSorted(a, cmp));
    }

    void initArrayOfIntegerRandom(Integer [] a) {
        Random random = new Random(123);
        for (int i = 0; i < a.length; ++i)
            a[i] = random.nextInt();
    }

    public void testSortingComparison(int n) {
        Integer [] a = new Integer[n];

        SuperComparator<Integer> cmp = new SuperComparator<Integer>();

        initArrayOfIntegerRandom(a);
        QuickSort.sort(a, cmp);
        Assertions.assertTrue(QuickSort.isSorted(a, cmp));
        System.out.println("Number of comparisons on array of size " + n + " (QuickSort.sort): " + cmp.getCountCmp());

        initArrayOfIntegerRandom(a);
        Arrays.sort(a, cmp);
        System.out.println("Number of comparisons on array of size " + n + " (Arrays.sort): " + cmp.getCountCmp() + "\n");
    }

    @Test
    @DisplayName("Test QuickSort.sort and Arrays.sort for 10, 100, ..., 10^6 Integer()'s")
    public void testQuickSortBig() {
        for (int n = 1; n <= 1000000; n *= 10)
            testSortingComparison(n);
    }
}