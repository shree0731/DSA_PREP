import java.util.*;

/**
 * Demonstrates the Dual-Heap Pattern for continuous median tracking, IPO capital maximization,
 * and Huffman-style stick connection.
 *
 * @author Shreepurnaa V
 */
public class MedianFinderAndTwoHeaps {


    /**
     * Find Median from Data Stream (LeetCode 295).
     * Maintains two heaps:
     * - maxHeap (left half): stores smaller half of elements.
     * - minHeap (right half): stores larger half of elements.
     * Invariants:
     * 1. maxHeap.size() == minHeap.size() OR maxHeap.size() == minHeap.size() + 1
     * 2. maxHeap.peek() <= minHeap.peek()
     */
    public static class MedianFinder {
        private final PriorityQueue<Integer> maxHeap; // stores smaller half
        private final PriorityQueue<Integer> minHeap; // stores larger half

        public MedianFinder() {
            this.maxHeap = new PriorityQueue<>(Collections.reverseOrder());
            this.minHeap = new PriorityQueue<>();
        }

        public void addNum(int num) {
            if (maxHeap.isEmpty() || num <= maxHeap.peek()) {
                maxHeap.offer(num);
            } else {
                minHeap.offer(num);
            }

            // Balance sizes
            if (maxHeap.size() > minHeap.size() + 1) {
                minHeap.offer(maxHeap.poll());
            } else if (minHeap.size() > maxHeap.size()) {
                maxHeap.offer(minHeap.poll());
            }
        }

        public double findMedian() {
            if (maxHeap.size() > minHeap.size()) {
                return maxHeap.peek();
            } else {
                return (maxHeap.peek() + (double) minHeap.peek()) / 2.0;
            }
        }
    }

    /**
     * IPO - Maximum Capital after completing at most k projects (LeetCode 502).
     * Dual heaps:
     * - minCapitalHeap: sorted by capital requirement.
     * - maxProfitHeap: available affordable projects sorted by profit.
     */
    public static int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        int n = profits.length;
        int[][] projects = new int[n][2];
        for (int i = 0; i < n; i++) {
            projects[i][0] = capital[i];
            projects[i][1] = profits[i];
        }

        Arrays.sort(projects, Comparator.comparingInt(a -> a[0]));
        PriorityQueue<Integer> maxProfit = new PriorityQueue<>(Collections.reverseOrder());
        int ptr = 0;

        for (int i = 0; i < k; i++) {
            while (ptr < n && projects[ptr][0] <= w) {
                maxProfit.offer(projects[ptr][1]);
                ptr++;
            }
            if (maxProfit.isEmpty()) break;
            w += maxProfit.poll();
        }
        return w;
    }
}
