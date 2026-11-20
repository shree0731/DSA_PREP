import java.util.*;

/**
 * Variable-size sliding window algorithms.
 *
 * @author Shreepurnaa V
 */
public class VariableSlidingWindow {

    /**
     * Longest Substring Without Repeating Characters (LeetCode 3).
     * Time: O(N), Space: O(min(N, AlphabetSize)).
     */
    public static int lengthOfLongestSubstring(String s) {
        int[] lastSeen = new int[128];
        Arrays.fill(lastSeen, -1);
        int maxLen = 0, left = 0;

        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            if (lastSeen[c] >= left) {
                left = lastSeen[c] + 1;
            }
            lastSeen[c] = right;
            maxLen = Math.max(maxLen, right - left + 1);
        }
        return maxLen;
    }

    /**
     * Minimum Window Substring (LeetCode 76).
     * Finds the minimum window in s containing all characters of t.
     * Time: O(S + T), Space: O(1).
     */
    public static String minWindow(String s, String t) {
        if (s.isEmpty() || t.isEmpty()) return "";
        int[] targetCount = new int[128];
        for (char c : t.toCharArray()) targetCount[c]++;

        int required = 0;
        for (int count : targetCount) if (count > 0) required++;

        int left = 0, right = 0, formed = 0;
        int[] windowCounts = new int[128];
        int minLen = Integer.MAX_VALUE, startIdx = 0;

        while (right < s.length()) {
            char c = s.charAt(right);
            windowCounts[c]++;
            if (targetCount[c] > 0 && windowCounts[c] == targetCount[c]) {
                formed++;
            }

            while (left <= right && formed == required) {
                if (right - left + 1 < minLen) {
                    minLen = right - left + 1;
                    startIdx = left;
                }
                char leftChar = s.charAt(left);
                windowCounts[leftChar]--;
                if (targetCount[leftChar] > 0 && windowCounts[leftChar] < targetCount[leftChar]) {
                    formed--;
                }
                left++;
            }
            right++;
        }
        return minLen == Integer.MAX_VALUE ? "" : s.substring(startIdx, startIdx + minLen);
    }
}
