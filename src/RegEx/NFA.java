package RegEx;

import Graphs.Digraphs.DiGraph;
import Misc.Bag;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.DirectedDFS;

import java.util.Stack;

/**
 * Created by piyus on 23-07-2017 at 12:00.
 */
public class NFA {
    private int M;
    private Digraph G;
    private char[] regEx;
    public NFA(String regEx) {
        this.regEx = regEx.toCharArray();
        this.G = buildNFA();
        this.M = regEx.length();
    }

    public boolean recognizes(String txt) {
        Bag<Integer> pc = new Bag<Integer>();  // currently reachable states via eps-transitions
        DirectedDFS dfs = new DirectedDFS(G, 0); // for the starting states
        for (int v = 0; v < G.V(); v++) {
            if (dfs.marked(v)) {  // check if the vertex v of the graph is reachable from the 0th vertex
                pc.add(v);
            }
        }

        for (int i = 0; i < txt.length(); i++) {
            Bag<Integer> match = new Bag<Integer>();  // states reachable after after scanning the ith char
            for (int v : pc) {
                if (v == M) continue;
                if (regEx[v] == txt.charAt(i) || regEx[v] == '.') {
                    match.add(v + 1);
                }
            }

            dfs = new DirectedDFS(G, match);  // build a dfs from the new possible positions
            pc = new Bag<Integer>();  // update the reachable stated vis the eps-transitions.
            for (int v = 0; v < G.V(); v++) {
                if (dfs.marked(v)) pc.add(v);
            }
        }
        for (int v : pc) {
            if (v == M) {  // if the accept state is reachable when the text ends then the regex was found
                return true;
            }
        }
        return false;
    }

    private Digraph buildNFA() {
        Digraph G = new Digraph(M + 1);
        Stack<Integer> ops = new Stack<Integer>();  // stack for parenthesis and or -> ( ) |
        for (int i = 0; i < M; i++) {
            int lp = i;  // idx of last ans currently open parenthesis.
            if (this.regEx[i] == '(' || this.regEx[i] == '|') {  // for ( or | push in the stack
                ops.push(i);
            } else if (this.regEx[i] == ')') {  // when ) is encountered, the step for |
                int or = ops.pop();
                if (this.regEx[i] == '|') {  // execute the step on if the parenthesis contains |
                    lp = ops.pop();
                    G.addEdge(lp, or + 1);
                    G.addEdge(or, i);
                } else {  // otherwise update lp. since if it wasn't | then it must be (
                    lp = or;
                }
            }
            if (i < M - 1 && this.regEx[i + 1] == '*') {  // add the two left links in the case of *
                G.addEdge(lp, i + 1);
                G.addEdge(i + 1, lp);
            }
            if (this.regEx[i] == '(' || this.regEx[i] == '*' || this.regEx[i] == ')') {  // add an eps-transition to the next char in the foll cases
                G.addEdge(i, i + 1);
            }
        }
        return G;
    }
}
