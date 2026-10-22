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

    /**
     * Approach 2: Hash Map Frequency Counter (Supports Unicode)
     * Time Complexity: O(N)
     * Space Complexity: O(K) where K is number of distinct characters
     * 
     * @param s first string
     * @param t second string
     * @return true if anagram
     */
    public static boolean isAnagramHashMap(String s, String t) {
        if (s == null || t == null) return false;
        if (s.length() != t.length()) return false;

        Map<Character, Integer> counts = new HashMap<>();
        for (char c : s.toCharArray()) {
            counts.put(c, counts.getOrDefault(c, 0) + 1);
        }

        for (char c : t.toCharArray()) {
            if (!counts.containsKey(c)) return false;
            int count = counts.get(c) - 1;
            if (count == 0) {
                counts.remove(c);
            } else {
                counts.put(c, count);
            }
        }

        return counts.isEmpty();
    }

    /**
     * Approach 3: Optimal 26-integer Bucket Array
     * Time Complexity: O(N)
     * Space Complexity: O(1) auxiliary (26 ints)
     * 
     * Increments count for characters in s and decrements for t in a single loop.
     * 
     * @param s first string (lowercase english letters)
     * @param t second string
     * @return true if anagram
     */
    public static boolean isAnagramOptimal(String s, String t) {
        if (s == null || t == null) return false;
        if (s.length() != t.length()) return false;

        int[] count = new int[26];
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'a']++;
            count[t.charAt(i) - 'a']--;
        }

        for (int c : count) {
            if (c != 0) return false;
        }
        return true;
    }
}
