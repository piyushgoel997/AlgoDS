package Queues;

/**
 * Created by piyus on 26-05-2017 at 22:35.
 */
public class Queue<Item> {

    // first element of the queue
    private Node<Item> first;
    // next to the last element of the queue
    private Node<Item> end;
    private int size;

    private class Node<Item> {
        Item data;
        Node<Item> next;
    }

    public Queue(){
        first = null;
        end = null;
        size = 0;
    }

    // enqueues the element to the end of the queue.
    public void enqueue(Item data) {
        size++;
        if (first == null) {
            first = new Node<>();
            first.data = data;
            first.next = end;
            return;
        }
        end = new Node<>();
        end.data = data;
        end = end.next;
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
}

