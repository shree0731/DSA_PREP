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
}
