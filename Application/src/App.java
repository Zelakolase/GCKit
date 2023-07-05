import java.util.ArrayList;
import java.util.HashMap;

import Cyclops.Algorithm;
import Cyclops.DeltaStrength;
import Harmonia.ClusteringCoefficient;
import classlib.Graph;

public class App {
    public static void main(String[] args) throws Exception {
        /* Declare a Graph and a Stopwatch */
        Graph G = new Graph();
        /*
         * As mentioned in the original README.md, you have to execute the application
         * while you are in
         * 'Application/src', because in the next line, we move to the parent directory
         * and access 'data'
         * folder, then the file.
         */
        G.importTSV("../data/GO:0048666.tsv");
        G.computeNodes("node1", "node2");

        /* Cluster it */

        // Compute DeltaStrength
        HashMap<String, Double> DeltaStrengths = DeltaStrength.compute(G, "node1", "node2", "combined_score");
        // Run the algorithm
        ArrayList<ArrayList<String>> Clusters = Algorithm.computeAL(G, DeltaStrengths, 0.1, 0.25, "node1", "node2", "combined_score");

        /* Evaluate it */

        // Get all clustering coefficients
        System.out.println("STARTING CYCLOPS");
        System.out.println("Cluster , ClusteringCoefficient");
        for (ArrayList<String> Cluster : Clusters) {
            double clusteringCoefficient = ClusteringCoefficient.compute(G, Cluster, "node1", "node2", "combined_score");
            if(clusteringCoefficient >= 0.75) System.out.println(Cluster + " , " + clusteringCoefficient);
        }
        // Get the power set of the whole graph, every element in the bigger ArrayList is a cluster
        System.out.println("\n === === === === === \n");
        System.out.println("STARTING POWERSET");
        System.out.println("Cluster , ClusteringCoefficient");
        MTPSCCC Calculation = new MTPSCCC();
        Calculation.run(G);
    }
}
