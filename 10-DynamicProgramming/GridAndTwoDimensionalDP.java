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

    /**
     * Minimum Path Sum (LeetCode 64).
     * Finds path from top-left to bottom-right minimizing cell sum.
     * Time: O(M * N), Space: O(N).
     */
    public static int minPathSum(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[] dp = new int[n];
        dp[0] = grid[0][0];

        for (int c = 1; c < n; c++) {
            dp[c] = dp[c - 1] + grid[0][c];
        }

        for (int r = 1; r < m; r++) {
            dp[0] += grid[r][0];
            for (int c = 1; c < n; c++) {
                dp[c] = Math.min(dp[c], dp[c - 1]) + grid[r][c];
            }
        }
        return dp[n - 1];
    }

    /**
     * Maximal Square (LeetCode 221).
     * Finds largest square containing only '1's and returns its area.
     * Recurrence: dp[r][c] = 1 + min(top, left, diagonal_top_left).
     * Time: O(M * N), Space: O(M * N).
     */
    public static int maximalSquare(char[][] matrix) {
        if (matrix == null || matrix.length == 0) return 0;
        int m = matrix.length, n = matrix[0].length;
        int[][] dp = new int[m + 1][n + 1];
        int maxSide = 0;

        for (int r = 1; r <= m; r++) {
            for (int c = 1; c <= n; c++) {
                if (matrix[r - 1][c - 1] == '1') {
                    dp[r][c] = 1 + Math.min(dp[r - 1][c],
                                   Math.min(dp[r][c - 1], dp[r - 1][c - 1]));
                    maxSide = Math.max(maxSide, dp[r][c]);
                }
            }
        }
        return maxSide * maxSide;
    }

    /**
     * Dungeon Game (LeetCode 174).
     * Determines knight's minimum initial health needed to rescue princess.
     * Solved backwards from princess cell (m-1, n-1) back to entrance (0, 0).
     * Time: O(M * N), Space: O(N).
     */
    public static int calculateMinimumHP(int[][] dungeon) {
        int m = dungeon.length, n = dungeon[0].length;
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[n - 1] = 1;

        for (int r = m - 1; r >= 0; r--) {
            for (int c = n - 1; c >= 0; c--) {
                int minHealthOnExit;
                if (r == m - 1 && c == n - 1) {
                    minHealthOnExit = 1;
                } else if (r == m - 1) {
                    minHealthOnExit = dp[c + 1];
                } else if (c == n - 1) {
                    minHealthOnExit = dp[c];
                } else {
                    minHealthOnExit = Math.min(dp[c], dp[c + 1]);
                }
                dp[c] = Math.max(1, minHealthOnExit - dungeon[r][c]);
            }
        }
        return dp[0];
    }

    public static void main(String[] args) {
        System.out.println("=== Testing Grid and Two-Dimensional DP ===");
        System.out.println("Unique Paths (3x7): " + uniquePaths(3, 7) + " (Expected: 28)");

        int[][] obstacleGrid = {
                {0, 0, 0},
                {0, 1, 0},
                {0, 0, 0}
        };
        System.out.println("Unique Paths with Obstacles: " + uniquePathsWithObstacles(obstacleGrid) + " (Expected: 2)");

        int[][] grid = {
                {1, 3, 1},
                {1, 5, 1},
                {4, 2, 1}
        };
        System.out.println("Minimum Path Sum: " + minPathSum(grid) + " (Expected: 7)");

        char[][] squareMatrix = {
                {'1', '0', '1', '0', '0'},
                {'1', '0', '1', '1', '1'},
                {'1', '1', '1', '1', '1'},
                {'1', '0', '0', '1', '0'}
        };
        System.out.println("Maximal Square Area: " + maximalSquare(squareMatrix) + " (Expected: 4)");

        int[][] dungeon = {
                {-2, -3, 3},
                {-5, -10, 1},
                {10, 30, -5}
        };
        System.out.println("Dungeon Game Minimum HP: " + calculateMinimumHP(dungeon) + " (Expected: 7)");
        System.out.println("All GridAndTwoDimensionalDP tests passed successfully!");
    }
}
