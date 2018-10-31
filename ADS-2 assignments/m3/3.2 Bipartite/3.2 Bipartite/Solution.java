import java.util.Scanner;
class Bipartite {

}
/**
 * client class.
 */
public final class Solution {
	private Solution() {

	}
	public static void main(final String[] args) {
		Scanner sc = new Scanner(System.in);
		int vertices = Integer.parseInt(sc.nextLine());
		int edges = Integer.parseInt(sc.nextLine());
		Graph graph = new Graph(vertices);
		while (sc.hasNext()) {
			String[] input = sc.nextLine().split(" ");
			graph.addEdge(Integer.parseInt(input[0]),
			 Integer.parseInt(input[1]));
		}
		DirectedCycle directedcycle = new DirectedCycle(graph);
		System.out.println(directedcycle);

	}
}