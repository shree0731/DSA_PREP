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
                if (nums[i] + nums[j] == target) return new int[]{i, j};
            }
        }
        return new int[0];
    }

    public static int[] twoSumTwoPass(int[] nums, int target) {
        if (nums == null || nums.length < 2) return new int[0];
        int initialCap = (int) Math.ceil(nums.length / 0.75f) + 1;
        Map<Integer, Integer> map = new HashMap<>(initialCap);
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement) && map.get(complement) != i) {
                return new int[]{i, map.get(complement)};
            }
        }
        return new int[0];
    }

    public static int[] twoSumOptimal(int[] nums, int target) {
        if (nums == null || nums.length < 2) return new int[0];
        // Pre-allocate capacity based on default 0.75 load factor to eliminate dynamic rehashing
        int initialCap = (int) Math.ceil(nums.length / 0.75f) + 1;
        Map<Integer, Integer> map = new HashMap<>(initialCap);

        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[]{map.get(complement), i};
            }
            map.put(nums[i], i);
        }
        return new int[0];
    }

    /**
     * Approach 4: Two-Pointer (Two Sum II - Input array is sorted)
     * Time Complexity: O(N)
     * Space Complexity: O(1)
     * 
     * Uses two converging pointers from the extremes of a sorted array.
     * 
     * @param sortedNums sorted array of integers
     * @param target sum to match
     * @return 1-based indices [left + 1, right + 1] as per LeetCode #167 convention
     */
    public static int[] twoSumSorted(int[] sortedNums, int target) {
        if (sortedNums == null || sortedNums.length < 2) return new int[0];
        int left = 0;
        int right = sortedNums.length - 1;

        while (left < right) {
            int sum = sortedNums[left] + sortedNums[right];
            if (sum == target) {
                return new int[]{left + 1, right + 1}; // 1-based index
            } else if (sum < target) {
                left++;
            } else {
                right--;
            }
        }
        return new int[0];
    }
}
