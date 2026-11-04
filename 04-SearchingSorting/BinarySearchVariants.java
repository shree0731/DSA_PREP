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

    /**
     * Upper Bound: Smallest index i such that nums[i] > target.
     * If all elements <= target, returns nums.length.
     * Time Complexity: O(log N)
     */
    public static int upperBound(int[] nums, int target) {
        if (nums == null) return 0;
        int low = 0, high = nums.length;

        while (low < high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] > target) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    /**
     * Find First and Last Position of Element in Sorted Array (LeetCode #34).
     * Time Complexity: O(log N)
     * Space Complexity: O(1)
     * 
     * @param nums sorted array
     * @param target target value
     * @return 2-element array [firstIndex, lastIndex] or [-1, -1]
     */
    public static int[] searchRange(int[] nums, int target) {
        int[] result = {-1, -1};
        if (nums == null || nums.length == 0) return result;

        int lb = lowerBound(nums, target);
        if (lb == nums.length || nums[lb] != target) {
            return result; // Target not present
        }

        int ub = upperBound(nums, target);
        result[0] = lb;
        result[1] = ub - 1;
        return result;
    }

    /**
     * Search in Rotated Sorted Array (LeetCode #33: Distinct values).
     * At least one half of the array is always sorted.
     * Time Complexity: O(log N)
     * Space Complexity: O(1)
     */
    public static int searchRotated(int[] nums, int target) {
        if (nums == null || nums.length == 0) return -1;
        int low = 0, high = nums.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] == target) return mid;

            // Check if left half is sorted
            if (nums[low] <= nums[mid]) {
                if (target >= nums[low] && target < nums[mid]) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            } else { // Right half is sorted
                if (target > nums[mid] && target <= nums[high]) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
        }
        return -1;
    }

    /**
     * Search in Rotated Sorted Array II (LeetCode #81: Contains duplicates).
     * When nums[low] == nums[mid] == nums[high], shrink search window.
     * Worst Time: O(N) when all elements are identical
     * Average Time: O(log N)
     */
    public static boolean searchRotatedWithDuplicates(int[] nums, int target) {
        if (nums == null || nums.length == 0) return false;
        int low = 0, high = nums.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] == target) return true;

            // Handle ambiguous boundary duplicates
            if (nums[low] == nums[mid] && nums[mid] == nums[high]) {
                low++;
                high--;
                continue;
            }

            if (nums[low] <= nums[mid]) {
                if (target >= nums[low] && target < nums[mid]) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            } else {
                if (target > nums[mid] && target <= nums[high]) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
        }
        return false;
    }

    /**
     * Find Peak Element (LeetCode #162).
     * An element is a peak if it is strictly greater than its neighbors.
     * Time Complexity: O(log N)
     * Space Complexity: O(1)
     */
    public static int findPeakElement(int[] nums) {
        if (nums == null || nums.length == 0) return -1;
        int low = 0, high = nums.length - 1;

        while (low < high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] > nums[mid + 1]) {
                // Downward slope: peak lies on the left (including mid)
                high = mid;
            } else {
                // Upward slope: peak lies strictly on the right
                low = mid + 1;
            }
        }
        return low;
    }

    public static void main(String[] args) {
        System.out.println("=== Testing Binary Search Variants ===");

        int[] sorted = {1, 3, 3, 5, 7, 8, 9, 9, 9, 11};
        System.out.println("Array: " + Arrays.toString(sorted));
        System.out.println("lowerBound(target=3) : idx = " + lowerBound(sorted, 3)); // 1
        System.out.println("upperBound(target=3) : idx = " + upperBound(sorted, 3)); // 3
        System.out.println("searchRange(target=9): " + Arrays.toString(searchRange(sorted, 9))); // [6, 8]
        System.out.println("searchRange(target=4): " + Arrays.toString(searchRange(sorted, 4))); // [-1, -1]

        System.out.println("\n--- Rotated Array Search ---");
        int[] rotated = {4, 5, 6, 7, 0, 1, 2};
        System.out.println("Rotated: " + Arrays.toString(rotated));
        System.out.println("searchRotated(target=0): idx = " + searchRotated(rotated, 0)); // 4
        System.out.println("searchRotated(target=3): idx = " + searchRotated(rotated, 3)); // -1

        int[] dupRotated = {2, 5, 6, 0, 0, 1, 2};
        System.out.println("searchRotatedWithDuplicates(target=0): " + searchRotatedWithDuplicates(dupRotated, 0)); // true

        System.out.println("\n--- Peak Element ---");
        int[] peakArr = {1, 2, 1, 3, 5, 6, 4};
        System.out.println("Peak in " + Arrays.toString(peakArr) + ": idx = " + findPeakElement(peakArr));
    }
}
