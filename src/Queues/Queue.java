package Queues;

import java.util.Iterator;

/**
 * Created by piyus on 26-05-2017 at 22:35.
 */
public class Queue<Item> implements Iterable {

    // first element of the queue
    private Node<Item> first;
    private int size;

    private class Node<T> {
        T data;
        Node<T> next;

        public Node(T data, Node<T> next) {
            this.data = data;
            this.next = next;
        }
    }

    public Queue(){
        first = null;
        size = 0;
    }

    // enqueues the element to the end of the queue.
    public void enqueue(Item data) {
        size++;
        if (first == null) {
            first = new Node<>(data, null);
            return;
        }
        Node<Item> curr = first;
        while (curr.next != null) {
            curr = curr.next;
        }
        curr.next = new Node<>(data, null);
    }

    // return the firsts element from the queue and removes it from the queue.
    public Item dequeue() {
        size--;
        Item temp = first.data;
        first = first.next;
        return temp;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public Item peek() {
        return first.data;
    }

    @Override
    public Iterator iterator() {
        return new QueueIterator();
    }

    private class QueueIterator implements Iterator {
        Node<Item> curr = first;
        @Override
        public boolean hasNext() {
            return curr.next != null;
        }

        @Override
        public Object next() {
            return (curr = curr.next);
        }
    }
}