import java.util.*;

/**
 * Problem: Permutations I (LeetCode #46) & Permutations II (LeetCode #47)
 * 
 * Given an array nums of distinct integers, return all the possible permutations.
 * A permutation is an arrangement of all elements into a sequence or order.
 * Total permutations for N elements: N!
 */
public class Permutations {

    public Permutations() {
        // Utility class
    }

    /**
     * Permutations I using visited boolean array.
     * Time Complexity: O(N! * N)
     * Space Complexity: O(N) visited array + recursion stack
     * 
     * @param nums array of distinct integers
     * @return all possible permutations
     */
    public static List<List<Integer>> permuteVisited(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length == 0) return result;
        boolean[] visited = new boolean[nums.length];
        backtrackPermuteVisited(nums, visited, new ArrayList<>(), result);
        return result;
    }

    private static void backtrackPermuteVisited(int[] nums, boolean[] visited, List<Integer> current, List<List<Integer>> result) {
        if (current.size() == nums.length) {
            result.add(new ArrayList<>(current));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (visited[i]) continue;

            visited[i] = true;
            current.add(nums[i]);
            backtrackPermuteVisited(nums, visited, current, result);
            current.remove(current.size() - 1);
            visited[i] = false;
        }
    }

    /**
     * Permutations I using In-Place Swapping.
     * Eliminates auxiliary boolean visited array and extra list allocations during descent.
     * Time Complexity: O(N! * N)
     * Space Complexity: O(N) stack frames
     * 
     * @param nums array of distinct integers
     * @return all permutations
     */
    public static List<List<Integer>> permuteSwap(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length == 0) return result;
        backtrackSwap(nums, 0, result);
        return result;
    }

    private static void backtrackSwap(int[] nums, int index, List<List<Integer>> result) {
        if (index == nums.length) {
            List<Integer> perm = new ArrayList<>();
            for (int n : nums) perm.add(n);
            result.add(perm);
            return;
        }

        for (int i = index; i < nums.length; i++) {
            swap(nums, index, i);             // Choose: swap element i into current slot
            backtrackSwap(nums, index + 1, result); // Explore
            swap(nums, index, i);             // Unchoose: backtrack swap to restore original array
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * Permutations II (LeetCode #47: May contain duplicates)
     * Sorts array and ensures identical elements are not reused at the same position.
     * Condition: if (visited[i] || (i > 0 && nums[i] == nums[i - 1] && !visited[i - 1])) continue;
     * 
     * Time Complexity: O(N! * N)
     * Space Complexity: O(N)
     * 
     * @param nums array with potential duplicates
     * @return all unique permutations
     */
    public static List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length == 0) return result;
        Arrays.sort(nums); // Group duplicates
        boolean[] visited = new boolean[nums.length];
        backtrackPermuteUnique(nums, visited, new ArrayList<>(), result);
        return result;
    }

    private static void backtrackPermuteUnique(int[] nums, boolean[] visited, List<Integer> current, List<List<Integer>> result) {
        if (current.size() == nums.length) {
            result.add(new ArrayList<>(current));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (visited[i]) continue;
            // If duplicate has not been visited in the current branch, skip it to prevent duplicate branch
            if (i > 0 && nums[i] == nums[i - 1] && !visited[i - 1]) continue;

            visited[i] = true;
            current.add(nums[i]);
            backtrackPermuteUnique(nums, visited, current, result);
            current.remove(current.size() - 1);
            visited[i] = false;
        }
    }
}
