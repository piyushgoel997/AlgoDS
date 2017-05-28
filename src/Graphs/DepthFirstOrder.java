package Graphs;

import java.util.Stack;

/**
 * Created by piyus on 28-05-2017 at 20:03.
 */
public class DepthFirstOrder {

    private Stack<Integer> postOrder;
    private boolean[] visited;

    public DepthFirstOrder(Graph graph) {
        int V = graph.numberOfVertices();
        visited = new boolean[V];
        for (int i = 0; i < V; i++) {
            visited[i] = false;
        }
        postOrder = new Stack<>();
        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                dfs(graph, i);
            }
        }
    }

    private void dfs(Graph graph, int i) {
        visited[i] = true;
        for (int j : graph.adj(i)) {
            if (!visited[j]) {
                dfs(graph, j);
            }
        }
        postOrder.push(i);
    }

    public Stack<Integer> depthFirstOrder() {
        return postOrder;
    }
}
