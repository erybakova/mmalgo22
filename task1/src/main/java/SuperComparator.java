import java.util.Comparator;

class SuperComparator <T extends Comparable<T>> implements Comparator<T> {
    long _countCmp;

    SuperComparator() {
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

    public void resetCountCmp() {
        _countCmp = 0;
    }
}