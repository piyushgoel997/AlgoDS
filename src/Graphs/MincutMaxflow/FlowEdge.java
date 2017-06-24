package Graphs.MincutMaxflow;

/**
 * Created by piyus on 24-06-2017 at 23:43.
 */
public class FlowEdge {
    int v; // from
    int w; // to
    double flow;
    double capacity;

    public FlowEdge(int v, int w, double capacity) {
        this.v = v;
        this.w = w;
        this.capacity = capacity;
        flow = 0;
    }

    public int from() {
        return v;
    }

    public int to() {
        return w;
    }

    public int other(int v) {
        if (v == this.v) {
            return w;
        }
        return v;
    }

    public double capacity() {
        return capacity;
    }

    public double flow() {
        return flow;
    }

    public double residualCapacityTo(int v) {
        if (v == this.v) {
            return capacity - flow;
        }
        return flow;
    }

    public void addResidualFlowTo(int v, double delta) {
        if (v == this.v) {
            flow -= delta;
            return;
        }
        flow += delta;
    }
}
