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

    /**
     * Validates that Kadane's correctly handles all negative values.
     * e.g., [-5, -2, -8] -> returns -2 (not 0).
     */
    public static boolean hasOnlyNegativeNumbers(int[] nums) {
        for (int num : nums) {
            if (num >= 0) return false;
        }
        return true;
    }

    /**
     * Approach 4: Divide and Conquer
     * Time Complexity: O(N log N)
     * Space Complexity: O(log N) recursive call stack
     * 
     * Splits array into halves and computes maximum of:
     * 1. Max subarray entirely in left half
     * 2. Max subarray entirely in right half
     * 3. Max subarray crossing the midpoint
     * 
     * @param nums array of integers
     * @return maximum subarray sum
     */
    public static int maxSubArrayDivideAndConquer(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        return dacHelper(nums, 0, nums.length - 1);
    }

    private static int dacHelper(int[] nums, int left, int right) {
        if (left == right) return nums[left];
        int mid = left + (right - left) / 2;

        int leftMax = dacHelper(nums, left, mid);
        int rightMax = dacHelper(nums, mid + 1, right);
        int crossMax = maxCrossingSum(nums, left, mid, right);

        return Math.max(Math.max(leftMax, rightMax), crossMax);
    }

    private static int maxCrossingSum(int[] nums, int left, int mid, int right) {
        int leftSum = Integer.MIN_VALUE;
        int sum = 0;
        for (int i = mid; i >= left; i--) {
            sum += nums[i];
            if (sum > leftSum) leftSum = sum;
        }

        int rightSum = Integer.MIN_VALUE;
        sum = 0;
        for (int i = mid + 1; i <= right; i++) {
            sum += nums[i];
            if (sum > rightSum) rightSum = sum;
        }

        return leftSum + rightSum;
    }

    /**
     * Approach 5: Explicit Dynamic Programming (Tabulation)
     * Time Complexity: O(N)
     * Space Complexity: O(N) dp table
     * 
     * State Definition:
     * dp[i] = Maximum subarray sum ending at index i.
     * 
     * Transition:
     * dp[i] = Math.max(nums[i], dp[i - 1] + nums[i])
     * 
     * @param nums array of integers
     * @return maximum subarray sum
     */
    public static int maxSubArrayDP(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int n = nums.length;
        int[] dp = new int[n];
        dp[0] = nums[0];
        int globalMax = dp[0];

        for (int i = 1; i < n; i++) {
            dp[i] = Math.max(nums[i], dp[i - 1] + nums[i]);
            globalMax = Math.max(globalMax, dp[i]);
        }
        return globalMax;
    }

    public static void main(String[] args) {
        System.out.println("=== Testing Maximum Subarray (Kadane's Algorithm) ===");

        int[][] testCases = {
            {-2, 1, -3, 4, -1, 2, 1, -5, 4},
            {1},
            {5, 4, -1, 7, 8},
            {-8, -3, -6, -2, -5, -4},
            {-1}
        };

        for (int[] test : testCases) {
            System.out.println("\nInput: " + Arrays.toString(test));
            System.out.println("  Cubic Brute Force : " + maxSubArrayBruteForceCubic(test));
            System.out.println("  Prefix Sum O(N^2) : " + maxSubArrayPrefixSumQuadratic(test));
            System.out.println("  Kadane's Algorithm: " + maxSubArrayKadane(test));
            System.out.println("  Divide & Conquer  : " + maxSubArrayDivideAndConquer(test));
            System.out.println("  Dynamic Prog (DP) : " + maxSubArrayDP(test));
            System.out.println("  Range Breakdown   : " + maxSubArrayWithRange(test));
        }
    }
}
