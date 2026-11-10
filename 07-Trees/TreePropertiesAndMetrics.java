import java.util.*;

/**
 * Computes structural metrics and equivalence properties of Binary Trees.
 * Covers Height, Balance Check, Diameter, Same/Symmetric Tree, Invert, and Max Path Sum.
 *
 * @author Shreepurnaa V
 */
public class TreePropertiesAndMetrics {

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
     * Maximum Depth of Binary Tree (LeetCode 104).
     * Approach 1: Recursive DFS. Time: O(N), Space: O(H).
     */
    public static int maxDepthDFS(TreeNode root) {
        if (root == null) return 0;
        return 1 + Math.max(maxDepthDFS(root.left), maxDepthDFS(root.right));
    }

    /**
     * Approach 2: Iterative BFS Level Counting. Time: O(N), Space: O(W).
     */
    public static int maxDepthBFS(TreeNode root) {
        if (root == null) return 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int depth = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode curr = queue.poll();
                if (curr.left != null) queue.offer(curr.left);
                if (curr.right != null) queue.offer(curr.right);
            }
            depth++;
        }
        return depth;
    }

    /**
     * Balanced Binary Tree (LeetCode 110).
     * Optimal O(N) bottom-up DFS returning height if balanced, or -1 if imbalanced.
     */
    public static boolean isBalanced(TreeNode root) {
        return checkHeight(root) != -1;
    }

    private static int checkHeight(TreeNode node) {
        if (node == null) return 0;
        int leftH = checkHeight(node.left);
        if (leftH == -1) return -1;

        int rightH = checkHeight(node.right);
        if (rightH == -1) return -1;

        if (Math.abs(leftH - rightH) > 1) return -1;
        return 1 + Math.max(leftH, rightH);
    }
}
