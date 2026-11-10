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
}
