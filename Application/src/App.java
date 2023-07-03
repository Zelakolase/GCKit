import java.util.ArrayList;
import java.util.HashMap;

import Cyclops.Algorithm;
import Cyclops.DeltaStrength;
import Harmonia.ClusteringCoefficient;
import classlib.Graph;
import classlib.stopWatch;

public class App {
    public static void main(String[] args) throws Exception {
        /* Declare a Graph and a Stopwatch */
        Graph G = new Graph();
        stopWatch W = new stopWatch();
        /*
        As mentioned in the original README.md, you have to execute the application while you are in
        'Application/src', because in the next line, we move to the parent directory and access 'data'
        folder, then the file.
        */
        W.start();
        G.importTSV("../data/GO:0008217.tsv");
        W.stop(); System.out.println("Time taken to import: " + W.nanoTimeElapsed / 10e9d + " seconds");
        W.start();
        G.computeNodes("node1", "node2");
        W.stop(); System.out.println("Time taken to identify nodes: " + W.nanoTimeElapsed / 10e9d + " seconds");

        /* Cluster it */

        // Compute DeltaStrength
        W.start();
        HashMap<String, Double> DeltaStrengths = DeltaStrength.compute(G, "node1", "node2", "combined_score");
        W.stop(); System.out.println("Time taken to calculate DeltaStrengths: " + W.nanoTimeElapsed / 10e9d + " seconds");
        // Run the algorithm
        W.start();
        ArrayList<ArrayList<String>> Clusters = Algorithm.computeAL(G, DeltaStrengths, 0.15, 0.35, "node1", "node2");
        W.stop(); System.out.println("Time taken to perform clusterization: " + W.nanoTimeElapsed / 10e9d + " seconds");
        
        /* Evaluate it */

        // Get all clustering coefficients
        W.start();
        for(ArrayList<String> Cluster : Clusters) {
            double clusteringCoefficient = ClusteringCoefficient.compute(G, Cluster, "node1", "node2", "combined_score");
            System.out.println(Cluster + " , " + clusteringCoefficient);
        }
        W.stop(); System.out.println("Time taken to measure one clustering coefficient for a single cluster: " + W.nanoTimeElapsed / Clusters.size() / 10e9d + " seconds");
    }
}
