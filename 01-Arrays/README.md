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

---

## 🎯 Two Sum Problem

### Problem Summary
Given an array `nums` and integer `target`, return indices of two numbers adding up to `target`.

### Comparison of Approaches

| Approach | Time Complexity | Auxiliary Space | Best Used When |
| :--- | :--- | :--- | :--- |
| **Brute Force** | $O(N^2)$ | $O(1)$ | Small $N < 100$, strict memory limit |
| **Two-Pass Hash Map** | $O(N)$ | $O(N)$ | General lookups with complete dataset |
| **One-Pass Hash Map** | $O(N)$ | $O(N)$ | Optimal standard solution (LeetCode #1) |
| **Two Pointers (Two Sum II)** | $O(N)$ | $O(1)$ | Input array is already sorted |
| **Sort + Two Pointers** | $O(N \log N)$ | $O(1)$ | Returning values (not original indices) |

### Key Optimization
By sizing `HashMap` with `(int) Math.ceil(nums.length / 0.75f) + 1`, we prevent expensive bucket re-allocations and rehashing overhead during insertion.

---

## 📈 Maximum Subarray (Kadane's Algorithm)

### Problem Summary (LeetCode #53)
Given an integer array `nums`, find the contiguous subarray which has the largest sum and return its sum.

### Comparison of Approaches

| Approach | Time Complexity | Space Complexity | Description |
| :--- | :--- | :--- | :--- |
| **Cubic Brute Force** | $O(N^3)$ | $O(1)$ | Try all `i`, `j` and calculate subarray sum in loop. |
| **Quadratic Prefix Sum** | $O(N^2)$ | $O(1)$ | Accumulate sum incrementally for each starting `i`. |
| **Divide and Conquer** | $O(N \log N)$ | $O(\log N)$ | Split into halves and compute cross-boundary max sum. |
| **Dynamic Programming** | $O(N)$ | $O(N)$ | Tabulate `dp[i] = Math.max(nums[i], dp[i-1] + nums[i])`. |
| **Kadane's Algorithm** | $O(N)$ | $O(1)$ | Optimal space-reduced DP keeping running current max. |

### Visual Representation of Kadane's
```text
nums:        [-2,  1, -3,  4, -1,  2,  1, -5,  4]
currentSum:  [-2,  1, -2,  4,  3,  5,  6,  1,  5]
maxSum:      [-2,  1,  1,  4,  4,  5,  6,  6,  6] -> Result = 6 ([4, -1, 2, 1])
```
