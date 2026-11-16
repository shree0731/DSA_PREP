import java.util.*;

/**
 * Partition and Interval Dynamic Programming algorithms.
 * Implements Matrix Chain Multiplication, Palindrome Partitioning II,
 * Burst Balloons, and Minimax Game Play.
 *
 * @author Shreepurnaa V
 */
public class PartitionAndIntervalDP {


    /**
     * Matrix Chain Multiplication (MCM).
     * Determines optimal parenthesization to minimize scalar multiplications.
     * Dimensions array p where matrix i has dimension p[i-1] x p[i].
     * Time: O(N^3), Space: O(N^2).
     */
    public static int matrixChainOrder(int[] p) {
        int n = p.length - 1; // Number of matrices
        int[][] dp = new int[n + 1][n + 1];

        // l is chain length
        for (int l = 2; l <= n; l++) {
            for (int i = 1; i <= n - l + 1; i++) {
                int j = i + l - 1;
                dp[i][j] = Integer.MAX_VALUE;
                for (int k = i; k < j; k++) {
                    int cost = dp[i][k] + dp[k + 1][j] + p[i - 1] * p[k] * p[j];
                    dp[i][j] = Math.min(dp[i][j], cost);
                }
            }
        }
        return dp[1][n];
    }
}
