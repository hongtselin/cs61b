package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Deque<T>, Iterable<T> {
    private int size;
    private Node sentinel;

    public class Node {
        private Node prev;
        private T item;
        private Node next;

        private Node(T i, Node p, Node n) {
            item = i;
            prev = p;
            next = n;
        }
    }

    // Constructor for empty LLD
    public LinkedListDeque() {
        size = 0;
        sentinel = new Node(null, null, null);
    }

    @Override
    public void addFirst(T item) {

        // if it is the first element add to the queue
        if (size == 0) {
            //point the sentinel and the first node to each other
            sentinel.next = new Node(item, sentinel, sentinel);
            sentinel.prev = sentinel.next;
        } else {
            // point sentinel.next (the fist Node)to the newly added Node
            sentinel.next = new Node(item, sentinel, sentinel.next);
            // point the original first node.prev to the newly added Node
            sentinel.next.next.prev = sentinel.next;
        }

        size += 1;
    }

    @Override
    public void addLast(T item) {

        // addLast and addFirst is the same to an empty list
        if (size == 0) {
            sentinel.next = new Node(item, sentinel, sentinel);
            sentinel.prev = sentinel.next;
        } else {
            // point sentinel.prev (the last Node) to the newly added Node
            sentinel.prev = new Node(item, sentinel.prev, sentinel);
            // point the original last node.next to the newly added Node
            sentinel.prev.prev.next = sentinel.prev;
        }
        size += 1;

    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public void printDeque() {
        for (T item : this) {
            System.out.print(item);
            System.out.print(" ");
        }
        System.out.println();

    }

    @Override
    public T removeFirst() {
        if (this.isEmpty()) {
            return null;
        } else {
            T removedItem = sentinel.next.item;

            if (size == 1) {
                // if only one element left in the list
                sentinel.next = null;
                sentinel.prev = null;
            } else {
                // if more than one element in list
                // make the second element the first
                // connect the second element.prev back to sentinel node
                sentinel.next = sentinel.next.next;
                sentinel.next.prev = sentinel;
            }
            size -= 1;
            return removedItem;
        }
    }

    @Override
    public T removeLast() {
        if (this.isEmpty()) {
            return null;
        } else {
            T removedItem = sentinel.prev.item;

            if (size == 1) {
                // if only one element left in the list
                sentinel.next = null;
                sentinel.prev = null;
            } else {
                // if more than one element in list
                // make the second last element the last
                // connect the second last element.next back to sentinel node
                sentinel.prev = sentinel.prev.prev;
                sentinel.prev.next = sentinel;
            }
            size -= 1;
            return removedItem;
        }
    }

    @Override
    public T get(int index) {
        if (index > size - 1) {
            return null;
        }

        Node cursor = sentinel.next;
        for (int i = 0; i <= index; i++) {

            if (i == index) {
                return cursor.item;
            }
            cursor = cursor.next;
        }

        return null;
    }

    public T getRecursive(int index) {
        if (index > size - 1) {
            return null;
        }

        return getRecursive(index, sentinel.next);

    }


    private T getRecursive(int index, Node currNode) {
        if (index == 0) {
            return currNode.item;
        } else {
            index -= 1;
            return getRecursive(index, currNode.next);
        }

    }


    public Iterator<T> iterator() {
        return new LLIterator();
    }

    private class LLIterator implements Iterator<T> {
        private int currPos;

        LLIterator() {
            currPos = 0;
        }

        public boolean hasNext() {
            return currPos < size;
        }

        public T next() {
            T nextItem = get(currPos);
            currPos += 1;
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

            if (otherDeque.size() != this.size()) {
                return false;
            }

            for (int i = 0; i < this.size(); i++) {
                if (!this.get(i).equals(otherDeque.get(i))) {
                    return false;
                }
            }

            return true;
        }

        return false;
    }

}
