import java.util.*;

/**
 * Merges K sorted lists, arrays, and streams using a Min-Heap of size K.
 * Covers Merge K Sorted Lists, Merge K Arrays, Smallest Range, and K Pairs Smallest Sum.
 *
 * @author Shreepurnaa V
 */
public class MergeKSortedStreams {

    public static class ListNode {
        public int val;
        public ListNode next;
        public ListNode(int val) { this.val = val; }
        public ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}
