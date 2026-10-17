import java.util.Arrays;

/**
 * Problem: Second Largest Element in an Array
 */
public class SecondLargestElement {

    public static int findSecondLargestSorting(int[] arr) {
        if (arr == null || arr.length < 2) {
            return -1;
        }
        int[] copy = arr.clone();
        Arrays.sort(copy);
        int n = copy.length;
        int largest = copy[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            if (copy[i] != largest) {
                return copy[i];
            }
        }
        return -1;
    }

    /**
     * Approach 2: Two-Pass Linear Scan
     * Time Complexity: O(2N) = O(N)
     * Space Complexity: O(1)
     * 
     * Pass 1: Find the absolute maximum element in the array.
     * Pass 2: Find the maximum element strictly smaller than the absolute maximum.
     * 
     * @param arr input array
     * @return second largest distinct element, or -1 if none exists
     */
    public static int findSecondLargestTwoPass(int[] arr) {
        if (arr == null || arr.length < 2) {
            return -1;
        }

        int largest = Integer.MIN_VALUE;
        for (int num : arr) {
            if (num > largest) {
                largest = num;
            }
        }

        int secondLargest = Integer.MIN_VALUE;
        for (int num : arr) {
            if (num > secondLargest && num < largest) {
                secondLargest = num;
            }
        }

        return (secondLargest == Integer.MIN_VALUE) ? -1 : secondLargest;
    }
}
