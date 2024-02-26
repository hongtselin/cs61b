package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T> {
    private int size;
    public int nextFirst;
    public int nextLast;
    public T[] items;

    public ArrayDeque() {
        size = 0;
        nextFirst = 0;
        nextLast = 1;
        items = (T[]) new Object[8];
    }

    /**
     * for circulate structure, return the next index in the array
     *
     * @param index
     * @return int
     */
    public int nextIndex(int index) {
        if (index + 1 > items.length - 1) {
            return index + 1 - items.length;
        } else {
            return index + 1;
        }
    }

    /**
     * for circulate structure, return the previous index in the array
     *
     * @param index
     * @return int
     */
    public int prevIndex(int index) {
        if (index - 1 < 0) {
            return index - 1 + items.length;
        } else {
            return index - 1;
        }
    }

    private void resize(int capacity) {
        T[] temp = (T[]) new Object[capacity];
        //System.arraycopy(items, 0, temp, 0, size);
        int cursor = nextFirst;
        for (int i = 0; i < size; i++) {
            temp[i] = items[this.nextIndex(cursor)];
            cursor = nextIndex(cursor);
        }
        items = temp;
        nextFirst = capacity - 1;
        nextLast = size;
    }

    private void downsize() {
        //
    }

    @Override
    public void addFirst(T item) {
        if (size == items.length) {
            this.resize(size * 2);
        }
        items[nextFirst] = item;
        size += 1;
        nextFirst = prevIndex(nextFirst);
    }

    @Override
    public void addLast(T item) {
        if (size == items.length) {
            this.resize(size * 2);
        }
        items[nextLast] = item;
        size += 1;
        nextLast = nextIndex(nextLast);

    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T get(int index) {
        if (index > (size - 1)) {
            return null;
        } else {
            if ((index + nextFirst) < items.length - 1) {
                return items[index + nextFirst];
            } else {
                return items[index + nextFirst - (items.length - 1)];
            }
        }
    }

    @Override
    public T removeFirst() {

        if (this.isEmpty()) {
            return null;
        }

        // resize the array if removed an item will bring usage ratio less than 25%
        if ((size - 1)   < 0.25 * items.length && (items.length > 16)){
            resize(items.length / 2);
        }

        T removedItem = items[nextIndex(nextFirst)];
        items[nextIndex(nextFirst)] = null;
        nextFirst = nextIndex(nextFirst);
        size -= 1;

        return removedItem;
    }

    @Override
    public T removeLast() {

        if (this.isEmpty()) {
            return null;
        }


        if ((size - 1)   < 0.25 * items.length && (items.length > 16)){
            resize(items.length / 2);
        }

        T removedItem = items[prevIndex(nextLast)];
        items[prevIndex(nextLast)] = null;
        nextLast = prevIndex(nextLast);
        size -= 1;
        return removedItem;
    }

    @Override
    public Iterator<T> iterator() {
        return new ADIterator();
    }

    public class ADIterator implements Iterator<T> {
        private int currPos;

        public ADIterator() {
            //point the cursor to the first item in the array
            currPos = nextIndex(nextFirst);
        }

        public boolean hasNext() {
            if (currPos < size) {
                return true;
            } else {
                return false;
            }
        }

        public T next() {
            T nextItem = items[currPos];
            currPos = nextIndex(currPos);
            return nextItem;
        }

    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (other instanceof Deque) {
            Deque<T> otherDeque = (Deque<T>) other;

            if (this.size() != otherDeque.size()) {
                return false;
            }

            int thisCursor = nextIndex(this.nextFirst);
            for (T otherItem : otherDeque) {
                if (otherItem != this.items[thisCursor]) {
                    return false;
                }
                thisCursor = nextIndex(thisCursor);
            }

            return true;
        }

        return false;
    }

    @Override
    public void printDeque() {
        for (T item : this) {
            System.out.print(item + " ");
        }
        System.out.println("");
    }

}
