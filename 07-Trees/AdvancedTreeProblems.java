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

    /**
     * Flatten Binary Tree to Linked List in-place in O(N) time and O(1) space (LeetCode 114).
     * Rewires rightmost node of left subtree to point to current right subtree (Morris-style linking).
     */
    public static void flatten(TreeNode root) {
        TreeNode curr = root;
        while (curr != null) {
            if (curr.left != null) {
                TreeNode prev = curr.left;
                while (prev.right != null) {
                    prev = prev.right;
                }
                prev.right = curr.right;
                curr.right = curr.left;
                curr.left = null;
            }
            curr = curr.right;
        }
    }

    /**
     * Count Complete Tree Nodes in O((log N)^2) time (LeetCode 222).
     * If left height == right height, left subtree is a full binary tree of size (2^h - 1).
     */
    public static int countNodes(TreeNode root) {
        if (root == null) return 0;
        int lh = getLeftHeight(root);
        int rh = getRightHeight(root);
        if (lh == rh) return (1 << lh) - 1;
        return 1 + countNodes(root.left) + countNodes(root.right);
    }

    private static int getLeftHeight(TreeNode node) {
        int h = 0;
        while (node != null) {
            h++;
            node = node.left;
        }
        return h;
    }

    private static int getRightHeight(TreeNode node) {
        int h = 0;
        while (node != null) {
            h++;
            node = node.right;
        }
        return h;
    }

    /**
     * All Nodes Distance K in Binary Tree (LeetCode 863).
     * Annotates parent links via BFS, then radiates outward K steps from target using visited set.
     */
    public static List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        Map<TreeNode, TreeNode> parentMap = new HashMap<>();
        buildParentMap(root, parentMap);

        Queue<TreeNode> queue = new LinkedList<>();
        Set<TreeNode> visited = new HashSet<>();
        queue.offer(target);
        visited.add(target);

        int dist = 0;
        while (!queue.isEmpty()) {
            if (dist == k) break;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode curr = queue.poll();
                if (curr.left != null && visited.add(curr.left)) queue.offer(curr.left);
                if (curr.right != null && visited.add(curr.right)) queue.offer(curr.right);
                TreeNode parent = parentMap.get(curr);
                if (parent != null && visited.add(parent)) queue.offer(parent);
            }
            dist++;
        }

        List<Integer> res = new ArrayList<>();
        while (!queue.isEmpty()) res.add(queue.poll().val);
        return res;
    }

    private static void buildParentMap(TreeNode root, Map<TreeNode, TreeNode> parentMap) {
        if (root == null) return;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode curr = queue.poll();
            if (curr.left != null) {
                parentMap.put(curr.left, curr);
                queue.offer(curr.left);
            }
            if (curr.right != null) {
                parentMap.put(curr.right, curr);
                queue.offer(curr.right);
            }
        }
    }

    /**
     * Computes minimum time in minutes to burn the entire binary tree starting from target node.
     */
    public static int minTimeToBurnTree(TreeNode root, TreeNode target) {
        if (root == null || target == null) return 0;
        Map<TreeNode, TreeNode> parentMap = new HashMap<>();
        buildParentMap(root, parentMap);

        Queue<TreeNode> queue = new LinkedList<>();
        Set<TreeNode> visited = new HashSet<>();
        queue.offer(target);
        visited.add(target);

        int minutes = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            boolean burnedAny = false;
            for (int i = 0; i < size; i++) {
                TreeNode curr = queue.poll();
                if (curr.left != null && visited.add(curr.left)) {
                    queue.offer(curr.left);
                    burnedAny = true;
                }
                if (curr.right != null && visited.add(curr.right)) {
                    queue.offer(curr.right);
                    burnedAny = true;
                }
                TreeNode parent = parentMap.get(curr);
                if (parent != null && visited.add(parent)) {
                    queue.offer(parent);
                    burnedAny = true;
                }
            }
            if (burnedAny) minutes++;
        }
        return minutes;
    }
}
