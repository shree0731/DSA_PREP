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
                for (int k = i; k <= j; k++) {
                    currentSum += nums[k];
                }
                maxSum = Math.max(maxSum, currentSum);
            }
        }
        return maxSum;
    }

    /**
     * Approach 2: Quadratic Prefix Running Sum
     * Time Complexity: O(N^2)
     * Space Complexity: O(1)
     * 
     * Eliminates the innermost loop by keeping a running sum as j extends.
     * 
     * @param nums array of integers
     * @return maximum subarray sum
     */
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
}
