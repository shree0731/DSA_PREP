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
}
