import java.util.Scanner;
/**
 * Class for graph matrix.
 */
class GraphMatrix {
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
    GraphMatrix(final Scanner scan) {
        this.v = Integer.parseInt(scan.nextLine());
        matrix = new int[v][v];
        String[] inputs = scan.nextLine().split(" ");
        addEdge(Integer.parseInt(inputs[0]), Integer.parseInt(inputs[1]));
    }
    /**
     * Adds an edge.
     *
     * @param      v1    the int.
     * @param      w1    the int.
     */
    public void addEdge(final int v1, final int w1) {
        if (v1 != w1) {
            if (!hasEdge(v1, w1)) {
                matrix[v1][w1] = 1;
                matrix[w1][v1] = 1;
                e++;
            }
        }
    }
    /**
     * method for vertices.
     *
     * @return  vertices.
     */
    public int V() {
        return v;
    }
    /**
     * method for edges.
     *
     * @return edges.
     */
    public int E() {
        return e;
    }
    /**
     * Determines if it has edge.
     *
     * @param      v1    the int.
     * @param      w1    the int.
     *
     * @return     True if has edge, False otherwise.
     */
    public boolean hasEdge(final int v1, final int w1) {
        if (matrix[v1][w1] == 1) {
            return true;
        }
        return false;
    }
    /**
     * method for adjacent vertex.
     *
     * @param      v1    the int.
     *
     * @return adjacent vertex.
     */
    public int[] adj(final int v1) {
        return matrix[v1];
    }
}