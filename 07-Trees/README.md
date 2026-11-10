# 07-Trees

Welcome to the **Trees & Binary Search Trees (BST)** module of the Java DSA Preparation repository.

## Overview
Trees are hierarchical, non-linear data structures consisting of nodes connected by directed edges without cycles. Mastering tree traversals, recursive subtree decomposition, BST invariants, and parent-pointer BFS unlocks most advanced graph and system problems.

---

## Planned Directory Structure
```
07-Trees/
├── TreeTraversals.java           # Recursive/Iterative DFS, BFS Level & Zigzag, Morris O(1) Traversal
├── TreePropertiesAndMetrics.java # Height, Balance Check, Diameter, Symmetry, Invert, Max Path Sum
├── TreeViewsAndPaths.java        # Right/Left/Top/Bottom/Vertical Views, LCA, Paths, Rebuild, Codec
├── BinarySearchTree.java         # CRUD, Validate BST, Kth Smallest, BST LCA, Successor, Recover BST
├── AdvancedTreeProblems.java     # Boundary, Flatten List, Complete Node Count, Distance K, Burn, BST Iterator
└── README.md                     # Architecture notes, traversal cheat sheets, and complexity matrix
```

---

## 🌳 Tree Anatomy & Types

```
          [1]          <-- Root (Depth 0, Height 2)
         /   \
       [2]   [3]       <-- Internal Nodes (Depth 1)
      /  \     \
    [4]  [5]   [6]     <-- Leaf Nodes (Depth 2, no children)
```

### Key Categories
- **Full Binary Tree**: Every node has either 0 or 2 children.
- **Complete Binary Tree**: All levels completely filled except possibly the last, which is filled left-to-right.
- **Perfect Binary Tree**: All internal nodes have 2 children and all leaves are at the same depth ($2^{h+1} - 1$ nodes).
- **Balanced Binary Tree**: For every node, $|height(left) - height(right)| \le 1$, guaranteeing $O(\log N)$ height.
- **Binary Search Tree (BST)**: For any node $x$, all left subtree keys $< x.\text{val} <$ all right subtree keys.

---

## 📊 Traversal Comparison

| Traversal | Order | Auxiliary Space | Use Case |
|:---|:---|:---:|:---|
| **Preorder** | Root $\to$ Left $\to$ Right | $O(H)$ | Tree serialization, copying tree |
| **Inorder** | Left $\to$ Root $\to$ Right | $O(H)$ | Retrieving BST keys in sorted order |
| **Postorder** | Left $\to$ Right $\to$ Root | $O(H)$ | Bottom-up metrics (height, delete, LCA) |
| **Level Order (BFS)** | Depth by depth | $O(W)$ max width | Shortest paths, level views |
| **Morris Inorder** | Left $\to$ Root $\to$ Right | **$O(1)$** | Space-constrained tree traversal via threads |

---

## 🧵 Morris Traversal Mechanics ($O(1)$ Space)

Standard recursive or stack-based traversals require $O(H)$ auxiliary memory to backtrack from a left subtree to its parent.
**Morris Traversal** eliminates the stack by modifying null right pointers of the **inorder predecessor**:
1. If `curr.left == null`: record `curr.val` and move `curr = curr.right`.
2. Else, locate `pred` (rightmost node in `curr.left`):
   - If `pred.right == null`: establish thread `pred.right = curr`, then advance `curr = curr.left`.
   - If `pred.right == curr`: thread already visited; revert `pred.right = null` to restore the tree, record `curr.val`, and move `curr = curr.right`.
Each edge is traversed at most 3 times $\implies O(N)$ amortized time.

---

## 📏 Bottom-Up Pruning Pattern

Avoid $O(N^2)$ top-down recomputations of height by passing height upward alongside validity/diameter states:
- **Balanced Binary Tree** returns `-1` immediately upon encountering an imbalanced child.
- **Max Path Sum** returns `Math.max(0, branchGain)` upward while updating a global variable with `node.val + left + right` for paths arching through the root node.

---

## 🧭 Coordinate & View Mapping

Treating the root as `(row = 0, hd = 0)`:
- Moving **Left**: `row + 1, hd - 1`
- Moving **Right**: `row + 1, hd + 1`

Using BFS with a `TreeMap<Integer, Integer>`:
- **Top View**: `putIfAbsent(hd, val)` keeps the shallowest node at each horizontal distance.
- **Bottom View**: `put(hd, val)` updates to the deepest node (and rightmost on ties).
