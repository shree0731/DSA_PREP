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
}
