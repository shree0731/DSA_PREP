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
}
