public class BinarySearch {
    public static int BinarySearch(int array[], int l, int r, int x) {
        if (r >= l) {
            int mid = l + (r - l) / 2;
            if (array[mid] == x)
                return mid;
            if (array[mid] > x)
                return BinarySearch(array, l, mid - 1, x);

            return BinarySearch(array, mid + 1, r, x);
        }

        return -1;
    }

    public static int Find(int[] array, int element) {
        return BinarySearch(array, 0, array.length - 1, element);
    }

    public static void main(String args[]) {
        BinarySearch ob = new BinarySearch();
        int array[] = { -5, -2, -2, 0, 1, 2, 3, 4, 10, 10, 21 };
        int element = 10;

        int res = ob.Find(array, element);

        if (res == -1)
            System.out.println("No such element in array");
        else
            System.out.println("Found at index " + res);
    }
}
