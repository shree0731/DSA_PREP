import java.util.*;

/**
 * BasicSorting provides implementations and behavioral analyses of elementary
 * comparison-based sorting algorithms: Bubble Sort, Selection Sort, and Insertion Sort.
 */
public class BasicSorting {

    public BasicSorting() {
        // Utility class
    }

    /**
     * Standard Bubble Sort.
     * Continuously swaps adjacent inverted elements until array is sorted.
     * Time Complexity: O(N^2)
     * Space Complexity: O(1)
     * 
     * @param arr array of integers
     */
    public static void bubbleSort(int[] arr) {
        if (arr == null || arr.length <= 1) return;
        int n = arr.length;

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    /**
     * Optimized Bubble Sort with early exit flag.
     * If no swaps occur in a pass, the array is already sorted, terminating in O(N) time.
     * Best Time: O(N)
     * Worst Time: O(N^2)
     * Space: O(1)
     * Stability: Stable
     * 
     * @param arr array of integers
     */
    public static void bubbleSortOptimized(int[] arr) {
        if (arr == null || arr.length <= 1) return;
        int n = arr.length;

        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < n - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) break; // Array is sorted!
        }
    }

    /**
     * Selection Sort.
     * Repeatedly identifies the minimum element in unsorted suffix and swaps it to current index.
     * Time Complexity: O(N^2) in all cases (Best, Average, Worst)
     * Space Complexity: O(1)
     * Stability: Unstable (long distance swaps can disrupt relative order of identical keys)
     * 
     * @param arr array of integers
     */
    public static void selectionSort(int[] arr) {
        if (arr == null || arr.length <= 1) return;
        int n = arr.length;

        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIdx]) {
                    minIdx = j;
                }
            }
            if (minIdx != i) {
                int temp = arr[i];
                arr[i] = arr[minIdx];
                arr[minIdx] = temp;
            }
        }
    }

    /**
     * Insertion Sort.
     * Builds sorted prefix one element at a time by shifting larger elements right.
     * Best Time: O(N) when array is already sorted or nearly sorted
     * Worst Time: O(N^2) reverse sorted array
     * Space: O(1)
     * Stability: Stable
     * 
     * @param arr array of integers
     */
    public static void insertionSort(int[] arr) {
        if (arr == null || arr.length <= 1) return;
        int n = arr.length;

        for (int i = 1; i < n; i++) {
            int key = arr[i];
            int j = i - 1;

            // Shift elements of arr[0..i-1] that are greater than key to one position ahead
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }
}
