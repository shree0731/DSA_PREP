# DSA Preparation (Java)

[![Language](https://img.shields.io/badge/Language-Java_8+-orange.svg)](https://www.oracle.com/java/)
[![Progress](https://img.shields.io/badge/Milestone-6_Modules_Completed-success.svg)](01-Arrays/)
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
- [ ] **08-Heaps & Priority Queues**
- [ ] **09-Graphs**
- [ ] **10-Dynamic Programming**

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
