import java.util.Arrays;
public class WordNet {
    private LinearProbingHashST<String, Integer> linearprobing;
    private Digraph digraph;

    // constructor takes the name of the two input files
    public WordNet(String synsets, String hypernyms) {
        linearprobing = new LinearProbingHashST<String, Integer>();
        int synsetV = readSynset(synsets);
        digraph = readHypernym(hypernyms, synsetV);
    }
    public int readSynset(String synset) {
        In in = new In("./Files/" + synset);
        int vertices = 0;
        while (!in.isEmpty()) {
            vertices++;
            String[] tokens = in.readString().split(",");
            int id = Integer.parseInt(tokens[0]);
            String[] nouns = tokens[1].split(" ");
            for (String each : nouns) {
                linearprobing.put(each, id);
            }


        }
        return vertices;


    }
    public Digraph readHypernym(String hypernym, int synsetv) {
        In in = new In("./Files/" + hypernym);
        Digraph graph = new Digraph(synsetv);
        while (!in.isEmpty()) {
            String[] tokens = in.readString().split(",");
            int hyponyms = Integer.parseInt(tokens[0]);
            //int hypernyms = Integer.parseInt(tokens[0]);
            for (int i = 0; i < tokens.length; i++) {
                int hypernyms = Integer.parseInt(tokens[1]);
                graph.addEdge(hyponyms, hypernyms);
            }
            //graph.addEdge(hyponyms,hypernyms);

        }
        //System.out.println(graph);
        return graph;
    }


    // returns all WordNet nouns
    // public Iterable<String> nouns() {

    // }

    // // is the word a WordNet noun?
    // public boolean isNoun(String word) {

    // }

    // // distance between nounA and nounB (defined below)
    // public int distance(String nounA, String nounB) {

    // }

    // // a synset (second field of synsets.txt) that is the common ancestor of nounA and nounB
    // // in a shortest ancestral path (defined below)
    // public String sap(String nounA, String nounB) {

    // }

    // // do unit testing of this class
    // public static void main(String[] args) {

    //}




}