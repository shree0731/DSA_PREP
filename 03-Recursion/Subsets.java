import java.util.*;

/**
 * Problem: Subsets I (LeetCode #78) & Subsets II (LeetCode #90)
 * 
 * Given an integer array nums that may or may not contain duplicates,
 * return all possible subsets (the power set).
 * The solution set must not contain duplicate subsets.
 */
public class Subsets {

    public Subsets() {
        // Utility class
    }

    /**
     * Subsets I (Pick / Don't Pick Pattern)
     * At each index, decide whether to include nums[index] in current subset.
     * Time Complexity: O(2^N * N)
     * Space Complexity: O(N) recursion stack
     * 
     * @param nums array of unique integers
     * @return all possible subsets
     */
    public static List<List<Integer>> subsetsIncludeExclude(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null) return result;
        includeExcludeHelper(nums, 0, new ArrayList<>(), result);
        return result;
    }

    private static void includeExcludeHelper(int[] nums, int index, List<Integer> current, List<List<Integer>> result) {
        if (index == nums.length) {
            result.add(new ArrayList<>(current));
            return;
        }

        // Choice 1: Include nums[index]
        current.add(nums[index]);
        includeExcludeHelper(nums, index + 1, current, result);

        // Backtrack: Remove choice
        current.remove(current.size() - 1);

        // Choice 2: Exclude nums[index]
        includeExcludeHelper(nums, index + 1, current, result);
    }

    /**
     * Subsets I (Looping Backtracking Pattern)
     * Iterates over start index, adding current subset at each step of the tree.
     * Time Complexity: O(2^N * N)
     * Space Complexity: O(N)
     * 
     * @param nums array of unique integers
     * @return all possible subsets
     */
    public static List<List<Integer>> subsetsLoop(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null) return result;
        backtrackSubsets(nums, 0, new ArrayList<>(), result);
        return result;
    }

    private static void backtrackSubsets(int[] nums, int start, List<Integer> current, List<List<Integer>> result) {
        result.add(new ArrayList<>(current));

        for (int i = start; i < nums.length; i++) {
            current.add(nums[i]);                         // Choose
            backtrackSubsets(nums, i + 1, current, result); // Explore
            current.remove(current.size() - 1);           // Unchoose
        }
    }

    /**
     * Subsets I (Bit Manipulation Power Set)
     * An array of length N has 2^N subsets, mapped 1-to-1 with binary numbers 0 to (2^N - 1).
     * Time Complexity: O(2^N * N)
     * Space Complexity: O(1) auxiliary
     */
    public static List<List<Integer>> subsetsBitwise(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null) return result;
        int n = nums.length;
        int totalSubsets = 1 << n; // 2^n

        for (int mask = 0; mask < totalSubsets; mask++) {
            List<Integer> subset = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) {
                    subset.add(nums[i]);
                }
            }
            result.add(subset);
        }
        return result;
    }

    /**
     * Subsets II (LeetCode #90: Input array contains duplicates)
     * Sorts array and skips identical elements at the same tree depth:
     * if (i > start && nums[i] == nums[i - 1]) continue;
     * 
     * Time Complexity: O(2^N * N)
     * Space Complexity: O(N)
     * 
     * @param nums array that may contain duplicates
     * @return all unique subsets
     */
    public static List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null) return result;
        Arrays.sort(nums); // Critical step: group duplicates together
        backtrackSubsetsWithDup(nums, 0, new ArrayList<>(), result);
        return result;
    }

    private static void backtrackSubsetsWithDup(int[] nums, int start, List<Integer> current, List<List<Integer>> result) {
        result.add(new ArrayList<>(current));

        for (int i = start; i < nums.length; i++) {
            // Skip duplicates at same recursion level
            if (i > start && nums[i] == nums[i - 1]) continue;

            current.add(nums[i]);
            backtrackSubsetsWithDup(nums, i + 1, current, result);
            current.remove(current.size() - 1);
        }
    }

    /**
     * Determines whether any subset of nums sums up to target.
     * Time Complexity: O(2^N)
     * Space Complexity: O(N)
     */
    public static boolean hasSubsetSum(int[] nums, int target) {
        if (nums == null) return false;
        return subsetSumHelper(nums, 0, target);
    }

    private static boolean subsetSumHelper(int[] nums, int index, int target) {
        if (target == 0) return true;
        if (index >= nums.length || target < 0) return false;
        // Either include nums[index] or exclude it
        return subsetSumHelper(nums, index + 1, target - nums[index]) ||
               subsetSumHelper(nums, index + 1, target);
    }
}
