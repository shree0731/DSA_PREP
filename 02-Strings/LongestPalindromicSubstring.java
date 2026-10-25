/**
 * @author Shreepurnaa V
 * @version 1.0
 */
import java.util.*;

/**
 * Problem: Longest Palindromic Substring (LeetCode #5)
 * 
 * Given a string s, return the longest palindromic substring in s.
 * 
 * Example 1:
 * Input: s = "babad"
 * Output: "bab" (or "aba")
 * 
 * Example 2:
 * Input: s = "cbbd"
 * Output: "bb"
 */
public class LongestPalindromicSubstring {

    public LongestPalindromicSubstring() {
        // Utility class
    }

    /**
     * Approach 1: Brute Force
     * Time Complexity: O(N^3)
     * Space Complexity: O(1) auxiliary
     * 
     * Tests all substrings and validates palindrome condition.
     * 
     * @param s input string
     * @return longest palindromic substring
     */
    public static String longestPalindromeBruteForce(String s) {
        if (s == null || s.length() <= 1) return s;
        String longest = "";

        for (int i = 0; i < s.length(); i++) {
            for (int j = i; j < s.length(); j++) {
                String sub = s.substring(i, j + 1);
                if (isPalindrome(sub) && sub.length() > longest.length()) {
                    longest = sub;
                }
            }
        }
        return longest;
    }

    private static boolean isPalindrome(String s) {
        int i = 0, j = s.length() - 1;
        while (i < j) {
            if (s.charAt(i++) != s.charAt(j--)) return false;
        }
        return true;
    }

    /**
     * Approach 2: 2D Dynamic Programming
     * Time Complexity: O(N^2)
     * Space Complexity: O(N^2) boolean table
     * 
     * State Definition:
     * dp[i][j] = true if substring s[i .. j] is a palindrome.
     * 
     * Base cases:
     * - Length 1: dp[i][i] = true
     * - Length 2: dp[i][i+1] = (s[i] == s[i+1])
     * 
     * Recurrence:
     * dp[i][j] = (s[i] == s[j]) && dp[i+1][j-1]
     * 
     * @param s input string
     * @return longest palindromic substring
     */
    public static String longestPalindromeDP(String s) {
        if (s == null || s.length() <= 1) return s;
        int n = s.length();
        boolean[][] dp = new boolean[n][n];

        int start = 0;
        int maxLen = 1;

        // Base case: 1-character palindromes
        for (int i = 0; i < n; i++) {
            dp[i][i] = true;
        }

        // Substrings of length >= 2
        for (int len = 2; len <= n; len++) {
            for (int i = 0; i <= n - len; i++) {
                int j = i + len - 1;

                if (s.charAt(i) == s.charAt(j)) {
                    if (len == 2 || dp[i + 1][j - 1]) {
                        dp[i][j] = true;
                        if (len > maxLen) {
                            start = i;
                            maxLen = len;
                        }
                    }
                }
            }
        }
        return s.substring(start, start + maxLen);
    }

    /**
     * Approach 3: Expand Around Center (Optimal)
     * Time Complexity: O(N^2)
     * Space Complexity: O(1) auxiliary
     * 
     * Palindromes can have odd length (1 center) or even length (2 centers).
     * There are (2N - 1) possible centers in a string of length N.
     * 
     * @param s input string
     * @return longest palindromic substring
     */
    public static String longestPalindromeExpandCenter(String s) {
        if (s == null || s.length() <= 1) return s;
        int start = 0, end = 0;

        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i);     // Odd length
            int len2 = expandAroundCenter(s, i, i + 1); // Even length
            int len = Math.max(len1, len2);

            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    private static int expandAroundCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return right - left - 1;
    }

    /**
     * Palindromic Substrings (LeetCode #647)
     * Counts the total number of palindromic substrings in s.
     * Time Complexity: O(N^2)
     * Space Complexity: O(1)
     * 
     * @param s input string
     * @return total count of palindromic substrings
     */
    public static int countSubstrings(String s) {
        if (s == null || s.length() == 0) return 0;
        int count = 0;

        for (int i = 0; i < s.length(); i++) {
            count += countPalindromesAround(s, i, i);     // Odd centers
            count += countPalindromesAround(s, i, i + 1); // Even centers
        }
        return count;
    }

    private static int countPalindromesAround(String s, int left, int right) {
        int count = 0;
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            count++;
            left--;
            right++;
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println("=== Testing Longest Palindromic Substring ===");

        String[] testCases = {
            "babad",
            "cbbd",
            "a",
            "ac",
            "racecar",
            "noon",
            "forgeeksskeegfor"
        };

        for (String test : testCases) {
            System.out.println("\nInput: '" + test + "'");
            System.out.println("  Brute Force   : " + longestPalindromeBruteForce(test));
            System.out.println("  Dynamic Prog  : " + longestPalindromeDP(test));
            System.out.println("  Expand Center : " + longestPalindromeExpandCenter(test));
            System.out.println("  Total Palindromes: " + countSubstrings(test));
        }
    }
}
