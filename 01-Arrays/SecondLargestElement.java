import java.util.Arrays;

/**
 * Problem: Second Largest Element in an Array
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

    /**
     * Approach 3: Optimal Single-Pass Scan
     * Time Complexity: O(N) in a single traversal
     * Space Complexity: O(1)
     * 
     * Maintains largest and secondLargest concurrently:
     * - If num > largest: secondLargest becomes largest, largest becomes num.
     * - Else if num > secondLargest and num != largest: secondLargest becomes num.
     * 
     * @param arr input array
     * @return second largest distinct element, or -1 if none exists
     */
    public static int findSecondLargestOptimal(int[] arr) {
        if (arr == null || arr.length < 2) {
            return -1;
        }

        int largest = Integer.MIN_VALUE;
        int secondLargest = Integer.MIN_VALUE;

        for (int num : arr) {
            if (num > largest) {
                secondLargest = largest;
                largest = num;
            } else if (num > secondLargest && num != largest) {
                secondLargest = num;
            }
        }

        return (secondLargest == Integer.MIN_VALUE) ? -1 : secondLargest;
    }
}
