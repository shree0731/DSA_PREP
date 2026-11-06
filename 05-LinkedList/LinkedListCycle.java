/**
 * Mathematical Derivation of Floyd's Cycle Entry Finding:
 * 
 * Let:
 *   L = distance from head to cycle entry
 *   C = circumference of the cycle
 *   x = distance from cycle entry to collision point
 * 
 * Distance traveled by slow = L + x
 * Distance traveled by fast = L + x + nC
 * 
 * Since fast travels twice as fast:
 *   2 * (L + x) = L + x + nC
 *   L + x = nC
 *   L = nC - x = (n - 1)C + (C - x)
 * 
 * (C - x) is the remaining distance from collision point to cycle entry!
 * Therefore, advancing one pointer from head and one from collision point
 * at equal speed guarantees meeting at the cycle entry node after L steps.
 */
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

    /**
     * Linked List Cycle Detection (LeetCode #141).
     * Fast pointer advances 2 steps while slow advances 1 step.
     * If they meet, a cycle exists.
     * Time Complexity: O(N)
     * Space Complexity: O(1)
     */
    public static boolean hasCycle(Node head) {
        if (head == null || head.next == null) return false;
        Node slow = head;
        Node fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) return true;
        }
        return false;
    }

    /**
     * Linked List Cycle II (LeetCode #142: Find starting node of cycle).
     * After collision, reset one pointer to head. Move both 1 step at a time;
     * their meeting point is the exact cycle entry node.
     * Time Complexity: O(N)
     * Space Complexity: O(1)
     */
    public static Node detectCycle(Node head) {
        if (head == null || head.next == null) return null;
        Node slow = head;
        Node fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                // Phase 2: Find entry
                Node entry = head;
                while (entry != slow) {
                    entry = entry.next;
                    slow = slow.next;
                }
                return entry;
            }
        }
        return null;
    }

    /**
     * Calculates the number of nodes in the cycle.
     * Time Complexity: O(N)
     * Space Complexity: O(1)
     */
    public static int countLoopLength(Node head) {
        Node slow = head, fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                int length = 1;
                Node temp = slow.next;
                while (temp != slow) {
                    length++;
                    temp = temp.next;
                }
                return length;
            }
        }
        return 0; // No cycle
    }

    /**
     * Removes the cycle from the linked list, restoring it to a linear structure.
     * Time Complexity: O(N)
     * Space Complexity: O(1)
     */
    public static boolean removeCycle(Node head) {
        Node entry = detectCycle(head);
        if (entry == null) return false;

        // Traverse to the tail of the cycle (node whose next points to entry)
        Node curr = entry;
        while (curr.next != entry) {
            curr = curr.next;
        }
        curr.next = null; // Break loop
        return true;
    }

    /**
     * Utility to print up to maxNodes elements to avoid infinite loops during debugging.
     */
    public static void printSafe(Node head, int maxNodes) {
        StringBuilder sb = new StringBuilder();
        Node curr = head;
        int count = 0;
        while (curr != null && count < maxNodes) {
            sb.append(curr.val).append(" -> ");
            curr = curr.next;
            count++;
        }
        sb.append(curr == null ? "null" : "(...) [cycle truncated]");
        System.out.println(sb.toString());
    }
}
