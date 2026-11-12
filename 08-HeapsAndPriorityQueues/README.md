# 08-HeapsAndPriorityQueues

Welcome to the **Heaps & Priority Queues** module of the Java DSA Preparation repository.

## Overview
A **Heap** is a specialized tree-based data structure that satisfies the **Heap Property**:
- **Min-Heap**: For any given node $C$, if $P$ is a parent node of $C$, then $key(P) \le key(C)$. The minimum element is always at the root.
- **Max-Heap**: For any given node $C$, if $P$ is a parent node of $C$, then $key(P) \ge key(C)$. The maximum element is always at the root.

Priority Queues in competitive programming and system software (like task schedulers, Dijkstra's shortest path, and Huffman coding) rely fundamentally on heap mechanics.

---

## Planned Directory Structure
```
08-HeapsAndPriorityQueues/
├── MinHeapMaxHeap.java                 # Custom Min/Max Heaps, Sift-Up/Down, O(N) BuildHeap, HeapSort
├── KthElementsAndTopK.java             # Kth Largest/Smallest, Top K Frequent, K Closest Points
├── MergeKSortedStreams.java            # Merge K Lists/Arrays, Smallest Range from K Lists, K Pairs Smallest Sum
├── MedianFinderAndTwoHeaps.java        # Running Median (LeetCode 295), Sliding Window Median, IPO
├── TaskSchedulerAndGreedyHeaps.java    # Task Scheduler, Reorganize String, Min Interval Query, SeatManager
└── README.md                           # Documentation, mathematical proofs, Big-O tables, interview guides
```

---

## 📐 1D Array Mapping for Complete Binary Trees

Because a binary heap is always a **complete binary tree**, it can be compactly stored in a flat 1D array without pointer overhead:

```
        Array Index:   0   1   2   3   4   5   6
        Values:       [10, 20, 15, 30, 40, 50, 60]

                         [10] (idx 0)
                        /    \
            (idx 1) [20]      [15] (idx 2)
                    /   \     /   \
        (idx 3)  [30]  [40] [50]  [60] (idx 6)
```

### Indexing Formulas (0-based)
- **Parent of node $i$**: `parent(i) = (i - 1) / 2`
- **Left Child of node $i$**: `left(i) = 2 * i + 1`
- **Right Child of node $i$**: `right(i) = 2 * i + 2`
- **First Non-Leaf Node**: `(n / 2) - 1`
