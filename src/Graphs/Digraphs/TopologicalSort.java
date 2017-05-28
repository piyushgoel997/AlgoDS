package Graphs.Digraphs;

import java.util.Stack;

/**
 * Created by piyus on 28-05-2017 at 16:57.
 */
public class TopologicalSort {

    private boolean[] visited;
    private Stack revPostOreder;

    public TopologicalSort(DiGraph dg) {
        int V = dg.numberOfVertices();
        visited = new boolean[V];
        for (int i = 0; i < V; i++) {
            visited[i] = false;
        }
        dfs(dg, 0);
    }

    public Stack getOrder() {
        return revPostOreder;
    }

    private void dfs(DiGraph dg, int v) {
        visited[v] = true;
        for (int i : dg.adj(v)) {
            if (!visited[i]) {
                dfs(dg, i);
            }
        }
        revPostOreder.push(v);
    }


}
