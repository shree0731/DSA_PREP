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

    private static void merge(int[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] leftArr = new int[n1];
        int[] rightArr = new int[n2];

        System.arraycopy(arr, left, leftArr, 0, n1);
        System.arraycopy(arr, mid + 1, rightArr, 0, n2);

        int i = 0, j = 0, k = left;

        while (i < n1 && j < n2) {
            // <= ensures stability (preserves original order for duplicate keys)
            if (leftArr[i] <= rightArr[j]) {
                arr[k++] = leftArr[i++];
            } else {
                arr[k++] = rightArr[j++];
            }
        }

        while (i < n1) arr[k++] = leftArr[i++];
        while (j < n2) arr[k++] = rightArr[j++];
    }

    /**
     * Counts the number of inversions in an array.
     * An inversion is a pair (i, j) such that i < j and arr[i] > arr[j].
     * Time Complexity: O(N log N)
     * Space Complexity: O(N)
     * 
     * @param arr input array
     * @return total inversion count
     */
    public static long countInversions(int[] arr) {
        if (arr == null || arr.length <= 1) return 0;
        int[] copy = arr.clone();
        int[] temp = new int[copy.length];
        return countInversionsHelper(copy, temp, 0, copy.length - 1);
    }

    private static long countInversionsHelper(int[] arr, int[] temp, int left, int right) {
        long invCount = 0;
        if (left < right) {
            int mid = left + (right - left) / 2;
            invCount += countInversionsHelper(arr, temp, left, mid);
            invCount += countInversionsHelper(arr, temp, mid + 1, right);
            invCount += mergeAndCount(arr, temp, left, mid, right);
        }
        return invCount;
    }

    private static long mergeAndCount(int[] arr, int[] temp, int left, int mid, int right) {
        int i = left;
        int j = mid + 1;
        int k = left;
        long invCount = 0;

        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
                // All remaining elements in left subarray [i .. mid] are greater than arr[j]
                invCount += (mid - i + 1);
            }
        }

        while (i <= mid) temp[k++] = arr[i++];
        while (j <= right) temp[k++] = arr[j++];
        System.arraycopy(temp, left, arr, left, right - left + 1);

        return invCount;
    }

    /**
     * Bottom-up Iterative Merge Sort.
     * Eliminates recursive call stack frames by merging pairs of subarrays of size 1, 2, 4, 8...
     * Time Complexity: O(N log N)
     * Space Complexity: O(N)
     */
    public static void mergeSortIterative(int[] arr) {
        if (arr == null || arr.length <= 1) return;
        int n = arr.length;

        for (int currSize = 1; currSize < n; currSize = 2 * currSize) {
            for (int leftStart = 0; leftStart < n - 1; leftStart += 2 * currSize) {
                int mid = Math.min(leftStart + currSize - 1, n - 1);
                int rightEnd = Math.min(leftStart + 2 * currSize - 1, n - 1);
                merge(arr, leftStart, mid, rightEnd);
            }
        }
    }

    /**
     * Verifies if an array is sorted in non-decreasing order.
     */
    public static boolean isSorted(int[] arr) {
        if (arr == null || arr.length <= 1) return true;
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] > arr[i + 1]) return false;
        }
        return true;
    }
}
