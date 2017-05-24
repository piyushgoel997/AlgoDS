package Trees;


import edu.princeton.cs.algs4.Queue;

/**
 * Created by piyus on 08-04-2017.
 */
public class BST<Key extends Comparable<Key>, Value> {

    private Node root;

    private class Node {
        private Key key;
        private Value value;
        private Node left, right;
        private int count;

        public Node(Key key, Value value) {
            this.key = key;
            this.value = value;
            left = null;
            right = null;
            count = 1; // count of elements in it's subtree.
        }
    }


    // Constructor
    public BST(Key key, Value value) {
        this.root = new Node(key, value);
    }

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

    // find appropriate place for the key if it doesn't exists then create it and put the value
    public void put(Key key, Value value) {
        root = put(root, key, value);
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

    // Hibbard deletion
    public void delete(Key key) {
        root = delete(root, key);
    }



    // Helper Functions

    // leave the node(to be deleted) without any reference, for garbage collection
    private Node delete(Node x, Key key) {
        if (x == null) {
            return null;
        }
        int comp = x.key.compareTo(key);
        if (comp == 1) {
            x.left = delete(x.left, key);
        }
        if (comp == -1) {
            x.right = delete(x.right, key);
        }else {
            if (x.left == null) {
                return x.right;
            }
            if (x.right == null) {
                return x.left;
            }
            Node temp = x;
            x = min(x.right);
            x.right = deleteMin(x.right);
            x.left = temp.left;
        }
        x.count--;
        return x;
    }

    private Node min(Node x) {
        if (x == null) {
            return null;
        }
        if (x.left == null) {
            return x;
        }
        x.left = min(x.left);
        return x;
    }

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

    private Node put(Node x, Key key, Value value) {
        if (x == null) {
            x = new Node(key, value);
        }
        int comp = x.key.compareTo(key);
        if (comp == 1) {
            x = put(x.left, key, value);
        } else if (comp == -1) {
            x = put(x.right, key, value);
        } else {
            x.value = value;
        }
        x.count++;
        //TODO alternate code if this doesn't work -> x.count = 1+size(x.left)+size(x.right)
        return x;
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


    public static void main(String[] args) {
    }
}
