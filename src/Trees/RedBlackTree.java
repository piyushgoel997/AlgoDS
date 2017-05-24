package Trees;

import edu.princeton.cs.algs4.Queue;

/**
 * Created by piyus on 13-04-2017 at 15:02.
 */
public class RedBlackTree<Key extends Comparable<Key>,Value> {

    public static final boolean RED = true;
    public static final boolean BLACK = false;

    private Node root;

    public RedBlackTree(Key key, Value value) {
        this.root = new Node();
        root.key = key;
        root.value = value;
    }

    private class Node {
        Key key;
        Value value;
        Node left;
        Node right;
        int count;
        boolean color;  // color of the parent link

        public Node() {
            this.count = 1;
        }
    }


    public void put(Key key, Value value) {
        root = put(root, key, value);
    }

    // Helper functions required for inserting elements.

    private Node put(Node x, Key key, Value value) {
        if (x == null) {
            return null;
        }
        //This part is exactly same as in the BST implementation.
        int comp = x.key.compareTo(key);
        if (comp == 1) {
            x = put(x.left, key, value);
        } else if (comp == -1) {
            x = put(x.right, key, value);
        } else {
            x.value = value;
        }

        // Imp - careful with these cases
        if (!isRed(x.left) && isRed(x.right)) {
            rotateLeft(x);
        }
        if (isRed(x.left) && isRed(x.left.left)) {
            rotateRight(x);
        }
        if (isRed(x.left) && isRed(x.right)) {
            flipColors(x);
        }

        x.count++;

        return x;
    }

    private boolean isRed(Node x) {
        if (x == null) {
            return false;
        }
        return x.color == RED;
    }

    private Node rotateLeft(Node a) {
        assert isRed(a.right);
        Node b = a.right;
        a.right = b.left;
        b.left = a;
        boolean temp = a.color;
        a.color = b.color;
        b.color = temp;
        return b;
    }

    private Node rotateRight(Node a) {
        assert isRed(a.left);
        Node b = a.left;
        a.left = b.right;
        b.right = a;
        boolean temp = a.color;
        a.color = b.color;
        b.color = temp;
        return b;
    }

    private void flipColors(Node h) {
        assert !isRed(h);
        assert isRed(h.left);
        assert isRed(h.right);
        h.color = RED;
        h.left.color = BLACK;
        h.right.color = BLACK;
    }



    // These functions are exact same as in a BST.

    // find the key and return its value
    public Value get(Key key) {
        Node x = root;
        while (x != null) {
            int comp = x.key.compareTo(key);
            if (comp == 1) {
                x = x.left;
            } else if (comp == -1) {
                x = x.left;
            } else {
                return x.value;
            }
        }
        return null;
    }

    // greatest key <= the given key
    public Key floor(Key key) {
        Node x = floor(root, key);
        if (x != null) {
            return x.key;
        }
        return null;
    }

    // smallest key >= the given key
    public Key ceiling(Key key) {
        Node x = ceiling(root, key);
        if (x == null) {
            return null;
        }
        return x.key;
    }

    public int size(Node root) {
        if (root == null) {
            return 0;
        }
        return root.count;
    }

    // no. of keys < the given key
    public int rank(Key key) {
        return rank(root, key);
    }

    // deletes the min key. similarly deleteMax can be implemented
    public void deleteMin() {
        root = deleteMin(root);
    }




    // Helper Functions

    private Node deleteMin(Node x) {
        if (x.left == null) {
            return x.right;
        }
        x.left = deleteMin(x.left);
        x.count--; //TODO
        return x;
    }

    private int rank(Node x, Key key) {
        if (x == null) {
            return 0;
        }
        int comp = x.key.compareTo(key);
        if (comp == 1) {
            return rank(x.left, key);
        } else if (comp == -1) {
            return rank(x.right, key) + 1 + size(x.left);
        } else {
            return size(x.left);
        }
    }

    private Node ceiling(Node x, Key key) {
        if (x == null) {
            return null;
        }
        int comp = x.key.compareTo(key);
        if (comp == 0) {
            return x;
        }
        if (comp == -1) {
            return ceiling(x.right, key);
        }
        Node temp = ceiling(x.left, key);
        if (temp == null) {
            return x;
        }
        return temp;
    }

    private Node floor(Node x, Key key) {
        if (x == null) {
            return null;
        }
        int comp = x.key.compareTo(key);
        if (comp == 1) {
            return floor(x.left, key);
        }
        if (comp == 0) {
            return x;
        }
        Node temp = floor(x.right, key);
        if (temp == null) {
            return x;
        }
        return temp;
    }


    // Iterator - InOrder Traversal

    public Iterable<Key> getKeys() {
        Queue<Key> keys = new Queue<>();
        inOrder(root, keys);
        return keys;
    }

    private void inOrder(Node x, Queue<Key> keys) {
        if (x == null) {
            return;
        }
        inOrder(x.left, keys);
        keys.enqueue(x.key);
        inOrder(x.right, keys);
    }
}
