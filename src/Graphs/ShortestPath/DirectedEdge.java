package Graphs.ShortestPath;

/**
 * Created by piyus on 10-06-2017 at 17:22.
 */
public class DirectedEdge implements Comparable<DirectedEdge> {

    private int v; // first vertex
    private int w; // second vertex
    private double weight; // weight of the edge

    public DirectedEdge(int v, int w, double weight) {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    public int from() {
        return v;
    }

    public int to() {
        return w;
    }

    public double weight() {
        return weight;
    }

    @Override
    public int compareTo(DirectedEdge o) {
        if (weight > o.weight) {
            return 1;
        }
        if (weight < o.weight) {
            return -1;
        }
        return 0;
    }
}
