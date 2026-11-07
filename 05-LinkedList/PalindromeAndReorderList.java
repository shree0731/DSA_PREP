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
}
