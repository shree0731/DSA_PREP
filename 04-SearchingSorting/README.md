# 04 - Searching & Sorting

Welcome to the **Searching & Sorting** module of the DSA Preparation repository.

## Overview
Searching and Sorting are the core algorithmic building blocks in computer science. Sorting organizes unordered data into a monotonically increasing or decreasing order, which unlocks powerful $\mathcal{O}(\log N)$ search algorithms.

## Topics Covered
1. **BasicSorting.java**: Bubble Sort (optimized with swapped flag), Selection Sort, Insertion Sort, stability demonstration.
2. **MergeSort.java**: Standard merge sort, iterative bottom-up merge sort, count inversions in an array.
3. **QuickSort.java**: Lomuto vs Hoare partitioning, 3-Way QuickSort (Dutch National Flag), QuickSelect ($\mathcal{O}(N)$ Kth largest).
4. **BinarySearchVariants.java**: Lower bound, Upper bound, First & Last position, Rotated sorted array search I & II, Peak element.
5. **BinarySearchOnAnswer.java**: Monotonic predicate framework, Integer square root, Koko Eating Bananas, Capacity to Ship Packages, Aggressive Cows / Magnetic Force.

---

## 🧭 Sorting Algorithms Classification

### 1. Stability in Sorting
- A sorting algorithm is **stable** if two objects with equal keys appear in the same order in sorted output as they appeared in the original input.
- **Stable**: Merge Sort, Insertion Sort, Bubble Sort, TimSort.
- **Unstable**: QuickSort, HeapSort, Selection Sort.

### 2. In-Place Sorting
- An algorithm is **in-place** if it modifies the input array without allocating auxiliary storage proportional to array length ($\mathcal{O}(1)$ auxiliary space).
- In-place: QuickSort ($\mathcal{O}(\log N)$ call stack), HeapSort ($\mathcal{O}(1)$), Insertion Sort, Bubble Sort, Selection Sort.
- Not in-place: Merge Sort ($\mathcal{O}(N)$ auxiliary buffer).

---

## ⚡ Big-O Complexity Comparison

| Algorithm | Best Time | Average Time | Worst Time | Space Complexity | Stable? | In-Place? |
| :--- | :--- | :--- | :--- | :--- | :--- | :--- |
| **Bubble Sort** | $O(N)$ | $O(N^2)$ | $O(N^2)$ | $O(1)$ | Yes | Yes |
| **Selection Sort** | $O(N^2)$ | $O(N^2)$ | $O(N^2)$ | $O(1)$ | No | Yes |
| **Insertion Sort** | $O(N)$ | $O(N^2)$ | $O(N^2)$ | $O(1)$ | Yes | Yes |
| **Merge Sort** | $O(N \log N)$ | $O(N \log N)$ | $O(N \log N)$ | $O(N)$ | Yes | No |
| **QuickSort** | $O(N \log N)$ | $O(N \log N)$ | $O(N^2)$ | $O(\log N)$ | No | Yes |
| **HeapSort** | $O(N \log N)$ | $O(N \log N)$ | $O(N \log N)$ | $O(1)$ | No | Yes |
| **QuickSelect** | $O(N)$ | $O(N)$ | $O(N^2)$ | $O(\log N)$ | N/A | Yes |
