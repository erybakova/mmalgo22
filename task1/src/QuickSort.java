import java.io.*;
import java.util.Arrays;
import java.util.Comparator;

public class QuickSort <T extends Comparable<T>> {
    public static int count_cmp_ = 0;

    public static <T> void Sort(T[] a, Comparator<T> cmp) {
        QuickSortImpl(a, 0, a.length, cmp);
    }

    public static <T> void QuickSortImpl(T[] a, int left, int right, Comparator<T> cmp) {
        if (left < right) {
            int pivot = Partition(a, left, right, cmp);

            QuickSortImpl(a, left, pivot, cmp);
            QuickSortImpl(a, pivot + 1, right, cmp);
        }
    }

    public static <T> int Partition(T[] a, int left, int right, Comparator<T> cmp) {
        T x = a[left];
        T y = a[right - 1];
        int length = right - left;
        T mid;
        if (length % 2 == 0)
            mid = a[left + (length / 2 - 1)];
        else
            mid = a[left + (length / 2)];

        T pivot = MedianOf3(x, y, mid, cmp);
        int piv_ind = java.util.Arrays.asList(a).indexOf(pivot);

        a[piv_ind] = a[left];
        a[left] = pivot;

        int i = left + 1;

        for (int j = left + 1; j < right; j++) {
            if (cmp.compare(a[j], pivot) < 0) {
                Swap(a, i, j);
                i++;
            }
        }
        Swap(a, left, i - 1);

        return i - 1;
    }

    public static <T> T MedianOf3(T x, T y, T z, Comparator<T> cmp) {
        if (cmp.compare(x, y) <= 0 && cmp.compare(y, z) <= 0)
            return y;  // a b c
        if (cmp.compare(x, z) <= 0 && cmp.compare(z, y) <= 0)
            return z;  // a c b
        if (cmp.compare(y, x) <= 0 && cmp.compare(x, z) <= 0)
            return x;  // b a c
        if (cmp.compare(y, z) <= 0 && cmp.compare(z, x) <= 0)
            return z;  // b c a
        if (cmp.compare(z, x) <= 0 && cmp.compare(x, y) <= 0)
            return x;  // c a b
        return y;  // c b a
    }

    public static <T> void Swap(T[] a, int i, int j) {
        T tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    public static void main(String[] args) {
        Integer [] a = { 10, 10, 4, 3, 2, 1, 0, -2, 125, -10 };

        Comparator<Integer> cmp = new Comparator<Integer>() {
            public int compare(Integer i, Integer j) {
                count_cmp_++;
                return i.compareTo(j);
            }
        };

        System.out.println(Arrays.toString(a));
        Sort(a, cmp);
        System.out.println(Arrays.toString(a));
        System.out.println(count_cmp_);
    }
}
