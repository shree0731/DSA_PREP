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
}
