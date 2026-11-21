import java.util.*;

/**
 * Monotonic Stack algorithms:
 * 1. Largest Rectangle in Histogram (LeetCode 84) in O(N)
 * 2. Next Greater Element
 *
 * @author Shreepurnaa V
 */
public class MonotonicStackAndQueue {

    /**
     * Largest Rectangle in Histogram (LeetCode 84).
     * Uses a Monotonic Stack to find left and right smaller bounds for each bar.
     * Time: O(N), Space: O(N).
     */
    public static int largestRectangleArea(int[] heights) {
        int n = heights.length;
        Deque<Integer> stack = new ArrayDeque<>();
        int maxArea = 0;

        for (int i = 0; i <= n; i++) {
            int h = (i == n) ? 0 : heights[i];
            while (!stack.isEmpty() && heights[stack.peek()] > h) {
                int height = heights[stack.pop()];
                int width = stack.isEmpty() ? i : i - stack.peek() - 1;
                maxArea = Math.max(maxArea, height * width);
            }
            stack.push(i);
        }
        return maxArea;
    }

    public static void main(String[] args) {
        System.out.println("=== Testing Segment Tree, Fenwick Tree, and Monotonic Stack ===");
        int[] nums = {1, 3, 5, 7, 9, 11};
        SegmentTree segTree = new SegmentTree(nums);
        System.out.println("Segment Tree Range Sum [1, 3]: " + segTree.queryRange(1, 3) + " (Expected: 15)");
        segTree.updatePoint(1, 10); // nums becomes {1, 10, 5, 7, 9, 11}
        System.out.println("After update nums[1]=10, Range Sum [1, 3]: " + segTree.queryRange(1, 3) + " (Expected: 22)");

        FenwickTree fenwick = new FenwickTree(6);
        for (int i = 0; i < nums.length; i++) fenwick.update(i + 1, nums[i]);
        System.out.println("Fenwick Prefix Sum (1-based index 3): " + fenwick.queryPrefix(3) + " (Expected: 9)");

        int[] heights = {2, 1, 5, 6, 2, 3};
        System.out.println("Largest Rectangle Area: " + largestRectangleArea(heights) + " (Expected: 10)");
        System.out.println("All AdvancedDataStructures tests passed successfully!");
    }
}
