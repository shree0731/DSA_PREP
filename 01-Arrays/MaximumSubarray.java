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

    /**
     * Result container representing maximum subarray metadata.
     */
    public static class SubarrayResult {
        public final int maxSum;
        public final int startIndex;
        public final int endIndex;
        public final int[] subarray;

        public SubarrayResult(int maxSum, int startIndex, int endIndex, int[] original) {
            this.maxSum = maxSum;
            this.startIndex = startIndex;
            this.endIndex = endIndex;
            this.subarray = Arrays.copyOfRange(original, startIndex, endIndex + 1);
        }

        @Override
        public String toString() {
            return "SubarrayResult{" +
                    "maxSum=" + maxSum +
                    ", range=[" + startIndex + ".." + endIndex + "]" +
                    ", subarray=" + Arrays.toString(subarray) +
                    '}';
        }
    }

    /**
     * Kadane's algorithm enhanced to track start and end indices of the optimal subarray.
     * Time Complexity: O(N)
     * Space Complexity: O(1) auxiliary
     * 
     * @param nums input array
     * @return SubarrayResult containing sum, indices, and elements
     */
    public static SubarrayResult maxSubArrayWithRange(int[] nums) {
        if (nums == null || nums.length == 0) {
            throw new IllegalArgumentException("Array cannot be null or empty");
        }

        int maxSum = nums[0];
        int currentSum = nums[0];
        int start = 0;
        int end = 0;
        int tempStart = 0;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > currentSum + nums[i]) {
                currentSum = nums[i];
                tempStart = i;
            } else {
                currentSum += nums[i];
            }

            if (currentSum > maxSum) {
                maxSum = currentSum;
                start = tempStart;
                end = i;
            }
        }

        return new SubarrayResult(maxSum, start, end, nums);
    }
}
