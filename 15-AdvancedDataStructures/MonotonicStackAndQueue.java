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
}
