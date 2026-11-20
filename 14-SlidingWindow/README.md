# 14-SlidingWindow & Two Pointers

Welcome to the **Sliding Window & Two Pointers** module of the Java DSA Preparation repository.

## Overview
The **Sliding Window** technique converts quadratic $O(N^2)$ brute-force nested loop subarray queries into linear $O(N)$ operations by maintaining a dynamic or fixed continuous window.

---

## Planned Directory Structure
```
14-SlidingWindow/
├── FixedSlidingWindow.java     # Max Sum Subarray of Size K, Sliding Window Maximum (Monotonic Deque), Anagrams
├── VariableSlidingWindow.java  # Longest Substring Without Repeats, Minimum Window Substring, Character Replacement
├── TwoPointerTechniques.java   # Trapping Rain Water (O(1) space), Container With Most Water, 3Sum Closest
└── README.md                   # Fixed vs Variable templates, Monotonic Deque invariant, complexity table
```

---

## 🪟 Sliding Window Templates

### 1. Fixed Window of Size $K$
```java
for (int i = 0; i < n; i++) {
    // 1. Add nums[i] to window
    if (i >= k - 1) {
        // 2. Record answer from window [i - k + 1, i]
        // 3. Remove nums[i - k + 1] from window
    }
}
```

### 2. Variable Window (Expand Right, Contract Left)
```java
int left = 0;
for (int right = 0; right < n; right++) {
    // 1. Expand: include nums[right]
    while (windowConditionViolated()) {
        // 2. Contract: shrink from left
        left++;
    }
    // 3. Update optimal answer
}
```
