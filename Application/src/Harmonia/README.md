# Harmonia Application
This application has two functionalities:
1. Measuring the weighted clustering coefficent for a certain (sub-)graph
2. Generating a custom power set for an entire graph

## Clustering Coefficient Algorithm
We feature a simple and computationally inextensive algorithm for measuring the clustering coefficient, taking into consideration the degrees of nodes and the weight of each edge.<br><br>
If we have two nodes 'A' and 'B' which have edges between them and edges connecting to other external nodes 'C', 'D', etc.., the degree of nodes 'A' and 'B' are calculated such that we do not account for external connections outside nodes 'A' and 'B'. For example, if node 'A' has 2 internal edges with 'B' and 3 external edges with other nodes, the degree value is 2, but not 5.<br><br>
Secondly, we only consider the internal weights of a given cluster (in our case, 'A' and 'B'). For example, if the sum of all weights between node 'A' and 'B' is 5.5, and the sum of external edges is 10.7, then the sum of the weights of 'A' in the cluster is 5.5.<br><br>
Given these two values, we can calculate `c = sum(c_v) / (v_n * e_n)`.
1. c : Clustering Coefficient
2. sum(c_v) : Sum of all clustering coefficients for all verticies (elements/nodes)
3. v_n : Number of nodes/verticies/elements
4. e_n : Number of internal edges in cluster