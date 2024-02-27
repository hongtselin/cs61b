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

        for (int i = 0; i < this.size() - 1; i++) {
            if (madCompare.compare(this.get(i), this.get(i + 1)) < 0) {
                maxItem = this.get(i + 1);
            }
        }

        return maxItem;
    }

    public T max(Comparator<T> c) {
        if (isEmpty()) {
            return null;
        }

        T maxItem = this.get(0);

        for (int i = 0; i < this.size() - 1; i++) {
            if (c.compare(this.get(i), this.get(i + 1)) < 0) {
                maxItem = this.get(i + 1);
            }
        }
        return maxItem;
    }
}
