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

    /**
     * Merge K Sorted Lists (LeetCode 23).
     * Maintains a Min-Heap of size K holding the head of each remaining list.
     * Time: O(N log K) where N is total number of nodes, Space: O(K).
     */
    public static ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;

        PriorityQueue<ListNode> minHeap = new PriorityQueue<>(
                lists.length, (a, b) -> Integer.compare(a.val, b.val)
        );

        for (ListNode node : lists) {
            if (node != null) minHeap.offer(node);
        }

        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;

        while (!minHeap.isEmpty()) {
            ListNode smallest = minHeap.poll();
            curr.next = smallest;
            curr = curr.next;
            if (smallest.next != null) {
                minHeap.offer(smallest.next);
            }
        }
        return dummy.next;
    }
}
