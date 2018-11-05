
public class LazyPrimMST {
    /**
     * double value.
     */
    private static final double FLOATING_POINT_EPSILON = 1E-12;
    /**
     * variable declaration.
     */
    private double weight;
    /**
     * variable declaration.
     */
    private Queue<Edge> mst;
    /**
     * variable declaration.
     */
    private boolean[] marked;
    /**
     * variable declaration.
     */
    private MinPQ<Edge> pq;

    /**
     * Compute a minimum spanning tree (or forest) of an edge-weighted graph.
     * @param G the edge-weighted graph
     */
    public LazyPrimMST(final EdgeWeightedGraph G) {
        mst = new Queue<Edge>();
        pq = new MinPQ<Edge>();
        marked = new boolean[G.V()];
        for (int v = 0; v < G.V(); v++) {
            if (!marked[v]) {
                prim(G, v);
            }
        }
        // check optimality conditions

    }
    /**
     * weight method.
     *
     * @param      G     graph
     * @param      s     integer
     */
    private void prim(final EdgeWeightedGraph G, final int s) {
        scan(G, s);
        while (!pq.isEmpty()) {
            Edge e = pq.delMin();
            int v = e.either(), w = e.other(v);

            assert marked[v] || marked[w];
            if (marked[v] && marked[w]) {
                continue;
            }
            mst.enqueue(e);
            weight += e.weight();
            if (!marked[v]) {
                scan(G, v);
            }
            if (!marked[w]) {
                scan(G, w);
            }
        }
    }
    /**
     * scan method
     *
     * @param      G  graph.
     * @param      v  int
     */
    private void scan(final EdgeWeightedGraph G, final int v) {
        assert !marked[v];
        marked[v] = true;
        for (Edge e : G.adj(v))
            if (!marked[e.other(v)]) pq.insert(e);
    }
    /**
     *Iterable edges.
     * @return   iterable edge.
     */
    public Iterable<Edge> edges() {
        return mst;
    }
    /**
     * weight method.
     *
     * @return weight.
     */
    public double weight() {
        return weight;
    }
}