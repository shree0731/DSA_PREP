# DSA Preparation (Java)

[![Language](https://img.shields.io/badge/Language-Java_8+-orange.svg)](https://www.oracle.com/java/)
[![Modules](https://img.shields.io/badge/Progress-4_Modules_Completed-brightgreen.svg)](01-Arrays/)
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

---

## 🚀 How to Run

Compile and run any Java file from the command line:

```bash
# 01-Arrays
cd 01-Arrays
javac BasicOperations.java && java BasicOperations
javac SecondLargestElement.java && java SecondLargestElement
javac TwoSum.java && java TwoSum
javac MaximumSubarray.java && java MaximumSubarray

# 02-Strings
cd ../02-Strings
javac StringOperations.java && java StringOperations
javac ValidAnagram.java && java ValidAnagram
javac ValidPalindrome.java && java ValidPalindrome
javac LongestSubstringWithoutRepeating.java && java LongestSubstringWithoutRepeating
javac LongestPalindromicSubstring.java && java LongestPalindromicSubstring

# 03-Recursion
cd ../03-Recursion
javac BasicRecursion.java && java BasicRecursion
javac Subsets.java && java Subsets
javac Permutations.java && java Permutations
javac CombinationSum.java && java CombinationSum
javac NQueens.java && java NQueens

# 04-SearchingSorting
cd ../04-SearchingSorting
javac BasicSorting.java && java BasicSorting
javac MergeSort.java && java MergeSort
javac QuickSort.java && java QuickSort
javac BinarySearchVariants.java && java BinarySearchVariants
javac BinarySearchOnAnswer.java && java BinarySearchOnAnswer
```

---

## 📌 Curriculum Roadmap

- [x] **01-Arrays**
- [x] **02-Strings**
- [x] **03-Recursion & Backtracking**
- [x] **04-Searching & Sorting**
- [ ] **05-Linked Lists**
- [ ] **06-Stacks & Queues**
- [ ] **07-Trees & Binary Search Trees**
- [ ] **08-Heaps & Priority Queues**
- [ ] **09-Graphs**
- [ ] **10-Dynamic Programming**

---

## 💻 Requirements
- Java Development Kit (JDK) 8 or higher
- Git

*Maintained by [Shreepurnaa V](https://github.com/shree0731)*

### ⚡ Algorithm Selection Rule of Thumb
- Need guaranteed $O(N \log N)$ and stability? $\implies$ **Merge Sort**
- Need in-place sorting with lowest constant factor? $\implies$ **Dual-Pivot QuickSort**
- Dataset has massive number of duplicate keys? $\implies$ **3-Way QuickSort (DNF)**
- Small array ($N \le 32$) or nearly sorted? $\implies$ **Insertion Sort**
- Finding $K$th largest in unsorted array? $\implies$ **QuickSelect ($O(N)$ average)**

> Milestone: 04-Searching & Sorting module completed with 56 structured commits, comprehensive tests, and Big-O cheat sheets.
