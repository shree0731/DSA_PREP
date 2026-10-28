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
}
