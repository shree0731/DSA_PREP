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
