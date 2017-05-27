package Queues;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by piyus on 23-03-2017.
 */
public class Deque<Item> implements Iterable<Item> {

    private class Node<T> {
        private T value;
        private Node<T> next;
        private Node<T> prev;

        public Node(T value, Node<T> next, Node<T> prev) {
            this.value = value;
            this.next = next;
            this.prev = prev;
        }
    }

    private Node<Item> first;
    private Node<Item> last;
    private int size;

    public Deque() {
        first = null;
        last = null;
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void addFirst(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }
        size++;
        if (first == null) {
            first = new Node<>(item, last, null);
            last = first;
            return;
        }
        first.prev = new Node<>(item, first, null);
        first = first.prev;
    }

    public void addLast(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }
        size++;
        if (last == null) {
            last = new Node<>(item, null, first);
            first = last;
            return;
        }
        last.next = new Node<>(item, null, last);
        last = last.next;
    }

    public Item removeFirst() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        size--;
        Item item = first.value;
        if (first == last) {
            first = null;
            last = first;
            return item;
        }
        first = first.next;
        first.prev = null;
        return item;
    }

    public Item removeLast() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        size--;
        Item item = last.value;
        if (first == last) {
            last = null;
            first = last;
            return item;
        }
        last = last.prev;
        last.next = null;
        return item;
    }

    @Override
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {

        private Node<Item> current = new Node<>(null, first, null);

        @Override
        public boolean hasNext() {
            return current.next != null;
        }

        @Override
        public Item next() {
            if (current.next == null) {
                throw new NoSuchElementException();
            }
            current = current.next;
            return current.value;
        }
    }

//    public static void main(String[] args) {
////        Deque<Integer> dq = new Deque<>();
////        dq.addFirst(1);
////        System.out.println(dq.size());
////        System.out.println(dq.removeLast());
////        System.out.println(dq.size());
////        dq.addLast(2);
////        System.out.println(dq.size());
////        System.out.println(dq.removeFirst());
////        System.out.println(dq.size());
////        Iterator i = dq.iterator();
////        System.out.println(i.hasNext());
////        System.out.println(i.next());
////        System.out.println(i.hasNext());
////        System.out.println(i.next());
////        System.out.println(i.hasNext());
////        System.out.println(i.next());
////        System.out.println(i.next());
//    }

}
