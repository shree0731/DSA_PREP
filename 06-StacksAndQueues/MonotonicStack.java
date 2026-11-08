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
}
