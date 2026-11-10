import java.util.*;

/**
 * Implements Binary Tree Views, Path Finding, LCA, Reconstruction, and Serialization.
 *
 * @author Shreepurnaa V
 */
public class TreeViewsAndPaths {

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

    private static class NodeHD {
        TreeNode node;
        int hd;
        NodeHD(TreeNode node, int hd) {
            this.node = node;
            this.hd = hd;
        }
    }

    /**
     * Binary Tree Right Side View using Reverse Preorder DFS (LeetCode 199).
     * Visits Root -> Right -> Left so first node seen at each depth is the rightmost node.
     */
    public static List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        rightViewDFS(root, 0, res);
        return res;
    }

    private static void rightViewDFS(TreeNode node, int level, List<Integer> res) {
        if (node == null) return;
        if (level == res.size()) res.add(node.val);
        rightViewDFS(node.right, level + 1, res);
        rightViewDFS(node.left, level + 1, res);
    }

    /**
     * Binary Tree Left Side View using Preorder DFS.
     */
    public static List<Integer> leftSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        leftViewDFS(root, 0, res);
        return res;
    }

    private static void leftViewDFS(TreeNode node, int level, List<Integer> res) {
        if (node == null) return;
        if (level == res.size()) res.add(node.val);
        leftViewDFS(node.left, level + 1, res);
        leftViewDFS(node.right, level + 1, res);
    }
}
