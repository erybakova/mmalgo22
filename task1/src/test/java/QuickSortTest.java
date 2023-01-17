import java.io.*;
import java.util.Arrays;

import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

public class QuickSortTest {
    @Test
    @DisplayName("Test QuickSort.sort for 10 Integer()'s")
    public void testQuickSortInteger10() {
        Integer [] a = { 10, 10, 4, 3, 2, 1, 0, -2, 125, -10 };
        System.out.println(Arrays.toString(a));

        ClassComparator cmp = new ClassComparator<Integer>();
        QuickSort.sort(a, cmp);

        System.out.println(Arrays.toString(a));
        System.out.println(cmp.getCountCmp());
    }

    @Test
    @DisplayName("Test Arrays.sort of 10 Integer()'s")
    public void testStandardSortInteger10() {
        Integer [] a = { 10, 10, 4, 3, 2, 1, 0, -2, 125, -10 };
        System.out.println(Arrays.toString(a));

        ClassComparator cmp = new ClassComparator<Integer>();
        Arrays.sort(a, cmp);

        System.out.println(Arrays.toString(a));
        System.out.println(cmp.getCountCmp());
    }

    @Test
    @DisplayName("Test QuickSort.sort for 100 equal Integer()'s")
    public void testQuickSortIntegerEqual() {
        Integer [] a = new Integer[100];

        for (int i = 0; i < 100; ++i)
            a[i] = 5;
        System.out.println(Arrays.toString(a));

        ClassComparator cmp = new ClassComparator<Integer>();
        QuickSort.sort(a, cmp);

        System.out.println(Arrays.toString(a));
        System.out.println(cmp.getCountCmp());
    }

    @Test
    @DisplayName("Test Arrays.sort for 100 equal Integer()'s")
    public void testStandardSortIntegerEqual() {
        Integer [] a = new Integer[100];

        for (int i = 0; i < 100; ++i)
            a[i] = 5;
        System.out.println(Arrays.toString(a));

        ClassComparator cmp = new ClassComparator<Integer>();
        Arrays.sort(a, cmp);

        System.out.println(Arrays.toString(a));
        System.out.println(cmp.getCountCmp());
    }

    @Test
    @DisplayName("Test QuickSort.sort for 100 sorted Integer()'s")
    public void testQuickSortIntegerSorted() {
        Integer [] a = new Integer[100];

        for (int i = 0; i < 100; ++i)
            a[i] = i + 1;
        System.out.println(Arrays.toString(a));

        ClassComparator cmp = new ClassComparator<Integer>();
        QuickSort.sort(a, cmp);

        System.out.println(Arrays.toString(a));
        System.out.println(cmp.getCountCmp());
    }

    @Test
    @DisplayName("Test Arrays.sort for 100 sorted Integer()'s")
    public void testStandardSortIntegerSorted() {
        Integer [] a = new Integer[100];

        for (int i = 0; i < 100; ++i)
            a[i] = i + 1;
        System.out.println(Arrays.toString(a));

        ClassComparator cmp = new ClassComparator<Integer>();
        Arrays.sort(a, cmp);

        System.out.println(Arrays.toString(a));
        System.out.println(cmp.getCountCmp());
    }

    @Test
    @DisplayName("Test QuickSort.sort for 100 inverse sorted Integer()'s")
    public void testQuickSortIntegerInvSorted() {
        Integer [] a = new Integer[100];

        for (int i = 0; i < 100; ++i)
            a[i] = 100 - i;
        System.out.println(Arrays.toString(a));

        ClassComparator cmp = new ClassComparator<Integer>();
        QuickSort.sort(a, cmp);

        System.out.println(Arrays.toString(a));
        System.out.println(cmp.getCountCmp());
    }

    @Test
    @DisplayName("Test Arrays.sort for 100 inverse sorted Integer()'s")
    public void testStandardSortIntegerInvSorted() {
        Integer [] a = new Integer[100];

        for (int i = 0; i < 100; ++i)
            a[i] = 100 - i;
        System.out.println(Arrays.toString(a));

        ClassComparator cmp = new ClassComparator<Integer>();
        Arrays.sort(a, cmp);

        System.out.println(Arrays.toString(a));
        System.out.println(cmp.getCountCmp());
    }

    public void testQuickSortInteger(int n) {
        Integer [] a = new Integer[n];

        for (int i = 0; i < n; ++i)
            a[i] = (int)(Math.random() * 201) - 100; // int from [-100; +100]
        //System.out.println(Arrays.toString(a));

        ClassComparator cmp = new ClassComparator<Integer>();
        QuickSort.sort(a, cmp);

        //System.out.println(Arrays.toString(a));
        System.out.println(cmp.getCountCmp());
    }

    public void testStandardSortInteger(int n) {
        Integer [] a = new Integer[n];

        for (int i = 0; i < n; ++i)
            a[i] = (int)(Math.random() * 201) - 100; // int from [-100; +100]
        //System.out.println(Arrays.toString(a));

        ClassComparator cmp = new ClassComparator<Integer>();
        Arrays.sort(a, cmp);

        //System.out.println(Arrays.toString(a));
        System.out.println(cmp.getCountCmp());
    }

    @Test
    @DisplayName("Test QuickSort.sort for 10, 100, ..., 10^6 Integer()'s")
    public void testQuickSortIntegerAll() {
        for (int n = 1; n <= 1000000; n *= 10)
            testQuickSortInteger(n);
    }

    @Test
    @DisplayName("Test Arrays.sort for 10, 100, ..., 10^6 Integer()'s")
    public void testStandardSortIntegerAll() {
        for (int n = 1; n <= 1000000; n *= 10)
            testStandardSortInteger(n);
    }
}
