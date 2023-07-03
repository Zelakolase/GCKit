package Harmonia;

import java.util.ArrayList;

import classlib.Graph;

/**
 * This class computes the power set of a graph based on some arguments, used in evaluation
 * @author Morad A.
 */
public class PowerSet {
    /**
     * Generates a power set for the Graph G where all clusters in the power set
     * have size interval [lowestSize , highestSize]
     * @param G The graph object 
     * @param lowestSize Inclusive lowest number of elements in one cluster
     * @param highestSize Inclusive highest number of elements in one cluster
     * @return An arraylist of clusters, each cluster is an arraylist of node names
     */
    public static ArrayList<ArrayList<String>> compute(Graph G, int lowestSize, int highestSize) {
        ArrayList<ArrayList<String>> output = new ArrayList<>();

        /* Computer power sets for every specific size within specified range [lowestSize , highestSize] */
        for(int size = lowestSize; size <= highestSize; size++) {
            int[] indices = new int[size];
            for(int index = 0; index < size; index++) indices[index] = index;
            while(true) {
                ArrayList<String> Combination = new ArrayList<>();
                for(int index = 0; index < size; index++) Combination.add(G.nodeNames.get(indices[index]));
                output.add(Combination);
                int i = size - 1;
                while(i >= 0 && indices[i] == G.nodeNames.size() - size + i) i--;
                if(i < 0) break;
                indices[i] ++;
                for(int j = i + 1; j < size; j++) indices[j] = indices[j-1] + 1;
            }
        }

        return output;
    }
}
