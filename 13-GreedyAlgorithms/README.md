# 13-GreedyAlgorithms & Interval Scheduling

Welcome to the **Greedy Algorithms** module of the Java DSA Preparation repository.

## Overview
A **Greedy Algorithm** makes the locally optimal choice at each stage with the hope of finding a global optimum.
Greedy works when a problem exhibits:
1. **Greedy Choice Property**: A globally optimal solution can be arrived at by making locally optimal decisions.
2. **Optimal Substructure**: An optimal solution to the problem contains optimal solutions to subproblems.

---

## Planned Directory Structure
```
13-GreedyAlgorithms/
├── IntervalScheduling.java   # Merge Intervals, Insert Interval, Non-overlapping, Burst Balloons Arrows
├── GreedyOptimization.java   # Gas Station Circular Tour, Jump Game I & II, Candy Distribution
├── GreedyHeuristics.java     # Fractional Knapsack, Assign Cookies, Lemonade Change
└── README.md                 # Interval sorting patterns, greedy proofs, decision matrix
```

---

## ⏱️ Interval Scheduling Sorting Rules
- **Merging / Grouping Intervals**: Sort by **Start Time** ascending `Comparator.comparingInt(a -> a[0])`.
- **Selecting Maximum Non-Overlapping Intervals**: Sort by **End Time** ascending `Comparator.comparingInt(a -> a[1])` (earliest finish time greedily leaves most room for subsequent intervals).
- **Meeting Rooms Count**: Track overlaps via Min-Heap of end times or two-pointer sweep-line.
