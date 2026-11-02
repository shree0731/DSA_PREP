import java.util.*;

/**
 * MergeSort provides recursive and iterative divide-and-conquer sorting implementations,
 * alongside inversion counting and memory buffer optimizations.
 */
public class MergeSort {

    public MergeSort() {
        // Utility class
    }

    /**
     * Standard Recursive Merge Sort.
     * Time Complexity: O(N log N) in all cases
     * Space Complexity: O(N) auxiliary
     * Stability: Stable
     * 
     * @param arr array of integers
     */
    public static void mergeSort(int[] arr) {
        if (arr == null || arr.length <= 1) return;
        mergeSortRecursive(arr, 0, arr.length - 1);
    }

    private static void mergeSortRecursive(int[] arr, int left, int right) {
        if (left >= right) return;
        int mid = left + (right - left) / 2;

        mergeSortRecursive(arr, left, mid);
        mergeSortRecursive(arr, mid + 1, right);
        merge(arr, left, mid, right);
    }
}
