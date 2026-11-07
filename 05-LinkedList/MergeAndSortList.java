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

    /**
     * Merge k Sorted Lists (LeetCode #23).
     * Uses a Min-Heap of size K.
     * Time Complexity: O(N log K) where N = total nodes, K = number of lists
     * Space Complexity: O(K) for PriorityQueue
     */
    public static Node mergeKLists(Node[] lists) {
        if (lists == null || lists.length == 0) return null;

        PriorityQueue<Node> pq = new PriorityQueue<>(lists.length, Comparator.comparingInt(a -> a.val));

        for (Node listHead : lists) {
            if (listHead != null) pq.add(listHead);
        }

        Node dummy = new Node(0);
        Node curr = dummy;

        while (!pq.isEmpty()) {
            Node minNode = pq.poll();
            curr.next = minNode;
            curr = curr.next;

            if (minNode.next != null) {
                pq.add(minNode.next);
            }
        }
        return dummy.next;
    }

    /**
     * Sort List (LeetCode #148: MergeSort on Linked List).
     * Time Complexity: O(N log N)
     * Space Complexity: O(log N) stack
     */
    public static Node sortList(Node head) {
        if (head == null || head.next == null) return head;

        // Split into two halves
        Node prev = null, slow = head, fast = head;
        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        prev.next = null; // Sever link

        Node l1 = sortList(head);
        Node l2 = sortList(slow);
        return mergeTwoLists(l1, l2);
    }

    /**
     * Intersection of Two Linked Lists (LeetCode #160).
     * Two pointers switch to opposite heads upon reaching null.
     * Both pointers traverse exactly (lengthA + lengthB) steps before meeting.
     * Time Complexity: O(N + M)
     * Space Complexity: O(1)
     */
    public static Node getIntersectionNode(Node headA, Node headB) {
        if (headA == null || headB == null) return null;
        Node pA = headA;
        Node pB = headB;

        while (pA != pB) {
            pA = (pA == null) ? headB : pA.next;
            pB = (pB == null) ? headA : pB.next;
        }
        return pA; // Either intersection node or null
    }

    public static Node fromArray(int[] arr) {
        if (arr == null || arr.length == 0) return null;
        Node head = new Node(arr[0]);
        Node curr = head;
        for (int i = 1; i < arr.length; i++) {
            curr.next = new Node(arr[i]);
            curr = curr.next;
        }
        return head;
    }

    public static List<Integer> toList(Node head) {
        List<Integer> list = new ArrayList<>();
        Node curr = head;
        while (curr != null) {
            list.add(curr.val);
            curr = curr.next;
        }
        return list;
    }

    public static void main(String[] args) {
        System.out.println("=== Testing Merge Two Sorted Lists ===");
        Node l1 = fromArray(new int[]{1, 2, 4});
        Node l2 = fromArray(new int[]{1, 3, 4});
        System.out.println("Merged: " + toList(mergeTwoLists(l1, l2)));

        System.out.println("\n=== Testing Merge K Sorted Lists ===");
        Node[] kLists = {
            fromArray(new int[]{1, 4, 5}),
            fromArray(new int[]{1, 3, 4}),
            fromArray(new int[]{2, 6})
        };
        System.out.println("Merged K Lists: " + toList(mergeKLists(kLists)));

        System.out.println("\n=== Testing Sort List (MergeSort) ===");
        Node unsorted = fromArray(new int[]{4, 2, 1, 3, 0});
        System.out.println("Unsorted: " + toList(unsorted));
        System.out.println("Sorted  : " + toList(sortList(unsorted)));
    }
}
