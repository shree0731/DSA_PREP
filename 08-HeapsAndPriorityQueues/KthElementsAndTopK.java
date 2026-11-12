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

    /**
     * Top K Frequent Elements using Frequency Min-Heap (LeetCode 347).
     * Time: O(N log K), Space: O(N).
     */
    public static int[] topKFrequentHeap(int[] nums, int k) {
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int n : nums) freqMap.put(n, freqMap.getOrDefault(n, 0) + 1);

        PriorityQueue<Map.Entry<Integer, Integer>> minHeap =
                new PriorityQueue<>(Comparator.comparingInt(Map.Entry::getValue));

        for (Map.Entry<Integer, Integer> entry : freqMap.entrySet()) {
            minHeap.offer(entry);
            if (minHeap.size() > k) minHeap.poll();
        }

        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = minHeap.poll().getKey();
        }
        return result;
    }

    /**
     * Top K Frequent Elements using Bucket Sort in strictly linear O(N) time (LeetCode 347).
     */
    public static int[] topKFrequentBucket(int[] nums, int k) {
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int n : nums) freqMap.put(n, freqMap.getOrDefault(n, 0) + 1);

        List<Integer>[] buckets = new List[nums.length + 1];
        for (int key : freqMap.keySet()) {
            int freq = freqMap.get(key);
            if (buckets[freq] == null) buckets[freq] = new ArrayList<>();
            buckets[freq].add(key);
        }

        int[] res = new int[k];
        int idx = 0;
        for (int f = buckets.length - 1; f >= 0 && idx < k; f--) {
            if (buckets[f] != null) {
                for (int val : buckets[f]) {
                    res[idx++] = val;
                    if (idx == k) break;
                }
            }
        }
        return res;
    }

    /**
     * K Closest Points to Origin (LeetCode 973).
     * Maintains a Max-Heap of size K ordered by Euclidean distance squared.
     * Time: O(N log K), Space: O(K).
     */
    public static int[][] kClosest(int[][] points, int k) {
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>(
                (a, b) -> Integer.compare((b[0]*b[0] + b[1]*b[1]), (a[0]*a[0] + a[1]*a[1]))
        );

        for (int[] p : points) {
            maxHeap.offer(p);
            if (maxHeap.size() > k) {
                maxHeap.poll();
            }
        }

        int[][] res = new int[k][2];
        for (int i = 0; i < k; i++) {
            res[i] = maxHeap.poll();
        }
        return res;
    }

    /**
     * Sort Characters By Frequency (LeetCode 451).
     * Time: O(N log K) where K is number of unique characters (<= 62).
     */
    public static String frequencySort(String s) {
        Map<Character, Integer> counts = new HashMap<>();
        for (char c : s.toCharArray()) counts.put(c, counts.getOrDefault(c, 0) + 1);

        PriorityQueue<Map.Entry<Character, Integer>> maxHeap =
                new PriorityQueue<>((a, b) -> Integer.compare(b.getValue(), a.getValue()));
        maxHeap.addAll(counts.entrySet());

        StringBuilder sb = new StringBuilder();
        while (!maxHeap.isEmpty()) {
            Map.Entry<Character, Integer> e = maxHeap.poll();
            for (int i = 0; i < e.getValue(); i++) sb.append(e.getKey());
        }
        return sb.toString();
    }
}
