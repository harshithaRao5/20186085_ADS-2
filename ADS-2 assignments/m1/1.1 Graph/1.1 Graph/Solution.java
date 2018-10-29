import java.util.Scanner;
interface Graph {
	public int V();
	public int E();
	public void addEdge(int v, int w);
	public Iterable<Integer> adj(int v);
	public boolean hasEdge(int v, int w);
}
class GraphList implements Graph {
	private int V;
	private int E;
	private Bag<Integer>[] adj;
	GraphList(int v) {
		this.V = v;
		this.E = 0;
		adj = (Bag<Integer>[]) new Bag[V];
		for (int i = 0; i < V; i++) {
			adj[i] = new Bag<Integer>();
		}
	}
	public int V() {
		return V;
	}
	public int E() {
		return E;
	}
	public void addEdge(int v, int w) {
		if(!hasEdge(v, w)) {
			E++;
		}
		if(v==w) {
			return;
		}
		adj[v].add(w);
        adj[w].add(v);
	}
	public Iterable<Integer> adj(int v) {
		return adj[v];
	}
	public boolean hasEdge(int v, int w) {
		for (int i = 0; i < adj[v].size(); i++) {
			if (i == w) {
				return true;
			}
		}
		return false;
	}
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append(V + " vertices, " + E + " edges " + "\n");
		if (E == 0) {
			s.append("No edges");
		}
		for (int v = 0; v < V; v++) {
			s.append(v + ": ");
			for (int w : adj[v]) {
				s.append(w + " ");
			}
			s.append("\n");
		}
		return s.toString();

	}
}
// class GraphMatrix implements Graph {
// 	private int V;
// 	private int E;
// 	private boolean[][] matrix;
// 	GraphMatrix()
// 	public int V() {
// 		return V;
// 	}
// 	public int E() {
// 		return E;
// 	}
// 	public void addEdge(int v, int w) {
// 		matrix[v][w] = true;
// 		matrix[w][v] = true;
// 	}
// 	public Iterable<Integer> adj(int v) {

// 	}
// 	public boolean hasEdge(int v, int w) {

// 	}

// }

public final class Solution {
	private Solution() {

	}
	public static void main(final String[] args) {
		Scanner sc = new Scanner(System.in);
		//SequentialSearchST< stobj = new SequentialSearchST()
		String type = sc.nextLine();
		int v = Integer.parseInt(sc.nextLine());
		int e = Integer.parseInt(sc.nextLine());
		String[] keyNames = sc.nextLine().split(",");
		while (sc.hasNext()) {
			String[] input = sc.nextLine().split(" ");
			switch (type) {
			case "List":
				GraphList globj = new GraphList(v);
				globj.addEdge(Integer.parseInt(input[0]), Integer.parseInt(input[1]));
				System.out.println(globj);
				break;
			}
		}


	}
}