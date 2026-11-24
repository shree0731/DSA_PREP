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
}
