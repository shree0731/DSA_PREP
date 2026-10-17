import java.util.Arrays;

/**
 * Problem: Second Largest and Second Smallest Elements in an Array
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

    public static int findSecondSmallest(int[] arr) {
        if (arr == null || arr.length < 2) return -1;
        int smallest = Integer.MAX_VALUE;
        int secondSmallest = Integer.MAX_VALUE;
        boolean foundSecond = false;

        for (int num : arr) {
            if (num < smallest) {
                if (smallest != Integer.MAX_VALUE) {
                    secondSmallest = smallest;
                    foundSecond = true;
                }
                smallest = num;
            } else if (num > smallest && (num < secondSmallest || !foundSecond)) {
                secondSmallest = num;
                foundSecond = true;
            }
        }
        return foundSecond ? secondSmallest : -1;
    }

    /**
     * Finds both second smallest and second largest in a single traversal.
     * 
     * @param arr input array
     * @return 2-element array [secondSmallest, secondLargest]
     */
    public static int[] findSecondSmallestAndLargest(int[] arr) {
        return new int[]{findSecondSmallest(arr), findSecondLargestOptimal(arr)};
    }
}
