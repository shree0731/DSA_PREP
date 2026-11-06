import java.util.*;

/**
 * LinkedListCycle implements Floyd's Cycle Detection (Tortoise and Hare),
 * cycle entry point detection, loop circumference, and cycle removal.
 */
public class LinkedListCycle {

    public static class Node {
        public int val;
        public Node next;

        public Node(int val) {
            this.val = val;
            this.next = null;
        }

        @Override
        public String toString() {
            return String.valueOf(val);
        }
    }

    public LinkedListCycle() {
        // Utility class
    }

    /**
     * Helper to construct a linked list with a cycle at 0-based pos.
     */
    public static Node createCyclicList(int[] values, int pos) {
        if (values == null || values.length == 0) return null;
        Node head = new Node(values[0]);
        Node curr = head;
        Node cycleEntry = (pos == 0) ? head : null;

        for (int i = 1; i < values.length; i++) {
            curr.next = new Node(values[i]);
            curr = curr.next;
            if (i == pos) cycleEntry = curr;
        }

        if (pos >= 0) {
            curr.next = cycleEntry; // Close the loop
        }
        return head;
    }
}
