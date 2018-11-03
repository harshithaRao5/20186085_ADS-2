import java.util.Scanner;
import java.util.Arrays;
class PageRank {
    //private String[] adjArray;
	private Digraph digraph;
    private Bag<Integer>[] adj;
	//private int pageR;
	PageRank(Digraph digraph1) {
		this.digraph = digraph1;
		adj = (Bag<Integer>[]) new Bag[digraph.V()];
        for (int v = 0; v < digraph.V(); v++) {
            adj[v] = new Bag<Integer>();
        }
		//this.adjArray = adjArray1;
	}
	public double getPR(int v) {
		int pageR = 1/digraph.V();
		for(int i = 0; i<1000; i++) {
			if(digraph.indegree(v)>0) {
				for(int j=0; j<adj.length;j++) {
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
		Digraph dobj = new Digraph(n);
		while (sc.hasNext()) {
			String[] tokens = sc.nextLine().split(" ");
            for (int i = 1; i < tokens.length; i++) {
                dobj.addEdge(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[i]));
            }
			PageRank pagerank = new PageRank(dobj);
			System.out.println(pagerank);
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
