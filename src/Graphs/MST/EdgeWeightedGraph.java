package Graphs.MST;

import Misc.Bag;

/**
 * Created by piyus on 03-06-2017 at 15:08.
 */
public class EdgeWeightedGraph {

    private Bag<Edge>[] adj; // adjacency list of edges indexed by the vertices.
    private int V; // no. of vertices.
    private int E; // no. of adj

    /**
     *
     * @param V - total number of vertices in the graph
     */
    public EdgeWeightedGraph(int V) {
        this.V = V;
        adj = (Bag<Edge>[]) new Bag[V];
        for (int i = 0; i < V; i++) {
            adj[i] = new Bag<>();
        }
    }

    public void addEdge(Edge edge) {
        E++;
        int v = edge.either();
        adj[v].add(edge);
        adj[edge.other(v)].add(edge);
    }

    /**
     * @return number of vertices.
     */
    public int V() {
        return V;
    }

    /**
     * @return number of adj.
     */
    public int E() {
        return E;
    }

    /**
     *
     * @param v - vertex whose adjacent adj are required.
     * @return Iterable(Bag) containing all the adj adjacent to the vertex 'v'.
     */
    public Iterable<Edge> adj(int v) {
        return adj[v];
    }

    /**
     *
     * @return Iterable(Bag) containing all the adj of the graph.
     */
    public Iterable<Edge> edges() {
        Bag<Edge> allEdges = new Bag<>();
        for (int i = 0; i < V; i++) {
            for (Edge e : adj[i]) {
                allEdges.add(e);
            }
        }
        return allEdges;
    }

}
