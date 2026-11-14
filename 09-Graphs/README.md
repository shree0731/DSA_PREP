# 09-Graphs

Welcome to the **Graphs** module of the Java DSA Preparation repository.

## Overview
A **Graph** $G = (V, E)$ consists of a set of vertices $V$ connected by edges $E$.
Graphs model complex real-world networks such as social networks, routing protocols, dependency resolution, and state machines.

---

## Planned Directory Structure
```
09-Graphs/
├── GraphRepresentationsAndTraversals.java  # Matrix/List, BFS, DFS, Undirected/Directed Cycle Checks
├── ShortestPathAlgorithms.java             # Dijkstra, Bellman-Ford, Floyd-Warshall, DAG Shortest Path
├── TopologicalSortAndDAG.java              # DFS Reverse Postorder, Kahn's BFS, Course Schedule, Alien Dict
├── DisjointSetAndMST.java                  # DSU with Path Compression, Kruskal MST, Prim MST, Redundant Edge
├── NetworkFlowAndAdvancedGraphs.java       # Word Ladder (Bidirectional BFS), Kosaraju SCC, Tarjan Bridges
└── README.md                               # Decision matrices, complexity proofs, and interview templates
```

---

## 🏛️ Graph Representations

### 1. Adjacency Matrix
- 2D array `matrix[V][V]` where `matrix[u][v] = 1` (or edge weight).
- **Space**: $O(V^2)$
- **Edge Existence Check**: $O(1)$
- **All Neighbors of $u$**: $O(V)$
- Best for **dense graphs** ($E \approx V^2$).

### 2. Adjacency List
- Array or List of Lists `List<List<Integer>> adj` storing neighbors for each vertex.
- **Space**: $O(V + E)$ (or $O(V + 2E)$ for undirected)
- **Edge Existence Check**: $O(\deg(u))$
- **All Neighbors of $u$**: $O(\deg(u))$
- Best for **sparse graphs** ($E \ll V^2$).
