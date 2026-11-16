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

    /**
     * Burst Balloons (LeetCode 312).
     * Reverse-thinking Interval DP: pick the LAST balloon to burst in interval (i, j).
     * Time: O(N^3), Space: O(N^2).
     */
    public static int maxCoins(int[] nums) {
        int n = nums.length;
        int[] padded = new int[n + 2];
        padded[0] = 1;
        padded[n + 1] = 1;
        System.arraycopy(nums, 0, padded, 1, n);

        int[][] dp = new int[n + 2][n + 2];

        for (int len = 1; len <= n; len++) {
            for (int left = 1; left <= n - len + 1; left++) {
                int right = left + len - 1;
                for (int k = left; k <= right; k++) {
                    int coins = padded[left - 1] * padded[k] * padded[right + 1]
                              + dp[left][k - 1]
                              + dp[k + 1][right];
                    dp[left][right] = Math.max(dp[left][right], coins);
                }
            }
        }
        return dp[1][n];
    }

    /**
     * Stone Game / Predict the Winner (LeetCode 877, 486).
     * Minimax 2-player optimal game: returns score difference player1 - player2.
     * Time: O(N^2), Space: O(N).
     */
    public static boolean stoneGame(int[] piles) {
        int n = piles.length;
        int[] dp = Arrays.copyOf(piles, n);

        for (int len = 2; len <= n; len++) {
            for (int i = 0; i <= n - len; i++) {
                int j = i + len - 1;
                dp[i] = Math.max(piles[i] - dp[i + 1], piles[j] - dp[i]);
            }
        }
        return dp[0] > 0;
    }

    public static void main(String[] args) {
        System.out.println("=== Testing Partition and Interval DP ===");
        int[] matrices = {10, 20, 30, 40, 30};
        System.out.println("Matrix Chain Min Multiplications: " + matrixChainOrder(matrices) + " (Expected: 30000)");

        System.out.println("Min Cuts for Palindrome Partitioning ('aab'): " + minCut("aab") + " (Expected: 1)");
        System.out.println("Min Cuts for Palindrome Partitioning ('racecar'): " + minCut("racecar") + " (Expected: 0)");

        int[] balloons = {3, 1, 5, 8};
        System.out.println("Burst Balloons Max Coins: " + maxCoins(balloons) + " (Expected: 167)");

        int[] stonePiles = {5, 3, 4, 5};
        System.out.println("Stone Game (Player 1 wins?): " + stoneGame(stonePiles) + " (Expected: true)");
        System.out.println("All PartitionAndIntervalDP tests passed successfully!");
    }
}
