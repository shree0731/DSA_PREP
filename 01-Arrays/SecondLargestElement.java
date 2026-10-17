import java.util.Arrays;

/**
 * Problem: Second Largest Element in an Array
 */
public class SecondLargestElement {

    /**
     * Approach 1: Sorting Based
     * Time Complexity: O(N log N)
     * Space Complexity: O(N) auxiliary copy
     * 
     * Sorts a copy of the array and traverses backwards from the end
     * to find the first element strictly smaller than the maximum.
     * 
     * @param arr input array
     * @return second largest distinct element, or -1 if none exists
     */
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
}
