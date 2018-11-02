public class SAP {
    private Digraph graph;
    // constructor takes a digraph (not necessarily a DAG)
    int distanceGlobal;

    public SAP(Digraph graph) {
        this.graph = graph;
        this.distanceGlobal = 0;
    }
    // length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
    public int length(Iterable<Integer> v, Iterable<Integer> w) {
        ancestor(v, w);
        return distanceGlobal;
    }

    // a common ancestor that participates in shortest ancestral path; -1 if no such path
    public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
        BreadthFirstDirectedPaths bfsV = new BreadthFirstDirectedPaths(graph, v);
        BreadthFirstDirectedPaths bfsW = new BreadthFirstDirectedPaths(graph, w);
        distanceGlobal = Integer.MAX_VALUE;
        int ancestors = -1;
        for (int i = 0; i < graph.V(); i++) {
            if (bfsV.hasPathTo(i) && bfsW.hasPathTo(i)) {
                int distance = bfsV.distTo(i) + bfsW.distTo(i);
                if (distance < distanceGlobal) {
                    //shortPath = distance;
                    distanceGlobal = distance;
                    ancestors = i;
                }
                // return ancestor;
            }
        }
        return ancestors;
    }

    // // do unit testing of this class
    // public static void main(String[] args)
}