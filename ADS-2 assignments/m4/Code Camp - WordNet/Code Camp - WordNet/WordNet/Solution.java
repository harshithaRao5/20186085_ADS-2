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
		String synset = StdIn.readString();
		// System.out.println(synset);
		String hypernym = StdIn.readString();
		String type = StdIn.readString();
		String queryNoun1 = StdIn.readLine();
		try {
			if (type.equals("Graph")) {
				WordNet wordnet = new WordNet(synset, hypernym);

			}
			if(type.equals("Queries"))
				while (StdIn.hasNextLine()) {
					String[] queryNoun = StdIn.readLine().split(" ");
					System.out.println(queryNoun[0]+", "+ queryNoun[1]);
					WordNet wordnet1 = new WordNet(synset, hypernym);
					wordnet1.distance(queryNoun[0], queryNoun[1]);
					wordnet1.sap(queryNoun[0], queryNoun[1]);
					if (queryNoun[0].equals("null")) {
						System.out.println("IllegalArgumentException");
					}
				}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}