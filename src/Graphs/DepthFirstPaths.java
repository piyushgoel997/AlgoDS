package Graphs;

import java.util.Stack;

/**
 * Created by piyus on 25-05-2017 at 00:48.
 */
public class DepthFirstPaths {

    // true for the vertices which have been visited at least once. False otherwise.
    private Boolean[] visited;
    // vertex used to reach the vertex at the index => denotes an edge
    private int[] prevVertex;
    // starting vertex
    private int start;

    public DepthFirstPaths(Graph graph, int start) {
        int V = graph.numberOfVertices();
        this.start = start;
        this.visited = new Boolean[V];
        this.prevVertex = new int[V];
        for (int i = 0; i < V; i++) {
            visited[i] = false;
        }
        for (int i = 0; i < V; i++) {
            prevVertex[i] = -1;
        }
        dfs(graph, start);
    }

    private void dfs(Graph graph, int start) {
        if (visited[start]) {
            return;
        }
        visited[start] = true;
        for (int i : graph.adj(start)) {
            dfs(graph, i);
            prevVertex[i] = start;
        }
    }

    // checks if the vertex 'v' has a path to the starting vertex, or vice versa.
    public boolean hasPathTo(int v) {
        return visited[v];
    }

    // returns an iterable(stack) containing the path from start to vertex 'v', if it exists.
    public Iterable pathTo(int v) {
        if (!visited[v]) {
            return null;
        }
        Stack<Integer> path = new Stack<>();
        while (v != start) {
            path.push(v);
            v = prevVertex[v];
        }
        path.push(start);
        return path;
    }

}
