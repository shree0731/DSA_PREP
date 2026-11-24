# 18-DivideAndConquer & Advanced Binary Search

Welcome to the **Divide and Conquer & Advanced Binary Search** module of the Java DSA Preparation repository.

## Overview
Divide and Conquer solves a problem by breaking it into subproblems of the same type, solving each recursively, and combining their results.
Binary Search on Answer applies this principle to monotonic solution search spaces.

---

## Planned Directory Structure
```
18-DivideAndConquer/
├── BinarySearchOnAnswer.java         # Ship Packages, Koko Eating Bananas, Split Array Largest Sum
├── RotatedSortedSearchAndMedian.java # Rotated Sorted Array Search, Median of Two Sorted Arrays O(log(min(M,N)))
├── DivideAndConquerAlgorithms.java   # Count Inversions via Merge Sort, Maximum Subarray D&C
└── README.md                         # Predicate functions, Master Theorem, binary search invariants
```

---

## 🎯 Binary Search on Answer Pattern
When problem asks for:
- "Minimum capacity to complete within $K$ days"
- "Maximum speed to finish eating within $H$ hours"

Define a monotonic boolean feasibility function `canAchieve(mid)`:
```
Search Space: [F, F, F, ..., T, T, T]  -> Find First True (Lower Bound)
```
Time Complexity: $O(\text{Verification Time} \cdot \log(\text{Range}))$.

---

## ⚡ Master Theorem Cheat Sheet
For recurrence $T(N) = a \cdot T(N / b) + O(N^d)$:
1. If $d < \log_b a \implies T(N) = O(N^{\log_b a})$.
2. If $d = \log_b a \implies T(N) = O(N^d \log N)$ (e.g. Merge Sort: $a=2, b=2, d=1$).
3. If $d > \log_b a \implies T(N) = O(N^d)$.
