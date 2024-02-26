package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Deque<T>{
    private int size;
    private Node sentinel;

    public class Node {
        public Node prev;
        public T item;
        public Node next;

        public Node(T i, Node p, Node n) {
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

    public LinkedListDeque(T i) {
        size = 1;
        sentinel = new Node(null, null, null);
        sentinel.next = new Node(i, sentinel, sentinel);
        sentinel.prev = sentinel.next;
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
            this.addFirst(item);
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
        } else  {
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
        } else  {
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
        if (index > size -1) {
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

    @Override
    public Iterator iterator() {
        return new LLIterator();
    }

    public class LLIterator implements Iterator<T> {
        private Node currNode;

        public LLIterator() {
            currNode = sentinel;
        }

        public boolean hasNext() {
            if (currNode.next != null) {
                return true;
            } else {
                return false;
            }
        }

        public T next() {
            T nextItem = currNode.next.item;
            currNode = currNode.next;
            return nextItem;
        }

    }


}
