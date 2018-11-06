/**
 * Class for edge weighted graph.
 */
public class EdgeWeightedGraph {
    /**
     * variable vertex.
     */
    private int vert;
    /**
     * variable edge.
     */
    private int edges;
    /**
     * bag of adjacent vertices list.
     */
    private Bag<Edge>[] adj;
    /**
     * Constructs the object.
     *
     * @param  v1 integer vertex.
     */
    public EdgeWeightedGraph(final int v1) {
        this.vert = v1;
        this.edges = 0;
        adj = (Bag<Edge>[]) new Bag[v1];
        for (int i = 0; i < v1; i++) {
            adj[i] = new Bag<Edge>();
        }
    }

    /**
     * return vertices.
     *
     * @return vertex count
     */
    public int vertices() {
        return this.vert;
    }
    /**
     * return edges.
     *
     * @return edge count
     */
    public int edges() {
        return this.edges;
    }
    /**
     * Adds an edge.
     *
     * @param      e Edge object
     */
    public void addEdge(final Edge e) {
        int v = e.either();
        int w = e.other(v);
        adj[v].add(e);
        adj[w].add(e);
        edges++;
    }
    /**
     * returns adjacent vertex.
     *
     * @param      v integer.
     *
     * @return  adjacent vertex.
     */
    public Iterable<Edge> adj(final int v) {

        return adj[v];
    }
    /**
     * degree that gives size of adjacent list.
     *
     * @param      v vertex.
     *
     * @return degree.
     */
    public int degree(final int v) {

        return adj[v].size();
    }
    /**
     * edges that iterates and add.
     *
     * @return iterable list.
     */
    public Iterable<Edge> edge() {
        Bag<Edge> list = new Bag<Edge>();
        for (int v = 0; v < vert; v++) {
            int selfLoops = 0;
            for (Edge e : adj(v)) {
                if (e.other(v) > v) {
                    list.add(e);
                } else if (e.other(v) == v) {
                    if (selfLoops % 2 == 0) {
                     list.add(e);
                    }
                    selfLoops++;
                }
            }
        }
        return list;
    }

}
