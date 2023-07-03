import classlib.Graph;

public class App {
    public static void main(String[] args) throws Exception {
        /* Declare a Graph */
        Graph G = new Graph();
        /*
        As mentioned in the original README.md, you have to execute the application while you are in
        'Application/src', because in the next line, we move to the parent directory and access 'data'
        folder, then the file.
        */
        G.importTSV("../data/GO:0008217.tsv");

        /* Cluster it */
        
        /* Evaluate it */
    }
}
