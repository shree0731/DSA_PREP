import java.util.*;

/**
 * Shortest path algorithms across weighted, unweighted, and negative-weight graphs.
 * Implements Dijkstra, Bellman-Ford, Floyd-Warshall, and Grid Shortest Path.
 *
 * @author Shreepurnaa V
 */
public class ShortestPathAlgorithms {

    public static class Edge {
        public final int to;
        public final int weight;

        public Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    /**
     * Dijkstra's Single-Source Shortest Path Algorithm.
     * Uses a Min-Heap (PriorityQueue) to greedily expand the minimum distance vertex.
     * Requires non-negative edge weights (w >= 0).
     * Time: O((V + E) log V), Space: O(V + E).
     */
    public static int[] dijkstra(int vertices, List<List<Edge>> adj, int source) {
        int[] dist = new int[vertices];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[source] = 0;

        // Min-Heap ordered by current path distance
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        pq.offer(new int[]{source, 0});

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int u = curr[0];
            int d = curr[1];

            if (d > dist[u]) continue; // Stale queue entry

            for (Edge edge : adj.get(u)) {
                int v = edge.to;
                int weight = edge.weight;

                if (dist[u] + weight < dist[v]) {
                    dist[v] = dist[u] + weight;
                    pq.offer(new int[]{v, dist[v]});
                }
            }
        }
        return dist;
    }

    /**
     * Bellman-Ford Algorithm: handles negative edge weights and detects negative weight cycles.
     * Relaxes all edges V - 1 times.
     * Time: O(V * E), Space: O(V).
     */
    public static int[] bellmanFord(int vertices, int[][] edges, int source) {
        int[] dist = new int[vertices];
        Arrays.fill(dist, 100_000_000); // Initial large bound
        dist[source] = 0;

        for (int i = 1; i <= vertices - 1; i++) {
            for (int[] e : edges) {
                int u = e[0], v = e[1], w = e[2];
                if (dist[u] != 100_000_000 && dist[u] + w < dist[v]) {
                    dist[v] = dist[u] + w;
                }
            }
        }

        // N-th relaxation to detect negative weight cycles
        for (int[] e : edges) {
            int u = e[0], v = e[1], w = e[2];
            if (dist[u] != 100_000_000 && dist[u] + w < dist[v]) {
                throw new IllegalStateException("Negative weight cycle detected in graph.");
            }
        }
        return dist;
    }

    /**
     * Floyd-Warshall Algorithm: All-Pairs Shortest Paths via 2D Dynamic Programming.
     * Time: O(V^3), Space: O(V^2).
     */
    public static int[][] floydWarshall(int[][] graph) {
        int v = graph.length;
        int[][] dist = new int[v][v];
        int INF = 100_000_000;

        for (int i = 0; i < v; i++) {
            for (int j = 0; j < v; j++) {
                dist[i][j] = graph[i][j];
            }
        }

        for (int k = 0; k < v; k++) {
            for (int i = 0; i < v; i++) {
                for (int j = 0; j < v; j++) {
                    if (dist[i][k] != INF && dist[k][j] != INF && dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }
        return dist;
    }
}
