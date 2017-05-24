package Graphs;

import Misc.Bag;

/**
 * Created by piyus on 23-05-2017 at 14:01.
 */
public class Graph {
    // vertex adjacency list implementation

    // total no of Vertices
    private final int V;
    // adjacency list
    private Bag<Integer>[] adj;

    public Graph(int V) {
        this.V = V;
        adj = (Bag<Integer>[]) new Bag[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new Bag<Integer>();
        }
    }

    public void addEdge(int v, int w) {
        adj[v].add(w);
        adj[w].add(v);
    }

    // return the iterator(since bag is an iterable) of all the vertices adjacent to the given vertex
    public Iterable<Integer> adj(int v) {
        return adj[v];
    }
}
