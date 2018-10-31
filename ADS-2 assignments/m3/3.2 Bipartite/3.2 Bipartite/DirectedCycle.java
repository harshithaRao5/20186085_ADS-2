/**
 * Class for directed cycle.
 */
public class DirectedCycle {
    /**
     * { var_description }.
     */
    private boolean[] marked;
    /**
     * { var_description }.
     */
    private int[] edgeTo;
    /**
     * { var_description }.
     */
    private boolean[] onStack;
    /**
     * { var_description }.
     */
    private Stack<Integer> cycle;
    /**
     * { var_description }
     */
    private int vertices;
    /**
     * { var_description }
     */
    private boolean isbipartite = false;
    /**
     * Determines whether the digraph {@code G} has a directed cycle and, if so
     * finds such a cycle.
     * @param G the digraph
     */
    public DirectedCycle(final Graph G) {
        this.vertices = 0;
        marked  = new boolean[G.V()];
        onStack = new boolean[G.V()];
        edgeTo  = new int[G.V()];
        for (int v = 0; v < G.V(); v++)
            if (!marked[v] && cycle == null) dfs(G, v);
    }
    /**
     * { function_description }.
     *
     * @param      G     { parameter_description }
     * @param      v     { parameter_description }
     */
    private void dfs(final Graph G, final int v) {
        isbipartite = !isbipartite;
        onStack[v] = true;
        marked[v] = true;
        for (int w : G.adj(v)) {

            // short circuit if directed cycle found
            if (cycle != null) return;

            // found new vertex, so recur
            else if (!marked[w]) {
                edgeTo[w] = v;
                dfs(G, w);
            }

            // trace back directed cycle
            else if (onStack[w]) {
                cycle = new Stack<Integer>();
                for (int x = v; x != w; x = edgeTo[x]) {
                    cycle.push(x);
                }
                cycle.push(w);
                cycle.push(v);

            }
        }
        onStack[v] = false;
    }
    /**
     * Determines if it has cycle.
     *
     * @return     True if has cycle, False otherwise.
     */
    public boolean hasCycle() {
        return cycle != null;
    }
    /**
     * { function_description }.
     *
     * @return     { description_of_the_return_value }
     */
    public Iterable<Integer> cycle() {
        return cycle;
    }
    /**
     * Determines if bipartite.
     *
     * @return     True if bipartite, False otherwise.
     */
    public boolean isBipartite() {
        return isbipartite;
    }
}

