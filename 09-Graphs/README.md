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

---

## 🧭 Shortest Path Decision Matrix

| Algorithm | Graph Type | Weights | Time Complexity | Space Complexity |
|:---|:---|:---|:---:|:---:|
| **BFS** | Directed / Undirected | Unweighted (all edge weight = 1) | $O(V + E)$ | $O(V)$ |
| **Dijkstra** | Directed / Undirected | Non-negative weights ($w \ge 0$) | $O((V + E) \log V)$ | $O(V + E)$ |
| **Bellman-Ford** | Directed | Negative weights permitted, detects -ve cycles | $O(V \cdot E)$ | $O(V)$ |
| **Floyd-Warshall** | Directed / Undirected | All-Pairs shortest paths ($w$ can be negative) | $O(V^3)$ | $O(V^2)$ |
| **DAG Shortest Path**| Directed Acyclic | Any weights (even negative), no cycles | $O(V + E)$ | $O(V + E)$ |

---

## 🔄 Cycle Detection Strategies

### Undirected Graph Cycle Detection
- Use **BFS or DFS with parent tracking**.
- If a visited neighbor is encountered that is **not the immediate parent**, a back-edge exists $\implies$ cycle!
- Time: $O(V + E)$, Space: $O(V)$.

### Directed Graph Cycle Detection
- Simple visited array is **insufficient** (cross edges do not form cycles in directed graphs).
- Must use **3-Color DFS** (or Kahn's Topological Sort):
  - **WHITE (0)**: Unvisited.
  - **GRAY (1)**: Currently active on call stack (visiting). Encountering a GRAY node confirms a back-edge and cycle!
  - **BLACK (2)**: Completely processed (subtree explored).

---

## ⚡ Shortest Path Algorithms Deep-Dive

### Dijkstra's Algorithm
- **Condition**: Non-negative edge weights only ($w \ge 0$).
- **Mechanism**: Greedy choice property with Min-Heap.
- **Why does negative weight break Dijkstra?** Once a node is extracted from the PriorityQueue, Dijkstra assumes its shortest distance is finalized. A negative edge visited later could reduce this distance, violating the greedy invariant.

### Bellman-Ford Algorithm
- Relaxes all $|E|$ edges $|V| - 1$ times.
- Since the longest simple path in a graph has at most $|V| - 1$ edges, all shortest paths are resolved after $|V| - 1$ passes.
- A further relaxation on pass $|V|$ indicates a **Negative Weight Cycle**.

---

## 🏗️ Topological Sort Mechanics
A Topological Ordering exists **if and only if** the graph is a Directed Acyclic Graph (**DAG**).
- **Kahn's Algorithm**: Count in-degrees. Vertices with `inDegree == 0` have no dependencies and are processed first.
- If total processed vertices $< V$, a cycle exists!

---

## 🌲 Disjoint Set Union (DSU) & Inverse Ackermann $\alpha(N)$
With **Path Compression** and **Union by Rank**, any sequence of $M$ operations on $N$ elements executes in:
$$O(M \cdot \alpha(N))$$
Where $\alpha(N)$ is the **Inverse Ackermann function**, which for all practical values ($N < 10^{80}$) satisfies $\alpha(N) \le 4$.
Thus, each DSU operation runs in **effectively constant time $O(1)$**.

---

## 🌉 Tarjan's Bridge Condition: `low[v] > tin[u]`
For an edge $(u, v)$:
- `tin[u]`: Timestamp when node $u$ was first visited.
- `low[v]`: Lowest discovery time reachable from $v$ through its subtree without using edge $(u, v)$.
- If `low[v] > tin[u]`, the subtree rooted at $v$ has **no back-edge** to $u$ or any ancestor of $u$.
  Removing $(u, v)$ isolates $v$, proving $(u, v)$ is a **Critical Bridge**!

---

## 📋 Comprehensive Problem Summary Matrix

| Problem | LeetCode | Core Graph Algorithm | Time Complexity | Space Complexity |
|:---|:---:|:---|:---:|:---:|
| **BFS / DFS Traversal** | — | Adjacency List Queue/Recursion | $O(V + E)$ | $O(V)$ |
| **Undirected Cycle** | — | BFS Parent Pointer | $O(V + E)$ | $O(V)$ |
| **Directed Cycle** | — | 3-Color DFS Back-Edge | $O(V + E)$ | $O(V)$ |
| **Dijkstra** | [#743](https://leetcode.com/problems/network-delay-time/) | Min-Heap PriorityQueue | $O((V + E) \log V)$ | $O(V + E)$ |
| **Bellman-Ford** | — | $V-1$ Edge Relaxations | $O(V \cdot E)$ | $O(V)$ |
| **Floyd-Warshall** | — | 3-Loop 2D Dynamic Programming | $O(V^3)$ | $O(V^2)$ |
| **Shortest Path in Binary Matrix** | [#1091](https://leetcode.com/problems/shortest-path-in-binary-matrix/) | 8-Directional Grid BFS | $O(N^2)$ | $O(N^2)$ |
| **Course Schedule I & II** | [#207](https://leetcode.com/problems/course-schedule/), [#210](https://leetcode.com/problems/course-schedule-ii/) | Kahn's Algorithm / Topo Sort | $O(V + E)$ | $O(V + E)$ |
| **Alien Dictionary** | [#269](https://leetcode.com/problems/alien-dictionary/) | Char DAG Topo Sort | $O(C)$ | $O(1)$ |
| **Disjoint Set Union (DSU)** | — | Path Compression + Rank | $O(\alpha(V))$ amortized | $O(V)$ |
| **Kruskal's MST** | — | Edge Sorting + DSU | $O(E \log E)$ | $O(V)$ |
| **Prim's MST** | — | Min-Heap PriorityQueue | $O(E \log V)$ | $O(V + E)$ |
| **Number of Provinces** | [#547](https://leetcode.com/problems/number-of-provinces/) | DSU / Connected Components | $O(V^2 \cdot \alpha(V))$ | $O(V)$ |
| **Redundant Connection** | [#684](https://leetcode.com/problems/redundant-connection/) | DSU Cycle-Closing Edge | $O(E \cdot \alpha(V))$ | $O(V)$ |
| **Word Ladder** | [#127](https://leetcode.com/problems/word-ladder/) | Bidirectional BFS | $O(M^2 \cdot N)$ | $O(M \cdot N)$ |
| **Is Graph Bipartite?** | [#785](https://leetcode.com/problems/is-graph-bipartite/) | 2-Color BFS/DFS | $O(V + E)$ | $O(V)$ |
| **Strongly Connected Components** | — | Kosaraju's Two-Pass DFS | $O(V + E)$ | $O(V + E)$ |
| **Critical Bridges** | [#1192](https://leetcode.com/problems/critical-connections-in-a-network/) | Tarjan's Discovery/Low DFS | $O(V + E)$ | $O(V + E)$ |

---

## 📊 Kosaraju 2-Pass DFS Flowchart
```
  [Original Graph G] ----(Pass 1: DFS finish order)----> [Stack]
                                                            |
  [Transpose Graph G^T] <--(Pass 2: Pop from stack)--------+
           |
           +--> Produces Isolated Strongly Connected Components (SCCs)
```

---

## 🎯 Graph Problem Identification Cheat Sheet
- **Connected components / Cycle in undirected**: Use BFS or Disjoint Set Union (DSU).
- **Cycle in directed**: 3-Color DFS (`color == 1` back-edge) or Kahn's in-degree count.
- **Shortest path (unweighted)**: BFS queue.
- **Shortest path (weighted, non-negative)**: Dijkstra Min-Heap.
- **Negative weights / detect negative cycle**: Bellman-Ford ($V-1$ iterations).
- **All-pairs shortest paths**: Floyd-Warshall ($O(V^3)$).
- **Bridges / Critical edges**: Tarjan's algorithm (`low[v] > tin[u]`).
