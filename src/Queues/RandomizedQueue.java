package Queues;

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by piyus on 23-03-2017.
 */
public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] rq;
    private int size;

    public RandomizedQueue() {
        this.rq = (Item[]) new Object[1];
        this.size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void enqueue(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }
        if (size == rq.length) {
            Item[] temp = (Item[]) new Object[2 * size];
            for (int i = 0; i < size; i++) {
                temp[i] = rq[i];
            }
            rq = temp;
        }
        rq[size++] = item;
    }

    public Item dequeue() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        int index = StdRandom.uniform(0, size);
        Item item = rq[index];
        rq[index] = rq[--size];
        rq[size] = null;
        if (size == rq.length / 4 && size > 1) {
            Item[] temp = (Item[]) new Object[rq.length / 2];
            for (int i = 0; i < size; i++) {
                temp[i] = rq[i];
            }
            rq = temp;
        }
        return item;
    }

    public Item sample() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        return rq[StdRandom.uniform(0, size)];
    }

    @Override
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<Item> {

        private int i = 0;

        @Override
        public boolean hasNext() {
            return size != i;
        }

        @Override
        public Item next() {
            if (i == size) {
                throw new NoSuchElementException();
            }
            return rq[i++];
        }
    }

//    public static void main(String[] args) {
//        RandomizedQueue rq = new RandomizedQueue();
//        rq.enqueue(1);
//        System.out.println(rq.size());
//        System.out.println(rq.dequeue());
//        System.out.println(rq.size());
//        rq.enqueue(2);
//        System.out.println(rq.size());
//        System.out.println(rq.dequeue());
//    }

}
