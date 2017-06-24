package Graphs.MincutMaxflow;

import Misc.Bag;

/**
 * Basically a graph made using Flow edge.
 * Created by piyus on 24-06-2017 at 23:50.
 */
public class FlowNetwork {
    private final int V;
    private Bag<FlowEdge> adj[];


    public FlowNetwork(int V) {
        this.V = V;
        adj = (Bag<FlowEdge>[]) new Object[V];
        for (int i = 0; i < V; i++) {
            adj[i] = new Bag<>();
        }
    }

    public void addEdge(FlowEdge e) {
        int v = e.from();
        int w = e.to();
        adj[v].add(e);
        adj[w].add(e);
    }

    public Iterable<FlowEdge> adj(int v) {
        return adj[v];
    }

    public int V() {
        return V;
    }
}
