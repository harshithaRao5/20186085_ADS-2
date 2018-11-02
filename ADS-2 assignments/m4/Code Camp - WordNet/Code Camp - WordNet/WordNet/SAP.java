public class SAP {
    private Digraph graph;
    // constructor takes a digraph (not necessarily a DAG)
    public SAP(Digraph graph) {
        this.graph = graph;
    }

    // // length of shortest ancestral path between v and w; -1 if no such path
    // public int length(int v, int w) {

    // }

    // // a common ancestor of v and w that participates in a shortest ancestral path; -1 if no such path
    // public int ancestor(int v, int w) {

    // }

    // length of shortest ancestral path between any vertex in v and any vertex in w; -1 if no such path
    public int length(Iterable<Integer> v, Iterable<Integer> w) {
        BreadthFirstDirectedPaths bfsV = new BreadthFirstDirectedPaths(graph, v);
        BreadthFirstDirectedPaths bfsW = new BreadthFirstDirectedPaths(graph, w);
        int shortPath = Integer.MAX_VALUE;
        for(int i = 0; i < graph.V(); i++) {
            if(bfsV.hasPathTo(i) && bfsW.hasPathTo(i)) {
                int distance = bfsV.distTo(i)+bfsW.distTo(i);
                if(distance < shortPath) {
                    shortPath = distance;
                    return shortPath;
                }
            }
        }
        return -1;
    }

    // a common ancestor that participates in shortest ancestral path; -1 if no such path
    public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
        BreadthFirstDirectedPaths bfsV = new BreadthFirstDirectedPaths(graph, v);
        BreadthFirstDirectedPaths bfsW = new BreadthFirstDirectedPaths(graph, w);
        int shortPath = Integer.MAX_VALUE;
        int ancestor = 0;
        for(int i = 0; i < graph.V(); i++) {
            if(bfsV.hasPathTo(i) && bfsW.hasPathTo(i)) {
                int distance = bfsV.distTo(i)+bfsW.distTo(i);
                if(distance < shortPath) {
                    shortPath = distance;
                    ancestor = i;
                    return ancestor;
                }
            }
        }
        return -1;
    }

    // // do unit testing of this class
    // public static void main(String[] args)
}