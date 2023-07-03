package Harmonia;

import java.util.ArrayList;
import java.util.HashMap;

import classlib.Graph;

/**
 * This class computes the weighted clustering coefficient for a cluster/graph
 * @author Morad A.
 */
public class ClusteringCoefficient {
    /**
     * Computes the clustering coefficient for a certain cluster
     * @param G The whole graph object
     * @param clusterNodeNames The names of the nodes in the cluster
     * @param nodeColumn1 The first column that contains one of the ends of an edge
     * @param nodeColumn2 The second column that contains the other end of an edge
     * @param weightColumn The name of the column that holds the weight of the edge
     * @return The clustering coefficient for the cluster specified in clusterNodeNames
     */
    public static double compute(Graph G, ArrayList<String> clusterNodeNames, String nodeColumn1, String nodeColumn2, String weightColumn) {
        double output = 0d;
        double maxScore = 0d; // The maximum coefficient possible for the cluster, assuming the max weight is 1.0
        int internalEdgeCount = 0; // Count how many internal edges in the cluster. Internal edge is any edge where both of ends is in clusterNodeNames
        /* Iterate over clusterNodeNames */
        for(String clusterNodeName : clusterNodeNames) {
            int degrees = 0; // Node degree
            double sumEdges = 0d; // Sum of all edges for that node
            /* Search for edges indicies where clusterNodename is envolved */
            ArrayList<Integer> edgesIndicies = G.edgeTable.getIDs(new HashMap<>() {{
                put(nodeColumn1, clusterNodeName);
            }});
            edgesIndicies.addAll(G.edgeTable.getIDs(new HashMap<>() {{
                put(nodeColumn2, clusterNodeName);
            }}));
            /* Iterate over these indicies */
            for(int rowIndex : edgesIndicies) {
                /* Get the row */
                HashMap<String, String> row = G.edgeTable.get(rowIndex);
                /* Exclude the row (aka. edge) if it is NOT an internal edge */
                if(! clusterNodeNames.contains(row.get(nodeColumn2))) continue;
                if(! clusterNodeNames.contains(row.get(nodeColumn1))) continue;
                /* Increment of degree */
                degrees ++;
                /* add the weight */
                sumEdges += Math.abs(Double.parseDouble(row.get(weightColumn)));
                /* Increment of Internal Edge Count */
                internalEdgeCount ++;
            }

            /* Multiply degrees by sumEdges, and add to output */
            output += degrees * sumEdges;
            /* Max Score calculation, the lonely '1' in the second term is the maximum weight possible */
            maxScore += (clusterNodeNames.size()-1) * (1 * clusterNodeNames.size()-1);
        }
        /* Divide by the number of cluster nodes to compute the arithmetic mean */
        /* The previous value was the sum of all cluster coefficients all cluster nodes */
        output /= (clusterNodeNames.size() * internalEdgeCount);
        /* We assume that maximum edges for the graph. */
        /* If the number of nodes in a graph is 'n', then the maximum number of edges is (n*(n-1))/2 */
        maxScore /= (clusterNodeNames.size() * ((clusterNodeNames.size() * (clusterNodeNames.size() - 1)) / 2));
        /* The final coefficient is percentage of closeness to the optimal solution */
        output /= maxScore;
        return output;
    }
}
