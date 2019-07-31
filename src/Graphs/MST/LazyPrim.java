package Graphs.MST;

import Queues.MinPQ;
import Queues.Queue;

/**
 * Created by piyus on 10-06-2017 at 13:30.
 * Lazy implementation of Prim's Algorithm for finding the MST.
 */
public class LazyPrim {
    private boolean[] marked;
    private Queue<Edge> mst;
    private MinPQ<Edge> pq;

    public LazyPrim(EdgeWeightedGraph G) {
        marked = new boolean[G.V()];
        pq = new MinPQ<>();
        mst = new Queue<>();

        visit(G, 0);
        while (!pq.isEmpty()) { // or while(!mst.size==G.V()-1) or while( not all are marked)
            Edge e = pq.deleteMin();
            int v = e.either();
            int w = e.other(v);
            if (marked[v] && marked[w]) { // ignore the edge if both vertices are marked
                continue;
            }
            mst.enqueue(e);
            if (marked[v]) {
                visit(G, w);
            } else {
                visit(G, v);
            }
        }
    }

    private void visit(EdgeWeightedGraph g, int v) {
        marked[v] = true;
        for (Edge e : g.adj(v)) {
            if (!marked[e.other(v)]) {
                pq.insert(e);
            }
        }
    }
}
