import java.util.*;

/**
 * BinarySearchVariants provides classic and advanced binary search patterns:
 * Lower Bound, Upper Bound, First/Last Occurrence, Rotated Sorted Arrays, and Peak Finding.
 */
public class BinarySearchVariants {

    public BinarySearchVariants() {
        // Utility class
    }

    /**
     * Standard Binary Search (LeetCode #704).
     * Time Complexity: O(log N)
     * Space Complexity: O(1)
     */
    public static int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;
        int low = 0, high = nums.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2; // Overflow-safe midpoint
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

    /**
     * Lower Bound: Smallest index i such that nums[i] >= target.
     * If all elements < target, returns nums.length.
     * Time Complexity: O(log N)
     */
    public static int lowerBound(int[] nums, int target) {
        if (nums == null) return 0;
        int low = 0, high = nums.length;

        while (low < high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] >= target) {
                high = mid; // Candidate found, try to find smaller index on left
            } else {
                low = mid + 1;
            }
        }
        return low;
    }
}
