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
	String[] keyNames;

	GraphList(int v,String[] keyNames1, int e) {
		this.V = v;
		this.E = e;
		keyNames=keyNames1;
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
		if (!hasEdge(v, w)) {
			E++;
		}
		if (v == w) {
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
		s.append(V + " vertices, " + E + " edges" + "\n");
		if (E > 0) {
			for (int i = 0; i < V; i++) {
				s.append(keyNames[i]+ ": ");
				for (int w : adj[i]) {
					s.append(keyNames[w] + " ");
				}
				s.append("\n");
			}
			return s.toString();
		} else {
			s.append("No edges");
			return s.toString();
		}
	}
}
class GraphMatrix {
	private int V;
	private int E;
	private int[][] matrix;
	String[] keyNames;
	GraphMatrix(int V, String[] keyNames1) {
        this.V = V;
        this.E = 0;
        keyNames=keyNames1;
        this.matrix = new int[V][V];
    }
	public int V() {
		return V;
	}
	public int E() {
		return E;
	}
	public void addEdge(int v, int w) {
		if (v != w) {
			if (!hasEdge(v, w)) {
				matrix[v][w] = 1;
				matrix[w][v] = 1;
				E++;
			}
		}
	}
	public boolean hasEdge(int v, int w) {
		if(matrix[v][w] == 1) {
            return true;
        }
        return false;
	}
	public void print() {
        String str = "";
        str += V + " vertices, " + E + " edges" + "\n";
        if (E > 0) {
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[0].length; j++) {
                    str += matrix[i][j] + " ";
                }
                str += "\n";
            }
            System.out.println(str);
        } else {
             str += "No edges";
            System.out.println(str);
        }
    }

}

public final class Solution {
	private Solution() {

	}
	public static void main(final String[] args) {
		Scanner sc = new Scanner(System.in);
		String type = sc.nextLine();
		int v = Integer.parseInt(sc.nextLine());
		int e = Integer.parseInt(sc.nextLine());
		String[] keyNames = sc.nextLine().split(",");
		while (sc.hasNext()) {
			String[] input = sc.nextLine().split(" ");
			switch (type) {
			case "List":
				GraphList globj = new GraphList(v,keyNames, e);
				globj.addEdge(Integer.parseInt(input[0]), Integer.parseInt(input[1]));
				System.out.println(globj);
				break;
			case "Matrix":
				GraphMatrix gmobj = new GraphMatrix(v,keyNames);
				gmobj.addEdge(Integer.parseInt(input[0]), Integer.parseInt(input[1]));
				gmobj.print();
			}

		}


	}
}