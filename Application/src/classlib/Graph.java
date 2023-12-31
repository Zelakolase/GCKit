package classlib;

import java.util.ArrayList;

/**
 * This class represents a single graph
 * @author Morad A.
 */
public class Graph {
    /* List of node names */
    public ArrayList<String> nodeNames = new ArrayList<>();
    /* Table of all edges and their respective values */
    public SparkDB edgeTable = new SparkDB();

    /**
     * Get all node names from edgeTable
     * @param nodeColumn1 The first column that contains one of the ends of an edge
     * @param nodeColumn2 The second column that contains the other end of an edge
     */
    public void computeNodes(String nodeColumn1, String nodeColumn2) {
        /* Combines all node names that exist in edge table to a single arraylist */
        ArrayList<String> allNodeNames = new ArrayList<>();
        allNodeNames.addAll(edgeTable.getColumn(nodeColumn1));
        allNodeNames.addAll(edgeTable.getColumn(nodeColumn2));

        /* Adds all unique node names into ArrayList<String> nodeName */
        for(String nodeName : allNodeNames) if(! nodeNames.contains(nodeName)) nodeNames.add(nodeName);
    }

    /**
     * Imports data from TSV files of StringDB tabular text output export option
     * @param filename The TSV Content
     * @throws Exception
     */
    public void importTSVString(String data) throws Exception {
        /* Converts TSV format to proper CSV format */
        data = data.replaceAll("\t", "\",\"") // Tab character to [","] String (what is in the brackets)
        .replaceAll("\n", "\"\n\"") // New line to ["\n"]
        .replaceFirst("#", "\""); // Remove the first '#' to be a double qoute
        data = data.substring(0, data.length() - 2);
        /* Imports proper CSV to edgeTable */
        edgeTable.readFromString(data);
    }

    /**
     * Returns a clone of the graph object
     */
    public Graph clone() {
        Graph G = new Graph();
        G.edgeTable = this.edgeTable;
        G.nodeNames = this.nodeNames;
        return G;
    }
}
