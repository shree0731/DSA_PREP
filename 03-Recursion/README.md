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

---

## 🌿 The Canonical Backtracking Template

All backtracking algorithms follow this universal 3-step blueprint:

```java
void backtrack(State state, Choices choices, List<Result> results) {
    if (isSolution(state)) {
        results.add(new Result(state));
        return;
    }

    for (Choice choice : choices) {
        if (!isValid(choice, state)) continue; // Pruning / Constraint Check

        makeChoice(choice, state);             // 1. Choose
        backtrack(state, choices, results);    // 2. Explore
        undoChoice(choice, state);             // 3. Unchoose (Backtrack)
    }
}
```

### State Space Pruning
- **Sorting before recursing**: Sorting input allows breaking early when choices exceed a target sum.
- **Skipping adjacent duplicates**: `if (i > start && nums[i] == nums[i - 1]) continue;`
- **Lookup caching**: Using boolean arrays or bitmasks instead of full board validation.

---

## ⚖️ Recursion vs Iteration Tradeoffs

| Criterion | Recursion | Iteration |
| :--- | :--- | :--- |
| **Code Readability** | Intuitive for trees, graphs, divide & conquer | Often requires manual stack or complex loops |
| **Memory Consumption** | $O(N)$ auxiliary stack frames | $O(1)$ auxiliary memory (usually) |
| **Execution Overhead** | Frame allocation, function calling cost | Fast machine jumps / register manipulation |
| **Risk** | `StackOverflowError` if depth exceeds JVM stack | Infinite loop if condition never breaks |

> 💡 **When to use recursion**: Trees, graphs (DFS), Divide & Conquer, Backtracking, and Combinatorial problems.
