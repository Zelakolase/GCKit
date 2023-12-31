package classlib;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Statistics for a bunch of datapoints, prints out results
 * @author Morad A.
 */
public class Statistics {
    /**
     * Calculates and prints statistics
     * @param data Input datapoints
     */
    public static void printStats(ArrayList<Double> data) {
        // Compute max value
        double max = Collections.max(data);
        System.out.println("Max value: " + max);

        // Compute min value
        double min = Collections.min(data);
        System.out.println("Min value: " + min);

        // Compute mean value
        double sum = 0.0;
        for (double num : data) sum += num;
        double mean = sum / data.size();
        System.out.println("Mean value: " + mean);

        // Compute standard deviation
        double variance = 0.0;
        for (double num : data) variance += Math.pow(num - mean, 2);
        double stddev = Math.sqrt(variance / data.size());
        System.out.println("Standard deviation: " + stddev);

        // Compute coefficient of variation
        double cv = (stddev / mean);
        System.out.println("Coefficient of variation: " + cv * 100 + "%");

        // Number of elements
        System.out.println("Number of clusters: " + data.size());
    }
}
