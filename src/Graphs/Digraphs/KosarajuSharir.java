package Graphs.Digraphs;

import Graphs.DepthFirstOrder;

import java.util.Stack;

/**
 * Created by piyus on 28-05-2017 at 18:13.
 */
public class KosarajuSharir {
    // Kosaraju-Sharir Algo to check strongly connected components
    // Phase 1: Compute post order of reversed graph(G)
    // Phase 2: Run dfs in G using this order.

    private boolean visited[];
    private int id[];
    private int ctr;

    public KosarajuSharir(DiGraph g) {
        ctr = 0;
        int V = g.numberOfVertices();
        visited = new boolean[V];
        for (int i = 0; i < V; i++) {
            visited[i] = false;
        }
        id = new int[V];
        // since this order is in a stack and we are popping out the elements(rather than iterating over them),
        // so the order is automatically reversed.
        // => There is no need to again reverse the depthFirstOrder.
        Stack<Integer> dfp = (new DepthFirstOrder(g.reverse())).depthFirstOrder();
        while (!dfp.isEmpty()) {
            int i = dfp.pop();
            if (!visited[i]) {
                dfs(g, i);
                ctr++;
            }
        }
    }

    private void dfs(DiGraph g, int v) {
        id[v] = ctr;
        visited[v] = true;
        for (int i : g.adj(v)) {
            if (!visited[i]) {
                dfs(g, i);
            }
        }
    }

    public boolean isStronglyConnected(int v, int w) {
        return id[v] == id[w];
    }



    public static void main(String args[]) {
        DiGraph g = new DiGraph(4);
        g.addEdge(0, 1);
        g.addEdge(1, 2);
        g.addEdge(3, 2);
        KosarajuSharir ks = new KosarajuSharir(g);
        System.out.println(ks.isStronglyConnected(0, 2));
        System.out.println(ks.isStronglyConnected(0, 3));
        g.addEdge(2, 0);
        ks = new KosarajuSharir(g);
        System.out.println(ks.isStronglyConnected(0, 2));
    }

}
