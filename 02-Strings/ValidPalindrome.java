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

    /**
     * Approach 2: Optimal Two Pointers (In-place)
     * Time Complexity: O(N)
     * Space Complexity: O(1) auxiliary
     * 
     * Scans from both ends skipping non-alphanumeric characters without allocating copies.
     * 
     * @param s input string
     * @return true if valid palindrome
     */
    public static boolean isPalindromeOptimal(String s) {
        if (s == null) return false;
        int left = 0;
        int right = s.length() - 1;

        while (left < right) {
            while (left < right && !Character.isLetterOrDigit(s.charAt(left))) {
                left++;
            }
            while (left < right && !Character.isLetterOrDigit(s.charAt(right))) {
                right--;
            }

            if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    /**
     * Valid Palindrome II (LeetCode #680)
     * Determines whether s can be a palindrome after deleting at most one character.
     * Time Complexity: O(N)
     * Space Complexity: O(1)
     * 
     * @param s input string
     * @return true if palindrome after <= 1 deletion
     */
    public static boolean validPalindromeII(String s) {
        if (s == null) return false;
        int left = 0;
        int right = s.length() - 1;

        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                // Try skipping either left or right character
                return isPalindromeRange(s, left + 1, right) || isPalindromeRange(s, left, right - 1);
            }
            left++;
            right--;
        }
        return true;
    }

    private static boolean isPalindromeRange(String s, int left, int right) {
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) return false;
            left++;
            right--;
        }
        return true;
    }

    /**
     * Checks if a sentence is palindromic at word level.
     * Example: "fall leaves after leaves fall" -> true
     */
    public static boolean isWordPalindrome(String sentence) {
        if (sentence == null) return false;
        String[] words = sentence.trim().toLowerCase().split("\\s+");
        int left = 0;
        int right = words.length - 1;

        while (left < right) {
            if (!words[left].equals(words[right])) return false;
            left++;
            right--;
        }
        return true;
    }
}
