# Main Application
This is the start of the main application.

## Folder Information
- src/ : Contains source code.
- lib/ : Contains the JAR files for GraphStream.
- data/ : Contains example Protein-Protein interaction networks from StringDB, the file name is the GeneOntology ID.
- Cyclops/ : Cyclops application source code
- Harmonia/ : Harmonia application source code

## Class Information
- App.java : Contains an example of running Harmonia and evaluating it based on Cyclops.
- classlib/
    - IO.java : Reads and Write information on the hard disk.
    - SparkDB.java : In-memory database. For more information visit the original [Repository](https://github.com/NaDeSys/SparkDB).
    - Graph.java : Represents a single graph with all of the edge and node information