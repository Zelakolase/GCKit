# GCKit
(G)raph (C)lustering Kit, A project to reach optimal clusterization and classification solution.<br>
<a rel="license" href="http://creativecommons.org/licenses/by-nc-sa/4.0/"><img alt="Creative Commons License" style="border-width:0" src="https://i.creativecommons.org/l/by-nc-sa/4.0/88x31.png" /></a><br />This work is licensed under a <a rel="license" href="http://creativecommons.org/licenses/by-nc-sa/4.0/">Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International License</a>. For any commercial use, please contact the author 'Zelakolase[at]tuta.io' to get a written permission if needed.
## Description & Problem Statement
GCKit is a research project to find optimal clusterization and classification algorithm(s). Classification and Clusterization techniques are particularly used in the analysis of Protein-Protein interaction networks, and it is a very active field of research. The project aims to do the following:
1. Establish proper grading and indicators for the performance and correctness of any classification algorithm
2. Find an optimal algorithm for classification and clusterization highly-connected graphs.

Besides, GCKit has Power Set calculation method, to get significant clusters via bruteforce over the whole graph.

NOTE: This kit is assumes that every input graph is a weighted graph.

Table of contents:
- [GCKit](#gckit)
  - [Description \& Problem Statement](#description--problem-statement)
  - [General Technical Information](#general-technical-information)
  - [Applications in Kit](#applications-in-kit)
  - [Build and run](#build-and-run)
  - [What is the next step?](#what-is-the-next-step)
  - [Author Information](#author-information)


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

## What is the next step?
Go to `Application/README.md` for more information about the code

## Author Information
Morad A., E-mail: Zelakolase[at]tuta.io, website: [zelakolase.neocities.org](zelakolase.neocities.org)