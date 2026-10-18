import java.util.*;

/**
 * Problem: Two Sum (LeetCode #1)
 */
public class TwoSum {

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

    /**
     * Approach 2: Two-Pass Hash Map
     * Time Complexity: O(N)
     * Space Complexity: O(N)
     * 
     * Pass 1: Insert all (value, index) pairs into a hash table.
     * Pass 2: For each element, look up complement (target - nums[i]).
     * 
     * @param nums array of integers
     * @param target sum to match
     * @return indices of the two numbers
     */
    public static int[] twoSumTwoPass(int[] nums, int target) {
        if (nums == null || nums.length < 2) return new int[0];
        Map<Integer, Integer> map = new HashMap<>();

        // Pass 1: Build frequency/index table
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }

        // Pass 2: Query complement
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement) && map.get(complement) != i) {
                return new int[]{i, map.get(complement)};
            }
        }
        return new int[0];
    }
}
