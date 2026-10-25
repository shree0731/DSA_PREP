# 02 - Strings

Welcome to the **Strings** module of the DSA Preparation repository.

## Overview
A **String** in Java is an object that represents a sequence of char values. Unlike primitive types, Java strings are **immutable** and stored in the **String Constant Pool** inside the heap memory for memory optimization.

## Topics Covered
1. **StringOperations.java**: Core string manipulation, immutability, interning, reverse words, string compression, and rotation.
2. **ValidAnagram.java**: Anagram verification via sorting, hash maps, frequency arrays, and group anagrams.
3. **ValidPalindrome.java**: Palindrome checks with two pointers, alphanumeric filtering, and single-character deletion tolerance.
4. **LongestSubstringWithoutRepeating.java**: Sliding window implementations (HashSet, HashMap, direct ASCII bucket table) and substring retrieval.
5. **LongestPalindromicSubstring.java**: Cubic brute force, dynamic programming table, and expand around center technique.

---

## 🧠 Java String Memory Architecture

### The String Constant Pool (SCP)
- When a string literal is created (e.g., `String s1 = "hello";`), JVM checks the String Constant Pool.
- If it already exists, reference to the pooled instance is returned.
- If instantiated with `new String("hello")`, an object is allocated in standard Heap memory outside the pool.
- Calling `.intern()` moves/links the string to the pool.

### Why Are Strings Immutable in Java?
1. **Security**: Strings are heavily used in network connections, database URLs, file paths, and reflection. Immutability prevents malicious alteration.
2. **Thread Safety**: Immutable objects are inherently thread-safe without requiring synchronization.
3. **Caching HashCode**: The `hashCode()` of a String is computed once and cached, making Strings ideal keys for `HashMap` and `HashSet`.
4. **Memory Optimization**: String Pool allows thousands of references to share identical literal data.

---

## ⚡ String Operations Complexity Cheat Sheet

| Operation | String | StringBuilder / StringBuffer |
| :--- | :--- | :--- |
| **`charAt(i)`** | $O(1)$ | $O(1)$ |
| **`length()`** | $O(1)$ | $O(1)$ |
| **Concatenation (`+`)** | $O(N + M)$ (allocates new string) | $O(1)$ amortized append |
| **`substring(start, end)`** | $O(K)$ where $K = \text{end} - \text{start}$ | $O(K)$ |
| **`indexOf(char)`** | $O(N)$ | $O(N)$ |
| **Reversal** | $O(N)$ (requires new object/char array) | $O(N)$ in-place buffer |
| **`equals()`** | $O(N)$ | N/A (uses Object `equals` by default) |

> ⚠️ **Performance Warning**: Concatenating strings in a loop using `+` creates $O(N^2)$ garbage objects! Always use `StringBuilder` for sequential appends.

---

## 🛠️ Essential String Operations Guide

### 1. Reverse Words in String
- Trim leading/trailing spaces.
- Split using regex `\\s+` to handle multi-space delimiters.
- Assemble in reverse order using `StringBuilder`.

### 2. Rotation Check via Doubled String
- Any cyclic rotation of $S_1$ is guaranteed to appear as a contiguous substring of $(S_1 + S_1)$ provided $|S_1| = |S_2|$.
- Time Complexity: $O(N)$ with KMP or standard `contains()`.

### 3. Run-Length Encoding Compression
- Iterate through string tracking consecutive repetitions.
- Only return compressed version if strictly shorter than original.

---

## 🔠 Anagrams (LeetCode #242 & #49)

### Approaches Comparison

| Method | Time Complexity | Space Complexity | Notes |
| :--- | :--- | :--- | :--- |
| **Sorting** | $O(N \log N)$ | $O(N)$ | Easy to implement, works well for small strings. |
| **Hash Map** | $O(N)$ | $O(K)$ | Handles full Unicode character set seamlessly. |
| **Fixed 26-Bucket Array** | $O(N)$ | $O(1)$ | Optimal performance and minimal memory overhead. |
| **Group Anagrams (Count Key)**| $O(N \times K)$ | $O(N \times K)$ | Optimal linear approach avoiding string sorting. |

---

## 🔁 Palindrome Patterns

### Two-Pointer In-Place Filtering
- Left and right pointers start at outer boundaries: `left = 0`, `right = length - 1`.
- Skip invalid characters using `Character.isLetterOrDigit()`.
- Compare lower-case values.
- Space Complexity: $O(1)$ compared to $O(N)$ regex cleaning.

### Tolerance Deletion (Valid Palindrome II)
- When a mismatch occurs at `(left, right)`, branch into two sub-checks:
  1. Skip left: verify `(left + 1, right)` is palindrome.
  2. Skip right: verify `(left, right - 1)` is palindrome.
- Total time stays $O(N)$ with no recursive stack blowup.

---

## 🪟 Sliding Window on Strings (LeetCode #3)

### Mechanism Comparison

1. **HashSet Sliding Window**:
   - Shrinks left pointer step-by-step: `while (set.contains(c)) { set.remove(s[left++]); }`.
   - Each character can be added and removed once $\implies \mathcal{O}(2N)$ steps.

2. **HashMap Last-Seen Index (Optimal)**:
   - Skips left pointer directly to `Math.max(left, map.get(c) + 1)`.
   - Handles backtracking edge cases like `"abba"` without revisiting intermediate positions $\implies \mathcal{O}(N)$ exact steps.

3. **ASCII Bucket Table**:
   - Replaces `HashMap` hashing with direct array indexing `int[128]` for cache-friendly execution.

---

## 🪞 Longest Palindromic Substring (LeetCode #5)

### Strategy Comparison

| Approach | Time Complexity | Space Complexity | Pros & Cons |
| :--- | :--- | :--- | :--- |
| **Brute Force** | $O(N^3)$ | $O(1)$ | Simple to verify, unacceptable for $N > 500$. |
| **Dynamic Programming** | $O(N^2)$ | $O(N^2)$ | Clean state recurrence, but high $O(N^2)$ memory footprint. |
| **Expand Around Center**| $O(N^2)$ | $O(1)$ | Optimal practical solution: zero auxiliary heap allocation. |
| **Manacher's Algorithm** | $O(N)$ | $O(N)$ | Linear time complexity using previously matched palindrome mirrors. |

---

## 📋 Module Summary & Problem Mapping

| File | LeetCode # | Primary Technique | Time Complexity | Space Complexity |
| :--- | :--- | :--- | :--- | :--- |
| **StringOperations.java** | #151, #205, #387 | String Pool, In-place Reversal, Character Count | $O(N)$ | $O(1)$ to $O(N)$ |
| **ValidAnagram.java** | #242, #49, #438 | 26-Integer Bucket / Frequency Hash Key | $O(N)$ | $O(1)$ / $O(N)$ |
| **ValidPalindrome.java** | #125, #680, #409 | Two Pointers Converging / Tolerance Skipping | $O(N)$ | $O(1)$ |
| **LongestSubstringWithoutRepeating.java** | #3 | Sliding Window + HashMap Last-Seen Index | $O(N)$ | $O(\min(N, M))$ |
| **LongestPalindromicSubstring.java** | #5, #647 | Expand Around Center | $O(N^2)$ | $O(1)$ |

---

## 🎯 String Interview Tips & Tricks
1. **Always ask character set scope**: Is input purely lowercase ASCII (`'a'-'z'`), standard ASCII 128, or UTF-8 Unicode?
2. **Frequency array over HashMap**: If input is restricted to 26 letters, `int[26]` is faster by orders of magnitude due to CPU cache locality and zero object boxing.
3. **Avoid repetitive string concatenation**: In Java, `str += c` inside a loop creates a new `String` and copies all characters every iteration ($\mathcal{O}(N^2)$ total work). Always use `StringBuilder`.
