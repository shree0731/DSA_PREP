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

    /**
     * Tarjan's Algorithm for Critical Connections / Bridges (LeetCode 1192).
     * Identifies edges whose removal disconnects the graph using discovery time (tin) and lowest link (low).
     * Condition for bridge (u, v): low[v] > tin[u].
     * Time: O(V + E), Space: O(V + E).
     */
    public static List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());
        for (List<Integer> edge : connections) {
            adj.get(edge.get(0)).add(edge.get(1));
            adj.get(edge.get(1)).add(edge.get(0));
        }

        int[] tin = new int[n];
        int[] low = new int[n];
        boolean[] visited = new boolean[n];
        List<List<Integer>> bridges = new ArrayList<>();
        int[] timer = {0};

        dfsBridge(0, -1, adj, visited, tin, low, timer, bridges);
        return bridges;
    }

    private static void dfsBridge(int u, int p, List<List<Integer>> adj, boolean[] visited,
                                  int[] tin, int[] low, int[] timer, List<List<Integer>> bridges) {
        visited[u] = true;
        tin[u] = low[u] = timer[0]++;

        for (int v : adj.get(u)) {
            if (v == p) continue;
            if (visited[v]) {
                low[u] = Math.min(low[u], tin[v]);
            } else {
                dfsBridge(v, u, adj, visited, tin, low, timer, bridges);
                low[u] = Math.min(low[u], low[v]);
                if (low[v] > tin[u]) {
                    bridges.add(Arrays.asList(u, v));
                }
            }
        }
    }
}
