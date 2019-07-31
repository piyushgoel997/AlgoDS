package Graphs.MST;

/**
 * Created by piyus on 03-06-2017 at 14:57.
 */
public class Edge implements Comparable<Edge> {

    private int v;
    private int w;
    private double weight;

    /**
     * Create a new Edge.
     * @param v - first vertex
     * @param w - second vertex
     * @param weight - weight of the edge
     */
    public Edge(int v, int w, double weight) {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    /**
     * returns either of the end point(vertex).
     * @return either of the end point(vertex).
     */
    public int either() {
        return v;
    }

    /**
     * returns the other end point(vertex) of the edge
     * @param v - known end point(vertex) of the edge.
     * @return w - other end point(vertex) of the edge.
     */
    public int other(int v) {
        if (v == this.v) {
            return w;
        }
        return v;
    }

    /**
     *
     * @return weight of the edge.
     */
    public double weight() {
        return weight;
    }

    @Override
    public int compareTo(Edge o) {
        if (this.weight == o.weight) {
            return 0;
        }
        if (this.weight > o.weight) {
            return 1;
        }
        return -1;
    }
}
