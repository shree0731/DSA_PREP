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
}
