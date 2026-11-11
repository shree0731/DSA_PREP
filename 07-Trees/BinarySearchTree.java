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
}
