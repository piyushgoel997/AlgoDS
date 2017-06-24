package Graphs.MincutMaxflow;

import Queues.Queue;

/**
 * Created by piyus on 25-06-2017 at 00:08.
 */
public class FordFulkerson {

    // these arrays will be made and updated in the hasAugmentingPath fn.
    private boolean[] marked;  // true if s->v path is in the residual network.
    private FlowEdge[] edgeTo;  // last edge on s->v path
    private double value;  // value of flow

    public FordFulkerson(FlowNetwork G, int s, int t) {
        value = 0;
        double bottle = Double.POSITIVE_INFINITY;  // bottleneck capacity
        while (hasAugmentingPath(G, s, t)) {
            for (int v = t; v != s; v = edgeTo[v].other(v)) {
                bottle = Math.min(bottle, edgeTo[v].residualCapacityTo(v));
            }

            for (int v = t; v != s; v = edgeTo[v].other(v)) {
                edgeTo[v].addResidualFlowTo(v, bottle);
            }
            value += bottle;
        }
    }

    private boolean hasAugmentingPath(FlowNetwork G, int s, int t) {
        // using bfs.
        edgeTo = new FlowEdge[G.V()];
        marked = new boolean[G.V()];

        Queue<Integer> queue = new Queue<>();
        queue.enqueue(s);
        marked[s] = true;
        while (!queue.isEmpty()) {
            int v = queue.dequeue();
            for (FlowEdge e : G.adj(v)) {
                int w = e.other(v);
                if (e.residualCapacityTo(w) > 0 && !marked[w]) {
                    edgeTo[w] = e;
                    marked[w] = true;
                    queue.enqueue(w);
                }
            }
        }
        return marked[t];  // is t reachable if we start from s. This is what was required.
    }

    public double maxFlowValue() {
        return value;
    }

    /**
     * returns true if the vertex v is in the s side after making the min cut.
     * @param v: vetex to be checked
     * @return
     */
    public boolean inCut(int v) {
        return marked[v];
    }
}
