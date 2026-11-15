import java.util.*;

/**
 * Knapsack and Subset Sum Dynamic Programming algorithms.
 * Implements 0/1 Knapsack, Unbounded Knapsack, Partition Equal Subset Sum, and Target Sum.
 *
 * @author Shreepurnaa V
 */
public class KnapsackAndSubsetsDP {


    /**
     * 0/1 Knapsack Problem.
     * Each item can be picked at most once.
     * Space optimized to 1D array iterating capacities in REVERSE order.
     * Time: O(N * W), Space: O(W).
     */
    public static int knapsack01(int[] weights, int[] values, int capacity) {
        int[] dp = new int[capacity + 1];

        for (int i = 0; i < weights.length; i++) {
            int w = weights[i], v = values[i];
            // Reverse iteration prevents using the current item multiple times
            for (int cap = capacity; cap >= w; cap--) {
                dp[cap] = Math.max(dp[cap], dp[cap - w] + v);
            }
        }
        return dp[capacity];
    }

    /**
     * Unbounded Knapsack Problem (Rod Cutting).
     * Items can be picked unlimited times.
     * Iterating capacities in FORWARD order allows using the same item repeatedly.
     * Time: O(N * W), Space: O(W).
     */
    public static int unboundedKnapsack(int[] weights, int[] values, int capacity) {
        int[] dp = new int[capacity + 1];

        for (int i = 0; i < weights.length; i++) {
            int w = weights[i], v = values[i];
            // Forward iteration enables multiple picks of current item
            for (int cap = w; cap <= capacity; cap++) {
                dp[cap] = Math.max(dp[cap], dp[cap - w] + v);
            }
        }
        return dp[capacity];
    }

    /**
     * Partition Equal Subset Sum (LeetCode 416).
     * Determines whether array can be partitioned into two subsets with equal sum.
     * Maps to 0/1 Knapsack where target = sum / 2.
     * Time: O(N * Target), Space: O(Target).
     */
    public static boolean canPartition(int[] nums) {
        int totalSum = 0;
        for (int x : nums) totalSum += x;
        if ((totalSum & 1) != 0) return false; // Odd sum cannot be partitioned equally

        int target = totalSum / 2;
        boolean[] dp = new boolean[target + 1];
        dp[0] = true;

        for (int num : nums) {
            for (int cap = target; cap >= num; cap--) {
                dp[cap] = dp[cap] || dp[cap - num];
            }
        }
        return dp[target];
    }

    /**
     * Target Sum (LeetCode 494).
     * Assigns + or - to each integer to reach target.
     * Mathematical reduction:
     *   P - N = target, P + N = sum => 2P = sum + target => P = (sum + target) / 2.
     * Time: O(N * P), Space: O(P).
     */
    public static int findTargetSumWays(int[] nums, int target) {
        int totalSum = 0;
        for (int x : nums) totalSum += x;
        if (Math.abs(target) > totalSum || ((totalSum + target) & 1) != 0) return 0;

        int positiveTarget = (totalSum + target) / 2;
        int[] dp = new int[positiveTarget + 1];
        dp[0] = 1;

        for (int num : nums) {
            for (int cap = positiveTarget; cap >= num; cap--) {
                dp[cap] += dp[cap - num];
            }
        }
        return dp[positiveTarget];
    }
}
