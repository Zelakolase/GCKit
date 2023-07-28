import java.util.ArrayList;
import java.util.HashMap;

import Cyclops.Algorithm;
import Cyclops.DeltaStrength;
import Harmonia.ClusteringCoefficient;
import classlib.Graph;
import classlib.IO;
import classlib.Statistics;
import classlib.stopWatch;

public class App {
    public static void main(String[] args) throws Exception {
        /* Declare a Graph */
        Graph G = new Graph();
        /*
         * As mentioned in the original README.md, you have to execute the application
         * while you are in
         * 'Application/src', because in the next line, we move to the parent directory
         * and access 'data'
         * folder, then the file.
         */
        String data = new String(IO.read("../data/SM00220.tsv"));
        G.importTSVString(data);
        G.computeNodes("node1", "node2");
        Graph GG = G.clone();
        /* Cluster it */

        // Compute DeltaStrength
        stopWatch sW = new stopWatch();
        sW.start();
        HashMap<String, Double> DeltaStrengths = DeltaStrength.compute(G, "node1", "node2", "combined_score");
        sW.stop();
        System.out.println("Delta Strength Took " + (sW.nanoTimeElapsed / 1_000_000.0) + " ms");
        // Run the algorithm
        sW.start();
        ArrayList<ArrayList<String>> Clusters = Algorithm.computeAL(G, DeltaStrengths, 0.0, 0.35, "node1", "node2", "combined_score", 1.05);
        sW.stop();
        System.out.println("Clustering Took " + (sW.nanoTimeElapsed / 1_000_000.0) + " ms");

        /* Evaluate it */

        // Get all clustering coefficients
        System.out.println("STARTING CYCLOPS");
        System.out.println("Cluster , ClusteringCoefficient");
        ArrayList<Double> CCs = new ArrayList<>();
        for (ArrayList<String> Cluster : Clusters) {
            double clusteringCoefficient = ClusteringCoefficient.compute(GG, Cluster, "node1", "node2", "combined_score");
            if(clusteringCoefficient == clusteringCoefficient) CCs.add(clusteringCoefficient);
            System.out.println(Cluster + " , " + clusteringCoefficient);
        }
        
        Statistics.printStats(CCs);
        // Get the power set of the whole graph, every element in the bigger ArrayList is a cluster
       /* 
        System.out.println("\n === === === === === \n");
        System.out.println("STARTING POWERSET");
        System.out.println("Cluster , ClusteringCoefficient");
        MTPSCCC Calculation = new MTPSCCC();
        //Calculation.run(G); -> Uncomment this line if you have a NASA supercomputer or you do not care about CPU usage
        */
    }
}
