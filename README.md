# DSA Preparation (Java)

[![Language](https://img.shields.io/badge/Language-Java_8+-orange.svg)](https://www.oracle.com/java/)
[![Modules](https://img.shields.io/badge/Modules-Arrays_%7C_Strings-blue.svg)](01-Arrays/)
[![License](https://img.shields.io/badge/License-MIT-green.svg)](LICENSE)

A curated, interview-focused repository for Data Structures and Algorithms preparation in Java. Every problem is implemented with multiple approaches (Brute Force, Better, and Optimal) along with Big-O complexity analysis and test cases.

---

## 📂 Modules Structure

### [01-Arrays](01-Arrays/)
- **[BasicOperations.java](01-Arrays/BasicOperations.java)**: Custom dynamic array, linear & binary search, in-place reversal, 3-step left/right rotations.
- **[SecondLargestElement.java](01-Arrays/SecondLargestElement.java)**: Sorting vs Two-Pass vs Optimal Single-Pass ($O(N)$ time, $O(1)$ space), duplicate handling, second smallest.
- **[TwoSum.java](01-Arrays/TwoSum.java)**: Brute Force, Two-Pass Hash Map, Optimal One-Pass Hash Map with preallocated capacity, Two Sum II two-pointer.
- **[MaximumSubarray.java](01-Arrays/MaximumSubarray.java)**: Cubic, Quadratic, Kadane's Algorithm ($O(N)$), Divide and Conquer ($O(N \log N)$), DP table, range tracker.
- **[README.md](01-Arrays/README.md)**: Array memory characteristics, complexity tables, problem walkthroughs.

### [02-Strings](02-Strings/)
- **[StringOperations.java](02-Strings/StringOperations.java)**: String Constant Pool demo, immutability, reverse words, string rotation, run-length compression, isomorphic check, first unique character.
- **[ValidAnagram.java](02-Strings/ValidAnagram.java)**: Sorting, Hash Map, 26-int frequency bucket, Group Anagrams (sorting key & frequency key), Sliding Window anagram search.
- **[ValidPalindrome.java](02-Strings/ValidPalindrome.java)**: Two-pointer in-place alphanumeric filtering, Valid Palindrome II (1 deletion tolerance), longest constructible palindrome.
- **[LongestSubstringWithoutRepeating.java](02-Strings/LongestSubstringWithoutRepeating.java)**: Brute force, Sliding window with Set, Optimal last-seen index map, ASCII array lookup, exact substring slice retrieval.
- **[LongestPalindromicSubstring.java](02-Strings/LongestPalindromicSubstring.java)**: Cubic brute force, 2D Dynamic Programming table, Expand Around Center ($O(N^2)$ time, $O(1)$ space), palindromic substrings count.
- **[README.md](02-Strings/README.md)**: String Pool internals, immutability benefits, sliding window & two-pointer pattern guides, interview tips.

---

## 🚀 How to Run

Compile and run any Java file from the command line:

```bash
# 01-Arrays
cd 01-Arrays
javac BasicOperations.java && java BasicOperations
javac SecondLargestElement.java && java SecondLargestElement
javac TwoSum.java && java TwoSum
javac MaximumSubarray.java && java MaximumSubarray

# 02-Strings
cd ../02-Strings
javac StringOperations.java && java StringOperations
javac ValidAnagram.java && java ValidAnagram
javac ValidPalindrome.java && java ValidPalindrome
javac LongestSubstringWithoutRepeating.java && java LongestSubstringWithoutRepeating
javac LongestPalindromicSubstring.java && java LongestPalindromicSubstring
```

---

## 📌 Curriculum Roadmap

- [x] **01-Arrays**
- [x] **02-Strings**
- [ ] **03-Recursion & Backtracking**
- [ ] **04-Searching & Sorting**
- [ ] **05-Linked Lists**
- [ ] **06-Stacks & Queues**
- [ ] **07-Trees & Binary Search Trees**
- [ ] **08-Heaps & Priority Queues**
- [ ] **09-Graphs**
- [ ] **10-Dynamic Programming**

---

## 💻 Requirements
- Java Development Kit (JDK) 8 or higher
- Git

*Maintained by [Shreepurnaa V](https://github.com/shree0731)*
