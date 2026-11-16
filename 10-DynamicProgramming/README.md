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

---

## 📈 1D DP State Transition Patterns
- **Fibonacci / Step transitions**: $dp[i] = dp[i - 1] + dp[i - 2]$ $\implies$ Keep two pointers (`prev1`, `prev2`) for $O(1)$ space.
- **Decision Choice (Take / Skip)**: $dp[i] = \max(dp[i - 1], \text{val}[i] + dp[i - 2])$.
- **Min / Max Cost Aggregation**: $dp[i] = \min_{c \in \text{coins}} (dp[i - c] + 1)$.
- **Patience Sorting (LIS)**: Binary search on active tails for $O(N \log N)$ complexity.

---

## 🎯 Patience Sorting for $O(N \log N)$ LIS
1. Maintain an array `tails` where `tails[i]` stores the smallest tail of all increasing subsequences of length $i + 1$.
2. For each number $x$ in input array:
   - Use **Binary Search** (`Arrays.binarySearch` or bisect left) to find insertion point.
   - If $x$ is larger than all elements in `tails`, append it (`size++`).
   - Otherwise, replace the first element $\ge x$ with $x$ (greedily minimizing tail value).
3. Resulting length of `tails` is the length of LIS!

---

## 🗺️ 2D Grid DP Patterns
1. **Top-Left to Bottom-Right**: Transitions only depend on `dp[r-1][c]` and `dp[r][c-1]`.
   - Dimension reduction: Reduce $O(M \times N)$ space down to $O(N)$ with a single rolling array.
2. **Reverse Dynamic Programming (Dungeon Game)**: When the decision at $(r, c)$ depends on the future state (e.g. surviving subsequent rooms), iterate backwards from $(M-1, N-1)$ down to $(0, 0)$.

---

## 🎒 The Knapsack Rule: Reverse vs Forward Loops
- **0/1 Knapsack (Single Choice)**:
  ```java
  for (int cap = capacity; cap >= weight; cap--) {
      dp[cap] = Math.max(dp[cap], dp[cap - weight] + val);
  }
  ```
  Iterating **in reverse** ensures that `dp[cap - weight]` came from the **previous row** (item not yet used).
- **Unbounded Knapsack (Multiple Choices)**:
  ```java
  for (int cap = weight; cap <= capacity; cap++) {
      dp[cap] = Math.max(dp[cap], dp[cap - weight] + val);
  }
  ```
  Iterating **forward** allows using the updated `dp[cap - weight]` from the **current row** (item can be reused).
