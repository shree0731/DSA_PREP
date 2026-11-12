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

    private static class ArrayEntry {
        int val;
        int row;
        int col;
        ArrayEntry(int val, int row, int col) {
            this.val = val;
            this.row = row;
            this.col = col;
        }
    }

    /**
     * Merges K sorted arrays into a single unified sorted List.
     * Time: O(N log K), Space: O(K).
     */
    public static List<Integer> mergeKSortedArrays(int[][] arrays) {
        List<Integer> result = new ArrayList<>();
        if (arrays == null || arrays.length == 0) return result;

        PriorityQueue<ArrayEntry> minHeap = new PriorityQueue<>(
                arrays.length, (a, b) -> Integer.compare(a.val, b.val)
        );

        for (int r = 0; r < arrays.length; r++) {
            if (arrays[r] != null && arrays[r].length > 0) {
                minHeap.offer(new ArrayEntry(arrays[r][0], r, 0));
            }
        }

        while (!minHeap.isEmpty()) {
            ArrayEntry curr = minHeap.poll();
            result.add(curr.val);
            if (curr.col + 1 < arrays[curr.row].length) {
                minHeap.offer(new ArrayEntry(arrays[curr.row][curr.col + 1], curr.row, curr.col + 1));
            }
        }
        return result;
    }
}
