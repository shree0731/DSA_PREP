import java.util.*;

/**
 * Foundational Graph representations and traversal algorithms.
 * Implements Adjacency Matrix, Adjacency List, BFS, DFS, and Cycle Detection.
 *
 * @author Shreepurnaa V
 */
public class GraphRepresentationsAndTraversals {

    public static class Graph {
        private final int vertices;
        private final List<List<Integer>> adj;

        public Graph(int vertices) {
            this.vertices = vertices;
            this.adj = new ArrayList<>(vertices);
            for (int i = 0; i < vertices; i++) {
                this.adj.add(new ArrayList<>());
            }
        }

        public void addEdge(int u, int v, boolean directed) {
            adj.get(u).add(v);
            if (!directed) {
                adj.get(v).add(u);
            }
        }

        public int getVertices() {
            return vertices;
        }

        public List<Integer> getNeighbors(int u) {
            return adj.get(u);
        }
    }

    /**
     * Breadth-First Search (BFS) Traversal.
     * Explores neighbors level by level using an explicit FIFO Queue.
     * Time: O(V + E), Space: O(V).
     */
    public static List<Integer> bfs(Graph graph, int start) {
        List<Integer> order = new ArrayList<>();
        boolean[] visited = new boolean[graph.getVertices()];
        Queue<Integer> queue = new LinkedList<>();

        visited[start] = true;
        queue.offer(start);

        while (!queue.isEmpty()) {
            int curr = queue.poll();
            order.add(curr);

            for (int neighbor : graph.getNeighbors(curr)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.offer(neighbor);
                }
            }
        }
        return order;
    }
}
