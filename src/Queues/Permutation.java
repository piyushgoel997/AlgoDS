package Queues;

import edu.princeton.cs.algs4.StdIn;

/**
 * Created by piyus on 23-03-2017.
 */
public class Permutation {
    public static void main(String[] args) {
//        int k = StdIn.readInt();
        RandomizedQueue<String> rq = new RandomizedQueue<>();
        int k = Integer.parseInt(args[0]);
        for (int i = 0; i < k; i++) {
            rq.enqueue(StdIn.readString());
        }
        for (int i = 0; i < k; i++) {
            System.out.println(rq.dequeue());
        }
    }
}
