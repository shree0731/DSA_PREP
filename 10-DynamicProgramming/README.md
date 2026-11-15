# 10-DynamicProgramming

Welcome to the **Dynamic Programming** module of the Java DSA Preparation repository.

## Overview
**Dynamic Programming (DP)** is an algorithmic paradigm that solves complex problems by breaking them down into simpler subproblems, storing intermediate results to avoid redundant calculations.

A problem is solvable via Dynamic Programming if it exhibits:
1. **Optimal Substructure**: An optimal solution to the problem contains optimal solutions to its subproblems.
2. **Overlapping Subproblems**: The recursive solution space repeatedly revisits the same subproblems.

---

## Planned Directory Structure
```
10-DynamicProgramming/
├── OneDimensionalDP.java         # Climbing Stairs, House Robber I/II, Coin Change I/II, LIS (O(N log N)), Max Product Subarray
├── GridAndTwoDimensionalDP.java  # Unique Paths I/II, Min Path Sum, Maximal Square, Dungeon Game (HP)
├── KnapsackAndSubsetsDP.java     # 0/1 Knapsack, Unbounded Knapsack, Partition Equal Subset, Target Sum
├── StringAndSequenceDP.java      # LCS, Edit Distance, Distinct Subsequences, Wildcard Matching, Print LCS
├── PartitionAndIntervalDP.java   # Matrix Chain Multiplication, Palindrome Partitioning II, Burst Balloons, Minimax Game
└── README.md                     # Recurrence formulas, transition graphs, space optimization patterns, Big-O cheat sheet
```

---

## 🧠 Memoization vs Tabulation

| Feature | Top-Down (Memoization) | Bottom-Up (Tabulation) |
|:---|:---|:---|
| **Approach** | Recursive + Cache (HashMap / Array) | Iterative table-filling |
| **State Evaluation** | Evaluates only subproblems required for answer | Evaluates all subproblems in topological order |
| **Call Stack** | Overhead of $O(\text{depth})$ recursion call stack | Zero call-stack overhead ($O(1)$) |
| **Space Optimization** | Rarely possible to reduce cache space | Often reducible to $O(1)$ or $O(N)$ rolling arrays |
