package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T>  {
    private Comparator<T> madCompare;

    public MaxArrayDeque(Comparator<T> c) {
        super();
        madCompare = c;
    }

    public T max() {
        if (isEmpty()) {
            return null;
        }

        T maxItem = this.get(0);

        for (int i = 0; i < this.size(); i++) {
            if (madCompare.compare(maxItem, this.get(i)) < 0) {
                maxItem = this.get(i);
            }
        }

        return maxItem;
    }

    public T max(Comparator<T> c) {
        if (isEmpty()) {
            return null;
        }

        T maxItem = this.get(0);

        for (int i = 0; i < this.size(); i++) {
            if (c.compare(maxItem, this.get(i)) < 0) {
                maxItem = this.get(i);
            }
        }
        return maxItem;
    }
}
