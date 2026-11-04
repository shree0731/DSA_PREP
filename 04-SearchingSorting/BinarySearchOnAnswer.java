import java.util.*;

/**
 * BinarySearchOnAnswer demonstrates applying binary search over a bounded monotonic answer space
 * rather than a static sorted index array.
 */
public class BinarySearchOnAnswer {

    public BinarySearchOnAnswer() {
        // Utility class
    }

    /**
     * Sqrt(x) (LeetCode #69).
     * Computes integer square root truncated to nearest integer.
     * Time Complexity: O(log X)
     * Space Complexity: O(1)
     */
    public static int mySqrt(int x) {
        if (x <= 1) return x;
        int low = 1, high = x / 2;
        int ans = 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            // Prevent integer overflow with division
            if (mid <= x / mid) {
                ans = mid;
                low = mid + 1; // Look for larger integer
            } else {
                high = mid - 1;
            }
        }
        return ans;
    }
}
