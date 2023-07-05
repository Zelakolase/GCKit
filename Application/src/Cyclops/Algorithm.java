package Cyclops;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import classlib.Graph;

/**
 * This class is the proposed clusterization algorithm
 * @author Morad A.
 */
public class Algorithm {
    /**
     * Algorithm computation as documented, assuming directed graph from nodeColumn1 -> nodeColumn2
     * @param G The graph object
     * @param DeltaStrengths The HashMap of DeltaStrengths where the key is the node name and the value is the delta strength
     * @param unclusteredCutoff The unclustered nodes cutoff percentage
     * @param headCutoff The head nodes cutoff percentage
     * @param nodeColumn1 The first column that contains one of the ends of an edge
     * @param nodeColumn2 The second column that contains the other end of an edge
     * @param weightColumn The column which holds the edges' weights
     * @return HashMap, key is the head node, and value is the cluster where every element in arraylist is a cluster member
     */
    public static HashMap<String, ArrayList<String>> computeHM(Graph G, HashMap<String, Double> DeltaStrengths, double unclusteredCutoff, double headCutoff, String nodeColumn1, String nodeColumn2, String weightColumn) {
        HashMap<String, ArrayList<String>> output = new HashMap<>();
        ArrayList<String> remainingNodes = new ArrayList<>();
        remainingNodes.addAll(G.nodeNames);

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
            /* Find all rows' indicies where the head node is a transmitter */
            ArrayList<Integer> indicies = G.edgeTable.getIDs(new HashMap<>() {{
                put(nodeColumn1, headNode);
            }});
            HashMap<Double, Integer> ScoreIndex = new HashMap<>();
            /* Find all weights for each row */
            for(int index : indicies) ScoreIndex.put(Double.parseDouble(G.edgeTable.get(index).get(weightColumn)), index);
            /* Choose top 50% of these rows based on highest weight */
            List<Map.Entry<Double, Integer>> topEntries = ScoreIndex.entrySet()
            .stream()
            .sorted(Collections.reverseOrder(Map.Entry.comparingByKey()))
            .limit((int) Math.floor(ScoreIndex.size() * 0.5))
            .collect(Collectors.toList());
            /* Initiate Empty Cluster */
            ArrayList<String> Cluster = new ArrayList<>();
            /* Add elements to the cluster */
            for(Map.Entry<Double, Integer> E : topEntries) Cluster.add(G.edgeTable.get(E.getValue()).get(nodeColumn2));
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

    /**
     * @see computeHM(..)
     */
    public static ArrayList<ArrayList<String>> computeAL(Graph G, HashMap<String, Double> DeltaStrengths, double unclusteredCutoff, double headCutoff, String nodeColumn1, String nodeColumn2, String weightColumn) {
        HashMap<String, ArrayList<String>> computation = computeHM(G, DeltaStrengths, unclusteredCutoff, headCutoff, nodeColumn1, nodeColumn2, weightColumn);
        ArrayList<ArrayList<String>> output = new ArrayList<>();
        for(Entry<String, ArrayList<String>> E : computation.entrySet()) {
            ArrayList<String> entry = E.getValue();
            entry.add(E.getKey());
            output.add(entry);
        }
        return output;
    }

    /**
     * returns nodes with certain defined criteria
     * @param DeltaStrengths The HashMap of DeltaStrengths where the key is the node name and the value is the delta strength
     * @param N Number of nodes to return
     * @param close True if we are looking for the closest nodes' deltaStrengths to zero, false if we are looking for the larget nodes' deltaStrengths
     * @return A list of node names that satisfy the criteria
     */
    private static List<String> findNodesWithCriteria(HashMap<String, Double> DeltaStrengths, int N, boolean close) {
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
