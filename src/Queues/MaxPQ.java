package Queues;

import java.util.ArrayList;

/**
 * Created by piyus on 06-04-2017.
 */
public class MaxPQ<Item extends Comparable<Item>> {
    private  ArrayList<Item> pq;
    private int n;

    public MaxPQ() {
        this.pq = new ArrayList<>();
        n = 0;
    }

    public void insert(Item item) {
        pq.add(item);
        swim(++n);
    }

    public void deleteMax() {
        if (n < 1) {
            throw new UnsupportedOperationException();
        }
        exch(1, n);
        pq.remove(n--);
        sink(1);
    }

    private void swim(int k) {
        while (k > 1 && pq.get(k).compareTo(pq.get(k / 2)) == 1) {
            exch(k, k / 2);
            k = k / 2;
        }
    }

    private void sink(int k) {
        while (k < n / 2 && (pq.get(k).compareTo(pq.get(2 * k)) == -1 || pq.get(k).compareTo(pq.get(2 * k + 1)) == -1)) {
            if (pq.get(2 * k).compareTo(pq.get(2 * k + 1)) == 1) {
                exch(k, 2 * k);
            } else {
                exch(k, 2 * k + 1);
            }
        }
    }

    private void exch(int k, int i) {
        Item temp = pq.get(i);
        pq.set(i, pq.get(k));
        pq.set(k, temp);
    }
}
