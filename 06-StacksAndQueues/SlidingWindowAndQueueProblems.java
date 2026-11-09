import java.util.*;

/**
 * Demonstrates Queue and Deque applications in Sliding Window and Breadth-First Search (BFS).
 * Covers Sliding Window Maximum, First Unique Character in Stream, Rotting Oranges, and Simplify Path.
 *
 * @author Shreepurnaa V
 */
public class SlidingWindowAndQueueProblems {


    /**
     * Sliding Window Maximum (LeetCode 239).
     * Maintains a monotonic decreasing Deque storing indices of nums.
     * Front of deque always holds maximum element index for current window.
     *
     * Time Complexity: O(N)
     * Space Complexity: O(K)
     */
    public static int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || k <= 0) return new int[0];
        int n = nums.length;
        int[] result = new int[n - k + 1];
        Deque<Integer> deque = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            while (!deque.isEmpty() && deque.peekFirst() < i - k + 1) {
                deque.pollFirst();
            }
            while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
                deque.pollLast();
            }
            deque.offerLast(i);
            if (i >= k - 1) {
                result[i - k + 1] = nums[deque.peekFirst()];
            }
        }
        return result;
    }

    /**
     * Finds the first non-repeating character in a stream of characters.
     * Uses a FIFO Queue to preserve arrival order and frequency array for counts.
     *
     * Time Complexity: O(N)
     * Space Complexity: O(1)
     */
    public static String firstNonRepeatingInStream(String stream) {
        StringBuilder result = new StringBuilder();
        int[] count = new int[26];
        Queue<Character> queue = new LinkedList<>();

        for (char ch : stream.toCharArray()) {
            count[ch - 'a']++;
            queue.offer(ch);

            while (!queue.isEmpty() && count[queue.peek() - 'a'] > 1) {
                queue.poll();
            }

            result.append(queue.isEmpty() ? '#' : queue.peek());
        }
        return result.toString();
    }

    /**
     * Rotting Oranges (LeetCode 994).
     * Multi-source BFS using a Queue to simulate minute-by-minute rot propagation.
     *
     * Time Complexity: O(R * C)
     * Space Complexity: O(R * C)
     */
    public static int orangesRotting(int[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        int rows = grid.length;
        int cols = grid[0].length;
        Queue<int[]> queue = new LinkedList<>();
        int freshCount = 0;

        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == 2) {
                    queue.offer(new int[]{r, c});
                } else if (grid[r][c] == 1) {
                    freshCount++;
                }
            }
        }

        if (freshCount == 0) return 0;

        int minutes = 0;
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        while (!queue.isEmpty() && freshCount > 0) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] curr = queue.poll();
                for (int[] dir : directions) {
                    int nr = curr[0] + dir[0];
                    int nc = curr[1] + dir[1];
                    if (nr >= 0 && nr < rows && nc >= 0 && nc < cols && grid[nr][nc] == 1) {
                        grid[nr][nc] = 2;
                        freshCount--;
                        queue.offer(new int[]{nr, nc});
                    }
                }
            }
            minutes++;
        }

        return freshCount == 0 ? minutes : -1;
    }

    /**
     * Simplify Path (LeetCode 71).
     * Resolves absolute Unix-style file path using Deque stack.
     *
     * Time Complexity: O(N)
     * Space Complexity: O(N)
     */
    public static String simplifyPath(String path) {
        Deque<String> stack = new ArrayDeque<>();
        String[] parts = path.split("/");

        for (String part : parts) {
            if (part.isEmpty() || part.equals(".")) {
                continue;
            }
            if (part.equals("..")) {
                if (!stack.isEmpty()) {
                    stack.pollLast();
                }
            } else {
                stack.offerLast(part);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (String dir : stack) {
            sb.append("/").append(dir);
        }
        return sb.length() == 0 ? "/" : sb.toString();
    }

    /**
     * Sliding Window Minimum using Monotonic Increasing Deque.
     *
     * Time Complexity: O(N)
     * Space Complexity: O(K)
     */
    public static int[] minSlidingWindow(int[] nums, int k) {
        if (nums == null || k <= 0) return new int[0];
        int n = nums.length;
        int[] result = new int[n - k + 1];
        Deque<Integer> deque = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            while (!deque.isEmpty() && deque.peekFirst() < i - k + 1) {
                deque.pollFirst();
            }
            while (!deque.isEmpty() && nums[deque.peekLast()] > nums[i]) {
                deque.pollLast();
            }
            deque.offerLast(i);
            if (i >= k - 1) {
                result[i - k + 1] = nums[deque.peekFirst()];
            }
        }
        return result;
    }
}
