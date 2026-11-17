# 11-Tries & Advanced String Structures

Welcome to the **Tries** module of the Java DSA Preparation repository.

## Overview
A **Trie** (Prefix Tree) is an $m$-ary tree data structure used to store a dynamic set or associative array where keys are usually strings.
Unlike a binary search tree, no node in the tree stores the key associated with that node; instead, its position in the tree defines the key.

---

## Planned Directory Structure
```
11-Tries/
├── TriePrefixTree.java           # Insert, Search, StartsWith, Auto-Complete
├── WordSearchAndDictionary.java  # Word Search II (2D Board Trie), Add/Search with '.' Wildcard
├── BitwiseTrieAndXOR.java        # Maximum XOR of Two Numbers in Array (Binary Trie)
├── KMPAndStringMatching.java     # Knuth-Morris-Pratt (KMP) O(N + M) Pattern Matching & LPS
└── README.md                     # Node structures, memory optimization, KMP vs Rabin-Karp
```

---

## 🏗️ Trie Node Structure & Indexing
Each `TrieNode` typically contains:
1. `TrieNode[] children = new TrieNode[26];` (for lowercase English letters `a-z`).
2. `boolean isEndOfWord;`
3. Child indexing formula: `int index = ch - 'a';` ($0 \le \text{index} < 26$).
- **Time Complexity**: $O(L)$ for insert, search, and prefix query, where $L$ is the string length.
- **Space Complexity**: $O(\Sigma \cdot L \cdot N)$ where $\Sigma$ is alphabet size, $L$ average word length, $N$ word count.
