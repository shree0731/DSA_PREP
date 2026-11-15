import java.util.*;

/**
 * 1-Dimensional Dynamic Programming algorithms.
 * Implements Climbing Stairs, House Robber I & II, Coin Change I & II,
 * Longest Increasing Subsequence (LIS), and Maximum Product Subarray.
 *
 * @author Shreepurnaa V
 */
public class OneDimensionalDP {


    /**
     * Climbing Stairs (LeetCode 70).
     * Solves step combinations using O(1) rolling space Fibonacci transition.
     * Time: O(N), Space: O(1).
     */
    public static int climbStairs(int n) {
        if (n <= 2) return n;
        int prev2 = 1, prev1 = 2;
        for (int i = 3; i <= n; i++) {
            int curr = prev1 + prev2;
            prev2 = prev1;
            prev1 = curr;
        }
        return prev1;
    }

    /**
     * House Robber (LeetCode 198).
     * Decides optimal loot without robbing two adjacent houses.
     * Time: O(N), Space: O(1).
     */
    public static int rob(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int robPrev = 0, skipPrev = 0;
        for (int num : nums) {
            int currentRob = skipPrev + num;
            int currentSkip = Math.max(skipPrev, robPrev);
            robPrev = currentRob;
            skipPrev = currentSkip;
        }
        return Math.max(robPrev, skipPrev);
    }

    /**
     * House Robber II (LeetCode 213).
     * Houses arranged in a circle: house[0] and house[n-1] are neighbors.
     * Decomposes problem into two linear passes: [0, n-2] and [1, n-1].
     * Time: O(N), Space: O(1).
     */
    public static int robII(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        return Math.max(robLinear(nums, 0, nums.length - 2),
                        robLinear(nums, 1, nums.length - 1));
    }

    private static int robLinear(int[] nums, int start, int end) {
        int robPrev = 0, skipPrev = 0;
        for (int i = start; i <= end; i++) {
            int currentRob = skipPrev + nums[i];
            int currentSkip = Math.max(skipPrev, robPrev);
            robPrev = currentRob;
            skipPrev = currentSkip;
        }
        return Math.max(robPrev, skipPrev);
    }

    /**
     * Coin Change I (LeetCode 322).
     * Finds the minimum number of coins to make up given amount (Unbounded Knapsack variant).
     * Time: O(Amount * Coins), Space: O(Amount).
     */
    public static int coinChange(int[] coins, int amount) {
        if (amount < 0) return -1;
        int max = amount + 1;
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, max);
        dp[0] = 0;

        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (coin <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }
}
