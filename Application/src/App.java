import classlib.Graph;

public class App {
    public static void main(String[] args) throws Exception {
        /* Declare a Graph */
        Graph G = new Graph();
        G.importTSV("../data/GO:0008217.tsv");
        /* Cluster it */
        /* Evaluate it */
    }
}
