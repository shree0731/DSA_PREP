import java.util.*;

/**
 * Problem: Longest Substring Without Repeating Characters (LeetCode #3)
 * 
 * Given a string s, find the length of the longest substring without repeating characters.
 * 
 * Examples:
 * Input: s = "abcabcbb" -> Output: 3 ("abc")
 * Input: s = "bbbbb"    -> Output: 1 ("b")
 * Input: s = "pwwkew"   -> Output: 3 ("wke")
 */
public class LongestSubstringWithoutRepeating {

    public LongestSubstringWithoutRepeating() {
        // Utility class
    }

    /**
     * Approach 1: Brute Force
     * Time Complexity: O(N^3)
     * Space Complexity: O(min(N, M)) where M is character alphabet size
     * 
     * Tests all possible substrings s[i .. j] for character uniqueness.
     * 
     * @param s input string
     * @return max length without repeating characters
     */
    public static int lengthOfLongestSubstringBruteForce(String s) {
        if (s == null || s.length() == 0) return 0;
        int maxLen = 0;
        int n = s.length();

        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                if (allUnique(s, i, j)) {
                    maxLen = Math.max(maxLen, j - i + 1);
                }
            }
        }
        return maxLen;
    }

    private static boolean allUnique(String s, int start, int end) {
        Set<Character> set = new HashSet<>();
        for (int k = start; k <= end; k++) {
            char c = s.charAt(k);
            if (set.contains(c)) return false;
            set.add(c);
        }
        return true;
    }

    /**
     * Approach 2: Sliding Window with HashSet
     * Time Complexity: O(2N) = O(N) in worst case each character is visited twice
     * Space Complexity: O(min(N, M))
     * 
     * Uses two pointers (left, right) representing window [left .. right].
     * 
     * @param s input string
     * @return max length
     */
    public static int lengthOfLongestSubstringSlidingWindow(String s) {
        if (s == null || s.length() == 0) return 0;
        Set<Character> set = new HashSet<>();
        int maxLen = 0;
        int left = 0;

        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            while (set.contains(c)) {
                set.remove(s.charAt(left));
                left++;
            }
            set.add(c);
            maxLen = Math.max(maxLen, right - left + 1);
        }
        return maxLen;
    }

    /**
     * Approach 3: Optimized Sliding Window with HashMap (Last Seen Index)
     * Time Complexity: O(N) exactly N steps
     * Space Complexity: O(min(N, M))
     * 
     * When duplicate character is detected at index right, we skip left pointer
     * directly to max(left, lastSeenIndex + 1).
     * 
     * @param s input string
     * @return max length
     */
    public static int lengthOfLongestSubstringOptimal(String s) {
        if (s == null || s.length() == 0) return 0;
        Map<Character, Integer> lastSeen = new HashMap<>();
        int maxLen = 0;
        int left = 0;

        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            if (lastSeen.containsKey(c)) {
                left = Math.max(left, lastSeen.get(c) + 1);
            }
            lastSeen.put(c, right);
            maxLen = Math.max(maxLen, right - left + 1);
        }
        return maxLen;
    }

    /**
     * Approach 4: Direct ASCII 128/256 Array Index Lookup
     * Time Complexity: O(N)
     * Space Complexity: O(1) auxiliary (fixed 128 ints)
     * 
     * Eliminates HashMap hashing overhead for standard ASCII text.
     * 
     * @param s input string
     * @return max length
     */
    public static int lengthOfLongestSubstringAscii(String s) {
        if (s == null || s.length() == 0) return 0;
        int[] lastIndex = new int[128];
        Arrays.fill(lastIndex, -1);

        int maxLen = 0;
        int left = 0;

        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            if (c < 128 && lastIndex[c] >= left) {
                left = lastIndex[c] + 1;
            }
            if (c < 128) {
                lastIndex[c] = right;
            }
            maxLen = Math.max(maxLen, right - left + 1);
        }
        return maxLen;
    }

    /**
     * Result container representing longest unique substring details.
     */
    public static class SubstringResult {
        public final int length;
        public final int startIndex;
        public final int endIndex;
        public final String substring;

        public SubstringResult(int length, int startIndex, int endIndex, String original) {
            this.length = length;
            this.startIndex = startIndex;
            this.endIndex = endIndex;
            this.substring = original.substring(startIndex, endIndex + 1);
        }

        @Override
        public String toString() {
            return "SubstringResult{" +
                    "length=" + length +
                    ", range=[" + startIndex + ".." + endIndex + "]" +
                    ", substring='" + substring + ''' +
                    '}';
        }
    }

    /**
     * Returns the exact longest substring without repeating characters.
     */
    public static SubstringResult findLongestUniqueSubstring(String s) {
        if (s == null || s.length() == 0) return new SubstringResult(0, 0, -1, "");
        Map<Character, Integer> lastSeen = new HashMap<>();
        int maxLen = 0;
        int bestStart = 0;
        int bestEnd = 0;
        int left = 0;

        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            if (lastSeen.containsKey(c)) {
                left = Math.max(left, lastSeen.get(c) + 1);
            }
            lastSeen.put(c, right);

            if (right - left + 1 > maxLen) {
                maxLen = right - left + 1;
                bestStart = left;
                bestEnd = right;
            }
        }
        return new SubstringResult(maxLen, bestStart, bestEnd, s);
    }

    /**
     * Checks whether a string contains all unique characters.
     */
    public static boolean hasAllUniqueChars(String s) {
        return lengthOfLongestSubstringOptimal(s) == (s == null ? 0 : s.length());
    }

    /**
     * Validates edge case behavior: single characters, all identical characters (e.g. 'bbbbb').
     */
    public static boolean isUniformString(String s) {
        if (s == null || s.length() <= 1) return true;
        char first = s.charAt(0);
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) != first) return false;
        }
        return true;
    }
}
