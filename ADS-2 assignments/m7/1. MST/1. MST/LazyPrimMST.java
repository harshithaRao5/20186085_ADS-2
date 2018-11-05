/**
 * Class for lazy primitive mst.
 */
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
     * @param g the edge-weighted graph
     */
    LazyPrimMST(final EdgeWeightedGraph g) {
        mst = new Queue<Edge>();
        pq = new MinPQ<Edge>();
        marked = new boolean[g.vertices()];
        for (int v = 0; v < g.vertices(); v++) {
            if (!marked[v]) {
                prim(g, v);
            }
        }
        // check optimality conditions

    }
    /**
     * weight method.
     *
     * @param      g     graph
     * @param      s     integer
     */
    private void prim(final EdgeWeightedGraph g, final int s) {
        scan(g, s);
        while (!pq.isEmpty()) {
            Edge e = pq.delMin();
            int v = e.either();
            int w = e.other(v);

            assert marked[v] || marked[w];
            if (marked[v] && marked[w]) {
                continue;
            }
            mst.enqueue(e);
            weight += e.weight();
            if (!marked[v]) {
                scan(g, v);
            }
            if (!marked[w]) {
                scan(g, w);
            }
        }
    }
    /**
     * scan method.
     *
     * @param      g  graph.
     * @param      v  int
     */
    private void scan(final EdgeWeightedGraph g, final int v) {
        assert !marked[v];
        marked[v] = true;
        for (Edge e : g.adj(v)) {
            if (!marked[e.other(v)]) {
                pq.insert(e);
            }
        }
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
