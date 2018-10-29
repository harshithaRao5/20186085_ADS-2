import java.util.Scanner;
import java.util.Arrays;
class GraphMatrix {
    private String[] tokens;
    private int[][] matrix;
    private int v;
    private int e;
    GraphMatrix() {
        e = 0;
    }
    GraphMatrix(Scanner scan) {
        this.v = Integer.parseInt(scan.nextLine());
        matrix = new int[v][v];
        int edge = Integer.parseInt(scan.nextLine());
        tokens = scan.nextLine().split(",");
        for (int i = 0; i < edge; i++) {
            String[] inputs = scan.nextLine().split(" ");
            addEdge(Integer.parseInt(inputs[0]), Integer.parseInt(inputs[1]));
        }
    }

    public void addEdge(int v, int w) {
    if(v != w) {
        if(!hasEdge(v, w)) {
        matrix[v][w] = 1;
        matrix[w][v] = 1;
        e++;
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
        str += v + " vertices, " + e + " edges" + "\n";
        if (e > 0) {
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
class GraphList {
    private int v;
    private int e;
    private Bag<Integer>[] adj;
    private String[] tokens;
    GraphList() {
    }
    GraphList(Scanner scan) {
        this.v = Integer.parseInt(scan.nextLine());
        adj = (Bag<Integer>[]) new Bag[v];
        for (int i = 0; i < v; i++) {
            adj[i] = new Bag<Integer>();
        }
        int e = Integer.parseInt(scan.nextLine());
        tokens = scan.nextLine().split(",");
        for (int i = 0; i < e; i++) {
            String[] inputs = scan.nextLine().split(" ");
            addEdge(Integer.parseInt(inputs[0]), Integer.parseInt(inputs[1]));
        }
    }
    public int v() {
        return v;
    }
    public int e() {
        return e;
    }
    public void addEdge(int v, int w) {
        if (v != w) {
            adj[v].add(w);
            adj[w].add(v);
            e++;
        } else {
            return;
        }
    }
    public Iterable<Integer> adj(int v) {
        return adj[v];
    }
    public boolean hasEdge(int v, int w) {
        return true;
    }
    public String toString() {
            StringBuilder s = new StringBuilder();
            s.append(v + " vertices, " + e + " edges" + "\n");
        if (e > 0) {
            for (int i = 0; i < v; i++) {
                s.append(tokens[i] + ": ");
                for (int j : adj[i]) {
                    s.append(tokens[j] + " ");
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
public class Solution {
    Solution() {
    }
    public static void main(final String[] args) {
        Scanner scan = new Scanner(System.in);
        String type = scan.nextLine();
        switch (type) {
        case "List":
            GraphList lisObj = new GraphList(scan);
            System.out.println(lisObj);
            break;
        case "Matrix":
            GraphMatrix lisMat = new GraphMatrix(scan);
            lisMat.print();
            break;
        default :
        break;
        }
    }
}
