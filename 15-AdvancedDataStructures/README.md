# 15-AdvancedDataStructures & Design

Welcome to the **Advanced Data Structures** module of the Java DSA Preparation repository.

## Overview
This module covers industrial and competitive programming data structures:
- **LRU & LFU Cache**: $O(1)$ memory eviction designs.
- **Segment Tree**: $O(\log N)$ range queries and point updates.
- **Binary Indexed Tree (Fenwick Tree)**: $O(\log N)$ prefix sum queries and point updates using bitwise lowbit indexing.
- **Monotonic Stack**: $O(N)$ histogram and range bounds computation.

---

## Planned Directory Structure
```
15-AdvancedDataStructures/
├── LRUAndLFUCache.java             # LRU Cache and LFU Cache in O(1) time
├── SegmentTree.java                # Array-based Segment Tree with Range Sum & Point Update
├── FenwickTree.java                # Binary Indexed Tree (BIT) with Prefix Queries
├── MonotonicStackAndQueue.java     # Largest Rectangle in Histogram, Daily Temperatures
└── README.md                       # Segment Tree vs Fenwick Tree, cache policies, Big-O reference
```

---

## 🌲 Segment Tree vs Fenwick Tree

| Feature | Segment Tree | Fenwick Tree (BIT) |
|:---|:---|:---|
| **Space Complexity** | $O(4N)$ | $O(N)$ minimal |
| **Code Length** | ~70 lines (more complex) | ~25 lines (very compact) |
| **Operations Supported** | Arbitrary associative operations (Sum, Min, Max, GCD) | Invertible prefix functions (Sum, XOR) |
| **Point Update** | $O(\log N)$ | $O(\log N)$ |
| **Range Query** | $O(\log N)$ | $O(\log N)$ |
