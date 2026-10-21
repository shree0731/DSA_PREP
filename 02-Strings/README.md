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
