import java.util.List;
import java.util.ArrayList;
/**
 * Class for word net.
 */
public class WordNet {
    /**
     * symbol table initializing.
     */
    private LinearProbingHashST<String, ArrayList<Integer>> linearprobing;
    private LinearProbingHashST<Integer, String> reverseSt;
    private SAP sap;
    private Digraph graph;
    private int vertices;
    /**
     * Constructs the object.
     *
     * @param      synsets    The synsets
     * @param      hypernyms  The hypernyms
     */
    public WordNet(String synsets, String hypernyms) {
        linearprobing = new LinearProbingHashST<String, ArrayList<Integer>>();
        reverseSt = new LinearProbingHashST<Integer, String>();
        vertices = readSynset(synsets);
        graph = new Digraph(vertices);
        readHypernym(hypernyms);
        sap = new SAP(graph);

    }
    WordNet() {

    }
    /**
     * Reads a synset.
     *
     * @param      synset     The synset
     * @param      hypernym  The hypernym
     */
    public int readSynset(String synset) {
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
                reverseSt.put(Integer.parseInt(synsetArray[0]), synsetArray[1]);
                if (linearprobing.contains(nounsArray[i])) {
                    idlist.addAll(linearprobing.get(synsetArray[i]));
                    linearprobing.put(synsetArray[i], idlist);
                } else {
                    linearprobing.put(nounsArray[i], idlist);
                }
            }
        //System.out.println("idlist");
        }
        return vertices;
        // readHypernym(hypernym, digraph);
    }
    /**
     * Reads a hypernym.
     *
     * @param      hypernyms1  The hypernyms1
     * @param      graph      The graph
     */
    public void readHypernym(String hypernyms1) {

        int count = 0;
        In in = new In("./Files/" + hypernyms1);
        while (!in.isEmpty()) {
            count++;
            String[] tokens = in.readString().split(",");
            for (int i = 1; i < tokens.length; i++) {
                graph.addEdge(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[i]));
            }
        }


    }

    /**
     * gets the nouns.
     *
     * @return  nouns.
     */
    public Iterable<String> nouns() {
        return linearprobing.keys();
    }

    /**
     * Determines if noun.
     *
     * @param      word  The word
     *
     * @return     True if noun, False otherwise.
     */
    public boolean isNoun(String word) {
        return linearprobing.contains(word);
    }


// distance between nounA and nounB (defined below)
    public int distance(String nounA, String nounB) {
        ArrayList<Integer> noun1 = linearprobing.get(nounA);
        ArrayList<Integer> noun2 = linearprobing.get(nounB);
        if (!isNoun(nounA) || !isNoun(nounB)) {
            throw new IllegalArgumentException();
        }
        return sap.length(noun1, noun2);

    }

//a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
//in a shortest ancestral path (defined below)
    public String sap(String nounA, String nounB) {
        ArrayList<Integer> noun1 = linearprobing.get(nounA);
        ArrayList<Integer> noun2 = linearprobing.get(nounB);
        if (!isNoun(nounA) || !isNoun(nounB)) {
            throw new IllegalArgumentException();
        }
        int id = sap.ancestor(noun1, noun2);
        return reverseSt.get(id);
    }
    public void display() {
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

// do unit testing of this class
// public static void main(String[] args)
}