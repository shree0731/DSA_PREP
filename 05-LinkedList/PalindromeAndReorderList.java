import java.util.*;

/**
 * PalindromeAndReorderList implements advanced list algorithms:
 * Palindrome Linked List, Reorder List, Remove Nth Node From End, and Add Two Numbers.
 */
public class PalindromeAndReorderList {

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

    public PalindromeAndReorderList() {
        // Utility class
    }

    /**
     * Palindrome Linked List (LeetCode #234).
     * Algorithm:
     * 1. Find middle of list using fast/slow pointers.
     * 2. Reverse second half of list.
     * 3. Compare values of first and second half.
     * Time Complexity: O(N)
     * Space Complexity: O(1)
     */
    public static boolean isPalindrome(Node head) {
        if (head == null || head.next == null) return true;

        Node slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // Reverse second half
        Node secondHalfHead = reverse(slow.next);
        Node p1 = head;
        Node p2 = secondHalfHead;
        boolean palindrome = true;

        while (p2 != null) {
            if (p1.val != p2.val) {
                palindrome = false;
                break;
            }
            p1 = p1.next;
            p2 = p2.next;
        }

        // Restore original list
        slow.next = reverse(secondHalfHead);
        return palindrome;
    }

    private static Node reverse(Node head) {
        Node prev = null, curr = head;
        while (curr != null) {
            Node next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }

    /**
     * Validates that list structure remains intact after palindrome check.
     */
    public static boolean verifyListRestoration(Node head, int[] original) {
        Node curr = head;
        for (int val : original) {
            if (curr == null || curr.val != val) return false;
            curr = curr.next;
        }
        return curr == null;
    }

    /**
     * Reorder List (LeetCode #143: L0 -> Ln -> L1 -> Ln-1 ...).
     * Time Complexity: O(N)
     * Space Complexity: O(1)
     */
    public static void reorderList(Node head) {
        if (head == null || head.next == null) return;

        // Step 1: Find middle
        Node slow = head, fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // Step 2: Reverse second half
        Node second = reverse(slow.next);
        slow.next = null; // Sever link

        // Step 3: Interweave nodes
        Node first = head;
        while (second != null) {
            Node tmp1 = first.next;
            Node tmp2 = second.next;

            first.next = second;
            second.next = tmp1;

            first = tmp1;
            second = tmp2;
        }
    }

    /**
     * Remove Nth Node From End of List (LeetCode #19: Single-pass).
     * Uses two pointers with gap of n between fast and slow.
     * Time Complexity: O(N)
     * Space Complexity: O(1)
     */
    public static Node removeNthFromEnd(Node head, int n) {
        Node dummy = new Node(0, head);
        Node fast = dummy;
        Node slow = dummy;

        for (int i = 0; i <= n; i++) {
            fast = fast.next;
        }

        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }

        slow.next = slow.next.next;
        return dummy.next;
    }
}
