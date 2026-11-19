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
}
