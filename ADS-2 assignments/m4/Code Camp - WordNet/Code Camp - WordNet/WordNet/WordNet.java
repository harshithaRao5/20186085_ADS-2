import java.util.List;
import java.util.ArrayList;
/**
 * Class for word net.
 */
public class WordNet {
    private LinearProbingHashST<String, List<Integer>> linearprobing;
    /**
     * Constructs the object.
     *
     * @param      synsets    The synsets
     * @param      hypernyms  The hypernyms
     */
    public WordNet(String synsets, String hypernyms) {
        linearprobing = new LinearProbingHashST<String, List<Integer>>();
        readSynset(synsets, hypernyms);
    }
    /**
     * Reads a synset.
     *
     * @param      synset     The synset
     * @param      hypernyms  The hypernyms
     */
    public void readSynset(String synset, String hypernyms) {
        int id = 0;
        int vertices = 0;
        In in = new In("./Files/" + synset);
        while (!in.isEmpty()) {
            vertices++;
            ArrayList<Integer> idlist = new ArrayList<Integer>();
            String[] synsetArray = in.readString().split(",");
            idlist.add(Integer.parseInt(synsetArray[0]));
            for (int i = 0; i < synsetArray[1].length(); i++) {
                String[] nounsArray = synsetArray[1].split(" ");
                if (linearprobing.contains(nounsArray[i])) {
                    idlist.addAll(linearprobing.get(synsetArray[i]));
                    linearprobing.put(synsetArray[1], idlist);
                } else {
                    linearprobing.put(nounsArray[i], idlist);
                }
            }
        }
        Digraph digraph = new Digraph(vertices);
        readHypernym(hypernyms, digraph);
    }

    public void readHypernym(String hypernyms, Digraph graph) {

        int count = 0;
        In in = new In("./Files/" + hypernyms);
        while (!in.isEmpty()) {
            count++;
            String[] tokens = in.readString().split(",");
            for (int i = 1; i < tokens.length; i++) {
                graph.addEdge(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[i]));
            }
        }
        DirectedCycle directedCycle = new DirectedCycle(graph);
        if (directedCycle.hasCycle()) {
            throw new IllegalArgumentException("Cycle detected");
        } else {
            int degree = 0;
            for (int i = 0; i < graph.V(); i++) {
                if (graph.outdegree(i) == 0) {
                    degree++;
                }
            }
            if (degree > 1) {
                throw new IllegalArgumentException("Multiple roots");
            }
            System.out.println(graph);
        }

    }

    // returns all WordNet nouns
    public Iterable<String> nouns() {
        return linearprobing.keys();
    }

    //is the word a WordNet noun?
    public boolean isNoun(String word) {
        return linearprobing.contains(word);
    }
}

// // distance between nounA and nounB (defined below)
// public int distance(String nounA, String nounB)

// // a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
// // in a shortest ancestral path (defined below)
// public String sap(String nounA, String nounB)

// // do unit testing of this class
// public static void main(String[] args)