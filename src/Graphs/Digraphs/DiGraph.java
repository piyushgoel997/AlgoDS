package Graphs.Digraphs;

import Graphs.Graph;
import Misc.Bag;

/**
 * Created by piyus on 28-05-2017 at 16:55.
 */
public class DiGraph extends Graph {
    // adjacency list
//    private Bag<Integer>[] adj;
//    private int V;


    public DiGraph(int V) {
        super(V);
    }

    @Override
    public void addEdge(int v, int w) {
        // except the line below the rest of the code is same.
        adj[v].add(w);
    }

    // this function isn't required for graphs but it is for digraphs.
    public DiGraph reverse() {
        DiGraph rev = new DiGraph(V);
        Bag<Integer>[] list = rev.adj;
        for (int i = 0; i < V; i++) {
            for (int j : adj(i)) {
                list[j].add(i);
            }
        }
        return rev;
    }

//    public int numberOfVertices() {
//        return V;
//    }
//
//    // return the iterator(since bag is an iterable) of all the vertices adjacent to the given vertex
//    public Iterable<Integer> adj(int v) {
//        return adj[v];
//    }

    public static void main(String[] args) {
        // code to test reverse
        DiGraph dg = new DiGraph(2);
        dg.addEdge(0, 1);
        dg = dg.reverse();
        for(int i : dg.adj(1)) {
            System.out.print(i);
        }
    }
}
