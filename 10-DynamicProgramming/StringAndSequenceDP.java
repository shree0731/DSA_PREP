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

    /**
     * Reconstructs and prints the Longest Common Subsequence string.
     * Backtracks through the 2D DP table.
     * Time: O(M + N), Space: O(M * N).
     */
    public static String printLCS(String text1, String text2) {
        int m = text1.length(), n = text2.length();
        int[][] dp = new int[m + 1][n + 1];

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        int i = m, j = n;
        while (i > 0 && j > 0) {
            if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                sb.append(text1.charAt(i - 1));
                i--;
                j--;
            } else if (dp[i - 1][j] > dp[i][j - 1]) {
                i--;
            } else {
                j--;
            }
        }
        return sb.reverse().toString();
    }

    /**
     * Longest Common Substring.
     * Substring requires characters to be contiguous.
     * When characters do not match, dp[i][j] resets to 0.
     * Time: O(M * N), Space: O(M * N).
     */
    public static int longestCommonSubstring(String s1, String s2) {
        int m = s1.length(), n = s2.length();
        int[][] dp = new int[m + 1][n + 1];
        int maxLen = 0;

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                    maxLen = Math.max(maxLen, dp[i][j]);
                } else {
                    dp[i][j] = 0; // Reset continuity
                }
            }
        }
        return maxLen;
    }

    /**
     * Edit Distance / Levenshtein Distance (LeetCode 72).
     * Minimum operations (Insert, Delete, Replace) to convert word1 to word2.
     * Time: O(M * N), Space: O(M * N).
     */
    public static int minDistance(String word1, String word2) {
        int m = word1.length(), n = word2.length();
        int[][] dp = new int[m + 1][n + 1];

        for (int i = 0; i <= m; i++) dp[i][0] = i;
        for (int j = 0; j <= n; j++) dp[0][j] = j;

        for (int i = 1; i <= m; i++) {
            char c1 = word1.charAt(i - 1);
            for (int j = 1; j <= n; j++) {
                char c2 = word2.charAt(j - 1);
                if (c1 == c2) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = 1 + Math.min(dp[i - 1][j - 1], // Replace
                                   Math.min(dp[i - 1][j],     // Delete
                                            dp[i][j - 1]));   // Insert
                }
            }
        }
        return dp[m][n];
    }

    /**
     * Distinct Subsequences (LeetCode 115).
     * Counts number of distinct subsequences of s which equals t.
     * Time: O(M * N), Space: O(N).
     */
    public static int numDistinct(String s, String t) {
        int m = s.length(), n = t.length();
        double[] dp = new double[n + 1];
        dp[0] = 1;

        for (int i = 1; i <= m; i++) {
            char sc = s.charAt(i - 1);
            for (int j = n; j >= 1; j--) {
                if (sc == t.charAt(j - 1)) {
                    dp[j] += dp[j - 1];
                }
            }
        }
        return (int) dp[n];
    }

    /**
     * Wildcard Matching (LeetCode 44).
     * '?' Matches any single character.
     * '*' Matches any sequence of characters (including the empty sequence).
     * Time: O(M * N), Space: O(N).
     */
    public static boolean isMatch(String s, String p) {
        int m = s.length(), n = p.length();
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;

        for (int j = 1; j <= n; j++) {
            if (p.charAt(j - 1) == '*') dp[j] = dp[j - 1];
        }

        for (int i = 1; i <= m; i++) {
            boolean[] next = new boolean[n + 1];
            char sc = s.charAt(i - 1);
            for (int j = 1; j <= n; j++) {
                char pc = p.charAt(j - 1);
                if (pc == sc || pc == '?') {
                    next[j] = dp[j - 1];
                } else if (pc == '*') {
                    next[j] = next[j - 1] || dp[j];
                }
            }
            dp = next;
        }
        return dp[n];
    }
}
