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
     */
    public void computeNodes(String nodeColumn1, String nodeColumn2) {
        /* Combines all node names that exist in edge table to a single arraylist */
        ArrayList<String> allNodeNames = new ArrayList<>();
        allNodeNames.addAll(edgeTable.getColumn(nodeColumn1));
        allNodeNames.addAll(edgeTable.getColumn(nodeColumn2));

        /* Adds all unique node names into ArrayList<String> nodeName */
        for(String nodeName : allNodeNames) {
            if(nodeName.contains(nodeName)) continue;
            nodeNames.add(nodeName);
        }
    }

    /**
     * Imports data from TSV files of StringDB tabular text output export option
     * @param filename The file path and name of the desired TSV file
     * @throws Exception
     */
    public void importTSV(String filename) throws Exception {
        /* Reads the file from disk */
        String data = new String(IO.read(filename));
        /* Converts TSV format to proper CSV format */
        data = data.replaceAll("\t", "\",\"") // Tab character to [","] String (what is in the brackets)
        .replaceAll("\n", "\"\n\"") // New line to ["\n"]
        .replaceFirst("#", "\""); // Remove the first '#' to be a double qoute
        data = data.substring(0, data.length() - 2);
        /* Imports proper CSV to edgeTable */
        edgeTable.readFromString(data);
    }
}
