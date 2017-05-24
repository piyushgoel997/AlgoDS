package Misc;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by piyus on 23-05-2017 at 14:05.
 */
public class Bag<Item> implements Iterable<Item> {

    // first element
    private Node<Item> first;
    // no of elements
    private int n;

    // helper linked list node class
    private class Node<Item> {
        private Item data;
        private Node next;

        public Node(Item data, Node next) {
            this.data = data;
            this.next = next;
        }
    }

    public Bag() {
        this.first = null;
        this.n = 0;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    public void add(Item data) {
        Node oldFirst = first;
        first = new Node<>(data, oldFirst);
        n++;
    }

    @Override
    public Iterator<Item> iterator() {
        return new ListIterator<Item> (first);
    }

    private class ListIterator<Item> implements Iterator<Item> {

        private Node<Item> current;

        private ListIterator(Node<Item> first) {
            this.current = first;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Item item = current.data;
            current = current.next;
            return item;
        }

    }
}
