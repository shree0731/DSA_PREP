import java.util.*;

/**
 * Segment Tree for range sum queries and point updates.
 * Space: O(4N), Build: O(N), Query: O(log N), Update: O(log N).
 *
 * @author Shreepurnaa V
 */
public class SegmentTree {

    private final int[] tree;
    private final int n;

    public SegmentTree(int[] nums) {
        this.n = nums.length;
        this.tree = new int[4 * n];
        if (n > 0) build(nums, 0, 0, n - 1);
    }

    private void build(int[] nums, int node, int start, int end) {
        if (start == end) {
            tree[node] = nums[start];
            return;
        }
        int mid = (start + end) >>> 1;
        int leftChild = 2 * node + 1;
        int rightChild = 2 * node + 2;
        build(nums, leftChild, start, mid);
        build(nums, rightChild, mid + 1, end);
        tree[node] = tree[leftChild] + tree[rightChild];
    }

    public int queryRange(int left, int right) {
        return query(0, 0, n - 1, left, right);
    }

    private int query(int node, int start, int end, int l, int r) {
        if (r < start || end < l) return 0; // Completely outside
        if (l <= start && end <= r) return tree[node]; // Completely inside

        int mid = (start + end) >>> 1;
        int leftSum = query(2 * node + 1, start, mid, l, r);
        int rightSum = query(2 * node + 2, mid + 1, end, l, r);
        return leftSum + rightSum;
    }

    public void updatePoint(int index, int val) {
        update(0, 0, n - 1, index, val);
    }

    private void update(int node, int start, int end, int idx, int val) {
        if (start == end) {
            tree[node] = val;
            return;
        }
        int mid = (start + end) >>> 1;
        if (idx <= mid) {
            update(2 * node + 1, start, mid, idx, val);
        } else {
            update(2 * node + 2, mid + 1, end, idx, val);
        }
        tree[node] = tree[2 * node + 1] + tree[2 * node + 2];
    }
}
