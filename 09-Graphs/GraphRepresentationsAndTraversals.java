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

    /**
     * Depth-First Search (DFS) Traversal.
     * Explores as deep as possible along each branch before backtracking.
     * Time: O(V + E), Space: O(V) call stack.
     */
    public static List<Integer> dfs(Graph graph, int start) {
        List<Integer> order = new ArrayList<>();
        boolean[] visited = new boolean[graph.getVertices()];
        dfsRecursive(graph, start, visited, order);
        return order;
    }

    private static void dfsRecursive(Graph graph, int curr, boolean[] visited, List<Integer> order) {
        visited[curr] = true;
        order.add(curr);

        for (int neighbor : graph.getNeighbors(curr)) {
            if (!visited[neighbor]) {
                dfsRecursive(graph, neighbor, visited, order);
            }
        }
    }

    /**
     * Undirected Graph Cycle Detection using BFS.
     * Tracks the parent node that led to each vertex to distinguish tree edges from cross/back edges.
     * Time: O(V + E), Space: O(V).
     */
    public static boolean hasCycleUndirected(Graph graph) {
        int v = graph.getVertices();
        boolean[] visited = new boolean[v];

        for (int i = 0; i < v; i++) {
            if (!visited[i]) {
                if (bfsCycleCheck(graph, i, visited)) return true;
            }
        }
        return false;
    }

    private static boolean bfsCycleCheck(Graph graph, int src, boolean[] visited) {
        Queue<int[]> queue = new LinkedList<>(); // [current, parent]
        visited[src] = true;
        queue.offer(new int[]{src, -1});

        while (!queue.isEmpty()) {
            int[] pair = queue.poll();
            int curr = pair[0];
            int parent = pair[1];

            for (int neighbor : graph.getNeighbors(curr)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.offer(new int[]{neighbor, curr});
                } else if (neighbor != parent) {
                    return true; // Visited node that is NOT the immediate parent -> cycle!
                }
            }
        }
        return false;
    }

    /**
     * Directed Graph Cycle Detection using 3-Color DFS.
     * 0 = WHITE (unvisited), 1 = GRAY (currently in recursion stack), 2 = BLACK (fully processed).
     * Time: O(V + E), Space: O(V).
     */
    public static boolean hasCycleDirected(Graph graph) {
        int v = graph.getVertices();
        int[] color = new int[v]; // 0: White, 1: Gray, 2: Black

        for (int i = 0; i < v; i++) {
            if (color[i] == 0) {
                if (dfsColorCheck(graph, i, color)) return true;
            }
        }
        return false;
    }

    private static boolean dfsColorCheck(Graph graph, int curr, int[] color) {
        color[curr] = 1; // Mark as currently exploring (GRAY)

        for (int neighbor : graph.getNeighbors(curr)) {
            if (color[neighbor] == 1) {
                return true; // Found back-edge to an active ancestor on recursion stack!
            }
            if (color[neighbor] == 0 && dfsColorCheck(graph, neighbor, color)) {
                return true;
            }
        }

        color[curr] = 2; // Mark as fully visited (BLACK)
        return false;
    }

    /**
     * Connected Components Count in an Undirected Graph.
     * Uses BFS traversal to discover all disconnected subgraphs.
     * Time: O(V + E), Space: O(V).
     */
    public static int countConnectedComponents(Graph graph) {
        int v = graph.getVertices();
        boolean[] visited = new boolean[v];
        int count = 0;

        for (int i = 0; i < v; i++) {
            if (!visited[i]) {
                count++;
                Queue<Integer> queue = new LinkedList<>();
                visited[i] = true;
                queue.offer(i);

                while (!queue.isEmpty()) {
                    int curr = queue.poll();
                    for (int neighbor : graph.getNeighbors(curr)) {
                        if (!visited[neighbor]) {
                            visited[neighbor] = true;
                            queue.offer(neighbor);
                        }
                    }
                }
            }
        }
        return count;
    }
}
