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

    /**
     * Top View of Binary Tree using BFS and Horizontal Distance (HD) TreeMap.
     * Records only the first node encountered at each HD coordinate.
     */
    public static List<Integer> topView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        Map<Integer, Integer> hdMap = new TreeMap<>();
        Queue<NodeHD> queue = new LinkedList<>();
        queue.offer(new NodeHD(root, 0));

        while (!queue.isEmpty()) {
            NodeHD curr = queue.poll();
            hdMap.putIfAbsent(curr.hd, curr.node.val);
            if (curr.node.left != null) queue.offer(new NodeHD(curr.node.left, curr.hd - 1));
            if (curr.node.right != null) queue.offer(new NodeHD(curr.node.right, curr.hd + 1));
        }

        res.addAll(hdMap.values());
        return res;
    }

    /**
     * Bottom View of Binary Tree using BFS and HD TreeMap.
     * Overwrites previous values at the same HD so the deepest node remains.
     */
    public static List<Integer> bottomView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        Map<Integer, Integer> hdMap = new TreeMap<>();
        Queue<NodeHD> queue = new LinkedList<>();
        queue.offer(new NodeHD(root, 0));

        while (!queue.isEmpty()) {
            NodeHD curr = queue.poll();
            hdMap.put(curr.hd, curr.node.val);
            if (curr.node.left != null) queue.offer(new NodeHD(curr.node.left, curr.hd - 1));
            if (curr.node.right != null) queue.offer(new NodeHD(curr.node.right, curr.hd + 1));
        }

        res.addAll(hdMap.values());
        return res;
    }

    /**
     * Vertical Order Traversal of a Binary Tree (LeetCode 987).
     * Coordinates grouped by column (HD), sorted by row then node value.
     */
    public static List<List<Integer>> verticalTraversal(TreeNode root) {
        TreeMap<Integer, TreeMap<Integer, PriorityQueue<Integer>>> map = new TreeMap<>();
        dfsVertical(root, 0, 0, map);

        List<List<Integer>> result = new ArrayList<>();
        for (TreeMap<Integer, PriorityQueue<Integer>> rows : map.values()) {
            List<Integer> colList = new ArrayList<>();
            for (PriorityQueue<Integer> pq : rows.values()) {
                while (!pq.isEmpty()) {
                    colList.add(pq.poll());
                }
            }
            result.add(colList);
        }
        return result;
    }

    private static void dfsVertical(TreeNode node, int row, int col,
                                    TreeMap<Integer, TreeMap<Integer, PriorityQueue<Integer>>> map) {
        if (node == null) return;
        map.putIfAbsent(col, new TreeMap<>());
        map.get(col).putIfAbsent(row, new PriorityQueue<>());
        map.get(col).get(row).offer(node.val);

        dfsVertical(node.left, row + 1, col - 1, map);
        dfsVertical(node.right, row + 1, col + 1, map);
    }

    /**
     * Lowest Common Ancestor of a Binary Tree (LeetCode 236).
     * Time: O(N), Space: O(H).
     */
    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        if (left != null && right != null) return root;
        return left != null ? left : right;
    }

    /**
     * Binary Tree Paths (LeetCode 257).
     * Collects all root-to-leaf path strings.
     */
    public static List<String> binaryTreePaths(TreeNode root) {
        List<String> paths = new ArrayList<>();
        if (root != null) dfsPaths(root, new StringBuilder(), paths);
        return paths;
    }

    private static void dfsPaths(TreeNode node, StringBuilder sb, List<String> paths) {
        int len = sb.length();
        sb.append(node.val);
        if (node.left == null && node.right == null) {
            paths.add(sb.toString());
        } else {
            sb.append("->");
            if (node.left != null) dfsPaths(node.left, sb, paths);
            if (node.right != null) dfsPaths(node.right, sb, paths);
        }
        sb.setLength(len);
    }

    /**
     * Path Sum II (LeetCode 113).
     * Collects all root-to-leaf paths whose sum equals targetSum.
     */
    public static List<List<Integer>> pathSumII(TreeNode root, int targetSum) {
        List<List<Integer>> result = new ArrayList<>();
        backtrackPathSum(root, targetSum, new ArrayList<>(), result);
        return result;
    }

    private static void backtrackPathSum(TreeNode node, int remaining, List<Integer> path, List<List<Integer>> res) {
        if (node == null) return;
        path.add(node.val);
        if (node.left == null && node.right == null && remaining == node.val) {
            res.add(new ArrayList<>(path));
        } else {
            backtrackPathSum(node.left, remaining - node.val, path, res);
            backtrackPathSum(node.right, remaining - node.val, path, res);
        }
        path.remove(path.size() - 1);
    }

    /**
     * Construct Binary Tree from Preorder and Inorder Traversal (LeetCode 105).
     * Uses HashMap for O(1) inorder root index lookup. Time: O(N), Space: O(N).
     */
    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        Map<Integer, Integer> inMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            inMap.put(inorder[i], i);
        }
        return buildHelper(preorder, 0, preorder.length - 1, 0, inorder.length - 1, inMap);
    }

    private static TreeNode buildHelper(int[] preorder, int preStart, int preEnd,
                                        int inStart, int inEnd, Map<Integer, Integer> inMap) {
        if (preStart > preEnd || inStart > inEnd) return null;
        TreeNode root = new TreeNode(preorder[preStart]);
        int inRoot = inMap.get(root.val);
        int numsLeft = inRoot - inStart;

        root.left = buildHelper(preorder, preStart + 1, preStart + numsLeft, inStart, inRoot - 1, inMap);
        root.right = buildHelper(preorder, preStart + numsLeft + 1, preEnd, inRoot + 1, inEnd, inMap);
        return root;
    }
}
