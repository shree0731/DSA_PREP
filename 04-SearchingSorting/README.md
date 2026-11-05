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

---

## 🔍 Elementary Sorts Analysis

### Insertion Sort: Why it matters
- Even though average time is $O(N^2)$, Insertion Sort is the fastest algorithm in practice for small arrays ($N \le 16$ to $32$).
- Java's `Arrays.sort()` (Dual-Pivot Quicksort) switches to Insertion Sort for short subarrays.
- Adaptive: only $O(N)$ comparisons when input is already sorted.

---

## 🧩 Merge Sort & Inversion Counting

### Divide-and-Conquer Recurrence
$$T(N) = 2T(N/2) + O(N) \implies O(N \log N)$$
By the Master Theorem ($a = 2, b = 2, d = 1$), time complexity is strictly $\Theta(N \log N)$ across all inputs.

### Inversion Counting
When `leftArr[i] > rightArr[j]`, since `leftArr` is already sorted, **all** elements from `i` to `mid` are strictly greater than `rightArr[j]`:
$$\text{inversions} += (\text{mid} - i + 1)$$
This solves the inversion count problem in $O(N \log N)$ rather than $O(N^2)$ brute force.

---

## ⚡ QuickSort Partitioning Schemes

### 1. Lomuto vs Hoare
- **Lomuto Partition**: Moves right-to-left with single index; easier to reason about, but does $\approx 3\times$ more swaps.
- **Hoare Partition**: Converging two-pointer search from both ends; stops scanning when matching elements found; significantly fewer swaps.

### 2. 3-Way Partitioning (Dutch National Flag)
- Standard Quicksort degrades to $O(N^2)$ when all elements are identical (e.g. $[1, 1, 1, 1]$).
- 3-Way partitioning divides into three sections: $< v$, $== v$, $> v$.
- Identical elements are grouped and never touched again in subsequent recursions $\implies O(N)$ best case for repeated keys.

---

## 🎯 Lower Bound & Upper Bound Invariants

- **Lower Bound (`std::lower_bound`)**: First element with value $\ge \text{target}$.
  - Predicate: `nums[mid] >= target` $\implies$ `high = mid`.
- **Upper Bound (`std::upper_bound`)**: First element with value $> \text{target}$.
  - Predicate: `nums[mid] > target` $\implies$ `high = mid`.
- **Target Count**:
  $$\text{count} = \text{upperBound}(target) - \text{lowerBound}(target)$$

---

## 🎯 Binary Search on Answer Space (Monotonic Predicate Pattern)

When a problem asks for the **minimum possible maximum** or **maximum possible minimum**, convert it to a decision problem:
$$P(x) = \begin{cases} \text{True} & \text{if solution } x \text{ is feasible} \\ \text{False} & \text{otherwise} \end{cases}$$

If $P(x)$ is monotonic (e.g., $\text{False}, \text{False}, \dots, \text{True}, \text{True}$), binary search can identify the exact inflection point in $O(\log(\text{search range}))$.

### Search on Answer Template
```java
int low = minFeasibleAnswer, high = maxFeasibleAnswer;
int best = high;

while (low <= high) {
    int mid = low + (high - low) / 2;
    if (isValid(mid)) {
        best = mid;
        high = mid - 1; // Minimize answer
    } else {
        low = mid + 1;
    }
}
return best;
```
