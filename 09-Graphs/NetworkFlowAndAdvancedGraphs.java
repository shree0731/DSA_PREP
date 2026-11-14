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

    /**
     * Is Graph Bipartite? (LeetCode 785).
     * Determines whether graph vertices can be partitioned into 2 independent sets using 2-color BFS.
     * Time: O(V + E), Space: O(V).
     */
    public static boolean isBipartite(int[][] graph) {
        int n = graph.length;
        int[] color = new int[n]; // 0 = uncolored, 1 = red, -1 = blue

        for (int i = 0; i < n; i++) {
            if (color[i] != 0) continue;
            Queue<Integer> queue = new LinkedList<>();
            queue.offer(i);
            color[i] = 1;

            while (!queue.isEmpty()) {
                int curr = queue.poll();
                for (int neighbor : graph[curr]) {
                    if (color[neighbor] == 0) {
                        color[neighbor] = -color[curr];
                        queue.offer(neighbor);
                    } else if (color[neighbor] == color[curr]) {
                        return false; // Same color on adjacent vertices!
                    }
                }
            }
        }
        return true;
    }
}
