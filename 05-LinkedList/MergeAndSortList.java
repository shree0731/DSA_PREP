import java.util.*;

/**
 * MergeAndSortList provides algorithms for merging sorted lists,
 * Merge K sorted lists with Min-Heap, MergeSort on Linked Lists, and intersection detection.
 */
public class MergeAndSortList {

    public static class Node {
        public int val;
        public Node next;

        public Node(int val) {
            this.val = val;
            this.next = null;
        }

        public Node(int val, Node next) {
            this.val = val;
            this.next = next;
        }

        @Override
        public String toString() {
            return String.valueOf(val);
        }
    }

    public MergeAndSortList() {
        // Utility class
    }
}
