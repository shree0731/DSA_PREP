import java.util.*;

/**
 * Fixed-size sliding window algorithms.
 *
 * @author Shreepurnaa V
 */
public class FixedSlidingWindow {

    /**
     * Maximum sum of any contiguous subarray of size K.
     * Time: O(N), Space: O(1).
     */
    public static int maxSumSubarray(int[] nums, int k) {
        int windowSum = 0;
        for (int i = 0; i < k; i++) windowSum += nums[i];

        int maxSum = windowSum;
        for (int i = k; i < nums.length; i++) {
            windowSum += nums[i] - nums[i - k];
            maxSum = Math.max(maxSum, windowSum);
        }
        return maxSum;
    }

    /**
     * Sliding Window Maximum (LeetCode 239).
     * Maintains a Monotonic Deque storing indices in decreasing order of their elements.
     * Time: O(N), Space: O(K).
     */
    public static int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || k <= 0) return new int[0];
        int n = nums.length;
        int[] result = new int[n - k + 1];
        Deque<Integer> deque = new ArrayDeque<>(); // Stores indices

        for (int i = 0; i < n; i++) {
            // Remove indices outside the current window
            while (!deque.isEmpty() && deque.peekFirst() < i - k + 1) {
                deque.pollFirst();
            }
            // Maintain monotonic decreasing order
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
     * Find All Anagrams in a String (LeetCode 438).
     * Time: O(N), Space: O(1) bounded by 26 letters.
     */
    public static List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();
        if (s.length() < p.length()) return result;

        int[] pCount = new int[26];
        int[] sCount = new int[26];
        for (char c : p.toCharArray()) pCount[c - 'a']++;

        int k = p.length();
        for (int i = 0; i < s.length(); i++) {
            sCount[s.charAt(i) - 'a']++;
            if (i >= k) {
                sCount[s.charAt(i - k) - 'a']--;
            }
            if (i >= k - 1 && Arrays.equals(pCount, sCount)) {
                result.add(i - k + 1);
            }
        }
        return result;
    }
}
