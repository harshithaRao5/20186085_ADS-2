import java.util.Scanner;
/**
 * client class.
 */
public final class Solution {
    /**
     * Constructs the object.
     */
    private Solution() {

    }
    /**
     * main method.
     * @param      args  The arguments
     */
    public static void main(final String[] args) {
        Scanner sc = new Scanner(System.in);
        int vertices = Integer.parseInt(sc.nextLine());
        int edges = Integer.parseInt(sc.nextLine());
        EdgeWeightedGraph edgeweightobj = new EdgeWeightedGraph(vertices);
        while (sc.hasNext()) {
            String[] tokens = sc.nextLine().split(" ");
            int i = Integer.parseInt(tokens[0]);
            int j = Integer.parseInt(tokens[1]);
            double weight = Double.parseDouble(tokens[2]);
            Edge edgeobj = new Edge(i, j, weight);
            edgeweightobj.addEdge(edgeobj);
        }
        LazyPrimMST mstobj = new LazyPrimMST(edgeweightobj);
        System.out.printf("%.5f\n", mstobj.weight());
    }
}
