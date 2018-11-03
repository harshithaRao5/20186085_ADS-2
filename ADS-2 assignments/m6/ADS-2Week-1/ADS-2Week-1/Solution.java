import java.util.Scanner;
import java.util.ArrayList;
class PageRank {
    private String[] adjArray;
	private Digraph digraph;
	//private int pageR;
	PageRank(Digraph digraph1, String[] adjArray1) {
		this.digraph = digraph1;
		this.adjArray = adjArray1;


	}
	public double getPR(int v) {
		//BreadthFirstDirectedPaths bfs = new BreadthFirstDirectedPaths(digraph, v);
		// ArrayList<Integer> adjarr = new ArrayList<Integer>();
		// adjarr.addAll(digraph.adj(v).next());
		int pageR = 1/digraph.V();
		for(int i = 0; i<1000; i++) {
			if(digraph.indegree(v)>0) {
				for(int j=0; j<adjArray.length;j++) {
				pageR += getPR(j)/digraph.outdegree(v);
			}
			}
		}
		if(digraph.indegree(v) == 0) {
			digraph.addEdge(v, digraph.V());
		}
		return pageR;
	}
	public String toString() {
		System.out.println(digraph);
		System.out.println();
		String s = "";
		for(int i = 0; i < digraph.V();i++) {
			s += digraph.V() + " - " + getPR(digraph.V());
		}
		return s;
	}

}

class WebSearch {

}

/**
 * Class for solution.
 */
public final class Solution {
	/**
	 * Constructs the object.
	 */
	private Solution() {

	}
	/**
	 * main method.
	 *
	 * @param      args  The arguments
	 */
	public static void main(final String[] args) {
		// read the first line of the input to get the number of vertices
		Scanner sc = new Scanner(System.in);
		// iterate count of vertices times
		int n = Integer.parseInt(sc.nextLine());
		while (n > 0) {
			String[] tokens = sc.nextLine().split(" ");
			int vertex = Integer.parseInt(tokens[0]);
			String[] adjVertex = tokens[1].split(" ");
			Digraph dobj = new Digraph(vertex);
			for (int i = 0; i < adjVertex.length; i++) {
				dobj.addEdge(vertex, Integer.parseInt(adjVertex[i]));
			}
			PageRank pagerank = new PageRank(dobj,adjVertex);
			n--;
		}

		// to read the adjacency list from std input
		// and build the graph


		// Create page rank object and pass the graph object to the constructor

		// print the page rank object

		// This part is only for the final test case

		// File path to the web content
		String file = "WebContent.txt";

		// instantiate web search object
		// and pass the page rank object and the file path to the constructor

		// read the search queries from std in
		// remove the q= prefix and extract the search word
		// pass the word to iAmFeelingLucky method of web search
		// print the return value of iAmFeelingLucky

	}
}
