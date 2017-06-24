package Queues;

/**
 * Created by piyus on 11-06-2017 at 13:42.
 */
public class IndexedMinPQ<Key extends Comparable<Key>> {

    // *IMP* the uses and meanings of these arrays might be confusing and difficult to understand.
    // read carefully and try to understand it.

    private Key[] keys; // basically keys in the order in which the are inserted.
    private int[] pq; // pq[i] is the index of the key in the heap position i.
    // pq and qp are basically inverse of each other. => pq[qp[i]] = qp[pq[i]] = i
    private int[] qp; // qp[j] is the heap position of the key with the index j.
    private int n;
    private int maxN;

    public IndexedMinPQ(int maxN) {
        this.maxN = maxN;
        n = 0;
        pq = new int[maxN];
        keys = (Key[]) new Object[maxN];
        qp = new int[maxN];
        for (int i = 0; i < maxN; i++) {
            qp[i] = -1;
        }
    }

    /**
     * @param i   - index
     * @param key - key associated with the index k.
     */
    public void insert(int i, Key key) {
        qp[i] = ++n;
        pq[n] = i;
        keys[i] = key;
        swim(n);
    }

    public void changeKey(int i, Key key) {
        keys[i] = key;
        swim(i);
        sink(i);
    }

    public int deleteMin() {
        int minIdx = pq[1];
        exch(1, n--);
        sink(1);
        qp[minIdx] = -1;
        return minIdx;
    }

    public int size() {
        return n;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public boolean contains(int i) {
        return qp[i] != -1;
    }


    //Helper Functions
    private void swim(int k) {
        while (k > 0 && keys[k].compareTo(keys[k / 2]) < 0) {
            exch(k, k / 2);
            k /= 2;
        }
    }


    private void sink(int k) {
        while (k < n / 2 && (keys[k].compareTo(keys[2 * k]) > 0 || keys[k].compareTo(keys[2 * k + 1]) > 0)) {
            if (keys[2 * k].compareTo(keys[2 * k + 1]) == 1) {
                exch(2 * k, k);
            } else {
                exch(2 * k + 1, k);
            }
        }
    }

    // *IMP* study carefully
    private void exch(int i, int j) {
        int temp = pq[i];
        pq[i] = pq[j];
        pq[j] = temp;
        qp[pq[i]] = i;
        qp[pq[j]] = j;
    }
}
