package Graphs.ShortestPath;


/**
 * Created by piyus on 17-06-2017 at 23:54.
 */
public class AcyclicSP {
    private DirectedEdge[] edgeTo;
    private double[] distTo;

    /**
     * @param G     - graph in which shortest paths need to be found
     * @param start - starting vertex of the graph
     */
    public AcyclicSP(EdgeWeightedDigraph G, int start) {
        edgeTo = new DirectedEdge[G.V()];
        distTo = new double[G.V()];
        for (int i = 0; i < distTo.length; i++) {
            distTo[i] = Double.POSITIVE_INFINITY;
        }
        distTo[start] = 0;
        //TODO make topological for EdgeWeightedDigraphs also.
//        TopologicalSort t = new TopologicalSort(G);
//        for (int v : t.getOrder()) {
//            for (DirectedEdge e : G.adj(v)) {
//                relax(e);
//            }
//        }
    }


    private void relax(DirectedEdge e) {
        int v = e.from();
        int w = e.to();
        if (distTo[w] > distTo[v] + e.weight()) {
            distTo[w] = distTo[v] + e.weight();
            edgeTo[w] = e;
        }
    }
}
