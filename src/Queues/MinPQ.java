package Queues;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by piyus on 03-06-2017 at 16:23.
 */
public class MinPQ<Item extends Comparable<Item>> implements Iterable {
    // Implemented using Binary Heap.
    private ArrayList<Item> pq;
    private int n;

    public MinPQ() {
        this.pq = new ArrayList<>();
        n = 0;
    }

    public void insert(Item itam) {
        pq.add(itam);
        swim(++n);
    }

    public Item deleteMin() {
        exch(1, n);
        Item temp = pq.remove(n--);
        sink(1);
        return temp;
    }

    private void sink(int k) {
        while (k < n / 2 && (pq.get(k).compareTo(pq.get(2 * k)) == 1 || pq.get(k).compareTo(pq.get(2 * k + 1)) == 1)) {
            if (pq.get(2 * k).compareTo(pq.get(2 * k + 1)) == -1) {
                exch(2 * k, k);
            } else {
                exch(2 * k + 1, k);
            }
        }
    }

    private void swim(int k) {
        while (k > 1 && pq.get(k).compareTo(pq.get(k / 2)) == -1) {
            exch(k, k / 2);
            k = k / 2;
        }
    }

    private void exch(int k, int i) {
        Item temp = pq.get(k);
        pq.set(k, pq.get(i));
        pq.set(i, temp);
    }

    public boolean isEmpty() {
        return n == 0;
    }

    @Override
    public Iterator iterator() {
        return new MinPQ_Iterator();
    }

    private class MinPQ_Iterator implements Iterator {
        int i = 0;
        @Override
        public boolean hasNext() {
            return i != pq.size();
        }

        @Override
        public Object next() {
            return pq.get(i++);
        }
    }
}
