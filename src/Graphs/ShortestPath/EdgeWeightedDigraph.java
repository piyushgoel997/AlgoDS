package Graphs.ShortestPath;

import Misc.Bag;

/**
 * Created by piyus on 10-06-2017 at 17:22.
 */
public class EdgeWeightedDigraph {
    // vertex indexed adjacency list.
    private Bag<DirectedEdge>[] adj;
    private int E; // number of edges
    private int V; // number of vertices

    public EdgeWeightedDigraph(int V) {
        E = 0;
        this.V = V;
        adj = (Bag<DirectedEdge>[]) new Object[V];
        for (int i = 0; i < V; i++) {
            adj[i] = new Bag<>();
        }
    }

    public void addEdge(DirectedEdge e) {
        E++;
        adj[e.from()].add(e);
    }

    public int E() {
        return E;
    }

    public int V() {
        return V;
    }

    /**
     *
     * @param v - the vertices whose adjacency list needs to be returned.
     * @return an iterable containing the directed edges starting from v.
     */
    public Iterable<DirectedEdge> adj(int v) {
        return adj[v];
    }

    public Iterable<DirectedEdge> edges() {
        Bag<DirectedEdge> allEdges = new Bag<>();
        for (int i = 0; i < V; i++) {
            for (DirectedEdge e : adj[i]) {
                allEdges.add(e);
            }
        }
        return allEdges;
    }

}
