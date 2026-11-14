import java.util.*;

/**
 * Advanced Graph algorithms.
 * Covers Word Ladder (Bidirectional BFS), Kosaraju's SCC, Tarjan's Bridges, and Bipartite Checking.
 *
 * @author Shreepurnaa V
 */
public class NetworkFlowAndAdvancedGraphs {


    /**
     * Word Ladder (LeetCode 127).
     * Shortest transformation sequence using Bidirectional BFS.
     * Time: O(M^2 * N), Space: O(M * N).
     */
    public static int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordSet = new HashSet<>(wordList);
        if (!wordSet.contains(endWord)) return 0;

        Set<String> beginSet = new HashSet<>();
        Set<String> endSet = new HashSet<>();
        beginSet.add(beginWord);
        endSet.add(endWord);

        int level = 1;
        while (!beginSet.isEmpty() && !endSet.isEmpty()) {
            if (beginSet.size() > endSet.size()) {
                Set<String> swap = beginSet;
                beginSet = endSet;
                endSet = swap;
            }

            Set<String> nextSet = new HashSet<>();
            for (String word : beginSet) {
                char[] chars = word.toCharArray();
                for (int i = 0; i < chars.length; i++) {
                    char old = chars[i];
                    for (char c = 'a'; c <= 'z'; c++) {
                        chars[i] = c;
                        String target = String.valueOf(chars);
                        if (endSet.contains(target)) return level + 1;
                        if (wordSet.remove(target)) {
                            nextSet.add(target);
                        }
                    }
                    chars[i] = old;
                }
            }
            beginSet = nextSet;
            level++;
        }
        return 0;
    }
}
