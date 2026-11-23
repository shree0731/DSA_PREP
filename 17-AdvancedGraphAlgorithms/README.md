# 17-AdvancedGraphAlgorithms & Network Flow

Welcome to the **Advanced Graph Algorithms & Network Flow** module of the Java DSA Preparation repository.

## Overview
This module explores graph theory concepts:
- **Eulerian Path & Circuit**: Traversing every edge exactly once via Hierholzer's algorithm.
- **Maximum Flow**: Ford-Fulkerson & Edmonds-Karp $O(V \cdot E^2)$ with residual capacity tracking.
- **Bipartite Matching**: Maximum cardinality matching via max-flow reduction.
- **Articulation Points & Cut Vertices**: Identifying structural bottlenecks in communication networks.

---

## Planned Directory Structure
```
17-AdvancedGraphAlgorithms/
├── EulerianPathAndCircuit.java      # Hierholzer Algorithm, Reconstruct Itinerary (LeetCode 332)
├── NetworkFlowAndMatching.java      # Edmonds-Karp Max Flow, Maximum Bipartite Matching
├── BridgesAndArticulationPoints.java # Tarjan's Articulation Points & Bridges
└── README.md                        # Eulerian criteria, Max Flow Min Cut theorem, Flow complexity
```

---

## 🌊 Max-Flow Min-Cut Theorem
In any network flow graph, the **Maximum Flow** passing from source $S$ to sink $T$ equals the **Minimum Capacity** of a cut $(S, T)$ whose removal disconnects $S$ from $T$.
- **Edmonds-Karp**: Implements Ford-Fulkerson using **BFS** to find shortest augmenting paths in $O(V \cdot E^2)$ time.

---

## ⚡ Articulation Condition vs Bridge Condition
- **Bridge $(u, v)$**: `low[v] > tin[u]` (Strictly greater: no back-edge reaches $u$ or its ancestors).
- **Articulation Point $u$**: `low[v] >= tin[u]` (Subtree cannot reach any strict ancestor of $u$).
