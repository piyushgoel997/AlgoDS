package StringProcessing.SymbolTables;

import Queues.Queue;

/**
 * Created by piyus on 03-07-2017 at 11:27.
 * R-way Trie
 */
public class RTrie<Value> {
    private static final int R = 256;  // extended ASCII
    private Node root;

    private static class Node {  // characters are implicitly defined by the link index
        private Object value;
        private Node[] next = new Node[R];
    }

    public void put(String key, Value val) {
        root = put(root, key, val, 0);
    }

    private Node put(Node x, String key, Value val, int d) {
        if (x == null) {
            x = new Node();
        }
        if (d == key.length()) {
            x.value = val;
            return x;
        }
        char c = key.charAt(d);
        x.next[c] = put(x.next[c], key, val, d + 1);
        return x;
    }

    public Value get(String key) {
        return get(root, key, 0);
    }

    private Value get(Node x, String key, int d) {
        if (x == null) {
            return null;
        }
        if (d == key.length()) {
            return (Value) x.value;
        }
        return get(x.next[(int) key.charAt(d)], key, d + 1);
    }

    /**
     * returns an iterable containing all of the strings present in the trie.
     */
    public Iterable<String> keys() {
        Queue<String> q = new Queue<>();
        collect(root, "", q);
        return q;
    }

    /**
     * @param x: current node
     * @param prefix: sequence of characters on the path from root to x.
     * @param q: wew need to send in the queue as input so that all of the strings are inserted in the same queue.
     */
    private void collect(Node x, String prefix, Queue<String> q) {
        if (x == null) {
            return;
        }
        if (x.value != null) {
            q.enqueue(prefix);
        }
        for(char c = 0; c < R;c++) {   // *IMP* way of making this loop with char instead of int.
            collect(x.next[c], prefix + c, q);
        }
    }

    public String longestPrefixOf(String query) {
        int length = search(root, query, 0);
        return query.substring(0, length);
    }

    private int search(Node x, String query, int d) {
        if (d >= query.length()) {
            return query.length();
        }
        char c = query.charAt(d);
        if (x.next[c] == null) {
            return d;
        }
        return search(x.next[c], query, d + 1);
    }
}
