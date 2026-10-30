import java.util.*;

/**
 * Problem: Combination Sum I (LeetCode #39) & Combination Sum II (LeetCode #40)
 * 
 * Find all unique combinations of numbers in candidates where the candidate numbers
 * sum to target.
 */
public class CombinationSum {

    public CombinationSum() {
        // Utility class
    }

    /**
     * Combination Sum I (LeetCode #39: Unlimited reuse of same element)
     * Time Complexity: O(2^T) where T is target value
     * Space Complexity: O(T / min(candidates)) recursion depth
     * 
     * @param candidates array of distinct positive integers
     * @param target target sum
     * @return all unique combinations
     */
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if (candidates == null) return result;
        backtrackCombSum1(candidates, target, 0, new ArrayList<>(), result);
        return result;
    }

    private static void backtrackCombSum1(int[] candidates, int remaining, int start, List<Integer> current, List<List<Integer>> result) {
        if (remaining == 0) {
            result.add(new ArrayList<>(current));
            return;
        }
        if (remaining < 0) return;

        for (int i = start; i < candidates.length; i++) {
            current.add(candidates[i]);
            // Notice: Pass i (not i + 1) because same element can be reused
            backtrackCombSum1(candidates, remaining - candidates[i], i, current, result);
            current.remove(current.size() - 1);
        }
    }

    /**
     * Optimized Combination Sum I with early loop pruning.
     * By sorting candidates, when candidates[i] > remaining, we can immediately break.
     */
    public static List<List<Integer>> combinationSumPruned(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if (candidates == null) return result;
        Arrays.sort(candidates);
        backtrackCombSum1Pruned(candidates, target, 0, new ArrayList<>(), result);
        return result;
    }

    private static void backtrackCombSum1Pruned(int[] candidates, int remaining, int start, List<Integer> current, List<List<Integer>> result) {
        if (remaining == 0) {
            result.add(new ArrayList<>(current));
            return;
        }

        for (int i = start; i < candidates.length; i++) {
            if (candidates[i] > remaining) break; // Pruning: remaining candidates are strictly larger

            current.add(candidates[i]);
            backtrackCombSum1Pruned(candidates, remaining - candidates[i], i, current, result);
            current.remove(current.size() - 1);
        }
    }

    /**
     * Combination Sum II (LeetCode #40: Each number may only be used once, input has duplicates).
     * Time Complexity: O(2^N)
     * Space Complexity: O(N)
     * 
     * @param candidates array with potential duplicates
     * @param target target sum
     * @return unique combinations
     */
    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if (candidates == null) return result;
        Arrays.sort(candidates);
        backtrackCombSum2(candidates, target, 0, new ArrayList<>(), result);
        return result;
    }

    private static void backtrackCombSum2(int[] candidates, int remaining, int start, List<Integer> current, List<List<Integer>> result) {
        if (remaining == 0) {
            result.add(new ArrayList<>(current));
            return;
        }

        for (int i = start; i < candidates.length; i++) {
            if (candidates[i] > remaining) break; // Pruning
            if (i > start && candidates[i] == candidates[i - 1]) continue; // Skip duplicate at same level

            current.add(candidates[i]);
            // Notice: Pass i + 1 because each element used at most once
            backtrackCombSum2(candidates, remaining - candidates[i], i + 1, current, result);
            current.remove(current.size() - 1);
        }
    }

    /**
     * Combination Sum III (LeetCode #216).
     * Find all valid combinations of k numbers that add up to n such that only numbers
     * 1 through 9 are used and each number is used at most once.
     * 
     * @param k number of elements
     * @param n target sum
     * @return all combinations
     */
    public static List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> result = new ArrayList<>();
        backtrackCombSum3(k, n, 1, new ArrayList<>(), result);
        return result;
    }

    private static void backtrackCombSum3(int k, int remaining, int start, List<Integer> current, List<List<Integer>> result) {
        if (remaining == 0 && current.size() == k) {
            result.add(new ArrayList<>(current));
            return;
        }
        if (current.size() >= k || remaining < 0) return;

        for (int i = start; i <= 9; i++) {
            if (i > remaining) break;
            current.add(i);
            backtrackCombSum3(k, remaining - i, i + 1, current, result);
            current.remove(current.size() - 1);
        }
    }

    /**
     * Counts the total number of combinations that add up to target.
     */
    public static int countCombinationWays(int[] candidates, int target) {
        return combinationSum(candidates, target).size();
    }
}
