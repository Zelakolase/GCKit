# Main Application
This is the start of the main application.

## Folder Information
- src/ : Contains source code.
- data/ : Contains example Protein-Protein interaction networks from StringDB, the file name is the GeneOntology ID.
- src/Cyclops/ : Cyclops application source code
- src/Harmonia/ : Harmonia application source code

## Class Information inside src/
- App.java : Contains an example of running Harmonia and evaluating it based on Cyclops.
- MTPSCCC.java : Multi-threaded Power set Clustering Coefficient Computation, a lazy bruteforce method to detect significant clusters
- classlib/
    - IO.java : Reads and Write information on the hard disk.
    - SparkDB.java : In-memory database. For more information visit the original [Repository](https://github.com/NaDeSys/SparkDB).
    - Graph.java : Represents a single graph with all of the edge and node information
    - stopWatch.java : A stop watch which measure elapsed time in nanoseconds, for future use.
- Cyclops/
    - DeltaStrength.java : Computes DeltaStrength for each node
    - Algorithm.java : The main algorithm
- Harmonia/
    - ClusteringCoefficient.java : Computes the clustering coefficient for a certain graph
    - PowerSet.java : Generates a power set of all possible clusters for a graph