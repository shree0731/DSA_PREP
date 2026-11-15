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
}
