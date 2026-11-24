import java.util.*;

/**
 * Rotated sorted array search and median of two sorted arrays.
 *
 * @author Shreepurnaa V
 */
public class RotatedSortedSearchAndMedian {

    /**
     * Search in Rotated Sorted Array (LeetCode 33).
     * Time: O(log N), Space: O(1).
     */
    public static int searchRotated(int[] nums, int target) {
        int low = 0, high = nums.length - 1;
        while (low <= high) {
            int mid = (low + high) >>> 1;
            if (nums[mid] == target) return mid;

            // Left half is sorted
            if (nums[low] <= nums[mid]) {
                if (nums[low] <= target && target < nums[mid]) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            } else { // Right half is sorted
                if (nums[mid] < target && target <= nums[high]) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
        }
        return -1;
    }
}
