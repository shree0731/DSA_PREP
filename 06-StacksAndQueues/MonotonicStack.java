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

    /**
     * Daily Temperatures (LeetCode 739).
     * Returns the number of days until a warmer day.
     *
     * Time Complexity: O(N)
     * Space Complexity: O(N)
     */
    public static int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        int[] waitDays = new int[n];
        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && temperatures[stack.peek()] < temperatures[i]) {
                int prevDay = stack.pop();
                waitDays[prevDay] = i - prevDay;
            }
            stack.push(i);
        }
        return waitDays;
    }

    /**
     * Next Smaller Element (Nearest Smaller to Right).
     *
     * Time Complexity: O(N)
     * Space Complexity: O(N)
     */
    public static int[] nextSmallerElement(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        Arrays.fill(result, -1);
        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && nums[stack.peek()] > nums[i]) {
                result[stack.pop()] = nums[i];
            }
            stack.push(i);
        }
        return result;
    }

    /**
     * Largest Rectangle in Histogram (LeetCode 84).
     * Finds the area of the largest rectangle in the histogram.
     *
     * Time Complexity: O(N)
     * Space Complexity: O(N)
     */
    public static int largestRectangleArea(int[] heights) {
        if (heights == null || heights.length == 0) return 0;
        int n = heights.length;
        Deque<Integer> stack = new ArrayDeque<>();
        int maxArea = 0;

        for (int i = 0; i <= n; i++) {
            int currentHeight = (i == n) ? 0 : heights[i];
            while (!stack.isEmpty() && currentHeight < heights[stack.peek()]) {
                int h = heights[stack.pop()];
                int width = stack.isEmpty() ? i : i - stack.peek() - 1;
                maxArea = Math.max(maxArea, h * width);
            }
            stack.push(i);
        }
        return maxArea;
    }

    /**
     * Maximal Rectangle in 2D Binary Matrix (LeetCode 85).
     * Reduces 2D matrix problem to row-by-row histogram evaluation.
     *
     * Time Complexity: O(Rows * Cols)
     * Space Complexity: O(Cols)
     */
    public static int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return 0;
        int cols = matrix[0].length;
        int[] heights = new int[cols];
        int maxArea = 0;

        for (char[] row : matrix) {
            for (int j = 0; j < cols; j++) {
                heights[j] = (row[j] == '1') ? heights[j] + 1 : 0;
            }
            maxArea = Math.max(maxArea, largestRectangleArea(heights));
        }
        return maxArea;
    }

    public static void main(String[] args) {
        System.out.println("=== Testing Next Greater Element I ===");
        int[] nums1 = {4, 1, 2};
        int[] nums2 = {1, 3, 4, 2};
        System.out.println("NGE I: " + Arrays.toString(nextGreaterElementI(nums1, nums2)) + " (Expected: [-1, 3, -1])");

        System.out.println("\n=== Testing Next Greater Element II (Circular) ===");
        int[] circular = {1, 2, 3, 4, 3};
        System.out.println("NGE II: " + Arrays.toString(nextGreaterElementII(circular)) + " (Expected: [2, 3, 4, -1, 4])");

        System.out.println("\n=== Testing Previous Smaller Element ===");
        int[] arr = {4, 5, 2, 10, 8};
        System.out.println("PSE: " + Arrays.toString(previousSmallerElement(arr)) + " (Expected: [-1, 4, -1, 2, 2])");

        System.out.println("\n=== Testing Next Smaller Element ===");
        System.out.println("NSE: " + Arrays.toString(nextSmallerElement(arr)) + " (Expected: [2, 2, -1, 8, -1])");

        System.out.println("\n=== Testing Daily Temperatures ===");
        int[] temps = {73, 74, 75, 71, 69, 72, 76, 73};
        System.out.println("Wait Days: " + Arrays.toString(dailyTemperatures(temps)) + " (Expected: [1, 1, 4, 2, 1, 1, 0, 0])");

        System.out.println("\n=== Testing Largest Rectangle in Histogram ===");
        int[] hist = {2, 1, 5, 6, 2, 3};
        System.out.println("Max Area: " + largestRectangleArea(hist) + " (Expected: 10)");

        System.out.println("\n=== Testing Maximal Rectangle in Matrix ===");
        char[][] matrix = {
            {'1','0','1','0','0'},
            {'1','0','1','1','1'},
            {'1','1','1','1','1'},
            {'1','0','0','1','0'}
        };
        System.out.println("Maximal Rectangle Area: " + maximalRectangle(matrix) + " (Expected: 6)");
        System.out.println("All MonotonicStack tests completed successfully!");
    }
}
