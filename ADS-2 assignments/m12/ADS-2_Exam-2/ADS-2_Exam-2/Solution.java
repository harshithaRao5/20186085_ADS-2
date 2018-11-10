import java.util.Scanner;
/**
 * client class.
 */
public final class Solution {
    /**
     * Constructs the object.
     */
    private Solution() {
        //unused constructor.
    }
    /**
     * main method.
     *
     * @param      args  The arguments
     */
    public static void main(final String[] args) {
        // Self loops are not allowed...
        // Parallel Edges are allowed...
        // Take the Graph input here...
        Scanner sc = new Scanner(System.in);
        int vertices = Integer.parseInt(sc.nextLine());
        int edges = Integer.parseInt(sc.nextLine());
        EdgeWeightedGraph ewgobj = new EdgeWeightedGraph(vertices);
        while(edges > 0) {
            String[] input = sc.nextLine().split(" ");
            int v = Integer.parseInt(input[0]);
            int w = Integer.parseInt(input[1]);
            double dist = Double.parseDouble(input[2]);
            Edge edge = new Edge(v, w, dist);
            ewgobj.addEdge(edge);
            edges--;
        }

        String caseToGo = sc.nextLine();
        switch (caseToGo) {
        case "Graph":
            //Print the Graph Object.
            System.out.println(ewgobj);
            break;

        case "DirectedPaths":
            // Handle the case of DirectedPaths, where two integers are given.
            // First is the source and second is the destination.
            // If the path exists print the distance between them.
            // Other wise print "No Path Found."
            String[] srcdest = sc.nextLine().split(" ");
            int source = Integer.parseInt(srcdest[0]);
            int destination = Integer.parseInt(srcdest[1]);
            DijkstraUndirectedSP dijobj = new
            DijkstraUndirectedSP(ewgobj, source);
            if(dijobj.hasPathTo(destination)) {
                System.out.println(dijobj.distTo(destination));
            } else {
                System.out.println("No Path Found.");
            }
            break;

        case "ViaPaths":
            // Handle the case of ViaPaths, where
            // three integers are given.
            // First is the source and second is the via is
            // the one where path should pass throuh.
            // third is the destination.
            // If the path exists print the distance between them.
            // Other wise print "No Path Found."
            String[] viapath = sc.nextLine().split(" ");
            int src = Integer.parseInt(viapath[0]);
            int via = Integer.parseInt(viapath[1]);
            int dest = Integer.parseInt(viapath[2]);
            DijkstraUndirectedSP dijjobj = new DijkstraUndirectedSP(ewgobj, src);
            DijkstraUndirectedSP dobj = new DijkstraUndirectedSP(ewgobj, via);
            String path = "";
            if(dijjobj.hasPathTo(via) && dobj.hasPathTo(dest)) {
                for(int i: dijjobj.pathTo(via)) {
                    path += i +" ";
                }
                for(int j:dobj.pathTo(dest)) {
                    path += j +" ";
                }
                path = path + viapath[2];
                double distance = dijjobj.distTo(via) + dobj.distTo(dest);
                System.out.println(path);
                System.out.println(distance);
            } else {
                System.out.println("No Path Found.");
            }
            break;

        default:
            break;
        }

    }
}