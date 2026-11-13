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

---

## 🧮 Mathematical Proof: $O(N)$ BuildHeap

A naive heap construction performs $N$ insertions $\implies O(N \log N)$.
However, bottom-up **Floyd's algorithm** (`buildHeap`) starts at the last non-leaf node `(n/2) - 1` and runs `siftDown` toward the root:

At height $h$, there are at most $\lceil \frac{n}{2^{h+1}} \rceil$ nodes, each taking at most $h$ swaps:
$$\text{Total Operations} \le \sum_{h=0}^{\lfloor \log_2 n \rfloor} \frac{n}{2^{h+1}} \cdot h = \frac{n}{2} \sum_{h=0}^{\infty} \frac{h}{2^h}$$

Using the arithmetic-geometric progression sum:
$$\sum_{h=0}^{\infty} \frac{h}{2^h} = 2 \implies \text{Total Work} \le \frac{n}{2} \cdot 2 = O(N)$$
Thus, building a heap from an arbitrary array takes strictly **linear time $O(N)$**.

---

## ⚡ HeapSort Characteristics
- **Time Complexity**: $O(N \log N)$ in all cases (best, worst, average).
- **Space Complexity**: $O(1)$ auxiliary memory (true in-place sorting).
- **Stability**: **Unstable** — siftDown operations swap non-adjacent elements across arbitrary levels.

---

## 🎯 Bounded Heap Pattern: $O(N \log K)$

To find the **$K$-th Largest** element:
- Maintain a **Min-Heap** bounded at size $K$.
- When a new element arrives, push it. If size exceeds $K$, `poll()` drops the smallest.
- The heap always holds the top $K$ largest elements; its root (`peek()`) is the $K$-th largest!
- Drastically out-performs full sorting: $\log K \ll \log N$ when $K \ll N$.

---

## 🔀 Multi-Way Merge ($K$ Pointers)
When merging $K$ sorted streams with $N$ total elements:
- Rather than checking all $K$ candidates ($O(K)$ per element $\implies O(N \cdot K)$), a **Min-Heap of size $K$** selects the minimum in $O(\log K)$ time.
- Total runtime is reduced to **$O(N \log K)$**, which is optimal.

---

## ⚖️ The Two-Heaps Dynamic Median Technique
By splitting numbers into:
- Lower half in a **Max-Heap** `left`
- Upper half in a **Min-Heap** `right`

We achieve:
- `addNum(x)`: $O(\log N)$
- `findMedian()`: **$O(1)$**
Size invariant: `left.size() == right.size()` or `left.size() == right.size() + 1`.

---

## ⏱️ Greedy Task Scheduling & Cooldown Queue
When executing tasks with cooldown $n$:
1. Always prioritize the task with the highest remaining frequency (**Max-Heap**).
2. Placed tasks enter a **FIFO Cooldown Queue** with their available unblock timestamp `time + n`.
3. When `current_time` reaches unblock timestamp, tasks return to the Max-Heap.

---

## 📋 Comprehensive Problem Summary Matrix

| Problem | LeetCode | Core Heap Pattern | Time Complexity | Space Complexity |
|:---|:---:|:---|:---:|:---:|
| **Build Heap (Floyd's Algorithm)** | — | Bottom-Up Sift-Down | $O(N)$ | $O(1)$ |
| **HeapSort** | [#912](https://leetcode.com/problems/sort-an-array/) | In-Place Max-Heapify + Swap | $O(N \log N)$ | $O(1)$ |
| **Kth Largest Element** | [#215](https://leetcode.com/problems/kth-largest-element-in-an-array/) | Min-Heap bounded at size $K$ | $O(N \log K)$ | $O(K)$ |
| **Top K Frequent Elements** | [#347](https://leetcode.com/problems/top-k-frequent-elements/) | Frequency Min-Heap / Bucket Sort | $O(N \log K)$ or $O(N)$ | $O(N)$ |
| **K Closest Points to Origin** | [#973](https://leetcode.com/problems/k-closest-points-to-origin/) | Max-Heap of size $K$ | $O(N \log K)$ | $O(K)$ |
| **Merge K Sorted Lists** | [#23](https://leetcode.com/problems/merge-k-sorted-lists/) | Min-Heap of size $K$ | $O(N \log K)$ | $O(K)$ |
| **Smallest Range from K Lists** | [#632](https://leetcode.com/problems/smallest-range-covering-elements-from-k-lists/) | Min-Heap + Running Max | $O(N \log K)$ | $O(K)$ |
| **Find Median from Data Stream** | [#295](https://leetcode.com/problems/find-median-from-data-stream/) | Dual Heaps (Max-Left, Min-Right) | $O(\log N)$ add, $O(1)$ median | $O(N)$ |
| **IPO (Maximized Capital)** | [#502](https://leetcode.com/problems/ipo/) | Min-Capital Heap + Max-Profit Heap | $O(N \log N + K \log N)$ | $O(N)$ |
| **Task Scheduler** | [#621](https://leetcode.com/problems/task-scheduler/) | Max-Heap + Cooldown Queue | $O(N)$ | $O(1)$ |
| **Reorganize String** | [#767](https://leetcode.com/problems/reorganize-string/) | Max-Heap Alternating Extract | $O(N \log 26)$ | $O(1)$ |
| **Min Interval for Query** | [#1851](https://leetcode.com/problems/minimum-interval-to-include-each-query/) | Sweep-Line + Length Min-Heap | $O(N \log N + Q \log Q)$ | $O(N + Q)$ |
| **Seat Reservation Manager** | [#1845](https://leetcode.com/problems/seat-reservation-manager/) | Min-Heap of Available Seats | $O(\log N)$ | $O(N)$ |
