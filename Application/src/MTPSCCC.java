import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import Harmonia.ClusteringCoefficient;
import classlib.Graph;
import classlib.IO;

/**
 * Multi Threaded Power Set Clustering Coefficient Computation
 * @author Morad A.
 */
public class MTPSCCC {
    Graph G = new Graph(); // Empty Graph Object

    public static void main(String[] args) throws Exception {
        MTPSCCC Obj = new MTPSCCC();
        Graph G = new Graph();
        String data = new String(IO.read("../data/0048666.tsv"));
        G.importTSVString(data);
        G.computeNodes("node1", "node2");
        Obj.run(G);
    }

    /**
     * Run MTPSCCC
     * @param G The specified Graph Object
     */
    public void run(Graph G) {
        this.G = G;
        /* In order to avoid Context Switching issues, we decided to limit the running thread to num. avail. processors */
        ExecutorService ES = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        /* For every set size for the power set, initialize a thread to compute it */
        int lowestSize = 5, highestSize = G.nodeNames.size();
        for(int currentSize = lowestSize; currentSize <= highestSize; currentSize++) {
            T thread = new T(currentSize);
            ES.submit(thread);
        }
        /* Soft Shutdown for the Thread Pool */
        SoftShutdown(ES);
    }

    /**
     * Shut downs the thread pool after 20 days or when all tasks are finished, which is earlier.
     * @param threadPool The Thread Pool to shutdown
     */
    public static void SoftShutdown(ExecutorService threadPool) {
        threadPool.shutdown();
        try {
            if (!threadPool.awaitTermination(480, TimeUnit.HOURS)) threadPool.shutdownNow();
        } catch (InterruptedException ex) {
            threadPool.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }

    public class T implements Runnable {

        int size = 0;
        public T(int size) {
            this.size = size;
        }

        @Override
        public void run() {
            int[] indices = new int[size];
            for(int index = 0; index < size; index++) indices[index] = index;
            while(true) {
                ArrayList<String> Combination = new ArrayList<>();
                for(int index = 0; index < size; index++) Combination.add(G.nodeNames.get(indices[index]));
                /* Compute Clustering Coefficient for Combination */
                double CC = ClusteringCoefficient.compute(G, Combination, "node1", "node2", "combined_score");
                /* If the Clustering Coefficient is higher than or equal 0.75, print it */
                if(CC >= 0.75) System.out.println(Combination + "," + CC);

                int i = size - 1;
                while(i >= 0 && indices[i] == G.nodeNames.size() - size + i) i--;
                if(i < 0) break;
                indices[i] ++;
                for(int j = i + 1; j < size; j++) indices[j] = indices[j-1] + 1;
            }
        }
        
    }
}
