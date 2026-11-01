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

    /**
     * Pair data structure to verify stability of sorting algorithms.
     */
    public static class Pair {
        public final int key;
        public final String originalIndex;

        public Pair(int key, String originalIndex) {
            this.key = key;
            this.originalIndex = originalIndex;
        }

        @Override
        public String toString() {
            return "(" + key + ", " + originalIndex + ")";
        }
    }

    /**
     * Demonstrates stable sorting on an array of Pairs using Insertion Sort.
     */
    public static void stableInsertionSort(Pair[] arr) {
        for (int i = 1; i < arr.length; i++) {
            Pair key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j].key > key.key) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Testing Basic Sorting Algorithms ===");

        int[] a1 = {64, 34, 25, 12, 22, 11, 90};
        System.out.println("Original: " + Arrays.toString(a1));
        bubbleSortOptimized(a1);
        System.out.println("Bubble Sorted   : " + Arrays.toString(a1));

        int[] a2 = {29, 10, 14, 37, 13};
        System.out.println("\nOriginal: " + Arrays.toString(a2));
        selectionSort(a2);
        System.out.println("Selection Sorted: " + Arrays.toString(a2));

        int[] a3 = {12, 11, 13, 5, 6};
        System.out.println("\nOriginal: " + Arrays.toString(a3));
        insertionSort(a3);
        System.out.println("Insertion Sorted: " + Arrays.toString(a3));

        System.out.println("\n--- Stability Test ---");
        Pair[] pairs = {
            new Pair(5, "first_5"),
            new Pair(3, "only_3"),
            new Pair(5, "second_5"),
            new Pair(2, "only_2")
        };
        System.out.println("Before Stable Sort: " + Arrays.toString(pairs));
        stableInsertionSort(pairs);
        System.out.println("After Stable Sort : " + Arrays.toString(pairs));
    }
}
