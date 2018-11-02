import java.util.Scanner;
import java.io.File;
import java.util.Arrays;
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
		// Scanner sc = new Scanner(System.in);
		String synset = StdIn.readLine();
		// System.out.println(synset);
		String hypernym = StdIn.readLine();
		String type = StdIn.readLine();
		try {
			if (type.equals("Graph")) {
				WordNet wordnet = new WordNet(synset, hypernym);
				wordnet.display();
			}
			//System.out.println("hello");
			if (type.equals("Queries"))
				while (StdIn.hasNextLine()) {
					WordNet wordnet1 = new WordNet(synset, hypernym);
					String queryNoun1 = StdIn.readLine();
					String[] queryNoun = queryNoun1.split(" ");
					if (queryNoun[0].equals("null")) {
						throw new IllegalArgumentException("IllegalArgumentException");
					}
					System.out.println("distance = " + wordnet1.distance(queryNoun[0], queryNoun[1]));
					wordnet1.sap(queryNoun[0], queryNoun[1]);
				}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
