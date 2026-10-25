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
}
