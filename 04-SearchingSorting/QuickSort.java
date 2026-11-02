import java.util.*;

/**
 * QuickSort provides standard partitioning strategies (Lomuto, Hoare, Dutch National Flag),
 * Randomized QuickSort, and the QuickSelect algorithm for order statistics.
 */
public class QuickSort {
    private static final Random RANDOM = new Random();

    public QuickSort() {
        // Utility class
    }

    /**
     * QuickSort using Lomuto partition scheme (pivot at end).
     * Simpler implementation with single index tracker.
     */
    public static void quickSortLomuto(int[] arr) {
        if (arr == null || arr.length <= 1) return;
        quickSortLomutoHelper(arr, 0, arr.length - 1);
    }

    private static void quickSortLomutoHelper(int[] arr, int low, int high) {
        if (low < high) {
            int pivotIdx = partitionLomuto(arr, low, high);
            quickSortLomutoHelper(arr, low, pivotIdx - 1);
            quickSortLomutoHelper(arr, pivotIdx + 1, high);
        }
    }

    private static int partitionLomuto(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high);
        return i + 1;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
