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
}
