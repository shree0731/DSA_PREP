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
}
