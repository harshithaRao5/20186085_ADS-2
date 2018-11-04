import java.util.Scanner;
import java.util.ArrayList;
class PageRank {
	private Digraph digraph;
	PageRank(Digraph digraph1) {
		this.digraph = digraph1;
	}
	public double getPR(int v) {
		double pageR = 1 / digraph.V();
		Digraph reverse = digraph.reverse();
		ArrayList<Double> initialRank = new ArrayList<Double>();
		for (int i = 0; i < digraph.V(); i++) {
			initialRank.add(pageR);
		}
		//System.out.println(initialRank);
		ArrayList<Double> tempRank = new ArrayList<Double>();
		for (int i = 0; i < initialRank.size(); i++) {
			tempRank.add(initialRank.get(i));
		}
		//System.out.println(tempRank);
		for (int i = 0; i < 1000; i++) {
			for (int j = 0; j < digraph.V(); j++) {
				double tempR = 0.0;
				for (int k : reverse.adj(v)) {
					//System.out.println(k);
					tempR +=  initialRank.get(k) / digraph.outdegree(v);
				}
				tempRank.add(tempR);
			}
		}
		return tempRank.get(v);
	}
	public String toString() {
		String s = "";
		for (int i = 0; i < digraph.V(); i++) {
			s += i + " - " + getPR(i) + "\n";
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
		while (sc.hasNext()) {
			String[] tokens = sc.nextLine().split(" ");
			for (int i = 1; i < tokens.length; i++) {
				dobj.addEdge(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[i]));
				if (tokens.length == 1) {
					for (int j = 0; j < tokens.length; j++) {
						dobj.addEdge(j, i);
					}
				}
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
