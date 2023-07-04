# Cyclops Application
This application has two functionalities:
1. Computation of DeltaStrength coefficient, a property for nodes and an important input in the proposed algorithm
2. Computation of the algorithm

It is noteworthy that the algorithm expects a weighted **directed** graph.

## Philosophy
It is assumed that nodes with a lot of ingoing and outgoing connections with near weights are not suitable to be clustered due to their membership in multiple potential clusters at the same time. The algorithm excludes these nodes and make hierarchial clusters with head and its highest 50% of edges, where heads give most value.