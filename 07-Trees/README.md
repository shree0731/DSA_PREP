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

---

## 🛠️ Reconstruction & Serialization
- **Preorder + Inorder** ([LeetCode 105](https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/)): `preorder[0]` identifies root; finding root in `inorder` partitions left vs right subtree sizes in $O(1)$ using a `HashMap`.
- **BFS Serialization** ([LeetCode 297](https://leetcode.com/problems/serialize-and-deserialize-binary-tree/)): Storing `#` for `null` children guarantees lossless structural fidelity in a single linear pass.

---

## 🔍 BST Deletion & Validation Invariants

### Node Deletion Cases (`deleteNode`)
1. **Leaf Node**: Remove reference directly.
2. **One Child**: Bypass node and attach its single child.
3. **Two Children**: Locate **Inorder Successor** (minimum node in right subtree), copy its value, and recursively delete the successor from the right subtree.

---

## 📋 Comprehensive Problem Summary Matrix

| Problem | LeetCode | Core Pattern | Time Complexity | Space Complexity |
|:---|:---:|:---|:---:|:---:|
| **Morris Inorder Traversal** | [#94](https://leetcode.com/problems/binary-tree-inorder-traversal/) | Threaded Tree Predecessor Links | $O(N)$ | $O(1)$ |
| **Level / Zigzag Traversal** | [#102](https://leetcode.com/problems/binary-tree-level-order-traversal/), [#103](https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/) | BFS Queue | $O(N)$ | $O(W)$ |
| **Balanced Binary Tree** | [#110](https://leetcode.com/problems/balanced-binary-tree/) | Bottom-up Height Pruning | $O(N)$ | $O(H)$ |
| **Diameter of Binary Tree** | [#543](https://leetcode.com/problems/diameter-of-binary-tree/) | DFS Global Height Sum | $O(N)$ | $O(H)$ |
| **Binary Tree Max Path Sum** | [#124](https://leetcode.com/problems/binary-tree-maximum-path-sum/) | Bottom-up Negative Clamping | $O(N)$ | $O(H)$ |
| **Right Side View** | [#199](https://leetcode.com/problems/binary-tree-right-side-view/) | Reverse Preorder DFS | $O(N)$ | $O(H)$ |
| **Vertical Order Traversal** | [#987](https://leetcode.com/problems/vertical-order-traversal-of-a-binary-tree/) | HD & Row Coordinate `TreeMap` | $O(N \log N)$ | $O(N)$ |
| **Lowest Common Ancestor** | [#236](https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/) | Postorder Existence Propagation | $O(N)$ | $O(H)$ |
| **Construct from Pre & Inorder** | [#105](https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/) | Inorder Index `HashMap` Partitioning | $O(N)$ | $O(N)$ |
| **Serialize & Deserialize** | [#297](https://leetcode.com/problems/serialize-and-deserialize-binary-tree/) | BFS with `#` Null Markers | $O(N)$ | $O(N)$ |
| **Validate BST** | [#98](https://leetcode.com/problems/validate-binary-search-tree/) | Range Propagation `(min, max)` | $O(N)$ | $O(H)$ |
| **Delete Node in BST** | [#450](https://leetcode.com/problems/delete-node-in-a-bst/) | Inorder Successor Replacement | $O(H)$ | $O(H)$ |
| **Kth Smallest in BST** | [#230](https://leetcode.com/problems/kth-smallest-element-in-a-bst/) | Iterative Inorder Early Stop | $O(H + k)$ | $O(H)$ |
| **LCA in BST** | [#235](https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/) | BST Split Point Descent | $O(H)$ | $O(1)$ |
| **Recover BST** | [#99](https://leetcode.com/problems/recover-binary-search-tree/) | Inorder Inversion Swap | $O(N)$ | $O(H)$ |
| **Flatten Tree to List** | [#114](https://leetcode.com/problems/flatten-binary-tree-to-linked-list/) | Morris-Style In-Place Rewiring | $O(N)$ | $O(1)$ |
| **Count Complete Nodes** | [#222](https://leetcode.com/problems/count-complete-tree-nodes/) | Full Subtree Bitwise Height Check | $O(\log^2 N)$ | $O(\log N)$ |
| **All Nodes Distance K** | [#863](https://leetcode.com/problems/all-nodes-distance-k-in-binary-tree/) | Parent Pointer Graph + BFS | $O(N)$ | $O(N)$ |
| **BST Iterator** | [#173](https://leetcode.com/problems/binary-search-tree-iterator/) | Controlled Lazy Left-Spine Stack | $O(1)$ amortized | $O(H)$ |
