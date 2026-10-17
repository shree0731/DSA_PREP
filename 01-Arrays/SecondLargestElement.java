import java.util.Arrays;

/**
 * Problem: Second Largest Element in an Array
 * 
 * Handles edge cases:
 * 1. null or length < 2 -> returns -1
 * 2. All elements identical (e.g. [7, 7, 7]) -> returns -1
 * 3. Array contains Integer.MIN_VALUE elements -> properly distinguished
 */
public class SecondLargestElement {

    public static int findSecondLargestSorting(int[] arr) {
        if (arr == null || arr.length < 2) return -1;
        int[] copy = arr.clone();
        Arrays.sort(copy);
        int n = copy.length;
        int largest = copy[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            if (copy[i] != largest) return copy[i];
        }
        return -1;
    }

    public static int findSecondLargestTwoPass(int[] arr) {
        if (arr == null || arr.length < 2) return -1;
        int largest = Integer.MIN_VALUE;
        for (int num : arr) {
            if (num > largest) largest = num;
        }
        int secondLargest = Integer.MIN_VALUE;
        for (int num : arr) {
            if (num > secondLargest && num < largest) {
                secondLargest = num;
            }
        }
        return (secondLargest == Integer.MIN_VALUE) ? -1 : secondLargest;
    }

    public static int findSecondLargestOptimal(int[] arr) {
        if (arr == null || arr.length < 2) return -1;

        int largest = Integer.MIN_VALUE;
        int secondLargest = Integer.MIN_VALUE;
        boolean foundSecond = false;

        for (int num : arr) {
            if (num > largest) {
                if (largest != Integer.MIN_VALUE) {
                    secondLargest = largest;
                    foundSecond = true;
                }
                largest = num;
            } else if (num < largest && (num > secondLargest || !foundSecond)) {
                secondLargest = num;
                foundSecond = true;
            }
        }

        return foundSecond ? secondLargest : -1;
    }
}
