import java.util.*;

/**
 * Implements Binary Search Tree (BST) algorithms and structural invariants in Java.
 * Covers CRUD operations, Validation, Kth Smallest, LCA, Successor, Constructors, and Recovery.
 *
 * @author Shreepurnaa V
 */
public class BinarySearchTree {

    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }

        public TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    /**
     * Searches BST for key in O(H) time and O(1) space (LeetCode 700).
     */
    public static TreeNode searchBST(TreeNode root, int val) {
        while (root != null && root.val != val) {
            root = (val < root.val) ? root.left : root.right;
        }
        return root;
    }

    /**
     * Inserts key into BST returning modified root (LeetCode 701).
     */
    public static TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) return new TreeNode(val);
        if (val < root.val) {
            root.left = insertIntoBST(root.left, val);
        } else if (val > root.val) {
            root.right = insertIntoBST(root.right, val);
        }
        return root;
    }

    /**
     * Delete Node in a BST (LeetCode 450).
     * Handles 0 children, 1 child, and 2 children (replaced by inorder successor).
     * Time: O(H), Space: O(H).
     */
    public static TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) return null;
        if (key < root.val) {
            root.left = deleteNode(root.left, key);
        } else if (key > root.val) {
            root.right = deleteNode(root.right, key);
        } else {
            if (root.left == null) return root.right;
            if (root.right == null) return root.left;

            TreeNode successor = findMin(root.right);
            root.val = successor.val;
            root.right = deleteNode(root.right, successor.val);
        }
        return root;
    }

    private static TreeNode findMin(TreeNode node) {
        while (node.left != null) node = node.left;
        return node;
    }

    /**
     * Validate Binary Search Tree (LeetCode 98).
     * Enforces strict range bounds (min, max) using Long boundaries to support Integer extremes.
     */
    public static boolean isValidBST(TreeNode root) {
        return validate(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private static boolean validate(TreeNode node, long minVal, long maxVal) {
        if (node == null) return true;
        if (node.val <= minVal || node.val >= maxVal) return false;
        return validate(node.left, minVal, node.val) && validate(node.right, node.val, maxVal);
    }

    /**
     * Kth Smallest Element in a BST (LeetCode 230).
     * Iterative inorder traversal stopping as soon as k-th node is visited.
     * Time: O(H + k), Space: O(H).
     */
    public static int kthSmallest(TreeNode root, int k) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode curr = root;

        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            curr = stack.pop();
            if (--k == 0) return curr.val;
            curr = curr.right;
        }
        throw new IllegalArgumentException("k exceeds BST size.");
    }

    /**
     * Lowest Common Ancestor of a Binary Search Tree (LeetCode 235).
     * Exploits BST ordering to descend directly toward the split point in O(H) time and O(1) space.
     */
    public static TreeNode lowestCommonAncestorBST(TreeNode root, TreeNode p, TreeNode q) {
        while (root != null) {
            if (p.val < root.val && q.val < root.val) {
                root = root.left;
            } else if (p.val > root.val && q.val > root.val) {
                root = root.right;
            } else {
                return root;
            }
        }
        return null;
    }

    /**
     * Finds Inorder Successor of target value in BST in O(H) time and O(1) space.
     */
    public static TreeNode inorderSuccessor(TreeNode root, TreeNode target) {
        TreeNode successor = null;
        while (root != null) {
            if (target.val < root.val) {
                successor = root;
                root = root.left;
            } else {
                root = root.right;
            }
        }
        return successor;
    }

    /**
     * Finds Inorder Predecessor of target value in BST in O(H) time and O(1) space.
     */
    public static TreeNode inorderPredecessor(TreeNode root, TreeNode target) {
        TreeNode predecessor = null;
        while (root != null) {
            if (target.val > root.val) {
                predecessor = root;
                root = root.right;
            } else {
                root = root.left;
            }
        }
        return predecessor;
    }
}
