import java.util.*;

/**
 * Demonstrates Monotonic Stack patterns for range boundary queries.
 * Covers Next/Previous Greater/Smaller, Daily Temperatures, and Largest Rectangle in Histogram.
 *
 * @author Shreepurnaa V
 */
public class MonotonicStack {


    /**
     * Next Greater Element I (LeetCode 496).
     * Finds the next greater element for each query in nums1 within nums2.
     *
     * Time Complexity: O(nums1.length + nums2.length)
     * Space Complexity: O(nums2.length)
     */
    public static int[] nextGreaterElementI(int[] nums1, int[] nums2) {
        Map<Integer, Integer> nextGreaterMap = new HashMap<>();
        Deque<Integer> stack = new ArrayDeque<>();

        for (int num : nums2) {
            while (!stack.isEmpty() && stack.peek() < num) {
                nextGreaterMap.put(stack.pop(), num);
            }
            stack.push(num);
        }

        int[] result = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            result[i] = nextGreaterMap.getOrDefault(nums1[i], -1);
        }
        return result;
    }

    /**
     * Next Greater Element II on Circular Array (LeetCode 503).
     * Simulates circular traversal by scanning array twice (indices 0 to 2N-1).
     *
     * Time Complexity: O(N)
     * Space Complexity: O(N)
     */
    public static int[] nextGreaterElementII(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        Arrays.fill(result, -1);
        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i < 2 * n; i++) {
            int current = nums[i % n];
            while (!stack.isEmpty() && nums[stack.peek()] < current) {
                result[stack.pop()] = current;
            }
            if (i < n) {
                stack.push(i);
            }
        }
        return result;
    }

    /**
     * Previous Smaller Element (Nearest Smaller to Left).
     * Returns nearest element to the left smaller than nums[i], or -1.
     *
     * Time Complexity: O(N)
     * Space Complexity: O(N)
     */
    public static int[] previousSmallerElement(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && stack.peek() >= nums[i]) {
                stack.pop();
            }
            result[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(nums[i]);
        }
        return result;
    }
}
