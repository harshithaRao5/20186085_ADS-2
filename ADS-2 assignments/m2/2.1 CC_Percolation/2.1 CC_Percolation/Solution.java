import java.util.Scanner;
public final class Solution {
    private Solution() {

    }
    public static void main(final String[] args) {
        Scanner sc = new Scanner(System.in);
        GraphMatrix gmobj = new GraphMatrix(sc);
        ConnectedComponents ccobj = new ConnectedComponents(gmobj);
        System.out.println(ccobj.connect());
    }
}