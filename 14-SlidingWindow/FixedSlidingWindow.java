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
}
