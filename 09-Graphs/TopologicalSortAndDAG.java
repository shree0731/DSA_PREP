import java.util.*;

/**
 * Topological sorting and Directed Acyclic Graph (DAG) dependency resolution.
 * Implements Kahn's Algorithm (in-degree BFS), DFS Reverse Postorder, Course Schedule, and Alien Dictionary.
 *
 * @author Shreepurnaa V
 */
public class TopologicalSortAndDAG {


    /**
     * Kahn's Algorithm for Topological Sort using In-Degree Queue.
     * Time: O(V + E), Space: O(V).
     * If output length < vertices, graph contains a cycle!
     */
    public static int[] topoSortKahn(int vertices, List<List<Integer>> adj) {
        int[] inDegree = new int[vertices];
        for (int u = 0; u < vertices; u++) {
            for (int v : adj.get(u)) inDegree[v]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < vertices; i++) {
            if (inDegree[i] == 0) queue.offer(i);
        }

        int[] order = new int[vertices];
        int idx = 0;

        while (!queue.isEmpty()) {
            int u = queue.poll();
            order[idx++] = u;

            for (int v : adj.get(u)) {
                if (--inDegree[v] == 0) {
                    queue.offer(v);
                }
            }
        }

        return idx == vertices ? order : new int[0]; // Empty array if cycle detected
    }

    /**
     * Topological Sort using DFS Reverse Postorder.
     * Vertices pushed to stack after all descendants are visited.
     * Time: O(V + E), Space: O(V).
     */
    public static int[] topoSortDFS(int vertices, List<List<Integer>> adj) {
        boolean[] visited = new boolean[vertices];
        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i < vertices; i++) {
            if (!visited[i]) {
                dfsTopo(i, adj, visited, stack);
            }
        }

        int[] order = new int[vertices];
        int idx = 0;
        while (!stack.isEmpty()) {
            order[idx++] = stack.pop();
        }
        return order;
    }

    private static void dfsTopo(int u, List<List<Integer>> adj, boolean[] visited, Deque<Integer> stack) {
        visited[u] = true;
        for (int v : adj.get(u)) {
            if (!visited[v]) {
                dfsTopo(v, adj, visited, stack);
            }
        }
        stack.push(u); // Reverse postorder
    }
}
