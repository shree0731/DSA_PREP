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

    /**
     * Palindrome Partitioning II (LeetCode 132).
     * Minimum cuts needed for a palindrome partitioning of string s.
     * Precomputes isPalindrome table, then runs 1D DP.
     * Time: O(N^2), Space: O(N^2).
     */
    public static int minCut(String s) {
        int n = s.length();
        boolean[][] isPalin = new boolean[n][n];

        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                if (s.charAt(i) == s.charAt(j) && (j - i <= 2 || isPalin[i + 1][j - 1])) {
                    isPalin[i][j] = true;
                }
            }
        }

        int[] cuts = new int[n];
        for (int i = 0; i < n; i++) {
            if (isPalin[0][i]) {
                cuts[i] = 0;
            } else {
                int min = i;
                for (int j = 0; j < i; j++) {
                    if (isPalin[j + 1][i]) {
                        min = Math.min(min, cuts[j] + 1);
                    }
                }
                cuts[i] = min;
            }
        }
        return cuts[n - 1];
    }
}
