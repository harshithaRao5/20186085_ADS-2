import java.util.Scanner;
import java.util.Arrays;
/**
 * Class for graph matrix.
 */
class GraphMatrix {
    /**
     * array declaration.
     */
    private String[] tokens;
    /**
     * 2d array declaration.
     */
    private int[][] matrix;
    /**
     * variable declaration.
     */
    private int v;
    /**
     * variable declaration.
     */
    private int e;
    /**
     * Constructs the object.
     */
    GraphMatrix() {
        e = 0;
    }
    /**
     * Constructs the object.
     *
     * @param      scan  The scan
     */
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
    /**
     * Adds an edge.
     *
     * @param      v    the int.
     * @param      w    the int.
     */
    public void addEdge(int v, int w) {
    if(v != w) {
        if(!hasEdge(v, w)) {
        matrix[v][w] = 1;
        matrix[w][v] = 1;
        e++;
        }
    }
    }
    /**
     * Determines if it has edge.
     *
     * @param      v    the int.
     * @param      w    the int.
     *
     * @return     True if has edge, False otherwise.
     */
    public boolean hasEdge(int v, int w) {
        if(matrix[v][w] == 1) {
            return true;
        }
        return false;
    }
    /**
     * display method.
     */
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
/**
 * List of graphs.
 */
class GraphList {
    /**
     * variable declaration.
     */
    private int v;
    /**
     * variable declaration.
     */
    private int e;
    /**
     * array declaration.
     */
    private Bag<Integer>[] adj;
    /**
     * array declaration.
     */
    private String[] tokens;
    /**
     * Constructs the object.
     */
    GraphList() {
    }
    /**
     * Constructs the object.
     *
     * @param      scan  The scan
     */
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
            addEdge(Integer.parseInt(inputs[0]),
                Integer.parseInt(inputs[1]));
        }
    }
    /**
     * method for vertices.
     *
     * @return  vertices.
     */
    public int v() {
        return v;
    }
    /**
     * method for edges.
     *
     * @return edges.
     */
    public int e() {
        return e;
    }
    /**
     * Adds an edge.
     *
     * @param      v    the int.
     * @param      w    the int.
     */
    public void addEdge(int v, int w) {
        if (v != w) {
            adj[v].add(w);
            adj[w].add(v);
            e++;
        } else {
            return;
        }
    }
    /**
     * method for adjacent vertex.
     *
     * @param      v    the int.
     *
     * @return adjacent vertex.
     */
    public Iterable<Integer> adj(int v) {
        return adj[v];
    }
    /**
     * Determines if it has edge.
     *
     * @param      v    the int.
     * @param      w    the int.
     *
     * @return     True if has edge, False otherwise.
     */
    public boolean hasEdge(int v, int w) {
        return true;
    }
    /**
     * Returns a string representation of the object.
     *
     * @return     String representation of the object.
     */
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
/**
 * Class for solution.
 */
public class Solution {
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
