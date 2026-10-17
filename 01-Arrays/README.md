# 01 - Arrays

Welcome to the **Arrays** module of the DSA Preparation repository.

## Overview
An **Array** is a linear data structure consisting of a collection of elements, each identified by at least one array index or key. In Java, arrays are objects, dynamically allocated in contiguous memory locations.

### Memory Layout & Characteristics
- **Contiguous Allocation**: Elements reside in sequential memory addresses.
- **Random Access (O(1))**: Element address formula:
  $$\text{Address}(A[i]) = \text{Base Address} + (i \times \text{Element Size})$$
- **Cache Locality**: Due to spatial locality, sequential traversal benefits heavily from CPU L1/L2 cache prefetching.
- **Homogeneous Elements**: Stores elements of identical data types.

---

## ⚡ Big-O Complexity Cheat Sheet

| Operation | Static Array | Dynamic Array (Amortized) | Worst Case Dynamic |
| :--- | :--- | :--- | :--- |
| **Access by Index** | $O(1)$ | $O(1)$ | $O(1)$ |
| **Search by Value** | $O(N)$ | $O(N)$ | $O(N)$ |
| **Insertion (End)** | N/A (Fixed size) | $O(1)$ | $O(N)$ (when resizing) |
| **Insertion (Middle/Beginning)** | $O(N)$ | $O(N)$ | $O(N)$ |
| **Deletion (End)** | $O(1)$ | $O(1)$ | $O(1)$ |
| **Deletion (Middle/Beginning)** | $O(N)$ | $O(N)$ | $O(N)$ |
| **Space Complexity** | $O(N)$ | $O(N)$ | $O(N)$ |

---

## Topics in this Module
1. `BasicOperations.java`: Custom dynamic array implementation and algorithms.
2. `SecondLargestElement.java`: Optimal single-pass search and duplicate handling.
3. `TwoSum.java`: Hash-map, two-pointer, and pair generation variants.
4. `MaximumSubarray.java`: Kadane's algorithm, range tracking, and divide-and-conquer.

---

## 🔍 Second Largest Element

### Problem Statement
Given an array of integers, find the second largest distinct element. If it does not exist (e.g., all elements are identical, or array size $< 2$), return `-1`.

### Comparison of Approaches

| Approach | Time Complexity | Space Complexity | Description |
| :--- | :--- | :--- | :--- |
| **Sorting** | $O(N \log N)$ | $O(N)$ | Sort copy of array; traverse backwards for first element $< \text{max}$. |
| **Two-Pass Scan** | $O(2N) = O(N)$ | $O(1)$ | Pass 1 finds maximum; Pass 2 finds maximum element $< \text{overall max}$. |
| **Single-Pass (Optimal)**| $O(N)$ | $O(1)$ | Track `largest` and `secondLargest` simultaneously in a single loop. |

### Key Takeaways
- Be vigilant of duplicates: having multiple copies of the maximum element does not make it the second largest.
- Ensure correct initialization (avoid assuming positive values or assuming `Integer.MIN_VALUE` cannot be an input).
