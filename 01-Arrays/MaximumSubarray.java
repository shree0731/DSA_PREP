import java.util.Arrays;

/**
 * Problem: Maximum Subarray (LeetCode #53)
 */
public class MaximumSubarray {

    public static int maxSubArrayBruteForceCubic(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int n = nums.length;
        int maxSum = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int currentSum = 0;
                for (int k = i; k <= j; k++) currentSum += nums[k];
                maxSum = Math.max(maxSum, currentSum);
            }
        }
        return maxSum;
    }

    public static int maxSubArrayPrefixSumQuadratic(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int n = nums.length;
        int maxSum = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            int currentSum = 0;
            for (int j = i; j < n; j++) {
                currentSum += nums[j];
                maxSum = Math.max(maxSum, currentSum);
            }
        }
        return maxSum;
    }

    /**
     * Approach 3: Kadane's Algorithm
     * Time Complexity: O(N)
     * Space Complexity: O(1)
     * 
     * Core Recurrence:
     * currentSum = Math.max(nums[i], currentSum + nums[i])
     * maxSum = Math.max(maxSum, currentSum)
     * 
     * @param nums array of integers
     * @return maximum subarray sum
     */
    public static int maxSubArrayKadane(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        int currentSum = nums[0];
        int maxSum = nums[0];

        for (int i = 1; i < nums.length; i++) {
            currentSum = Math.max(nums[i], currentSum + nums[i]);
            maxSum = Math.max(maxSum, currentSum);
        }
        return maxSum;
    }
}
