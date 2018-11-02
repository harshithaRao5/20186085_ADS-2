/**
 * Class for directed cycle.
 */
class DirectedCycle {
    /**
     * { var_description }.
     */
    private boolean[] marked;
    /**
     * // marked[v] = has vertex v been marked?.
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
     * Constructs the object.
     *
     * @param      g     { parameter_description }.
     */
    DirectedCycle(final Digraph g) {
        marked  = new boolean[g.vertices()];
        onStack = new boolean[g.vertices()];
        edgeTo  = new int[g.vertices()];
        for (int v = 0; v < g.vertices(); v++) {
            if (!marked[v] && cycle == null) {
                dfs(g, v);
            }
        }
    }
    /**
     * { function_description }.
     *
     * @param      g     { parameter_description }.
     * @param      v     { parameter_description }.
     */
    private void dfs(final Digraph g, final int v) {
        onStack[v] = true;
        marked[v] = true;
        for (int w : g.adj(v)) {

            // short circuit if directed cycle found
            if (cycle != null) {
                return;
            } else if (!marked[w]) {
                edgeTo[w] = v;
                dfs(g, w);
            } else if (onStack[w]) {
                cycle = new Stack<Integer>();
                for (int x = v; x != w; x = edgeTo[x]) {
                    cycle.push(x);
                }
                cycle.push(w);
                cycle.push(v);
                //assert check();
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
        if (cycle != null) {
            return true;
        }
        return false;
    }
    // /**
    //  * { function_description }.
    //  *
    //  * @return     { description_of_the_return_value }
    //  */
    // public Iterable<Integer> cycle() {
    //     return cycle;
    // }
    /**
     * { function_description }.
     *
     * @return     { description_of_the_return_value }.
     */
    // private boolean check() {
    //     if (hasCycle()) {
    //         // verify cycle
    //         int first = -1, last = -1;
    //         for (int v : cycle()) {
    //             if (first == -1) {
    //                 first = v;
    //             }
    //             last = v;
    //         }
    //         if (first != last) {
    //             System.out.println("cycle begins with %d and ends with %d\n"
    //                                + first + last);
    //             return false;
    //         }
    //     }
    //     return true;
    // }
}
