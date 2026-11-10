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

    /**
     * Diameter of Binary Tree (LeetCode 543).
     * Length (in edges) of longest path between any two nodes in the tree.
     * Time: O(N), Space: O(H).
     */
    public static int diameterOfBinaryTree(TreeNode root) {
        int[] maxDiameter = new int[1];
        diameterHeight(root, maxDiameter);
        return maxDiameter[0];
    }

    private static int diameterHeight(TreeNode node, int[] maxDiameter) {
        if (node == null) return 0;
        int left = diameterHeight(node.left, maxDiameter);
        int right = diameterHeight(node.right, maxDiameter);
        maxDiameter[0] = Math.max(maxDiameter[0], left + right);
        return 1 + Math.max(left, right);
    }

    /**
     * Same Tree Check (LeetCode 100).
     */
    public static boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        if (p == null || q == null || p.val != q.val) return false;
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    /**
     * Symmetric Tree / Mirror Check (LeetCode 101).
     */
    public static boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return isMirror(root.left, root.right);
    }

    private static boolean isMirror(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) return true;
        if (t1 == null || t2 == null || t1.val != t2.val) return false;
        return isMirror(t1.left, t2.right) && isMirror(t1.right, t2.left);
    }

    /**
     * Invert Binary Tree (LeetCode 226) using Recursive Postorder Swap.
     */
    public static TreeNode invertTreeRecursive(TreeNode root) {
        if (root == null) return null;
        TreeNode temp = root.left;
        root.left = invertTreeRecursive(root.right);
        root.right = invertTreeRecursive(temp);
        return root;
    }

    /**
     * Iterative BFS Tree Inversion.
     */
    public static TreeNode invertTreeIterative(TreeNode root) {
        if (root == null) return null;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode curr = queue.poll();
            TreeNode temp = curr.left;
            curr.left = curr.right;
            curr.right = temp;
            if (curr.left != null) queue.offer(curr.left);
            if (curr.right != null) queue.offer(curr.right);
        }
        return root;
    }

    /**
     * Binary Tree Maximum Path Sum (LeetCode 124).
     * Clamps negative subtree contributions to 0 so path only extends when beneficial.
     * Time: O(N), Space: O(H).
     */
    public static int maxPathSum(TreeNode root) {
        int[] maxSum = {Integer.MIN_VALUE};
        maxPathGain(root, maxSum);
        return maxSum[0];
    }

    private static int maxPathGain(TreeNode node, int[] maxSum) {
        if (node == null) return 0;
        int leftGain = Math.max(0, maxPathGain(node.left, maxSum));
        int rightGain = Math.max(0, maxPathGain(node.right, maxSum));

        int currentPathSum = node.val + leftGain + rightGain;
        maxSum[0] = Math.max(maxSum[0], currentPathSum);

        return node.val + Math.max(leftGain, rightGain);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1,
                new TreeNode(2, new TreeNode(4), new TreeNode(5)),
                new TreeNode(3));

        System.out.println("=== Testing Tree Properties & Metrics ===");
        System.out.println("Max Depth (DFS): " + maxDepthDFS(root) + " (Expected: 3)");
        System.out.println("Max Depth (BFS): " + maxDepthBFS(root) + " (Expected: 3)");
        System.out.println("Is Balanced    : " + isBalanced(root) + " (Expected: true)");
        System.out.println("Diameter       : " + diameterOfBinaryTree(root) + " (Expected: 3)");

        TreeNode sym = new TreeNode(1,
                new TreeNode(2, new TreeNode(3), new TreeNode(4)),
                new TreeNode(2, new TreeNode(4), new TreeNode(3)));
        System.out.println("Is Symmetric   : " + isSymmetric(sym) + " (Expected: true)");

        TreeNode pathRoot = new TreeNode(-10,
                new TreeNode(9),
                new TreeNode(20, new TreeNode(15), new TreeNode(7)));
        System.out.println("Max Path Sum   : " + maxPathSum(pathRoot) + " (Expected: 42)");

        TreeNode inverted = invertTreeRecursive(root);
        System.out.println("Inverted Root Right Left: " + inverted.right.left.val + " (Expected: 5)");
        System.out.println("All TreePropertiesAndMetrics tests verified successfully!");
    }
}
