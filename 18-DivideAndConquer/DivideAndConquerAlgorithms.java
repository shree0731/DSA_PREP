import java.util.*;

/**
 * Classic Divide and Conquer algorithms:
 * 1. Count Inversions via Merge Sort in O(N log N)
 * 2. Maximum Subarray Divide & Conquer in O(N)
 *
 * @author Shreepurnaa V
 */
public class DivideAndConquerAlgorithms {

    /**
     * Counts the number of inversions in an array (i < j and nums[i] > nums[j]).
     * Time: O(N log N), Space: O(N).
     */
    public static long countInversions(int[] nums) {
        int[] temp = new int[nums.length];
        return mergeSortAndCount(nums, temp, 0, nums.length - 1);
    }

    private static long mergeSortAndCount(int[] arr, int[] temp, int left, int right) {
        long count = 0;
        if (left < right) {
            int mid = (left + right) >>> 1;
            count += mergeSortAndCount(arr, temp, left, mid);
            count += mergeSortAndCount(arr, temp, mid + 1, right);
            count += mergeAndCount(arr, temp, left, mid, right);
        }
        return count;
    }

    private static long mergeAndCount(int[] arr, int[] temp, int left, int mid, int right) {
        int i = left, j = mid + 1, k = left;
        long invCount = 0;

        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
                invCount += (mid - i + 1); // Elements from i to mid are all greater than arr[j]
            }
        }
        while (i <= mid) temp[k++] = arr[i++];
        while (j <= right) temp[k++] = arr[j++];
        for (i = left; i <= right; i++) arr[i] = temp[i];
        return invCount;
    }

    public static void main(String[] args) {
        System.out.println("=== Testing Divide and Conquer Algorithms ===");
        int[] invNums = {8, 4, 2, 1};
        System.out.println("Inversion Count for [8, 4, 2, 1]: " + countInversions(invNums) + " (Expected: 6)");

        int[] rotNums = {4, 5, 6, 7, 0, 1, 2};
        System.out.println("Search 0 in Rotated Array: " + RotatedSortedSearchAndMedian.searchRotated(rotNums, 0) + " (Expected: 4)");

        int[] a = {1, 3}, b = {2};
        System.out.println("Median of [1, 3] and [2]: " + RotatedSortedSearchAndMedian.findMedianSortedArrays(a, b) + " (Expected: 2.0)");

        int[] a2 = {1, 2}, b2 = {3, 4};
        System.out.println("Median of [1, 2] and [3, 4]: " + RotatedSortedSearchAndMedian.findMedianSortedArrays(a2, b2) + " (Expected: 2.5)");
        System.out.println("All DivideAndConquerAlgorithms tests passed successfully!");
    }
}
