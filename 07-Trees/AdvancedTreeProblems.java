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
}
