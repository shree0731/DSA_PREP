import java.util.*;

/**
 * Maximum Flow and Maximum Bipartite Matching algorithms.
 * Implements Edmonds-Karp algorithm with residual graph.
 *
 * @author Shreepurnaa V
 */
public class NetworkFlowAndMatching {

    public static class FlowNetwork {
        private final int n;
        private final int[][] capacity;
        private final List<List<Integer>> adj;

        public FlowNetwork(int n) {
            this.n = n;
            this.capacity = new int[n][n];
            this.adj = new ArrayList<>(n);
            for (int i = 0; i < n; i++) adj.add(new ArrayList<>());
        }

        public void addEdge(int u, int v, int cap) {
            capacity[u][v] += cap;
            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        /**
         * Edmonds-Karp Max Flow algorithm.
         * Time: O(V * E^2), Space: O(V^2).
         */
        public int edmondsKarp(int source, int sink) {
            int maxFlow = 0;
            int[] parent = new int[n];

            while (bfs(source, sink, parent)) {
                int push = Integer.MAX_VALUE;
                for (int v = sink; v != source; v = parent[v]) {
                    int u = parent[v];
                    push = Math.min(push, capacity[u][v]);
                }
                for (int v = sink; v != source; v = parent[v]) {
                    int u = parent[v];
                    capacity[u][v] -= push;
                    capacity[v][u] += push; // Residual back-edge
                }
                maxFlow += push;
            }
            return maxFlow;
        }

        private boolean bfs(int src, int target, int[] parent) {
            Arrays.fill(parent, -1);
            parent[src] = -2;
            Queue<Integer> queue = new LinkedList<>();
            queue.offer(src);

            while (!queue.isEmpty()) {
                int u = queue.poll();
                if (u == target) return true;

                for (int v : adj.get(u)) {
                    if (parent[v] == -1 && capacity[u][v] > 0) {
                        parent[v] = u;
                        queue.offer(v);
                    }
                }
            }
            return false;
        }
    }
}
