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
