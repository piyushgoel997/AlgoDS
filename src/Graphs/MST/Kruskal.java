package Graphs.MST;

import Misc.UF;
import Queues.MinPQ;
import Queues.Queue;

/**
 * Created by piyus on 10-06-2017 at 12:46.
 */
public class Kruskal {
    private Queue<Edge> mst;

    public Kruskal(EdgeWeightedGraph G) {
        mst = new Queue<>();
        MinPQ<Edge> pq = new MinPQ<>();
        for (Edge e : G.edges()) {
            pq.insert(e);
        }
        UF uf = new UF(G.V());
        while (!pq.isEmpty()) {
            Edge e = pq.deleteMin();
            int v = e.either();
            int w = e.other(v);
            // to check if considering 'e' makes any cycle.
            if (!uf.connected(v, w)) {
                uf.union(v, w);
                mst.enqueue(e);
            }
        }
    }

    public Iterable<Edge> getMST() {
        return mst;
    }
}
