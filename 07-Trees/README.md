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
