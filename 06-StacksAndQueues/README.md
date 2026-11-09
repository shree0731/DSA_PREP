# 06-StacksAndQueues

Welcome to the **Stacks and Queues** module of the Java DSA Preparation repository.

## Overview
Stacks and Queues are fundamental linear data structures governed by strict element access policies:
- **Stack**: **LIFO** (Last In, First Out) — elements are added and removed from the same end (*top*).
- **Queue**: **FIFO** (First In, First Out) — elements are added at the *rear* (enqueue) and removed from the *front* (dequeue).
- **Deque**: Double-Ended Queue allowing insertion and deletion from both ends in $O(1)$ time.

---

## Planned Directory Structure
```
06-StacksAndQueues/
├── StackImplementations.java           # Custom ArrayStack, LinkedStack, MinStack, TwoStacks
├── QueueImplementations.java           # CircularArrayQueue, LinkedQueue, Stack/Queue Adapters, Deque
├── MonotonicStack.java                 # Next/Previous Greater/Smaller, Daily Temperatures, Histogram
├── ExpressionEvaluation.java           # Balanced Parentheses, Longest Valid, Infix-Postfix, Calculator
├── SlidingWindowAndQueueProblems.java  # Sliding Window Max (Deque), Stream Uniques, Rotting Oranges BFS
└── README.md                           # Documentation, complexity reference, and interview templates
```

---

## 🏛️ Memory Architecture & Principles

### Stack (LIFO)
A stack restricts manipulation to the top element:
```
       Push ↓   ↑ Pop
     +---------------+
top  |    Node 3     |
     +---------------+
     |    Node 2     |
     +---------------+
     |    Node 1     | (bottom)
     +---------------+
```

### Queue (FIFO)
A queue maintains separate pointers for insertion and deletion:
```
  Enqueue (rear)                       Dequeue (front)
        ↓                                     ↓
   +---------+     +---------+     +---------+
   | Node 3  | --> | Node 2  | --> | Node 1  | --> Out
   +---------+     +---------+     +---------+
```

### Deque (Double-Ended Queue)
Permits $O(1)$ bidirectional operations: `pushFirst`, `pushLast`, `pollFirst`, `pollLast`.

---

## 📊 Big-O Complexity Comparison

| Operation | Array Stack | Linked Stack | Circular Array Queue | Linked Queue | Monotonic Stack |
|:---|:---:|:---:|:---:|:---:|:---:|
| **Push / Enqueue** | $O(1)$ amortized | $O(1)$ | $O(1)$ amortized | $O(1)$ | $O(1)$ amortized |
| **Pop / Dequeue** | $O(1)$ | $O(1)$ | $O(1)$ | $O(1)$ | $O(1)$ amortized |
| **Peek / Top / Front**| $O(1)$ | $O(1)$ | $O(1)$ | $O(1)$ | $O(1)$ |
| **Search by Value** | $O(N)$ | $O(N)$ | $O(N)$ | $O(N)$ | $O(N)$ |
| **Auxiliary Memory** | $O(N)$ buffer | $O(N)$ node refs | $O(N)$ buffer | $O(N)$ node refs | $O(N)$ stack |

---

## 🛠️ Stack Implementations Guide

### MinStack Space Trade-offs
1. **Dual Stack**: Stores each minimum separately in an auxiliary stack.
   - Time: $O(1)$ push/pop/getMin
   - Auxiliary Space: $O(N)$
2. **Mathematical Encoding ($2x - \text{min}$)**:
   - When pushing a smaller element $x < \text{min}$, store $2x - \text{min} < x$.
   - When popping, if $\text{top} < \text{min}$, the original value was $\text{min}$, and previous min was $2\text{min} - \text{top}$.
   - Time: $O(1)$ push/pop/getMin
   - Auxiliary Space: $O(1)$

---

## 🔄 Queue & Stack Adapters: Amortized Analysis

### Queue using Two Stacks (`QueueUsingStacks`)
- `push(x)`: $O(1)$ directly pushed to `inStack`.
- `pop()`: If `outStack` is empty, move all $N$ elements from `inStack` to `outStack` ($O(N)$ once).
  - Subsequent pops are $O(1)$.
  - Each item is pushed into `inStack` once, popped from `inStack` once, pushed into `outStack` once, and popped from `outStack` once.
  - Total operations per element = 4. **Amortized Time Complexity = $O(1)$**.

---

## 📈 Monotonic Stack Decision Guide

A **Monotonic Stack** maintains its elements in strictly sorted order.
When an incoming element violates the monotonic order, elements are popped until the invariant is restored.

### Pattern Matrix
| Query | Stack Invariant | Traversal Direction |
|:---|:---|:---:|
| **Next Greater Element** | Monotonic Decreasing | Left to Right |
| **Next Smaller Element** | Monotonic Increasing | Left to Right |
| **Previous Greater Element** | Monotonic Decreasing | Right to Left (or Left to Right with monotonic push) |
| **Previous Smaller Element** | Monotonic Increasing | Left to Right |

### Histogram Largest Rectangle Mechanics
For each bar $i$, we find:
1. `leftBoundary[i]`: index of previous smaller bar.
2. `rightBoundary[i]`: index of next smaller bar.
3. Width of rectangle bounded by height $h$: $\text{width} = \text{right} - \text{left} - 1$.
Using a single pass with a sentinel height $0$ at index $n$ allows computation in exact $O(N)$ time and $O(N)$ space.

---

## 🧮 Expression Parsing Mechanics

### Dijkstra's Shunting-yard Algorithm
Operates via an operator stack and output buffer:
1. Operands are output immediately.
2. Operators pop higher or equal precedence operators from stack to output, then push themselves.
3. Left parenthesis `(` is pushed onto stack.
4. Right parenthesis `)` pops operators to output until matching `(` is encountered.

---

## 🪟 Sliding Window Deque Invariant

In **Sliding Window Maximum** ([LeetCode 239](https://leetcode.com/problems/sliding-window-maximum/)), we maintain:
1. **Monotonicity**: Deque stores indices with values in strictly decreasing order:
   $$nums[d_0] > nums[d_1] > \dots > nums[d_m]$$
2. **Freshness**: All stored indices satisfy $d_i \ge i - k + 1$.
3. **Efficiency**: Each index enters and leaves the deque at most once $\implies O(N)$ total time.
