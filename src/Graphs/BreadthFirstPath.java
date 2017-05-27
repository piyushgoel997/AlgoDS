package Graphs;

import Queues.Queue;

/**
 * Created by piyus on 26-05-2017 at 22:18.
 */
public class BreadthFirstPath {

    private Boolean[] visited;
    private int[] prevVertex;
    private int start;

    public BreadthFirstPath(Graph graph, int start) {
        this.start = start;
        int V = graph.numberOfVertices();
        this.visited = new Boolean[V];
        this.prevVertex = new int[V];
        for (int i = 0; i < V; i++) {
            visited[i] = false;
        }
        for (int i = 0; i < V; i++) {
            prevVertex[i] = -1;
        }
        bfs(graph, start);
    }

    private void bfs(Graph graph, int start) {
        Queue<Integer> queue = new Queue<>();
        queue.enqueue(start);
        while (!queue.isEmpty()) {
            int v = queue.dequeue();
            for (int i : graph.adj(v)) {
                if (visited[i] == false) {
                    prevVertex[i] = v;
                    visited[i] = true;
                    queue.enqueue(i);
                }
            }
        }
    }
}
