package Graphs;

/**
 * Created by piyus on 27-05-2017 at 12:07.
 */
public class Bipartite {
    // used to check if a graph is bipartite

    private boolean bipartite;
    private boolean[] visited;
    private boolean[] set;


    public Bipartite(Graph graph) {
        int num = graph.numberOfVertices();
        visited = new boolean[num];
        set = new boolean[num];
        for (int i = 0; i < num; i++) {
            visited[i] = false;
        }
        set[0] = true;
        bipartite = dfs(graph, 0);
    }

    private boolean dfs(Graph g, int v) {
        visited[v] = true;
        for (int i : g.adj(v)) {
            if (visited[i]) {
                if (set[i] == set[v]) {
                    return false;
                }
            } else {
                visited[i] = true;
                set[i] = !set[v];
                if (!dfs(g, i)) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isBipartite() {
        return bipartite;
    }

    public static void main(String[] args) {
        int V = 4;
        Graph g = new Graph(V);
        for (int i = 0; i < V - 1; i++) {
            g.addEdge(i, i + 1);
        }
        g.addEdge(V - 1, 0);
        Bipartite b = new Bipartite(g);
        System.out.print(b.isBipartite());
    }
}
