import java.io.*;
import java.util.Arrays;
import java.util.Comparator;

class ClassComparator <T extends Comparable<T>> implements Comparator<T> {
    long _countCmp;

    ClassComparator() {
        _countCmp = 0;
    }

    @Override
    public int compare(T lhs, T rhs) {
        _countCmp++;
        return lhs.compareTo(rhs);
    }

    public long getCountCmp() {
        return _countCmp;
    }
}

public class QuickSort {
    public static <T extends Comparable<T>> void sort(T[] array) {
        _quickSortImpl(array, 0, array.length, new ClassComparator<T>());
    }

    public static <T> void sort(T[] a, Comparator<T> cmp) {
        _quickSortImpl(a, 0, a.length, cmp);
    }

    private static <T> void _quickSortImpl(T[] a, int left, int right, Comparator<T> cmp) {
        if (left < right) {
            int pivot = _partition(a, left, right, cmp);

            _quickSortImpl(a, left, pivot, cmp);
            _quickSortImpl(a, pivot + 1, right, cmp);
        }
    }

    private static <T> int _partition(T[] a, int left, int right, Comparator<T> cmp) {
        T x = a[left];
        T y = a[right - 1];
        int length = right - left;
        T mid;
        if (length % 2 == 0)
            mid = a[left + (length / 2 - 1)];
        else
            mid = a[left + (length / 2)];

        T pivot = _medianOf3(x, y, mid, cmp);
        int piv_ind = java.util.Arrays.asList(a).indexOf(pivot);

        a[piv_ind] = a[left];
        a[left] = pivot;

        int i = left + 1;

        for (int j = left + 1; j < right; j++) {
            if (cmp.compare(a[j], pivot) < 0) {
                _swap(a, i, j);
                i++;
            }
        }
        _swap(a, left, i - 1);

        return i - 1;
    }

    private static <T> T _medianOf3(T x, T y, T z, Comparator<T> cmp) {
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

    private static <T> void _swap(T[] a, int i, int j) {
        T tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    public static void main(String[] args) {
        Integer [] a = { 10, 10, 4, 3, 2, 1, 0, -2, 125, -10 };
        System.out.println(Arrays.toString(a));

        ClassComparator cmp = new ClassComparator<Integer>();

        QuickSort.sort(a, cmp);

        System.out.println(Arrays.toString(a));
        System.out.println(cmp.getCountCmp());
    }
}
