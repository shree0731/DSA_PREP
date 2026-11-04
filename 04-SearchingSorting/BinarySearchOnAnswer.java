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

    /**
     * Koko Eating Bananas (LeetCode #875).
     * Finds minimum integer eating speed k to eat all bananas within h hours.
     * Time Complexity: O(N * log(max(piles)))
     * Space Complexity: O(1)
     */
    public static int minEatingSpeed(int[] piles, int h) {
        int low = 1;
        int high = 0;
        for (int p : piles) high = Math.max(high, p);

        int ans = high;
        while (low <= high) {
            int midSpeed = low + (high - low) / 2;
            if (canEatAll(piles, midSpeed, h)) {
                ans = midSpeed;
                high = midSpeed - 1; // Try to find smaller speed
            } else {
                low = midSpeed + 1;
            }
        }
        return ans;
    }

    private static boolean canEatAll(int[] piles, int speed, int h) {
        long hoursNeeded = 0;
        for (int p : piles) {
            hoursNeeded += (p + speed - 1) / speed; // Ceiling division
        }
        return hoursNeeded <= h;
    }
}
