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
}
