import java.util.*;

/**
 * Bitmask combinatorics, counting bits, and bit-level transformations.
 *
 * @author Shreepurnaa V
 */
public class BitmaskAndCombinatorics {

    /**
     * Subsets Generation using Bitmask (LeetCode 78).
     * Masks from 0 to (2^N - 1) represent all 2^N subsets.
     * Time: O(N * 2^N), Space: O(N * 2^N).
     */
    public static List<List<Integer>> generateSubsets(int[] nums) {
        List<List<Integer>> subsets = new ArrayList<>();
        int n = nums.length;
        int totalSubsets = 1 << n;

        for (int mask = 0; mask < totalSubsets; mask++) {
            List<Integer> sub = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) {
                    sub.add(nums[i]);
                }
            }
            subsets.add(sub);
        }
        return subsets;
    }
}
