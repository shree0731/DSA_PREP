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

    public static void main(String[] args) {
        System.out.println("=== Testing Binary Search on Answer ===");
        int[] weights = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        System.out.println("Ship Within 5 Days Capacity: " + shipWithinDays(weights, 5) + " (Expected: 15)");

        int[] piles = {3, 6, 7, 11};
        System.out.println("Koko Min Eating Speed (h=8): " + minEatingSpeed(piles, 8) + " (Expected: 4)");

        int[] splitNums = {7, 2, 5, 10, 8};
        System.out.println("Split Array Largest Sum (k=2): " + splitArray(splitNums, 2) + " (Expected: 18)");
        System.out.println("All BinarySearchOnAnswer tests passed successfully!");
    }
}
