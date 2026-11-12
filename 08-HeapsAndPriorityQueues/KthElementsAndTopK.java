import java.util.*;

/**
 * Solves Top-K, K-th smallest/largest, and frequency ranking problems using Heaps.
 *
 * @author Shreepurnaa V
 */
public class KthElementsAndTopK {


    /**
     * Kth Largest Element in an Array (LeetCode 215).
     * Maintains a Min-Heap of size K. The root always holds the K-th largest element seen so far.
     * Time: O(N log K), Space: O(K).
     */
    public static int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(k);
        for (int num : nums) {
            minHeap.offer(num);
            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }
        return minHeap.peek();
    }

    /**
     * Kth Smallest Element in an Array.
     * Maintains a Max-Heap of size K.
     * Time: O(N log K), Space: O(K).
     */
    public static int findKthSmallest(int[] nums, int k) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(k, Collections.reverseOrder());
        for (int num : nums) {
            maxHeap.offer(num);
            if (maxHeap.size() > k) {
                maxHeap.poll();
            }
        }
        return maxHeap.peek();
    }
}
