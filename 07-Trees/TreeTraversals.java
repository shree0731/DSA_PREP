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

    /**
     * Iterative Preorder Traversal using explicit Stack (LeetCode 144).
     * Push right child first so left child is popped first.
     */
    public static List<Integer> preorderIterative(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode curr = stack.pop();
            res.add(curr.val);
            if (curr.right != null) stack.push(curr.right);
            if (curr.left != null) stack.push(curr.left);
        }
        return res;
    }

    /**
     * Iterative Inorder Traversal using explicit Stack (LeetCode 94).
     */
    public static List<Integer> inorderIterative(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode curr = root;

        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            curr = stack.pop();
            res.add(curr.val);
            curr = curr.right;
        }
        return res;
    }

    /**
     * Iterative Postorder Traversal using Two Stacks (LeetCode 145).
     */
    public static List<Integer> postorderIterativeTwoStacks(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        Deque<TreeNode> s1 = new ArrayDeque<>();
        Deque<TreeNode> s2 = new ArrayDeque<>();
        s1.push(root);

        while (!s1.isEmpty()) {
            TreeNode curr = s1.pop();
            s2.push(curr);
            if (curr.left != null) s1.push(curr.left);
            if (curr.right != null) s1.push(curr.right);
        }
        while (!s2.isEmpty()) {
            res.add(s2.pop().val);
        }
        return res;
    }

    /**
     * Iterative Postorder Traversal using One Stack with last-visited pointer.
     */
    public static List<Integer> postorderIterativeOneStack(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode curr = root;
        TreeNode lastVisited = null;

        while (curr != null || !stack.isEmpty()) {
            if (curr != null) {
                stack.push(curr);
                curr = curr.left;
            } else {
                TreeNode peekNode = stack.peek();
                if (peekNode.right != null && lastVisited != peekNode.right) {
                    curr = peekNode.right;
                } else {
                    res.add(peekNode.val);
                    lastVisited = stack.pop();
                }
            }
        }
        return res;
    }

    /**
     * Level Order Traversal using BFS Queue (LeetCode 102).
     * Time: O(N), Space: O(W) where W is max tree width.
     */
    public static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            List<Integer> level = new ArrayList<>(levelSize);
            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                level.add(node.val);
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
            result.add(level);
        }
        return result;
    }

    /**
     * Binary Tree Zigzag Level Order Traversal (LeetCode 103).
     * Alternates left-to-right and right-to-left level insertion.
     */
    public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean leftToRight = true;

        while (!queue.isEmpty()) {
            int size = queue.size();
            LinkedList<Integer> level = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (leftToRight) {
                    level.addLast(node.val);
                } else {
                    level.addFirst(node.val);
                }
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
            result.add(level);
            leftToRight = !leftToRight;
        }
        return result;
    }

    /**
     * Morris Inorder Traversal in O(N) time and O(1) auxiliary space.
     * Establishes temporary threaded links from inorder predecessor back to current node,
     * then cleanly restores tree structure.
     */
    public static List<Integer> morrisInorder(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        TreeNode curr = root;

        while (curr != null) {
            if (curr.left == null) {
                res.add(curr.val);
                curr = curr.right;
            } else {
                TreeNode pred = curr.left;
                while (pred.right != null && pred.right != curr) {
                    pred = pred.right;
                }
                if (pred.right == null) {
                    pred.right = curr; // Create temporary thread
                    curr = curr.left;
                } else {
                    pred.right = null; // Remove temporary thread
                    res.add(curr.val);
                    curr = curr.right;
                }
            }
        }
        return res;
    }
}
