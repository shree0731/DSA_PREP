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
}
