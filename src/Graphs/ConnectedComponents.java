package Graphs;

/**
 * Created by piyus on 27-05-2017 at 00:01.
 */
public class ConnectedComponents {
    private boolean[] visited;
    private int count;
    // gives same id to the connected elements
    private int[] id;

    public ConnectedComponents(Graph g) {
        int V = g.numberOfVertices();
        count = 0;
        visited = new boolean[V];
        id = new int[V];
        for (int i = 0; i < V; i++) {
            visited[i] = false;
        }
        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                dfs(g, i);
                count++;
            }
        }
    }

    private void dfs(Graph g, int i) {
        visited[i] = true;
        id[i] = count;
        for (int j : g.adj(i)) {
            if (!visited[i]) {
                dfs(g, j);
            }
        }
    }

    // returns the no of connected components.
    public int count() {
        return count;
    }

    // returns the id of the connected component to which the vertex v belongs.
    public int id(int v) {
        return id[v];
    }
}
