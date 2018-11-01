import java.util.Arrays;
/**
 * Class for word net.
 */
public class WordNet {
    //SeparateChainingHashST<String, Integer> seperateChaining;
    /**
     * Constructs the object.
     *
     * @param      synsets    The synsets
     * @param      hypernyms  The hypernyms
     */
    public WordNet(String synsets, String hypernyms) {
        //seperateChaining
        readSynset(synsets, hypernyms);
    }

    public void readSynset(String synset, String hypernyms) {
        int id = 0;
        int vertices = 0;
        try {
            In inObj = new In("./Files/" + synset);
            while (!inObj.isEmpty()) {
                vertices++;
                String[] synsetArray = inObj.readString().split(",");
                id = Integer.parseInt(synsetArray[0]);
                String[] nounsArray = synsetArray[1].split(" ");
            }
            Digraph digraph = new Digraph(vertices);
            readHypernym(hypernyms, digraph);
            if(digraph.outdegree(vertices) == 0) {
                System.out.println("Multiple roots");
            }
        } catch (Exception e) {
            System.out.println("File not found");
        }
    }

    public void readHypernym(String hypernyms, Digraph graph) {
        try {
            int count = 0;
            In in = new In("./Files/" + hypernyms);
            while (!in.isEmpty()) {
                count++;
                String[] tokens = in.readString().split(",");
                for(int i = 1; i < tokens.length;i++) {
                    graph.addEdge(Integer.parseInt(tokens[0]),Integer.parseInt(tokens[i]));
                }
            }
            DirectedCycle directedCycle = new DirectedCycle(graph);
            if (directedCycle.hasCycle()) {
                System.out.println("Cycle detected");
            } else {
                System.out.println(graph);
            }
        } catch (Exception e) {
            System.out.println("File not found");
        }

    }

    // // returns all WordNet nouns
    // public Iterable<String> nouns()

    //is the word a WordNet noun?
    public boolean isNoun(String word) {
        return false;
    }
}

// // distance between nounA and nounB (defined below)
// public int distance(String nounA, String nounB)

// // a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
// // in a shortest ancestral path (defined below)
// public String sap(String nounA, String nounB)

// // do unit testing of this class
// public static void main(String[] args)