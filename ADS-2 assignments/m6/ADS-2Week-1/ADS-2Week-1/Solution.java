import java.util.Scanner;
import java.util.Arrays;
class PageRank {
	private Digraph digraph;
	PageRank(Digraph graph) {
		this.digraph = graph;
	}
	public Double getPR(int v) {
		//array to store the initial pr values of each vertex.
		Double[] pageRank = new Double[digraph.V()];
		for (int i = 0; i < digraph.V(); i++) {
			pageRank[i] = 1.0 / digraph.V();
		}
		pageRank = getPrVal(pageRank);
		return pageRank[v];
	}
	public Double[] getPrVal(Double[] pageR) {
		for (int i = 0; i < digraph.V(); i++) {
			if (digraph.outdegree(i) == 0) {
				for (int j = 0; j < digraph.V(); j++) {
					if (i != j) {
						digraph.addEdge(i, j);
					}
				}
			}
		}
		for (int l = 0; l < 1000; l++) {
			Double[] tempArray = new Double[digraph.V()];
			for (int i = 0; i < digraph.V(); i++) {
				// if (digraph.outdegree(i) == 0) {
				// 	for (int j = 0; j < digraph.V(); j++) {
				// 		if(i!=j){
				// 		digraph.addEdge(i, j);
				// 		}
				// 	}
				// }
				Double rank = 0.0;
				for (int k : digraph.reverse().adj(i)) {
					rank += pageR[k] / digraph.outdegree(k);
				}
				tempArray[i] = rank;
			}
			pageR = tempArray;
		}
		return pageR;
	}

	public String toString() {
		String s = "";
		for (int i = 0; i < digraph.V(); i++) {
			s += i + " - " + (getPR(i)) + "\n";
		}
		return s;
	}
}

class WebSearch {

}


public class Solution {

	public static void main(String[] args) {
		// read the first line of the input to get the number of vertices
		Scanner sc = new Scanner(System.in);
		// iterate count of vertices times
		int n = Integer.parseInt(sc.nextLine());
		Digraph dobj = new Digraph(n);
		//Digraph diobj = new Digraph(n);
		while (sc.hasNext()) {
			String[] tokens = sc.nextLine().split(" ");
			for (int i = 1; i < tokens.length; i++) {
				int vertex = Integer.parseInt(tokens[0]);
				dobj.addEdge(vertex, Integer.parseInt(tokens[i]));
				//diobj.addEdge(vertex, Integer.parseInt(tokens[i]));

			}
		}
		System.out.println(dobj);
		PageRank pagerank = new PageRank(dobj);
		System.out.println(pagerank);




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
