import java.util.*;

/**
 * Demonstrates Queue and Deque applications in Sliding Window and Breadth-First Search (BFS).
 * Covers Sliding Window Maximum, First Unique Character in Stream, Rotting Oranges, and Simplify Path.
 *
 * @author Shreepurnaa V
 */
public class SlidingWindowAndQueueProblems {


    /**
     * Sliding Window Maximum (LeetCode 239).
     * Maintains a monotonic decreasing Deque storing indices of nums.
     * Front of deque always holds maximum element index for current window.
     *
     * Time Complexity: O(N)
     * Space Complexity: O(K)
     */
    public static int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || k <= 0) return new int[0];
        int n = nums.length;
        int[] result = new int[n - k + 1];
        Deque<Integer> deque = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            while (!deque.isEmpty() && deque.peekFirst() < i - k + 1) {
                deque.pollFirst();
            }
            while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
                deque.pollLast();
            }
            deque.offerLast(i);
            if (i >= k - 1) {
                result[i - k + 1] = nums[deque.peekFirst()];
            }
        }
        return result;
    }

    /**
     * Finds the first non-repeating character in a stream of characters.
     * Uses a FIFO Queue to preserve arrival order and frequency array for counts.
     *
     * Time Complexity: O(N)
     * Space Complexity: O(1)
     */
    public static String firstNonRepeatingInStream(String stream) {
        StringBuilder result = new StringBuilder();
        int[] count = new int[26];
        Queue<Character> queue = new LinkedList<>();

        for (char ch : stream.toCharArray()) {
            count[ch - 'a']++;
            queue.offer(ch);

            while (!queue.isEmpty() && count[queue.peek() - 'a'] > 1) {
                queue.poll();
            }

            result.append(queue.isEmpty() ? '#' : queue.peek());
        }
        return result.toString();
    }
}
