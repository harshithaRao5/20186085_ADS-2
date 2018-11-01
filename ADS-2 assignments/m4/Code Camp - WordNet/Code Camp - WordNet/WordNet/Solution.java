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
		WordNet wordnet = new WordNet(synset, hypernym);
		try {
			if (type.equals("Graph")) {
				System.out.println(wordnet.readHypernym(hypernym));

			} else {
				while (!StdIn.isEmpty()) {
					String[] queryNoun = StdIn.readLine().split(" ");
					if (queryNoun[0].equals("null")) {
						System.out.println("IllegalArgumentException");
					} else {
						WordNet wordnet1 = new WordNet();
						System.out.println(wordnet1.distance(queryNoun[0], queryNoun[1]));
					}
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}


	}
}