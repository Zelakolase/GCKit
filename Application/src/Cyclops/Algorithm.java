package Cyclops;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import classlib.Graph;

/**
 * This class is the proposed clusterization algorithm
 * @author Morad A.
 */
public class Algorithm {
    public HashMap<String, ArrayList<String>> computeHM(Graph G, HashMap<String, Double> DeltaStrengths, double unclusteredCutoff, double headCutoff, String nodeColumn1, String nodeColumn2) {
        HashMap<String, ArrayList<String>> output = new HashMap<>();
        ArrayList<String> remainingNodes = G.nodeNames;

        /* Compute how many nodes we will cutoff */
        int unclusteredCutoffNumber = (int) Math.ceil(unclusteredCutoff * G.nodeNames.size());
        int headCutoffNumber = (int) Math.ceil(headCutoff * G.nodeNames.size());
        /* Find the 'unclusteredCutoffNumber' nodes that are closest to zero DeltaStrength */
        List<String> unclusteredNodes = findNodesWithCriteria(DeltaStrengths, unclusteredCutoffNumber, true);
        remainingNodes.removeAll(unclusteredNodes);
        /* Find the 'headCutoffNumber' nodes that have the highest DeltaStrength values */
        List<String> headNodes = findNodesWithCriteria(DeltaStrengths, headCutoffNumber, false);
        remainingNodes.removeAll(headNodes);
        /* CLUSTER IT!!! */
        // Iterate over all head nodes
        for(String headNode : headNodes) {
            ArrayList<String> Cluster = G.edgeTable.get(new HashMap<>() {{
                put(nodeColumn1, headNode); // Find all edges where the direction goes from head to another node
            }}, nodeColumn2); // Find all the 'another nodes'
            Cluster.removeAll(unclusteredNodes); // Remove unclustered nodes
            remainingNodes.removeAll(Cluster); // The rest of the nodes are processed
            output.put(headNode, Cluster);
        }
        // Optimize time!!! Iterate over left-over nodes to be heads
        for(String nodeName : remainingNodes) {
            ArrayList<String> Cluster = G.edgeTable.get(new HashMap<>() {{
                put(nodeColumn1, nodeName); // Find all edges where the direction goes from head to another node
            }}, nodeColumn2); // Find all the 'another nodes'
            Cluster.removeAll(unclusteredNodes); // Remove unclustered nodes
            output.put(nodeName, Cluster);
        }

        return output;
    }

    public ArrayList<ArrayList<String>> computeAL(Graph G, HashMap<String, Double> DeltaStrengths, double unclusteredCutoff, double headCutoff, String nodeColumn1, String nodeColumn2) {
        HashMap<String, ArrayList<String>> computation = computeHM(G, DeltaStrengths, unclusteredCutoff, headCutoff, nodeColumn1, nodeColumn2);
        ArrayList<ArrayList<String>> output = new ArrayList<>();
        for(Entry<String, ArrayList<String>> E : computation.entrySet()) {
            ArrayList<String> entry = E.getValue();
            entry.add(E.getKey());
            output.add(entry);
        }
        return output;
    }

    public static List<String> findNodesWithCriteria(HashMap<String, Double> DeltaStrengths, int N, boolean close) {
        List<String> nodes = new ArrayList<>(DeltaStrengths.keySet());
        Collections.sort(nodes, new Comparator<String>() {
            @Override
            public int compare(String node1, String node2) {
                double delta1 = DeltaStrengths.get(node1);
                double delta2 = DeltaStrengths.get(node2);
                if(close) return Double.compare(Math.abs(delta1), Math.abs(delta2));
                return Double.compare(delta2, delta1);
            }
        });
        return nodes.subList(0, N);
    }
}
