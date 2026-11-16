import java.util.*;

/**
 * String and Sequence Dynamic Programming algorithms.
 * Implements Longest Common Subsequence (LCS), LCS Path Reconstruction,
 * Longest Common Substring, Edit Distance, Distinct Subsequences, and Wildcard Matching.
 *
 * @author Shreepurnaa V
 */
public class StringAndSequenceDP {


    /**
     * Longest Common Subsequence (LeetCode 1143).
     * Solves longest common subsequence across two strings.
     * Time: O(M * N), Space: O(M * N).
     */
    public static int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length(), n = text2.length();
        int[][] dp = new int[m + 1][n + 1];

        for (int i = 1; i <= m; i++) {
            char c1 = text1.charAt(i - 1);
            for (int j = 1; j <= n; j++) {
                char c2 = text2.charAt(j - 1);
                if (c1 == c2) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[m][n];
    }
}
