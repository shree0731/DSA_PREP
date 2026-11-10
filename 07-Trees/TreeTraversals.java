import java.util.*;

/**
 * Demonstrates fundamental Binary Tree traversal strategies in Java.
 * Covers Recursive DFS, Iterative Stack DFS, BFS Level/Zigzag, and Morris O(1) Traversal.
 *
 * @author Shreepurnaa V
 */
public class TreeTraversals {

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
     * Builds sample binary tree:
     *        1
     *       / \
     *      2   3
     *     / \   \
     *    4   5   6
     */
    public static TreeNode buildSampleTree() {
        TreeNode n4 = new TreeNode(4);
        TreeNode n5 = new TreeNode(5);
        TreeNode n6 = new TreeNode(6);
        TreeNode n2 = new TreeNode(2, n4, n5);
        TreeNode n3 = new TreeNode(3, null, n6);
        return new TreeNode(1, n2, n3);
    }

    /**
     * Recursive Preorder Traversal (Root -> Left -> Right).
     * Time: O(N), Space: O(H) recursion stack.
     */
    public static List<Integer> preorderRecursive(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        preorderHelper(root, res);
        return res;
    }

    private static void preorderHelper(TreeNode node, List<Integer> res) {
        if (node == null) return;
        res.add(node.val);
        preorderHelper(node.left, res);
        preorderHelper(node.right, res);
    }

    /**
     * Recursive Inorder Traversal (Left -> Root -> Right).
     * Time: O(N), Space: O(H).
     */
    public static List<Integer> inorderRecursive(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        inorderHelper(root, res);
        return res;
    }

    private static void inorderHelper(TreeNode node, List<Integer> res) {
        if (node == null) return;
        inorderHelper(node.left, res);
        res.add(node.val);
        inorderHelper(node.right, res);
    }

    /**
     * Recursive Postorder Traversal (Left -> Right -> Root).
     * Time: O(N), Space: O(H).
     */
    public static List<Integer> postorderRecursive(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        postorderHelper(root, res);
        return res;
    }

    private static void postorderHelper(TreeNode node, List<Integer> res) {
        if (node == null) return;
        postorderHelper(node.left, res);
        postorderHelper(node.right, res);
        res.add(node.val);
    }
}
