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

    /**
     * Capacity To Ship Packages Within D Days (LeetCode #1011).
     * Finds least weight capacity of ship to transport all packages within days.
     * Search space: [max(weights), sum(weights)]
     */
    public static int shipWithinDays(int[] weights, int days) {
        int maxWeight = 0, sumWeight = 0;
        for (int w : weights) {
            maxWeight = Math.max(maxWeight, w);
            sumWeight += w;
        }

        int low = maxWeight, high = sumWeight;
        int ans = sumWeight;

        while (low <= high) {
            int capacity = low + (high - low) / 2;
            if (canShip(weights, capacity, days)) {
                ans = capacity;
                high = capacity - 1;
            } else {
                low = capacity + 1;
            }
        }
        return ans;
    }

    private static boolean canShip(int[] weights, int capacity, int days) {
        int daysUsed = 1;
        int currentLoad = 0;

        for (int w : weights) {
            if (currentLoad + w > capacity) {
                daysUsed++;
                currentLoad = 0;
            }
            currentLoad += w;
        }
        return daysUsed <= days;
    }

    /**
     * Aggressive Cows / Magnetic Force Between Two Balls (LeetCode #1552).
     * Maximizes minimum distance between m placed cows/balls.
     * Time Complexity: O(N log(max_distance))
     */
    public static int maxDistance(int[] position, int m) {
        Arrays.sort(position);
        int n = position.length;
        int low = 1;
        int high = position[n - 1] - position[0];
        int ans = 1;

        while (low <= high) {
            int midDist = low + (high - low) / 2;
            if (canPlaceBalls(position, m, midDist)) {
                ans = midDist;
                low = midDist + 1; // Try to maximize distance
            } else {
                high = midDist - 1;
            }
        }
        return ans;
    }

    private static boolean canPlaceBalls(int[] position, int m, int minDist) {
        int count = 1;
        int lastPos = position[0];

        for (int i = 1; i < position.length; i++) {
            if (position[i] - lastPos >= minDist) {
                count++;
                lastPos = position[i];
                if (count == m) return true;
            }
        }
        return count >= m;
    }
}
