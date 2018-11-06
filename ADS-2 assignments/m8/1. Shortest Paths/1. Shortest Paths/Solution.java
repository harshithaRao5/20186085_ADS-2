import java.util.Scanner;
import java.util.HashMap;
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
        Scanner sc = new Scanner(System.in);
        String[] input = sc.nextLine().split(" ");
        String[] stations = sc.nextLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int m = Integer.parseInt(input[1]);
        EdgeWeightedGraph ewgobj = new EdgeWeightedGraph(n);
        HashMap<String, Integer> hashobj = new HashMap<String, Integer>();
        for (int i = 0; i < n; i++) {
            hashobj.put(stations[i], i);
        }
        while (m > 0) {
            String[] distance = sc.nextLine().split(" ");
            Edge edge = new Edge(hashobj.get(distance[0]),
            hashobj.get(distance[1]), Double.parseDouble(distance[2]));
            ewgobj.addEdge(edge);
            m--;
        }
        int queries = Integer.parseInt(sc.nextLine());
        while (queries > 0) {
            String[] srcDest = sc.nextLine().split(" ");
            int source = hashobj.get(srcDest[0]);
            DijkstraUndirectedSP dijkstra = new
            DijkstraUndirectedSP(ewgobj, source);
            if (dijkstra.hasPathTo(hashobj.get(srcDest[1]))) {
                System.out.println(
                    (int) dijkstra.distTo(hashobj.get(srcDest[1])));
            }
            queries--;
        }
    }
}

