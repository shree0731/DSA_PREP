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

---

## 🔤 String DP Recurrence Relations

### 1. Longest Common Subsequence (LCS)
$$dp[i][j] = \begin{cases} 1 + dp[i-1][j-1] & \text{if } s_1[i-1] == s_2[j-1] \\ \max(dp[i-1][j], dp[i][j-1]) & \text{otherwise} \end{cases}$$

### 2. Edit Distance (Levenshtein)
$$dp[i][j] = \begin{cases} dp[i-1][j-1] & \text{if } s_1[i-1] == s_2[j-1] \\ 1 + \min(dp[i-1][j-1], dp[i-1][j], dp[i][j-1]) & \text{otherwise} \end{cases}$$
Where operations correspond to:
- `dp[i-1][j-1]`: **Replace** character
- `dp[i-1][j]`: **Delete** from $s_1$
- `dp[i][j-1]`: **Insert** into $s_1$

---

## 🎈 Interval DP Paradigm
In Interval DP, we compute solutions for sub-intervals $[i, j]$ starting from smallest length $len = 1$ to total length $N$:
```java
for (int len = 2; len <= n; len++) {
    for (int i = 0; i <= n - len; i++) {
        int j = i + len - 1;
        for (int k = i; k < j; k++) {
            dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k + 1][j] + cost(i, k, j));
        }
    }
}
```
Key insight for **Burst Balloons**: Think of $k$ as the **last balloon to burst** in $(i, j)$ rather than the first.

---

## 📋 Complete Problem Summary Matrix

| Problem | LeetCode | Core DP Pattern | Time Complexity | Space Complexity |
|:---|:---:|:---|:---:|:---:|
| **Climbing Stairs** | [#70](https://leetcode.com/problems/climbing-stairs/) | 1D Fibonacci Transition | $O(N)$ | $O(1)$ |
| **House Robber I** | [#198](https://leetcode.com/problems/house-robber/) | 1D Rolling Choice | $O(N)$ | $O(1)$ |
| **House Robber II** | [#213](https://leetcode.com/problems/house-robber-ii/) | 1D Circular Range Decomposition | $O(N)$ | $O(1)$ |
| **Coin Change I** | [#322](https://leetcode.com/problems/coin-change/) | Unbounded Knapsack Min Value | $O(N \cdot \text{Amt})$ | $O(\text{Amt})$ |
| **Coin Change II** | [#518](https://leetcode.com/problems/coin-change-ii/) | Unbounded Knapsack Combinations | $O(N \cdot \text{Amt})$ | $O(\text{Amt})$ |
| **Longest Increasing Subsequence** | [#300](https://leetcode.com/problems/longest-increasing-subsequence/) | Patience Sorting + Binary Search | $O(N \log N)$ | $O(N)$ |
| **Maximum Product Subarray** | [#152](https://leetcode.com/problems/maximum-product-subarray/) | Dual Running Min/Max | $O(N)$ | $O(1)$ |
| **Unique Paths I** | [#62](https://leetcode.com/problems/unique-paths/) | 2D Grid Rolling Row | $O(M \cdot N)$ | $O(N)$ |
| **Unique Paths II** | [#63](https://leetcode.com/problems/unique-paths-ii/) | 2D Grid with Obstacles | $O(M \cdot N)$ | $O(N)$ |
| **Minimum Path Sum** | [#64](https://leetcode.com/problems/minimum-path-sum/) | 2D Grid Cumulative Min | $O(M \cdot N)$ | $O(N)$ |
| **Maximal Square** | [#221](https://leetcode.com/problems/maximal-square/) | 2D Min of 3 Neighbors | $O(M \cdot N)$ | $O(M \cdot N)$ |
| **Dungeon Game** | [#174](https://leetcode.com/problems/dungeon-game/) | Reverse Grid Health DP | $O(M \cdot N)$ | $O(N)$ |
| **0/1 Knapsack** | — | Reverse Capacity 1D DP | $O(N \cdot W)$ | $O(W)$ |
| **Unbounded Knapsack** | — | Forward Capacity 1D DP | $O(N \cdot W)$ | $O(W)$ |
| **Partition Equal Subset** | [#416](https://leetcode.com/problems/partition-equal-subset-sum/) | Boolean 0/1 Subset Sum | $O(N \cdot \text{Target})$ | $O(\text{Target})$ |
| **Target Sum** | [#494](https://leetcode.com/problems/target-sum/) | Subset Sum Algebraic Reduction | $O(N \cdot \text{Target})$ | $O(\text{Target})$ |
| **Longest Common Subsequence** | [#1143](https://leetcode.com/problems/longest-common-subsequence/) | 2D Match / Diagonal DP | $O(M \cdot N)$ | $O(M \cdot N)$ |
| **Edit Distance** | [#72](https://leetcode.com/problems/edit-distance/) | 2D Insert/Delete/Replace Min | $O(M \cdot N)$ | $O(M \cdot N)$ |
| **Distinct Subsequences** | [#115](https://leetcode.com/problems/distinct-subsequences/) | 1D Reverse Matching Count | $O(M \cdot N)$ | $O(N)$ |
| **Wildcard Matching** | [#44](https://leetcode.com/problems/wildcard-matching/) | Sequence Pattern Matching | $O(M \cdot N)$ | $O(N)$ |
| **Matrix Chain Multiplication** | — | Classical Interval DP | $O(N^3)$ | $O(N^2)$ |
| **Palindrome Partitioning II** | [#132](https://leetcode.com/problems/palindrome-partitioning-ii/) | Precomputed Palindrome + 1D DP | $O(N^2)$ | $O(N^2)$ |
| **Burst Balloons** | [#312](https://leetcode.com/problems/burst-balloons/) | Reverse Last Balloon Interval DP | $O(N^3)$ | $O(N^2)$ |
| **Stone Game** | [#877](https://leetcode.com/problems/stone-game/) | 2-Player Minimax DP | $O(N^2)$ | $O(N)$ |

---

## 🌲 DP Decision Tree Cheat Sheet
- **Does the problem ask for count/min/max of choices?** $\implies$ Dynamic Programming candidate.
- **Can you choose elements multiple times?** $\implies$ Unbounded Knapsack (forward loop).
- **At most once per element?** $\implies$ 0/1 Knapsack (reverse loop).
- **Two strings with matching / transitions?** $\implies$ 2D matrix matching (`dp[i][j]`).
- **Optimal merge of adjacent components?** $\implies$ Interval DP (`len` loop from 2 to $N$).

---
*Module 10 completed and fully verified with test harnesses.*
