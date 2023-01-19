import java.util.Arrays;
import java.util.Comparator;

public class QuickSort {
    public static <T extends Comparable<T>> void sort(T[] array) {
        _quickSortImpl(array, 0, array.length, new SuperComparator<T>());
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
        int len = right - left;
        T mid = a[left + len / 2];

        T pivot = _medianOf3(x, y, mid, cmp);
        int piv_ind;

        if (cmp.compare(x, pivot) == 0)
            piv_ind = left;
        else if (cmp.compare(mid, pivot) == 0)
            piv_ind = left + len / 2;
        else piv_ind = right - 1;

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

    public static <T> boolean isSorted(T[] a, Comparator<T> cmp) {
        if (a.length <= 1)
            return true;

        int i = 1;
        while (i < a.length) {
            if (cmp.compare(a[i - 1], a[i]) > 0)
                return false;
            ++i;
        }
        return true;
    }

    public static void main(String[] args) {
        Integer [] a = { 10, 10, 4, 3, 2, 1, 0, -2, 125, -10 };
        System.out.println(Arrays.toString(a));

        SuperComparator cmp = new SuperComparator<Integer>();

        QuickSort.sort(a, cmp);

        System.out.println(Arrays.toString(a));
        System.out.println(cmp.getCountCmp());
    }
}
