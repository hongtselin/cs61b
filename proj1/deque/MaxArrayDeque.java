package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T>  {
    private Comparator<T> MADcompare;

    public MaxArrayDeque(Comparator<T> c) {
        super();
        MADcompare = c;
    }

    public T max() {
        if (isEmpty()) {
            return null;
        }
        T maxItem = this.items[nextFirst];
        int cursor = nextFirst;
        for (int i = 0; i < this.size(); i++) {
            if (MADcompare.compare(items[cursor], items[nextIndex(cursor)]) < 0) {
                maxItem = items[nextIndex(cursor)];
            }
            cursor = nextIndex(cursor);
        }
        return maxItem;
    }

    public T max(Comparator<T> c) {
        if (isEmpty()) {
            return null;
        }
        T maxItem = this.items[nextFirst];
        int cursor = nextFirst;
        for (int i = 0; i < this.size(); i++) {
            if (MADcompare.compare(items[cursor], items[nextIndex(cursor)]) < 0) {
                maxItem = items[nextIndex(cursor)];
            }
            cursor = nextIndex(cursor);
        }
        return maxItem;
    }
}
