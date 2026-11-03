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

    /**
     * QuickSort using Hoare partition scheme (pivot at start, converging pointers).
     * Makes approximately 3x fewer swaps than Lomuto on average.
     */
    public static void quickSortHoare(int[] arr) {
        if (arr == null || arr.length <= 1) return;
        quickSortHoareHelper(arr, 0, arr.length - 1);
    }

    private static void quickSortHoareHelper(int[] arr, int low, int high) {
        if (low < high) {
            int p = partitionHoare(arr, low, high);
            quickSortHoareHelper(arr, low, p);
            quickSortHoareHelper(arr, p + 1, high);
        }
    }

    private static int partitionHoare(int[] arr, int low, int high) {
        int pivot = arr[low];
        int i = low - 1;
        int j = high + 1;

        while (true) {
            do { i++; } while (arr[i] < pivot);
            do { j--; } while (arr[j] > pivot);
            if (i >= j) return j;
            swap(arr, i, j);
        }
    }

    /**
     * Randomized QuickSort.
     * Selects random pivot to eliminate O(N^2) adversarial worst cases on sorted inputs.
     * Expected Time: O(N log N)
     */
    public static void quickSortRandomized(int[] arr) {
        if (arr == null || arr.length <= 1) return;
        quickSortRandomHelper(arr, 0, arr.length - 1);
    }

    private static void quickSortRandomHelper(int[] arr, int low, int high) {
        if (low < high) {
            int randomIdx = low + RANDOM.nextInt(high - low + 1);
            swap(arr, randomIdx, high);
            int pivotIdx = partitionLomuto(arr, low, high);
            quickSortRandomHelper(arr, low, pivotIdx - 1);
            quickSortRandomHelper(arr, pivotIdx + 1, high);
        }
    }
}
