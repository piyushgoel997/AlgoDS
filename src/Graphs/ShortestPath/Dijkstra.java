package Graphs.ShortestPath;

import Queues.IndexedMinPQ;
import Queues.MinPQ;

/**
 * Created by piyus on 11-06-2017 at 22:57.
 */
public class Dijkstra {

    private double[] distTo; // distTo[i] = dist b/w the vertex 'i' and the starting point.
    private DirectedEdge[] edgeTo; // edgeTo[i] = the edge that leads to the vertex 'i' in the Shortest Path Tree.
    private IndexedMinPQ<Double> pq; // vertex indexed and keys are the min distance from starting point encountered upto that point in time.

    public Dijkstra(EdgeWeightedDigraph G, int s) {
        int V = G.V();
        distTo = new double[V];
        edgeTo = new DirectedEdge[V];
        distTo[0] = 0.0;
        for (int i = 1; i < V; i++) {
            distTo[i] = Double.POSITIVE_INFINITY;
        }
        pq.insert(s, 0.0);
        while (!pq.isEmpty()) {
            int v = pq.deleteMin();
            for (DirectedEdge e : G.adj(v)) {
                relax(e);
            }
        }
    }

    private void relax(DirectedEdge e) {
        int v = e.from();
        int w = e.to();
        if (distTo[w] > distTo[v] + e.weight()) {
            distTo[w] = distTo[v] + e.weight();
            edgeTo[w] = e;
            if (pq.contains(w)) {
                pq.changeKey(w, distTo[w]);
            } else {
                pq.insert(w, distTo[w]);
            }
        }
    }
}
