package Cyclops;

import java.util.ArrayList;
import java.util.HashMap;

import classlib.Graph;

/**
 * This class computes DeltaStrength for all nodes, this assumes directed graphs
 * @author Morad A.
 */
public class DeltaStrength {
    /**
     * This function computes DeltaStrength for all nodes,
     * such that the direction of the graph nodeColumn1 -> nodeColumn2
     * @param G The graph object
     * @param nodeColumn1 The first column that contains one of the ends of an edge
     * @param nodeColumn2 The second column that contains the other end of an edge
     * @param weightColumn The name of the column that holds the weight of the edge
     * @return HashMap, where key is the node name, and value is the value of DeltaStrength for that node
     */
    public static HashMap<String, Double> compute(Graph G, String nodeColumn1, String nodeColumn2, String weightColumn) {
        HashMap<String, Double> output = new HashMap<>();
        /* Iterate over all nodes */
        for(String nodeName : G.nodeNames) {
            double deltaStrength = 0d;
            /* Sum all outgoing edges */
            ArrayList<String> outgoingWeightsArrayList = G.edgeTable.get(new HashMap<>() {{
                put(nodeColumn1, nodeName); // Choosing criteria: The first node column has nodeName
            }}, weightColumn); // Grab all weight
            for(String weight : outgoingWeightsArrayList) deltaStrength += Double.parseDouble(weight);
            /* Decrease all ingoing edges */
            ArrayList<String> ingoingWeightsArrayList = G.edgeTable.get(new HashMap<>() {{
                put(nodeColumn2, nodeName); // Choosing criteria: The second node column has nodeName
            }}, weightColumn); // Grab all weight
            for(String weight : ingoingWeightsArrayList) deltaStrength -= Double.parseDouble(weight);
            /* Add the result to final output hashmap */
            output.put(nodeName, deltaStrength);
        }

        return output;
    }
}
