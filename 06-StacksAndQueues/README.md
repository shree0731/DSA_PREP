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
