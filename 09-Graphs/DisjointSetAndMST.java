import java.util.*;

/**
 * Implements Disjoint Set Union (DSU / Union-Find) and Minimum Spanning Tree algorithms (Kruskal and Prim).
 *
 * @author Shreepurnaa V
 */
public class DisjointSetAndMST {

    public static class DisjointSet {
        private final int[] parent;
        private final int[] rank;
        private final int[] size;

        public DisjointSet(int n) {
            this.parent = new int[n];
            this.rank = new int[n];
            this.size = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }

        public int find(int i) {
            if (parent[i] == i) return i;
            return parent[i] = find(parent[i]); // Path compression
        }

        public boolean unionByRank(int u, int v) {
            int rootU = find(u);
            int rootV = find(v);
            if (rootU == rootV) return false;

            if (rank[rootU] < rank[rootV]) {
                parent[rootU] = rootV;
            } else if (rank[rootV] < rank[rootU]) {
                parent[rootV] = rootU;
            } else {
                parent[rootV] = rootU;
                rank[rootU]++;
            }
            return true;
        }

        public boolean isConnected(int u, int v) {
            return find(u) == find(v);
        }
    }

    /**
     * Kruskal's Algorithm for Minimum Spanning Tree (MST).
     * Sorts edges by weight and greedily connects disjoint sets using DSU.
     * Time: O(E log E), Space: O(V).
     */
    public static int kruskalMST(int vertices, int[][] edges) {
        Arrays.sort(edges, Comparator.comparingInt(a -> a[2]));
        DisjointSet dsu = new DisjointSet(vertices);
        int mstWeight = 0;
        int edgesCount = 0;

        for (int[] e : edges) {
            int u = e[0], v = e[1], w = e[2];
            if (dsu.unionByRank(u, v)) {
                mstWeight += w;
                if (++edgesCount == vertices - 1) break;
            }
        }
        return mstWeight;
    }

    /**
     * Prim's Algorithm for Minimum Spanning Tree (MST) using Min-Heap.
     * Time: O(E log V), Space: O(V + E).
     */
    public static int primMST(int vertices, List<List<int[]>> adj) {
        boolean[] visited = new boolean[vertices];
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a[1])); // [node, weight]
        minHeap.offer(new int[]{0, 0});
        int totalWeight = 0;

        while (!minHeap.isEmpty()) {
            int[] curr = minHeap.poll();
            int u = curr[0];
            int w = curr[1];

            if (visited[u]) continue;
            visited[u] = true;
            totalWeight += w;

            for (int[] edge : adj.get(u)) {
                int next = edge[0];
                int weight = edge[1];
                if (!visited[next]) {
                    minHeap.offer(new int[]{next, weight});
                }
            }
        }
        return totalWeight;
    }

    /**
     * Number of Provinces (LeetCode 547).
     * Solved via Disjoint Set Union.
     */
    public static int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        DisjointSet dsu = new DisjointSet(n);
        int provinces = n;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (isConnected[i][j] == 1 && dsu.unionByRank(i, j)) {
                    provinces--;
                }
            }
        }
        return provinces;
    }
}
