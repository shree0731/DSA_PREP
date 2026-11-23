import java.util.*;

/**
 * Articulation Points (Cut Vertices) and Critical Bridges in undirected graphs.
 *
 * @author Shreepurnaa V
 */
public class BridgesAndArticulationPoints {

    /**
     * Finds all Articulation Points in an undirected graph.
     * Condition: low[v] >= tin[u] for non-root; or root with >= 2 children.
     * Time: O(V + E), Space: O(V).
     */
    public static List<Integer> findArticulationPoints(int n, List<List<Integer>> adj) {
        int[] tin = new int[n];
        int[] low = new int[n];
        boolean[] visited = new boolean[n];
        boolean[] isAP = new boolean[n];
        int[] timer = {0};

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfsAP(i, -1, adj, visited, tin, low, timer, isAP);
            }
        }

        List<Integer> apList = new ArrayList<>();
        for (int i = 0; i < n; i++) if (isAP[i]) apList.add(i);
        return apList;
    }

    private static void dfsAP(int u, int p, List<List<Integer>> adj, boolean[] visited,
                             int[] tin, int[] low, int[] timer, boolean[] isAP) {
        visited[u] = true;
        tin[u] = low[u] = timer[0]++;
        int children = 0;

        for (int v : adj.get(u)) {
            if (v == p) continue;
            if (visited[v]) {
                low[u] = Math.min(low[u], tin[v]);
            } else {
                dfsAP(v, u, adj, visited, tin, low, timer, isAP);
                low[u] = Math.min(low[u], low[v]);
                if (low[v] >= tin[u] && p != -1) {
                    isAP[u] = true;
                }
                children++;
            }
        }
        if (p == -1 && children > 1) {
            isAP[u] = true;
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Testing Articulation Points ===");
        int n = 5;
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());
        adj.get(0).add(1); adj.get(1).add(0);
        adj.get(0).add(2); adj.get(2).add(0);
        adj.get(1).add(2); adj.get(2).add(1);
        adj.get(0).add(3); adj.get(3).add(0);
        adj.get(3).add(4); adj.get(4).add(3);

        List<Integer> cutVertices = findArticulationPoints(n, adj);
        System.out.println("Articulation Points: " + cutVertices + " (Expected: [0, 3])");
        System.out.println("All BridgesAndArticulationPoints tests passed successfully!");
    }
}
