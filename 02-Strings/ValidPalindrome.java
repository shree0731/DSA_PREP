import java.util.*;

/**
 * Problem: Valid Palindrome (LeetCode #125) & Valid Palindrome II (LeetCode #680)
 * 
 * A phrase is a palindrome if, after converting all uppercase letters into lowercase
 * letters and removing all non-alphanumeric characters, it reads the same forward
 * and backward.
 */
public class ValidPalindrome {

    public ValidPalindrome() {
        // Utility class
    }

    /**
     * Approach 1: Filter and Reverse
     * Time Complexity: O(N)
     * Space Complexity: O(N) creates filtered copy
     * 
     * @param s input string
     * @return true if palindrome
     */
    public static boolean isPalindromeFiltered(String s) {
        if (s == null) return false;
        StringBuilder filtered = new StringBuilder();

        for (char c : s.toCharArray()) {
            if (Character.isLetterOrDigit(c)) {
                filtered.append(Character.toLowerCase(c));
            }
        }

        String cleaned = filtered.toString();
        String reversed = filtered.reverse().toString();
        return cleaned.equals(reversed);
    }
}
