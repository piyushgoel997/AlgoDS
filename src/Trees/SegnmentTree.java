package Trees;

/**
 * Created by piyus on 02-11-2017 at 10:59.
 * https://www.hackerearth.com/practice/data-structures/advanced-data-structures/segment-trees/tutorial/
 */
public class SegnmentTree {
    private int[] tree; // array representation of the segnment tree, since it is a binary tree.
    private int[] array; // array containing the data in the given array format.

    public SegnmentTree(int[] array) {
        tree = new int[2 * array.length + 2];
        this.array = array;
        build(0, 0, array.length - 1);
    }

    /**
     * @param node - index in the tree array, current segnment node that is being visited in the tree (0, for calling)
     * @param start - the first index of the current segnment node (0, for calling)
     * @param end - the last index of the current segnment node (n - 1, for calling)
     */
    private void build(int node, int start, int end) {
        if (start == end) {
            tree[node] = array[start];
            return;
        }
        int mid = (start + end) / 2;
        build(2 * node, start, mid);
        build(2 * node + 1, mid + 1, end);
        tree[node] = tree[2 * node] + tree[2 * node + 1];
    }

    /**
     * time - O(log(n))
     * add the value 'x' at the index 'idx' and update the tree accordingly.
     * @param x - the value to be added
     * @param idx - index at which the value is to be added
     * @param node - index in the tree array, current segnment node that is being visited in the tree (0, for calling)
     * @param start - the first index of the current segnment node (0, for calling)
     * @param end - the last index of the current segnment node (n - 1, for calling)
     */
    public void add(int x, int idx, int node, int start, int end) {
        if (start == end) {
            tree[node] += x;
            array[idx] += x;
            return;
        }
        int mid = (start + end) / 2;
        if (start <= idx && mid >= idx) {
            add(x, idx, 2 * node, start, mid);
        } else {
            add(x, idx, 2 * node + 1, mid + 1, end);
        }
        tree[node] = tree[2 * node] + tree[2 * node + 1];

    }
}
