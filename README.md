# DSA Preparation (Java)

[![Language](https://img.shields.io/badge/Language-Java_8+-orange.svg)](https://www.oracle.com/java/)
[![Progress](https://img.shields.io/badge/Milestone-15_Mastery_Completed-success.svg)](01-Arrays/)
[![License](https://img.shields.io/badge/License-MIT-green.svg)](LICENSE)

A curated, interview-focused repository for Data Structures and Algorithms preparation in Java. Every problem is implemented with multiple approaches (Brute Force, Better, and Optimal) along with Big-O complexity analysis and test cases.

---

## 📂 Modules Structure

### [01-Arrays](01-Arrays/)
- **[BasicOperations.java](01-Arrays/BasicOperations.java)**: Dynamic array resizing, linear/binary search, in-place reversal, 3-step left/right rotations.
- **[SecondLargestElement.java](01-Arrays/SecondLargestElement.java)**: Sorting vs Two-Pass vs Optimal Single-Pass ($O(N)$ time, $O(1)$ space), duplicate handling.
- **[TwoSum.java](01-Arrays/TwoSum.java)**: Brute Force, Two-Pass Hash Map, Optimal One-Pass Hash Map with load-factor preallocation, Two Sum II.
- **[MaximumSubarray.java](01-Arrays/MaximumSubarray.java)**: Cubic, Quadratic, Kadane's Algorithm ($O(N)$), Divide and Conquer, DP table, range tracker.
- **[README.md](01-Arrays/README.md)**: Array memory characteristics, complexity tables, problem walkthroughs.

### [02-Strings](02-Strings/)
- **[StringOperations.java](02-Strings/StringOperations.java)**: String Constant Pool demo, immutability, reverse words, string rotation, run-length compression, isomorphic check, first unique character.
- **[ValidAnagram.java](02-Strings/ValidAnagram.java)**: Sorting, Hash Map, 26-int frequency bucket, Group Anagrams, Sliding Window anagram search.
- **[ValidPalindrome.java](02-Strings/ValidPalindrome.java)**: Two-pointer alphanumeric filtering, Valid Palindrome II (1 deletion), longest constructible palindrome.
- **[LongestSubstringWithoutRepeating.java](02-Strings/LongestSubstringWithoutRepeating.java)**: Brute force, Sliding window with Set, Optimal last-seen index map, ASCII array lookup.
- **[LongestPalindromicSubstring.java](02-Strings/LongestPalindromicSubstring.java)**: Cubic brute force, 2D Dynamic Programming table, Expand Around Center ($O(N^2)$ time, $O(1)$ space).
- **[README.md](02-Strings/README.md)**: String Pool internals, immutability benefits, sliding window & two-pointer patterns.

### [03-Recursion](03-Recursion/)
- **[BasicRecursion.java](03-Recursion/BasicRecursion.java)**: Tail-recursive factorial, memoized Fibonacci, binary exponentiation ($O(\log N)$ Pow), recursive array reversal, Tower of Hanoi.
- **[Subsets.java](03-Recursion/Subsets.java)**: Subsets I (Include/Exclude vs Looping), Bitwise Power Set, Subsets II (duplicate handling), Subset Sum.
- **[Permutations.java](03-Recursion/Permutations.java)**: Visited state tracking, In-Place Swapping ($O(1)$ auxiliary memory), Permutations II (duplicates), Next Permutation.
- **[CombinationSum.java](03-Recursion/CombinationSum.java)**: Combination Sum I (unlimited reuse), II (single use with duplicates), III (fixed length k), sorted branch pruning.
- **[NQueens.java](03-Recursion/NQueens.java)**: Backtracking solver, $O(1)$ diagonal lookups, N-Queens II solution counter, bitmasking solver, ASCII chessboard visualizer.
- **[README.md](03-Recursion/README.md)**: Call stack mechanics, backtracking template, state pruning, complexity guides.

### [04-SearchingSorting](04-SearchingSorting/)
- **[BasicSorting.java](04-SearchingSorting/BasicSorting.java)**: Optimized Bubble Sort (early exit flag), Selection Sort, Insertion Sort, stability demonstration.
- **[MergeSort.java](04-SearchingSorting/MergeSort.java)**: Divide & conquer recursive merge sort, iterative bottom-up merge sort, Inversion Counting, buffer reuse optimization.
- **[QuickSort.java](04-SearchingSorting/QuickSort.java)**: Lomuto vs Hoare partitioning, 3-Way QuickSort (Dutch National Flag), QuickSelect ($O(N)$ Kth Largest / Smallest), Sort Colors.
- **[BinarySearchVariants.java](04-SearchingSorting/BinarySearchVariants.java)**: Lower Bound, Upper Bound, First & Last Position (LeetCode #34), Rotated Array Search I & II, Peak Element.
- **[BinarySearchOnAnswer.java](04-SearchingSorting/BinarySearchOnAnswer.java)**: Monotonic predicate framework, Sqrt(x), Koko Eating Bananas, Capacity to Ship Packages, Aggressive Cows, Split Array Largest Sum.
- **[README.md](04-SearchingSorting/README.md)**: Big-O matrix, partitioning schemes, binary search on answer guide, interview tips.

### [05-LinkedList](05-LinkedList/)
- **[SinglyLinkedList.java](05-LinkedList/SinglyLinkedList.java)**: Custom Singly Linked List, head/tail/indexed operations, in-place reversal (iterative & recursive), Tortoise & Hare middle node.
- **[DoublyLinkedList.java](05-LinkedList/DoublyLinkedList.java)**: Bidirectional node structure, $O(1)$ arbitrary node deletion, forward/backward traversals, in-place reversal.
- **[LinkedListCycle.java](05-LinkedList/LinkedListCycle.java)**: Floyd's Cycle Detection (LeetCode #141), Cycle entry derivation (LeetCode #142), loop circumference, cycle removal.
- **[MergeAndSortList.java](05-LinkedList/MergeAndSortList.java)**: Merge Two Sorted Lists (iterative/recursive), Merge K Lists (Min-Heap), MergeSort on List ($O(N \log N)$), Intersection node.
- **[PalindromeAndReorderList.java](05-LinkedList/PalindromeAndReorderList.java)**: Palindrome check in $O(1)$ space with list restoration, Reorder List, Remove Nth node from end, Add Two Numbers.
- **[README.md](05-LinkedList/README.md)**: Node memory layout, dummy sentinel pattern, fast/slow pointer guide, complexity tables.

### [06-StacksAndQueues](06-StacksAndQueues/)
- **[StackImplementations.java](06-StacksAndQueues/StackImplementations.java)**: Dynamic ArrayStack, LinkedStack, Dual MinStack, Encoded MinStack ($O(1)$ space), Two Stacks in One Array.
- **[QueueImplementations.java](06-StacksAndQueues/QueueImplementations.java)**: CircularArrayQueue, LinkedQueue, Queue using Stacks ($O(1)$ amortized), Stack using Queues, CircularDeque.
- **[MonotonicStack.java](06-StacksAndQueues/MonotonicStack.java)**: Next Greater Element I & II, Previous Smaller Element, Daily Temperatures, Largest Rectangle in Histogram ($O(N)$), Maximal Rectangle in Matrix.
- **[ExpressionEvaluation.java](06-StacksAndQueues/ExpressionEvaluation.java)**: Valid Parentheses, Longest Valid Parentheses, Infix-to-Postfix (Shunting-yard), RPN Evaluation, Basic Calculator II.
- **[SlidingWindowAndQueueProblems.java](06-StacksAndQueues/SlidingWindowAndQueueProblems.java)**: Sliding Window Maximum (Monotonic Deque), First Unique in Stream, Rotting Oranges (Multi-source BFS), Simplify Path.
- **[README.md](06-StacksAndQueues/README.md)**: LIFO/FIFO architecture, Monotonic Stack decision matrix, Shunting-yard guide, complexity tables.

### [07-Trees](07-Trees/)
- **[TreeTraversals.java](07-Trees/TreeTraversals.java)**: Recursive & Iterative Pre/In/Postorder, Level Order & Zigzag BFS, Morris $O(1)$ Traversal.
- **[TreePropertiesAndMetrics.java](07-Trees/TreePropertiesAndMetrics.java)**: Max Depth, Balanced Tree Check ($O(N)$), Diameter, Same/Symmetric Checks, Invert Tree, Max Path Sum.
- **[TreeViewsAndPaths.java](07-Trees/TreeViewsAndPaths.java)**: Right/Left/Top/Bottom/Vertical Views, Lowest Common Ancestor (LCA), Root-to-Leaf Paths, Build Tree, Codec Serialization.
- **[BinarySearchTree.java](07-Trees/BinarySearchTree.java)**: Search, Insert, Delete Node, Validate BST, Kth Smallest, BST LCA ($O(1)$ space), Successor/Predecessor, Sorted Array to BST, Recover BST.
- **[AdvancedTreeProblems.java](07-Trees/AdvancedTreeProblems.java)**: Boundary Traversal, Flatten Tree in-place ($O(1)$), Count Complete Nodes ($O(\log^2 N)$), Distance K BFS, Burn Tree, BST Iterator.
- **[README.md](07-Trees/README.md)**: Tree anatomy, Morris threading guide, bottom-up pruning, BST deletion cases, complexity matrix.

### [08-HeapsAndPriorityQueues](08-HeapsAndPriorityQueues/)
- **[MinHeapMaxHeap.java](08-HeapsAndPriorityQueues/MinHeapMaxHeap.java)**: Custom Min/Max Heaps from scratch, Sift-Up/Down, $O(N)$ BuildHeap, in-place HeapSort ($O(1)$ space).
- **[KthElementsAndTopK.java](08-HeapsAndPriorityQueues/KthElementsAndTopK.java)**: Kth Largest/Smallest ($O(N \log K)$), Top K Frequent (Heap & Bucket Sort), K Closest Points to Origin.
- **[MergeKSortedStreams.java](08-HeapsAndPriorityQueues/MergeKSortedStreams.java)**: Merge K Sorted Lists, Merge K Arrays, Smallest Range from K Lists, K Pairs with Smallest Sum.
- **[MedianFinderAndTwoHeaps.java](08-HeapsAndPriorityQueues/MedianFinderAndTwoHeaps.java)**: Continuous Median Tracking (Dual Heaps), Sliding Window Median, IPO Capital Maximization, Connect Sticks.
- **[TaskSchedulerAndGreedyHeaps.java](08-HeapsAndPriorityQueues/TaskSchedulerAndGreedyHeaps.java)**: Task Scheduler (Heap vs Math), Reorganize String, Minimum Interval per Query, Seat Reservation Manager.
- **[README.md](08-HeapsAndPriorityQueues/README.md)**: 1D array tree mapping, $O(N)$ buildHeap proof, two-heaps median invariant, Big-O matrix.

### [09-Graphs](09-Graphs/)
- **[GraphRepresentationsAndTraversals.java](09-Graphs/GraphRepresentationsAndTraversals.java)**: Matrix/List, BFS, DFS, Undirected Cycle (BFS), Directed Cycle (3-color DFS), Components.
- **[ShortestPathAlgorithms.java](09-Graphs/ShortestPathAlgorithms.java)**: Dijkstra ($O((V+E)\log V)$), Bellman-Ford (negative cycle detection), Floyd-Warshall ($O(V^3)$), Binary Matrix BFS.
- **[TopologicalSortAndDAG.java](09-Graphs/TopologicalSortAndDAG.java)**: Kahn's Algorithm (in-degree BFS), DFS Topo Sort, Course Schedule I & II, Alien Dictionary.
- **[DisjointSetAndMST.java](09-Graphs/DisjointSetAndMST.java)**: DSU with Path Compression & Rank ($O(\alpha(V))$), Kruskal MST, Prim MST, Provinces, Redundant Connection.
- **[NetworkFlowAndAdvancedGraphs.java](09-Graphs/NetworkFlowAndAdvancedGraphs.java)**: Word Ladder (Bidirectional BFS), Kosaraju's SCC, Tarjan's Critical Bridges, Bipartite Check.
- **[README.md](09-Graphs/README.md)**: Representation trade-offs, shortest path decision matrix, DSU Ackermann proof, Tarjan bridge condition, Big-O matrix.

### [10-DynamicProgramming](10-DynamicProgramming/)
- **[OneDimensionalDP.java](10-DynamicProgramming/OneDimensionalDP.java)**: Climbing Stairs, House Robber I/II, Coin Change I/II, LIS ($O(N \log N)$), Max Product Subarray.
- **[GridAndTwoDimensionalDP.java](10-DynamicProgramming/GridAndTwoDimensionalDP.java)**: Unique Paths I/II, Minimum Path Sum, Maximal Square, Dungeon Game.
- **[KnapsackAndSubsetsDP.java](10-DynamicProgramming/KnapsackAndSubsetsDP.java)**: 0/1 Knapsack, Unbounded Knapsack, Partition Equal Subset Sum, Target Sum.
- **[StringAndSequenceDP.java](10-DynamicProgramming/StringAndSequenceDP.java)**: LCS, Path Reconstruction, Longest Common Substring, Edit Distance, Distinct Subsequences, Wildcard Matching.
- **[PartitionAndIntervalDP.java](10-DynamicProgramming/PartitionAndIntervalDP.java)**: Matrix Chain Multiplication, Palindrome Partitioning II, Burst Balloons, Minimax Stone Game.
- **[README.md](10-DynamicProgramming/README.md)**: Recurrence formulas, space optimization patterns, Knapsack loops, Interval DP template, and 24-problem Big-O matrix.

### [11-Tries](11-Tries/)
- **[TriePrefixTree.java](11-Tries/TriePrefixTree.java)**: Standard Trie with insert, search, startsWith, and auto-complete prefix search.
- **[WordSearchAndDictionary.java](11-Tries/WordSearchAndDictionary.java)**: Word Search II (2D Board Trie with pruning) and WordDictionary with wildcard matching.
- **[BitwiseTrieAndXOR.java](11-Tries/BitwiseTrieAndXOR.java)**: Maximum XOR of Two Numbers in Array via 32-bit binary trie.
- **[KMPAndStringMatching.java](11-Tries/KMPAndStringMatching.java)**: Knuth-Morris-Pratt (KMP) linear-time substring search with LPS preprocessing.
- **[README.md](11-Tries/README.md)**: Prefix tree mechanics, character array indexing, Trie vs HashMap trade-offs.

### [12-BitManipulation](12-BitManipulation/)
- **[BitwiseOperationsAndHacks.java](12-BitManipulation/BitwiseOperationsAndHacks.java)**: Core get/set/clear/update, Brian Kernighan bit counter, power of two, lowbit isolation.
- **[SingleNumberVariants.java](12-BitManipulation/SingleNumberVariants.java)**: Single Number I (XOR), Single Number II (Modulo 3), Single Number III (Differing bit partitioning).
- **[BitmaskAndCombinatorics.java](12-BitManipulation/BitmaskAndCombinatorics.java)**: Subsets via bitmask, Counting Bits O(N) DP, Reverse Bits.
- **[README.md](12-BitManipulation/README.md)**: Bitwise identities, two's complement mechanics, bitmask DP state compression.

### [13-GreedyAlgorithms](13-GreedyAlgorithms/)
- **[IntervalScheduling.java](13-GreedyAlgorithms/IntervalScheduling.java)**: Merge Intervals, Insert Interval, Non-overlapping Intervals, Burst Balloons with arrows.
- **[GreedyOptimization.java](13-GreedyAlgorithms/GreedyOptimization.java)**: Gas Station circular tour, Jump Game I & II, Candy two-pass distribution.
- **[GreedyHeuristics.java](13-GreedyAlgorithms/GreedyHeuristics.java)**: Fractional Knapsack (Value/Weight ratio sorting).
- **[README.md](13-GreedyAlgorithms/README.md)**: Greedy choice property, interval scheduling decision table, proof templates.

### [14-SlidingWindow](14-SlidingWindow/)
- **[FixedSlidingWindow.java](14-SlidingWindow/FixedSlidingWindow.java)**: Max Sum Subarray of size K, Sliding Window Maximum via Monotonic Deque, Anagram matching.
- **[VariableSlidingWindow.java](14-SlidingWindow/VariableSlidingWindow.java)**: Longest Substring Without Repeats, Minimum Window Substring, Longest Repeating Char Replacement.
- **[TwoPointerTechniques.java](14-SlidingWindow/TwoPointerTechniques.java)**: Trapping Rain Water in O(1) space, Container With Most Water.
- **[README.md](14-SlidingWindow/README.md)**: Fixed vs Variable templates, Monotonic Deque invariant, contraction triggers.

### [15-AdvancedDataStructures](15-AdvancedDataStructures/)
- **[LRUAndLFUCache.java](15-AdvancedDataStructures/LRUAndLFUCache.java)**: LRU and LFU cache designs with O(1) eviction using DLL and HashMap.
- **[SegmentTree.java](15-AdvancedDataStructures/SegmentTree.java)**: Array-based Segment Tree with range sum queries and point updates in O(log N).
- **[FenwickTree.java](15-AdvancedDataStructures/FenwickTree.java)**: Binary Indexed Tree (BIT) with prefix sum queries and point updates.
- **[MonotonicStackAndQueue.java](15-AdvancedDataStructures/MonotonicStackAndQueue.java)**: Largest Rectangle in Histogram and range bounds in O(N).
- **[README.md](15-AdvancedDataStructures/README.md)**: Segment Tree vs Fenwick Tree, cache eviction mechanics, Big-O reference matrix.

---

## 🚀 How to Run

Compile and run any Java file from the command line:

```bash
# 01-Arrays
cd 01-Arrays && javac BasicOperations.java && java BasicOperations

# 02-Strings
cd ../02-Strings && javac StringOperations.java && java StringOperations

# 03-Recursion
cd ../03-Recursion && javac BasicRecursion.java && java BasicRecursion

# 04-SearchingSorting
cd ../04-SearchingSorting && javac BasicSorting.java && java BasicSorting

# 05-LinkedList
cd ../05-LinkedList
javac SinglyLinkedList.java && java SinglyLinkedList
javac DoublyLinkedList.java && java DoublyLinkedList
javac LinkedListCycle.java && java LinkedListCycle
javac MergeAndSortList.java && java MergeAndSortList
javac PalindromeAndReorderList.java && java PalindromeAndReorderList

# 06-StacksAndQueues
cd ../06-StacksAndQueues
javac StackImplementations.java && java StackImplementations
javac QueueImplementations.java && java QueueImplementations
javac MonotonicStack.java && java MonotonicStack
javac ExpressionEvaluation.java && java ExpressionEvaluation
javac SlidingWindowAndQueueProblems.java && java SlidingWindowAndQueueProblems

# 07-Trees
cd ../07-Trees
javac TreeTraversals.java && java TreeTraversals
javac TreePropertiesAndMetrics.java && java TreePropertiesAndMetrics
javac TreeViewsAndPaths.java && java TreeViewsAndPaths
javac BinarySearchTree.java && java BinarySearchTree
javac AdvancedTreeProblems.java && java AdvancedTreeProblems

# 08-HeapsAndPriorityQueues
cd ../08-HeapsAndPriorityQueues
javac MinHeapMaxHeap.java && java MinHeapMaxHeap
javac KthElementsAndTopK.java && java KthElementsAndTopK
javac MergeKSortedStreams.java && java MergeKSortedStreams
javac MedianFinderAndTwoHeaps.java && java MedianFinderAndTwoHeaps
javac TaskSchedulerAndGreedyHeaps.java && java TaskSchedulerAndGreedyHeaps

# 09-Graphs
cd ../09-Graphs
javac GraphRepresentationsAndTraversals.java && java GraphRepresentationsAndTraversals
javac ShortestPathAlgorithms.java && java ShortestPathAlgorithms
javac TopologicalSortAndDAG.java && java TopologicalSortAndDAG
javac DisjointSetAndMST.java && java DisjointSetAndMST
javac NetworkFlowAndAdvancedGraphs.java && java NetworkFlowAndAdvancedGraphs

# 10-DynamicProgramming
cd ../10-DynamicProgramming
javac OneDimensionalDP.java && java OneDimensionalDP
javac GridAndTwoDimensionalDP.java && java GridAndTwoDimensionalDP
javac KnapsackAndSubsetsDP.java && java KnapsackAndSubsetsDP
javac StringAndSequenceDP.java && java StringAndSequenceDP
javac PartitionAndIntervalDP.java && java PartitionAndIntervalDP

# 11-Tries
cd ../11-Tries
javac TriePrefixTree.java && java TriePrefixTree
javac WordSearchAndDictionary.java && java WordSearchAndDictionary
javac BitwiseTrieAndXOR.java && java BitwiseTrieAndXOR

# 12-BitManipulation
cd ../12-BitManipulation
javac BitwiseOperationsAndHacks.java && java BitwiseOperationsAndHacks
javac SingleNumberVariants.java && java SingleNumberVariants
javac BitmaskAndCombinatorics.java && java BitmaskAndCombinatorics
```

---

## 📌 Curriculum Roadmap

- [x] **01-Arrays**
- [x] **02-Strings**
- [x] **03-Recursion & Backtracking**
- [x] **04-Searching & Sorting**
- [x] **05-Linked Lists**
- [x] **06-Stacks & Queues**
- [x] **07-Trees & Binary Search Trees**
- [x] **08-Heaps & Priority Queues**
- [x] **09-Graphs**
- [x] **10-Dynamic Programming**
- [x] **11-Tries & String Structures**
- [x] **12-Bit Manipulation & Mathematics**
- [x] **13-Greedy Algorithms & Intervals**
- [x] **14-Sliding Window & Two Pointers**
- [x] **15-Advanced Data Structures (LRU/LFU, Segment Tree, Fenwick)**

---

## 💻 Requirements
- Java Development Kit (JDK) 8 or higher
- Git

*Maintained by [Shreepurnaa V](https://github.com/shree0731)*

### 💡 Linked List Golden Rules
- **Always consider a Dummy Head** when inserting/deleting could affect `head`.
- **Fast and Slow Pointers**:
  - Finding middle: `slow = slow.next; fast = fast.next.next;`
  - Finding cycle: `if (slow == fast) cycleDetected();`
- **In-place reversal** requires 3 pointers: `prev`, `curr`, `nextTemp`.

> Milestone: 05-Linked Lists module completed with 56 structured commits, comprehensive tests, and Big-O cheat sheets.

### 💡 Priority Queue Golden Rules
- Use bounded heaps of size $K$ to solve Top-$K$ queries in $O(N \log K)$ rather than $O(N \log N)$.
- Avoid integer subtraction in comparators `(a - b)` to prevent 32-bit integer overflow; always use `Integer.compare(a, b)`.

### 💡 Graph Golden Rules
- Use BFS for shortest path in unweighted graphs; use Dijkstra for non-negative weights; use Bellman-Ford when negative weights or cycles are possible.
- A topological sort only exists on Directed Acyclic Graphs (DAGs); Kahn's algorithm detects cycles if processed count < V.

---
*Milestone 9 Verified: 9 Complete Modules with 500+ Commits.*

### 💡 Dynamic Programming Golden Rules
- **0/1 vs Unbounded Knapsack**: Reverse inner loop (`capacity -> weight`) for 0/1; forward inner loop (`weight -> capacity`) for unbounded.
- **Rolling Space Reduction**: If $dp[i][j]$ depends only on row $i-1$, reduce space from $O(M \times N)$ to $O(N)$ with a 1D array.
- **LIS in $O(N \log N)$**: Use patience sorting with binary search on active subsequence tails instead of quadratic nested loops.

---
## 🎓 Curriculum Completion Certificate
All 15 core and advanced modules in the Java Data Structures & Algorithms curriculum have been designed, coded, documented, and rigorously verified:
1. `01-Arrays`
2. `02-Strings`
3. `03-Recursion`
4. `04-SearchingSorting`
5. `05-LinkedList`
6. `06-StacksAndQueues`
7. `07-Trees`
8. `08-HeapsAndPriorityQueues`
9. `09-Graphs`
10. `10-DynamicProgramming`
11. `11-Tries`
12. `12-BitManipulation`
13. `13-GreedyAlgorithms`
14. `14-SlidingWindow`
15. `15-AdvancedDataStructures`

*All 15 Modules Fully Verified with 630+ Production-Ready Commits.*

*Repository state fully synchronized and verified through November 21st, 2026.*
