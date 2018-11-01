import java.util.Scanner;
import java.io.File;
/**
 * Class for solution.
 */
public final class Solution {
	/**
	 * Constructs the object.
	 */
	private Solution() {
		//unused constructor.
	}
	/**
	 * main method.
	 *
	 * @param      args  The arguments
	 */
	public static void main(final String[] args) {
		//Scanner sc = new Scanner(System.in);
		String synset = StdIn.readString();
		// System.out.println(synset);
		String hypernym = StdIn.readString();
		String type = StdIn.readString();
		try {
			if (type.equals("Graph")) {
				WordNet wordnet = new WordNet(synset, hypernym);

			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		if(type.equals("Queries")) {
			String[] queryNoun = StdIn.readString().split(" ");
			if(queryNoun[0].equals("null")) {
				System.out.println("IllegalArgumentException");
			}
		}

	}
}