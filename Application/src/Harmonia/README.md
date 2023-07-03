# Harmonia Application
This application has two functionalities:
1. Measuring the weighted clustering coefficent for a certain (sub-)graph
2. Generating a custom power set for an entire graph

## Clustering Coefficient Algorithm
We feature a simple and computationally inextensive algorithm for measuring the clustering coefficient, taking into consideration the degrees of nodes and the weight of each edge.<br><br>
If we have two nodes 'A' and 'B' which have edges between them and edges connecting to other external nodes 'C', 'D', etc.., the degree of nodes 'A' and 'B' are calculated such that we do not account for external connections outside nodes 'A' and 'B'. For example, if node 'A' has 2 internal edges with 'B' and 3 external edges with other nodes, the degree value is 2, but not 5.<br><br>
Secondly, we only consider the internal weights of a given cluster (in our case, 'A' and 'B'). For example, if the sum of all weights between node 'A' and 'B' is 5.5, and the sum of external edges is 10.7, then the sum of the weights of 'A' in the cluster is 5.5.<br><br>
Given these two values, we can calculate $$c = \frac{\sum_{v} c_{v}}{v_{n} \cdot e_{n}}$$.
1. c : Clustering Coefficient
2. sum(c_v) : Sum of all clustering coefficients for all verticies (elements/nodes)
3. v_n : Number of nodes/verticies/elements
4. e_n : Number of internal edges in cluster

After calculating `c`, we will calculate `maxC` which is the highest clustering coefficient the cluster can have, then we say that the final clustering coefficient: $$\text{Final} = \frac{c}{\text{maxC}}$$.

If the clustering coefficient for a given cluster equals 1.0, then every node in the cluster is connected to every other node with the highest possible weight.

This equation is thouroughly tested, you can use it safely under BY-NC-SA.

## Power Set Algorithm
This algorithm computes all the possible combinations of the input ArrayList, given a lower bound and upper bound (both inclusive) for the size of each combination. In order to calculate the size of the output beforehand, the formula is: $$\sum_{x=lowerBound}^{upperBound} \binom{size}{x}$$