import java.util.*;

/**
 * Problem: Valid Anagram (LeetCode #242) & Group Anagrams (LeetCode #49)
 * 
 * An Anagram is a word or phrase formed by rearranging the letters of a different
 * word or phrase, typically using all the original letters exactly once.
 */
public class ValidAnagram {

    public ValidAnagram() {
        // Utility class
    }

    /**
     * Approach 1: Sorting Based
     * Time Complexity: O(N log N)
     * Space Complexity: O(N) for char arrays
     * 
     * @param s first string
     * @param t second string
     * @return true if t is an anagram of s
     */
    public static boolean isAnagramSorting(String s, String t) {
        if (s == null || t == null) return false;
        if (s.length() != t.length()) return false;

        char[] sArr = s.toCharArray();
        char[] tArr = t.toCharArray();
        Arrays.sort(sArr);
        Arrays.sort(tArr);

        return Arrays.equals(sArr, tArr);
    }
}
