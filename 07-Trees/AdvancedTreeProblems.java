import java.util.*;

/**
 * Advanced Binary Tree & BST algorithms.
 * Covers Boundary Traversal, Flatten to LinkedList, Complete Tree Node Count, Distance K, Burn Tree, BST Iterator.
 *
 * @author Shreepurnaa V
 */
public class AdvancedTreeProblems {

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
     * Anti-Clockwise Boundary Traversal of Binary Tree.
     * Combines root, left boundary (excluding leaves), bottom leaves, and reversed right boundary.
     */
    public static List<Integer> boundaryOfBinaryTree(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        if (!isLeaf(root)) res.add(root.val);

        addLeftBoundary(root.left, res);
        addLeaves(root, res);
        addRightBoundary(root.right, res);
        return res;
    }

    private static boolean isLeaf(TreeNode node) {
        return node.left == null && node.right == null;
    }

    private static void addLeftBoundary(TreeNode curr, List<Integer> res) {
        while (curr != null) {
            if (!isLeaf(curr)) res.add(curr.val);
            curr = (curr.left != null) ? curr.left : curr.right;
        }
    }

    private static void addLeaves(TreeNode node, List<Integer> res) {
        if (isLeaf(node)) {
            res.add(node.val);
            return;
        }
        if (node.left != null) addLeaves(node.left, res);
        if (node.right != null) addLeaves(node.right, res);
    }

    private static void addRightBoundary(TreeNode curr, List<Integer> res) {
        List<Integer> temp = new ArrayList<>();
        while (curr != null) {
            if (!isLeaf(curr)) temp.add(curr.val);
            curr = (curr.right != null) ? curr.right : curr.left;
        }
        for (int i = temp.size() - 1; i >= 0; i--) {
            res.add(temp.get(i));
        }
    }
}
