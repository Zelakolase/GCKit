# GCKit
(G)raph (C)lustering Kit, A project to reach optimal clusterization and classification solution.<br>
<a rel="license" href="http://creativecommons.org/licenses/by-nc-sa/4.0/"><img alt="Creative Commons License" style="border-width:0" src="https://i.creativecommons.org/l/by-nc-sa/4.0/88x31.png" /></a><br />This work is licensed under a <a rel="license" href="http://creativecommons.org/licenses/by-nc-sa/4.0/">Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International License</a>. For any commercial use, please contact the author 'Zelakolase[at]tuta.io' to get a written permission if needed.
## Description & Problem Statement
GCKit is a research project to find optimal clusterization and classification algorithm(s). Classification and Clusterization techniques are particularly used in the analysis of Protein-Protein interaction networks, and it is a very active field of research. The project aims to do the following:
1. Establish proper grading and indicators for the performance and correctness of any classification algorithm
2. Find an optimal algorithm for classification and clusterization highly-connected graphs.

Besides, GCKit has Power Set calculation method, to get significant clusters via bruteforce over the whole graph.

NOTE: This kit is assumes that every input graph is a weighted graph.

## General Technical Information
We used Java programming language to build the kit, with [SparkDB](https://github.com/NaDeSys/SparkDB). All of the source code files will be included inside 'Application/' The source code tries to follow ISO-25010 standard for code quality, to ensure high readability and maintainability.

## Applications in Kit
| Name      | Function |
| ----------- | ----------- |
| Cyclops      | Proposed Clusterization algorithm for weighted directed graphs        |
| Harmonia   | Evaluation of the correctness and performance for any clusterization process       |

Check the README of each application for more information about these applications. All of the source code exists in 'Application/'

## Build and run
All of the commands must be executed in 'Application/src/'
- Build classfile: `$ javac App.java`
- Run classfile: `$ java app`

## Datasets available in Application/data
The datasets are available via StringDB export function. According to StringDB: "All data and download files in STRING are freely available under a 'Creative Commons BY 4.0' license. When using the data, please provide appropriate credit — and inform users of any changes or additions that you might have made to the data."

- GO:0008217 : Regulation of Blood Pressure
- GO:0045776 : Negative regulation of Blood Pressure
- GO:0048666 : Neuron Development
- GO:1903522 : Regulation of Blood Circulation

## What is the next step?
Go to `Application/README.md` for more information about the code

## Author Information
Morad A., E-mail: Zelakolase[at]tuta.io, website: [zelakolase.neocities.org](zelakolase.neocities.org)