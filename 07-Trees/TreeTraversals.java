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
}
