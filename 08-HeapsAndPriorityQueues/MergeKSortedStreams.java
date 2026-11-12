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

    /**
     * Smallest Range Covering Elements from K Lists (LeetCode 632).
     * Maintains a Min-Heap of size K alongside a running maximum.
     * Window width = (currentMax - minHeap.peek().val).
     * Time: O(N log K), Space: O(K).
     */
    public static int[] smallestRange(List<List<Integer>> nums) {
        PriorityQueue<ArrayEntry> minHeap = new PriorityQueue<>(
                nums.size(), (a, b) -> Integer.compare(a.val, b.val)
        );

        int currentMax = Integer.MIN_VALUE;
        for (int i = 0; i < nums.size(); i++) {
            int val = nums.get(i).get(0);
            minHeap.offer(new ArrayEntry(val, i, 0));
            currentMax = Math.max(currentMax, val);
        }

        int rangeStart = 0;
        int rangeEnd = Integer.MAX_VALUE;

        while (minHeap.size() == nums.size()) {
            ArrayEntry minEntry = minHeap.poll();
            if ((long) currentMax - minEntry.val < (long) rangeEnd - rangeStart) {
                rangeStart = minEntry.val;
                rangeEnd = currentMax;
            }

            if (minEntry.col + 1 < nums.get(minEntry.row).size()) {
                int nextVal = nums.get(minEntry.row).get(minEntry.col + 1);
                minHeap.offer(new ArrayEntry(nextVal, minEntry.row, minEntry.col + 1));
                currentMax = Math.max(currentMax, nextVal);
            }
        }
        return new int[]{rangeStart, rangeEnd};
    }

    /**
     * Find K Pairs with Smallest Sums (LeetCode 373).
     * Time: O(K log K), Space: O(K).
     */
    public static List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums1.length == 0 || nums2.length == 0 || k == 0) return result;

        // Min-Heap stores index pairs [i, j]
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(
                (a, b) -> (nums1[a[0]] + nums2[a[1]]) - (nums1[b[0]] + nums2[b[1]])
        );

        for (int i = 0; i < Math.min(nums1.length, k); i++) {
            minHeap.offer(new int[]{i, 0});
        }

        while (k-- > 0 && !minHeap.isEmpty()) {
            int[] pair = minHeap.poll();
            int i = pair[0];
            int j = pair[1];
            result.add(Arrays.asList(nums1[i], nums2[j]));

            if (j + 1 < nums2.length) {
                minHeap.offer(new int[]{i, j + 1});
            }
        }
        return result;
    }
}
