import java.util.*;

/**
 * Knuth-Morris-Pratt (KMP) string matching algorithm with Longest Prefix Suffix (LPS) array.
 * Performs linear-time exact substring search without backtracking in text.
 *
 * @author Shreepurnaa V
 */
public class KMPAndStringMatching {

    /**
     * Computes the Longest Prefix Suffix (LPS) array.
     * lps[i] stores the length of the longest proper prefix of pattern[0..i] that is also a suffix of pattern[0..i].
     * Time: O(M), Space: O(M).
     */
    public static int[] computeLPS(String pattern) {
        int m = pattern.length();
        int[] lps = new int[m];
        int len = 0;
        int i = 1;

        while (i < m) {
            if (pattern.charAt(i) == pattern.charAt(len)) {
                lps[i++] = ++len;
            } else if (len != 0) {
                len = lps[len - 1]; // Fallback to previous longest prefix
            } else {
                lps[i++] = 0;
            }
        }
        return lps;
    }

    /**
     * Finds all 0-based start indices of pattern occurrences in text using KMP.
     * Time: O(N + M), Space: O(M).
     */
    public static List<Integer> kmpSearch(String text, String pattern) {
        List<Integer> occurrences = new ArrayList<>();
        if (pattern.isEmpty()) return occurrences;

        int[] lps = computeLPS(pattern);
        int n = text.length(), m = pattern.length();
        int i = 0, j = 0;

        while (i < n) {
            if (text.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;
            }
            if (j == m) {
                occurrences.add(i - j);
                j = lps[j - 1]; // Continue searching for next match
            } else if (i < n && text.charAt(i) != pattern.charAt(j)) {
                if (j != 0) {
                    j = lps[j - 1];
                } else {
                    i++;
                }
            }
        }
        return occurrences;
    }
}
