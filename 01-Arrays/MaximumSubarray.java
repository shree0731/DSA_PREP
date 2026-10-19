import java.util.Arrays;

/**
 * Problem: Maximum Subarray (LeetCode #53)
 */
public class MaximumSubarray {

    /**
     * Approach 1: Cubic Brute Force
     * Time Complexity: O(N^3)
     * Space Complexity: O(1)
     * 
     * Evaluates every conceivable subarray [i .. j] by computing the sum
     * with an inner loop.
     * 
     * @param nums array of integers
     * @return maximum subarray sum
     */
    public static int maxSubArrayBruteForceCubic(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int n = nums.length;
        int maxSum = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int currentSum = 0;
                for (int k = i; k <= j; k++) {
                    currentSum += nums[k];
                }
                maxSum = Math.max(maxSum, currentSum);
            }
        }
        return maxSum;
    }
}
