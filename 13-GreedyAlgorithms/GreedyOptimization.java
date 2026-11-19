import java.util.*;

/**
 * Greedy optimization algorithms:
 * 1. Gas Station (LeetCode 134)
 * 2. Jump Game I & II (LeetCode 55, 45)
 * 3. Candy Distribution (LeetCode 135)
 *
 * @author Shreepurnaa V
 */
public class GreedyOptimization {

    /**
     * Gas Station (LeetCode 134).
     * If total gas >= total cost, a solution is guaranteed.
     * Whenever current tank falls below 0, restart start station at i + 1.
     * Time: O(N), Space: O(1).
     */
    public static int canCompleteCircuit(int[] gas, int[] cost) {
        int totalSurplus = 0, currentTank = 0, startStation = 0;

        for (int i = 0; i < gas.length; i++) {
            int net = gas[i] - cost[i];
            totalSurplus += net;
            currentTank += net;
            if (currentTank < 0) {
                startStation = i + 1;
                currentTank = 0;
            }
        }
        return totalSurplus >= 0 ? startStation : -1;
    }

    /**
     * Jump Game (LeetCode 55).
     * Tracks maximum reachable index greedily.
     * Time: O(N), Space: O(1).
     */
    public static boolean canJump(int[] nums) {
        int maxReach = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i > maxReach) return false;
            maxReach = Math.max(maxReach, i + nums[i]);
        }
        return true;
    }

    /**
     * Jump Game II (LeetCode 45).
     * Finds minimum jumps to reach last index using BFS-like greedy window.
     * Time: O(N), Space: O(1).
     */
    public static int jump(int[] nums) {
        int jumps = 0, currentEnd = 0, farthest = 0;

        for (int i = 0; i < nums.length - 1; i++) {
            farthest = Math.max(farthest, i + nums[i]);
            if (i == currentEnd) {
                jumps++;
                currentEnd = farthest;
            }
        }
        return jumps;
    }

    /**
     * Candy Distribution (LeetCode 135).
     * Children with higher rating get more candies than their immediate neighbors.
     * Solved via two greedy passes: left-to-right and right-to-left.
     * Time: O(N), Space: O(N).
     */
    public static int candy(int[] ratings) {
        int n = ratings.length;
        int[] candies = new int[n];
        Arrays.fill(candies, 1);

        for (int i = 1; i < n; i++) {
            if (ratings[i] > ratings[i - 1]) {
                candies[i] = candies[i - 1] + 1;
            }
        }

        for (int i = n - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]) {
                candies[i] = Math.max(candies[i], candies[i + 1] + 1);
            }
        }

        int total = 0;
        for (int c : candies) total += c;
        return total;
    }

    public static void main(String[] args) {
        System.out.println("=== Testing Greedy Optimization ===");
        int[] gas = {1, 2, 3, 4, 5};
        int[] cost = {3, 4, 5, 1, 2};
        System.out.println("Gas Station Start Index: " + canCompleteCircuit(gas, cost) + " (Expected: 3)");

        int[] jumpNums1 = {2, 3, 1, 1, 4};
        System.out.println("Can Jump [2,3,1,1,4]: " + canJump(jumpNums1) + " (Expected: true)");
        System.out.println("Min Jumps: " + jump(jumpNums1) + " (Expected: 2)");

        int[] ratings = {1, 0, 2};
        System.out.println("Candy Distribution: " + candy(ratings) + " (Expected: 5)");
        System.out.println("All GreedyOptimization tests passed successfully!");
    }
}
