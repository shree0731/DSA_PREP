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

    /**
     * Merge Two Sorted Lists (LeetCode #21: Iterative).
     * Time Complexity: O(N + M)
     * Space Complexity: O(1)
     */
    public static Node mergeTwoLists(Node list1, Node list2) {
        Node dummy = new Node(0);
        Node curr = dummy;

        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                curr.next = list1;
                list1 = list1.next;
            } else {
                curr.next = list2;
                list2 = list2.next;
            }
            curr = curr.next;
        }

        curr.next = (list1 != null) ? list1 : list2;
        return dummy.next;
    }

    /**
     * Merge Two Sorted Lists (Recursive).
     * Time Complexity: O(N + M)
     * Space Complexity: O(N + M) call stack
     */
    public static Node mergeTwoListsRecursive(Node l1, Node l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;

        if (l1.val <= l2.val) {
            l1.next = mergeTwoListsRecursive(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoListsRecursive(l1, l2.next);
            return l2;
        }
    }
}
