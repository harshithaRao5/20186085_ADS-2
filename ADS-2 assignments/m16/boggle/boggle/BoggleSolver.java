import java.util.Set;
import java.util.HashSet;
public class BoggleSolver {
	// Initializes the data structure using the given
	// array of strings as the dictionary.
	// (You can assume each word in the dictionary
	//  contains only the uppercase letters A through Z.)
	private TrieST<Integer> dictionaryTrie;
	private Set<String> validWords;
	private boolean[][] marked;
	public BoggleSolver(String[] dictionary) {
		dictionaryTrie = new TrieST<Integer>();
		validWords = new HashSet<String>();
		int[] points = {0, 0, 0, 1, 1, 2, 3, 5, 11};
		for (String word : dictionary) {
			if (word.length() >= 8) {
				dictionaryTrie.put(word, 11);
			} else {
				dictionaryTrie.put(word, points[word.length()]);
			}
		}
	}
	// Returns the set of all valid words in the given Boggle board, as an Iterable.
	public Iterable<String> getAllValidWords(BoggleBoard board) {
		marked = new boolean[board.rows()][board.cols()];
		for (int i = 0; i < board.rows(); i++) {
			for (int j = 0; j < board.cols(); j++) {
				String sb = appendCharacter("", board.getLetter(i, j));
				dfs(board, marked, i, j, sb);
			}
		}
		return validWords;
	}
	private String appendCharacter(String sb, char c) {
		if (c == 'Q') {
			return sb + "QU";
		} else {
			return sb + c + "";
		}
	}
	private boolean isValidWord(String word) {
		if (word.length() < 3) {
			return false;
		}
		return dictionaryTrie.contains(word);
	}

	public void dfs(BoggleBoard board, boolean[][] marked,
	                int rows, int cols, String word) {
		if (dictionaryTrie.hasPrefix(word)) return;

		if (isValidWord(word)) {
			System.out.println(word + "----");
			validWords.add(word);
		}
		marked[rows][cols] = true;
		for (int i = rows - 1; i <= rows + 1; i++) {
			for (int j = cols - 1; j <= cols + 1; j++) {
					//System.out.println(row + " " + col);
				if (isValidRowColumn(i, j, board) && !marked[i][j]) {
					word = appendCharacter(word, board.getLetter(i, j));
					//System.out.println(word);
					dfs(board, marked, i, j, word);
				}
			}
		}
		marked[rows][cols] = false;
	}

	private boolean isValidRowColumn(int row, int col, BoggleBoard board) {
		return (row >= 0 && col >= 0 && row < board.rows() && col < board.cols());
	}

	// Returns the score of the given word
	// if it is in the dictionary, zero otherwise.
	// (You can assume the word contains
	// only the uppercase letters A through Z.)
	public int scoreOf(String word) {
		if (dictionaryTrie.contains(word)) {
			return dictionaryTrie.get(word);
		} else {
			return 0;
		}
	}
}