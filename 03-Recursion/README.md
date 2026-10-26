# 03 - Recursion & Backtracking

Welcome to the **Recursion & Backtracking** module of the DSA Preparation repository.

## Overview
**Recursion** is a programming technique where a method calls itself directly or indirectly to solve a problem by breaking it down into smaller subproblems of the same type.
**Backtracking** is an algorithmic technique for solving problems recursively by trying to build a solution incrementally, one piece at a time, removing those solutions that fail to satisfy the constraints at any point in time.

## Topics Covered
1. **BasicRecursion.java**: Call stack mechanics, factorial (tail recursion), Fibonacci, fast exponentiation, Tower of Hanoi.
2. **Subsets.java**: Power set generation, Subsets I & II (handling duplicates), bit manipulation.
3. **Permutations.java**: Permutations I & II (swapping vs boolean visited array), next permutation.
4. **CombinationSum.java**: Combinations I, II & III with branch pruning and duplicate elimination.
5. **NQueens.java**: N-Queens solver, O(1) diagonal lookup tracking, bitmasking, chessboard visualization.

---

## 🥞 The Call Stack & Recursion Tree

### How the Call Stack Works
1. Every recursive method invocation allocates a new **stack frame** in the JVM thread call stack.
2. The stack frame stores:
   - Local variables
   - Parameters passed
   - Return address (program counter to resume execution)
3. When the **base case** is reached, stack frames begin returning and popping off the stack in LIFO (Last-In, First-Out) order.
4. Missing a base case causes indefinite allocations leading to `java.lang.StackOverflowError`.

```text
Recursion Tree for fib(4):
               fib(4)
             /        \
         fib(3)        fib(2)
        /      \       /     \
     fib(2)   fib(1) fib(1) fib(0)
    /      \
 fib(1)   fib(0)
```
