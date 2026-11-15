import java.util.*;

/**
 * 2-Dimensional and Grid Dynamic Programming algorithms.
 * Covers Unique Paths I/II, Minimum Path Sum, Maximal Square, and Dungeon Game.
 *
 * @author Shreepurnaa V
 */
public class GridAndTwoDimensionalDP {


    /**
     * Unique Paths (LeetCode 62).
     * Computes distinct paths from top-left to bottom-right moving only right or down.
     * Space optimized to 1D rolling array.
     * Time: O(M * N), Space: O(N).
     */
    public static int uniquePaths(int m, int n) {
        int[] dp = new int[n];
        Arrays.fill(dp, 1);

        for (int r = 1; r < m; r++) {
            for (int c = 1; c < n; c++) {
                dp[c] += dp[c - 1];
            }
        }
        return dp[n - 1];
    }

    /**
     * Unique Paths II (LeetCode 63).
     * Handles obstacles where obstacleGrid[r][c] == 1 blocks path.
     * Time: O(M * N), Space: O(N).
     */
    public static int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int n = obstacleGrid[0].length;
        int[] dp = new int[n];
        dp[0] = obstacleGrid[0][0] == 1 ? 0 : 1;

        for (int[] row : obstacleGrid) {
            for (int c = 0; c < n; c++) {
                if (row[c] == 1) {
                    dp[c] = 0;
                } else if (c > 0) {
                    dp[c] += dp[c - 1];
                }
            }
        }
        return dp[n - 1];
    }
}
