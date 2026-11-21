import java.util.*;

/**
 * Binary Indexed Tree (Fenwick Tree).
 * Implements prefix sum queries and point updates using 1-based indexing and lowbit formula: i & (-i).
 * Time: O(log N), Space: O(N).
 *
 * @author Shreepurnaa V
 */
public class FenwickTree {

    private final int[] tree;
    private final int n;

    public FenwickTree(int size) {
        this.n = size;
        this.tree = new int[n + 1];
    }

    public void update(int index, int delta) {
        for (int i = index; i <= n; i += (i & -i)) {
            tree[i] += delta;
        }
    }

    public int queryPrefix(int index) {
        int sum = 0;
        for (int i = index; i > 0; i -= (i & -i)) {
            sum += tree[i];
        }
        return sum;
    }

    public int queryRange(int left, int right) {
        return queryPrefix(right) - queryPrefix(left - 1);
    }
}
