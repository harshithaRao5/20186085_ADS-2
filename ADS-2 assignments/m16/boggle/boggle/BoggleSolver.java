import java.util.Set;
import java.util.TreeSet;
/**
 * Class for boggle solver.
 */
public class BoggleSolver {
    // Initializes the data structure using the given
    // array of strings as the dictionary.
    // (You can assume each word in the dictionary
    //  contains only the uppercase letters A through Z.)
    /**
    *object creation for trieST.
    */
    private TrieST<Integer> dictionaryTrie;
    /**
     * set of valid words.
     */
    private Set<String> validWords;
    /**
     * visited character.
     */
    private boolean[][] marked;
    /**
     * Constructs the object.
     *
     * @param      dictionary  The dictionary
     */
    public BoggleSolver(final String[] dictionary) {
        dictionaryTrie = new TrieST<Integer>();
        validWords = new TreeSet<String>();
        final int three = 3;
        final int five = 5;
        final int eight = 8;
        final int eleven = 11;
        int[] points = {0, 0, 0, 1, 1, 2, three, five, eleven};
        for (String word : dictionary) {
            if (word.length() >= eight) {
                dictionaryTrie.put(word, eleven);
            } else {
                dictionaryTrie.put(word, points[word.length()]);
            }
        }
    }
    /**
     * Gets all valid words.
     *Returns the set of all valid words in the
     * given Boggle board, as an Iterable.
     * @param      board  The board
     *
     * @return     All valid words.
     */
    public Iterable<String> getAllValidWords(final BoggleBoard board) {
        if (board == null) {
            throw new IllegalArgumentException("board is null");
        }
        marked = new boolean[board.rows()][board.cols()];
        for (int i = 0; i < board.rows(); i++) {
            for (int j = 0; j < board.cols(); j++) {
                String sb = appendCharacter("", board.getLetter(i, j));
                dfs(board, marked, i, j, sb);
            }
        }
        return validWords;
    }
    /**
     * Appends a character.
     *
     * @param      sb String
     * @param      c  character that to be added for the string.
     *
     * @return  appended String.
     */
    private String appendCharacter(final String sb, final char c) {
        String s = sb;
        if (c == 'Q') {
            s += "QU";
            return s;
        } else {
            s += c;
            return s;
        }
    }
    /**
     * Determines if valid word.
     *
     * @param      word  The word
     *
     * @return     True if valid word, False otherwise.
     */
    private boolean isValidWord(final String word) {
        final int three1 = 3;
        if (word.length() < three1) {
            return false;
        }
        return dictionaryTrie.contains(word);
    }
    /**
     * dfs implementation to find the words.
     *
     * @param      board   The board
     * @param      marked1  The marked
     * @param      rows    The rows
     * @param      cols    The cols
     * @param      word    The word
     */
    public void dfs(final BoggleBoard board, final boolean[][] marked1,
        final int rows, final int cols, final String word) {
        if (!dictionaryTrie.hasPrefix(word)) {
            return;
        }

        if (isValidWord(word)) {
            //System.out.println(word + "----" + scoreOf(word));
            validWords.add(word);
        }
        marked1[rows][cols] = true;
        for (int i = rows - 1; i <= rows + 1; i++) {
            for (int j = cols - 1; j <= cols + 1; j++) {
                if (isValidRowColumn(i, j, board) && !marked1[i][j]) {
                    String sequence = appendCharacter(word,
                        board.getLetter(i, j));
                    dfs(board, marked1, i, j, sequence);
                }
            }
        }
        marked[rows][cols] = false;
    }
    /**
     * Determines if valid row column.
     *
     * @param      row    The row
     * @param      col    The col
     * @param      board  The board
     *
     * @return     True if valid row column, False otherwise.
     */
    private boolean isValidRowColumn(final int row,
        final int col, final BoggleBoard board) {
        return (row >= 0 && col >= 0
           && row < board.rows() && col < board.cols());
    }
    // Returns the score of the given word
    // if it is in the dictionary, zero otherwise.
    // (You can assume the word contains
    // only the uppercase letters A through Z.)
    /**
     * score of word.
     *
     * @param      word  The word
     *
     * @return  score.
     */

    public int scoreOf(final String word) {
        if (word == null) {
            return 0;
        }
        if (dictionaryTrie.contains(word)) {
            return dictionaryTrie.get(word);
        }
        return 0;
    }
}
