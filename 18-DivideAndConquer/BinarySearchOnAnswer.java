import java.util.*;

/**
 * Binary Search on Answer (Feasibility Predicate Optimization).
 *
 * @author Shreepurnaa V
 */
public class BinarySearchOnAnswer {

    /**
     * Capacity To Ship Packages Within D Days (LeetCode 1011).
     * Time: O(N log(Sum - Max)), Space: O(1).
     */
    public static int shipWithinDays(int[] weights, int days) {
        int low = 0, high = 0;
        for (int w : weights) {
            low = Math.max(low, w);
            high += w;
        }

        while (low < high) {
            int mid = (low + high) >>> 1;
            if (canShip(weights, days, mid)) {
                high = mid; // Try smaller capacity
            } else {
                low = mid + 1; // Need higher capacity
            }
        }
        return low;
    }

    private static boolean canShip(int[] weights, int days, int capacity) {
        int neededDays = 1, currentLoad = 0;
        for (int w : weights) {
            if (currentLoad + w > capacity) {
                neededDays++;
                currentLoad = 0;
            }
            currentLoad += w;
        }
        return neededDays <= days;
    }

    /**
     * Koko Eating Bananas (LeetCode 875).
     * Time: O(N log(MaxPile)), Space: O(1).
     */
    public static int minEatingSpeed(int[] piles, int h) {
        int low = 1, high = 1;
        for (int p : piles) high = Math.max(high, p);

        while (low < high) {
            int speed = (low + high) >>> 1;
            int hours = 0;
            for (int p : piles) {
                hours += (p + speed - 1) / speed;
            }
            if (hours <= h) {
                high = speed;
            } else {
                low = speed + 1;
            }
        }
        return low;
    }

    /**
     * Split Array Largest Sum (LeetCode 410).
     */
    public static int splitArray(int[] nums, int k) {
        return shipWithinDays(nums, k);
    }
}
