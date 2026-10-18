import java.util.*;

/**
 * Problem: Two Sum (LeetCode #1)
 */
public class TwoSum {

    /**
     * Approach 1: Brute Force
     * Time Complexity: O(N^2)
     * Space Complexity: O(1)
     * 
     * Iterates over all possible pairs (i, j) where i < j and checks
     * if nums[i] + nums[j] == target.
     * 
     * @param nums array of integers
     * @param target sum to match
     * @return 2-element array containing indices [i, j], or empty array if not found
     */
    public static int[] twoSumBruteForce(int[] nums, int target) {
        if (nums == null || nums.length < 2) return new int[0];
        int n = nums.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[0];
    }
}
