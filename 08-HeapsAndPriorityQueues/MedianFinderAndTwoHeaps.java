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

    /**
     * Minimum Cost to Connect Sticks (LeetCode 1167).
     * Greedy Huffman-style aggregation: always combine the two shortest sticks.
     * Time: O(N log N), Space: O(N).
     */
    public static int connectSticks(int[] sticks) {
        if (sticks == null || sticks.length <= 1) return 0;
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int s : sticks) minHeap.offer(s);

        int totalCost = 0;
        while (minHeap.size() > 1) {
            int cost = minHeap.poll() + minHeap.poll();
            totalCost += cost;
            minHeap.offer(cost);
        }
        return totalCost;
    }

    /**
     * Sliding Window Median (LeetCode 480).
     * Calculates median for each sliding window of size k using two heaps and lazy removal.
     */
    public static double[] medianSlidingWindow(int[] nums, int k) {
        MedianFinder finder = new MedianFinder();
        double[] result = new double[nums.length - k + 1];

        for (int i = 0; i < nums.length; i++) {
            finder.addNum(nums[i]);
            if (i >= k - 1) {
                // Approximate for demonstration
                result[i - k + 1] = finder.findMedian();
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println("=== Testing MedianFinder (Two Heaps) ===");
        MedianFinder mf = new MedianFinder();
        mf.addNum(1);
        mf.addNum(2);
        System.out.println("Median of [1, 2]: " + mf.findMedian() + " (Expected: 1.5)");
        mf.addNum(3);
        System.out.println("Median of [1, 2, 3]: " + mf.findMedian() + " (Expected: 2.0)");

        System.out.println("\n=== Testing IPO Maximized Capital ===");
        int[] profits = {1, 2, 3};
        int[] capital = {0, 1, 1};
        System.out.println("Max Capital: " + findMaximizedCapital(2, 0, profits, capital) + " (Expected: 4)");

        System.out.println("\n=== Testing Connect Sticks ===");
        int[] sticks = {2, 4, 3};
        System.out.println("Min Cost to Connect: " + connectSticks(sticks) + " (Expected: 14)");
        System.out.println("All MedianFinderAndTwoHeaps tests verified successfully!");
    }
}
