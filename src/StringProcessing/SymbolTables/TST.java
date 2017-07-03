package StringProcessing.SymbolTables;

import Queues.Queue;

/**
 * Created by piyus on 03-07-2017 at 11:51.
 * Ternary Search Trie
 */
public class TST<Value> {
    private Node root;

    private class Node {
        char c;
        Value val;
        Node left, middle, right;
    }

    public void put(String key, Value val) {
        root =put(root, key, val, 0);
    }

    private Node put(Node x, String key, Value val, int d) {
        char c = key.charAt(d);
        if (x == null) {
            x = new Node();
            x.c = c;
        }
        if (c < x.c) {
            x.left = put(x.left, key, val, d);
        } else if (c > x.c) {
            x.right = put(x.right, key, val, d);
        } else if (d == key.length()) {
            x.val = val;
        } else {
            x.middle = put(x.middle, key, val, d + 1);
        }
        return x;
    }

    public Value get(String key) {
        return get(root, key, 0).val;
    }

    private Node get(Node x, String key, int d) {
        if (x == null) return null;

        char c = key.charAt(d);
        if (x.c < c) {
            return get(x.left, key, d);
        } else if (x.c > c) {
            return get(x.right, key, d);
        } else if (key.length() - 1 == d) {
            return x;
        } else {
            return get(x.middle, key, d + 1);
        }
    }

    /**
     * returns an iterable containing all of the strings present in the trie.
     */
    public Iterable<String> keys() {
        Queue<String> q = new Queue<>();
        collect(root, "", q);
        return q;
    }

    private void collect(Node x, String prefix, Queue<String> q) {
        if (x == null) {
            return;
        }
        if (x.val != null) {
            q.enqueue(prefix + x.c);
        }
        collect(x.left, prefix, q);
        collect(x.right, prefix, q);
        collect(x.middle, prefix + x.c, q);
    }
}
