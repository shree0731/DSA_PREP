# 05 - Linked Lists

Welcome to the **Linked Lists** module of the DSA Preparation repository.

## Overview
A **Linked List** is a linear data structure where elements (nodes) are stored non-contiguously in heap memory. Each node contains a data field and one or more references (pointers) to neighboring nodes.

## Topics Covered
1. **SinglyLinkedList.java**: Node structure, head/tail/indexed insertions, deletions, in-place reversal (iterative & recursive), and middle node search.
2. **DoublyLinkedList.java**: Bidirectional node pointers, O(1) node deletion, forward/backward traversals, and reversal.
3. **LinkedListCycle.java**: Floyd's Tortoise & Hare cycle detection, cycle entry point derivation, loop length, and cycle removal.
4. **MergeAndSortList.java**: Merging 2 sorted lists, Merge K sorted lists (Min-Heap), Merge Sort on Linked List (O(N log N)), and intersection of two lists.
5. **PalindromeAndReorderList.java**: Palindrome check in O(1) space with list restoration, Reorder List, Remove Nth node from end, Add Two Numbers, and Odd-Even grouping.

---

## 🧠 Memory Architecture: Arrays vs Linked Lists

| Property | Dynamic Array (`ArrayList`) | Singly Linked List | Doubly Linked List |
| :--- | :--- | :--- | :--- |
| **Memory Allocation** | Contiguous chunk | Dispersed heap nodes | Dispersed heap nodes |
| **Cache Locality** | Excellent (CPU prefetching) | Poor (pointer chasing) | Poor (pointer chasing) |
| **Memory Overhead** | Unused reserved capacity | 1 reference per node (8 bytes on 64-bit JVM) | 2 references per node (16 bytes) |
| **Access by Index** | $O(1)$ direct address | $O(N)$ traversal | $O(N)$ traversal |
| **Insert/Delete at Head** | $O(N)$ (requires shifting) | $O(1)$ | $O(1)$ |
| **Insert/Delete at Tail** | $O(1)$ amortized | $O(1)$ with tail pointer | $O(1)$ |

---

## 🛡️ The Dummy / Sentinel Node Technique
A **Dummy Node** (or Sentinel Node) is a dummy head placed before the true head of the list:
```java
Node dummy = new Node(0);
dummy.next = head;
```
### Why use a Dummy Node?
1. **Eliminates Null Head Edge Cases**: Inserting or deleting the head node is identical to modifying internal nodes.
2. **Simplifies Loop Invariants**: The head can be reassigned seamlessly (`return dummy.next`).
3. **Avoids Special Case Conditions**: Zero extra `if (head == null)` or `if (curr == head)` checks needed.

---

## ⚡ Big-O Complexity Cheat Sheet

| Operation | Singly Linked List | Singly (with Tail) | Doubly Linked List |
| :--- | :--- | :--- | :--- |
| **Prepend (Insert Head)** | $O(1)$ | $O(1)$ | $O(1)$ |
| **Append (Insert Tail)** | $O(N)$ | $O(1)$ | $O(1)$ |
| **Insert at Index $K$** | $O(K)$ | $O(K)$ | $O(\min(K, N - K))$ |
| **Delete Head** | $O(1)$ | $O(1)$ | $O(1)$ |
| **Delete Tail** | $O(N)$ (needs second-to-last node) | $O(N)$ | $O(1)$ (using `tail.prev`) |
| **Search by Value** | $O(N)$ | $O(N)$ | $O(N)$ |
| **Reverse List** | $O(N)$ time, $O(1)$ space | $O(N)$ | $O(N)$ |

---

## 🔁 Singly vs Doubly Linked Lists Tradeoffs

- **Memory Overhead**: Doubly linked list nodes store both `prev` and `next`, increasing per-node reference overhead by 100%.
- **O(1) Deletion**: In a singly linked list, deleting an arbitrary node requires finding its predecessor ($O(N)$). In a doubly linked list, given the node reference, deletion is strictly $O(1)$.
- **Bidirectional Iteration**: Essential for LRU Caches, deque implementations, and text editors.

---

## ⭕ Cycle Detection & Floyd's Algorithm (LeetCode #141 & #142)

### The Mathematics:
- Distance from Head to Cycle Start: $L$
- Distance from Cycle Start to Meeting Point: $x$
- Cycle Circumference: $C$
$$2(L + x) = L + x + kC \implies L = kC - x$$
Advancing a pointer from `head` and one from the `meetingPoint` step-by-step causes them to collide precisely at the cycle start after traversing distance $L$.

---

## 🔀 MergeSort on Linked Lists

Unlike arrays where MergeSort requires $O(N)$ auxiliary storage, on Linked Lists MergeSort achieves:
- **$O(1)$ Auxiliary Space**: Merging requires only relinking existing node pointers (`next`).
- **Ideal for Non-Contiguous Memory**: Dispersed node references avoid cache penalties during pointer reassignments.

---

## 🎭 The Halving & In-Place Reversal Pattern

A powerful pattern shared by **Palindrome Linked List** and **Reorder List**:
1. **Find Middle**: Tortoise & Hare `slow = head, fast = head; while (fast.next != null && fast.next.next != null)`
2. **Reverse 2nd Half**: `Node second = reverse(slow.next); slow.next = null;`
3. **Traverse/Interweave**: Advance through both halves concurrently in $O(N)$ time with $O(1)$ extra memory.

---

## 📋 Module Summary & Problem Mapping

| Class | LeetCode # | Primary Technique | Time Complexity | Space Complexity |
| :--- | :--- | :--- | :--- | :--- |
| **SinglyLinkedList.java** | #206, #876 | 3-Pointer Reversal, Fast & Slow Pointers | $O(N)$ | $O(1)$ |
| **DoublyLinkedList.java** | N/A | Bidirectional Relinking, $O(1)$ Node Deletion | $O(1)$ to $O(N)$ | $O(1)$ |
| **LinkedListCycle.java** | #141, #142 | Floyd's Cycle Detection & Mathematical Entry | $O(N)$ | $O(1)$ |
| **MergeAndSortList.java** | #21, #23, #148, #160 | Dummy Head, Min-Heap, MergeSort | $O(N \log K)$ | $O(1)$ to $O(K)$ |
| **PalindromeAndReorderList.java** | #2, #19, #143, #234, #328 | In-Place Half-Reversal, Sentinel Dummy | $O(N)$ | $O(1)$ |

---

## 🎯 Top Linked List Interview Tips
1. **Always use a Dummy / Sentinel Node** whenever head might be deleted, swapped, or merged.
2. **Never lose the reference to `curr.next`** before modifying pointer connections.
3. **Watch out for memory leaks / cycles**: When breaking lists into halves, immediately sever the connection: `slow.next = null`.
