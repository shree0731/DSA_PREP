# DSA Preparation (Java)

[![Language](https://img.shields.io/badge/Language-Java_8+-orange.svg)](https://www.oracle.com/java/)
[![Topics](https://img.shields.io/badge/Module-01--Arrays-blue.svg)](01-Arrays/)
[![License](https://img.shields.io/badge/License-MIT-green.svg)](LICENSE)

A curated, interview-focused repository for Data Structures and Algorithms preparation in Java. Every problem is implemented with multiple approaches (Brute Force, Better, and Optimal) along with Big-O complexity analysis and test cases.

---

## 📂 Modules Structure

### [01-Arrays](01-Arrays/)
- **[BasicOperations.java](01-Arrays/BasicOperations.java)**:
  - Custom Dynamic Array (amortized $O(1)$ append, element shifting)
  - Linear Search & Binary Search (Iterative and Recursive)
  - In-place Array Reversal
  - Array Rotations (Left & Right via 3-step Reversal Algorithm)
- **[SecondLargestElement.java](01-Arrays/SecondLargestElement.java)**:
  - Approach 1: Sorting ($O(N \log N)$)
  - Approach 2: Two-Pass Linear Scan ($O(2N)$)
  - Approach 3: Optimal Single-Pass ($O(N)$ time, $O(1)$ space)
  - Symmetry: Second Smallest Element
- **[TwoSum.java](01-Arrays/TwoSum.java)**:
  - Approach 1: Brute Force ($O(N^2)$)
  - Approach 2: Two-Pass Hash Map ($O(N)$)
  - Approach 3: Optimal One-Pass Hash Map ($O(N)$ time, $O(N)$ space with optimized load-factor preallocation)
  - Approach 4: Two Sum II - Two Pointer ($O(N)$ time, $O(1)$ space)
  - All Unique Pairs Generator
- **[MaximumSubarray.java](01-Arrays/MaximumSubarray.java)**:
  - Approach 1: Cubic Brute Force ($O(N^3)$)
  - Approach 2: Quadratic Running Sum ($O(N^2)$)
  - Approach 3: Optimal Kadane's Algorithm ($O(N)$ time, $O(1)$ space)
  - Approach 4: Divide and Conquer ($O(N \log N)$)
  - Approach 5: Tabular Dynamic Programming ($O(N)$)
  - Range and Subarray Element Retrieval
- **[README.md](01-Arrays/README.md)**:
  - In-depth theoretical foundations, memory cache locality, complexity cheat-sheet, problem walkthroughs, and pattern guides.

---

## 🚀 How to Run

Compile and execute any file directly from the command line:

```bash
# Navigate to the module directory
cd 01-Arrays

# Compile
javac BasicOperations.java
java BasicOperations

javac SecondLargestElement.java
java SecondLargestElement

javac TwoSum.java
java TwoSum

javac MaximumSubarray.java
java MaximumSubarray
```

---

## 📌 Roadmap

- [x] **01-Arrays**
- [ ] **02-Strings**
- [ ] **03-Recursion & Backtracking**
- [ ] **04-Searching & Sorting**
- [ ] **05-Linked Lists**
- [ ] **06-Stacks & Queues**
- [ ] **07-Trees & Binary Search Trees**
- [ ] **08-Heaps & Priority Queues**
- [ ] **09-Graphs**
- [ ] **10-Dynamic Programming**

---
*Maintained by [Shreepurnaa V](https://github.com/shree0731)*
