public class ConnectedComponents {
    private boolean[] marked;
    private int[] id;
    private int[] size;
    private int count;

    /**
     * Computes the connected components of the undirected graph {@code G}.
     *
     * @param G the undirected graph
     */
    public ConnectedComponents(GraphMatrix G) {
        marked = new boolean[G.V()];
        id = new int[G.V()];
        size = new int[G.V()];
        for (int v = 0; v < G.V(); v++) {
            if (!marked[v]) {
                dfs(G, v);
                count++;
            }
        }
    }

    /**
     * Computes the connected components of the edge-weighted graph {@code G}.
     *
     * @param G the edge-weighted graph
     */


    // depth-first search for a Graph
    private void dfs(GraphMatrix G, int v) {
        marked[v] = true;
        id[v] = count;
        size[count]++;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                dfs(G, w);
            }
        }
    }
    public int id(int v) {

        return id[v];
    }

    public int size(int v) {

        return size[id[v]];
    }

    public int count() {
        return count;
    }

    public boolean connected(int v, int w) {

        return id(v) == id(w);
    }

}
