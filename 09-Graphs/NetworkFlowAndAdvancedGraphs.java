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

    /**
     * Kosaraju's Algorithm for Strongly Connected Components (SCC).
     * 1. DFS to get finishing time order in stack.
     * 2. Reverse all edges (transpose graph).
     * 3. DFS in stack order on transposed graph.
     * Time: O(V + E), Space: O(V + E).
     */
    public static int countSCC(int v, List<List<Integer>> adj) {
        Deque<Integer> stack = new ArrayDeque<>();
        boolean[] visited = new boolean[v];

        for (int i = 0; i < v; i++) {
            if (!visited[i]) dfsFillStack(i, adj, visited, stack);
        }

        List<List<Integer>> transpose = new ArrayList<>(v);
        for (int i = 0; i < v; i++) transpose.add(new ArrayList<>());
        for (int u = 0; u < v; u++) {
            for (int neighbor : adj.get(u)) transpose.get(neighbor).add(u);
        }

        Arrays.fill(visited, false);
        int sccCount = 0;

        while (!stack.isEmpty()) {
            int u = stack.pop();
            if (!visited[u]) {
                sccCount++;
                dfsCollect(u, transpose, visited);
            }
        }
        return sccCount;
    }

    private static void dfsFillStack(int u, List<List<Integer>> adj, boolean[] visited, Deque<Integer> stack) {
        visited[u] = true;
        for (int v : adj.get(u)) {
            if (!visited[v]) dfsFillStack(v, adj, visited, stack);
        }
        stack.push(u);
    }

    private static void dfsCollect(int u, List<List<Integer>> transpose, boolean[] visited) {
        visited[u] = true;
        for (int v : transpose.get(u)) {
            if (!visited[v]) dfsCollect(v, transpose, visited);
        }
    }
}
