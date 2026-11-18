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

    /**
     * Counting Bits (LeetCode 338).
     * DP formula: dp[i] = dp[i >> 1] + (i & 1).
     * Time: O(N), Space: O(N).
     */
    public static int[] countBits(int n) {
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            dp[i] = dp[i >> 1] + (i & 1);
        }
        return dp;
    }

    /**
     * Reverse Bits of a 32-bit unsigned integer (LeetCode 190).
     * Time: O(1), Space: O(1).
     */
    public static int reverseBits(int n) {
        int rev = 0;
        for (int i = 0; i < 32; i++) {
            rev = (rev << 1) | (n & 1);
            n >>>= 1;
        }
        return rev;
    }

    public static void main(String[] args) {
        System.out.println("=== Testing Bitmask and Combinatorics ===");
        int[] nums = {1, 2, 3};
        List<List<Integer>> subsets = generateSubsets(nums);
        System.out.println("Generated " + subsets.size() + " subsets: " + subsets);

        int[] bitCounts = countBits(5);
        System.out.println("Counting Bits for 5: " + Arrays.toString(bitCounts) + " (Expected: [0, 1, 1, 2, 1, 2])");

        int[] singleNums1 = {4, 1, 2, 1, 2};
        System.out.println("Single Number I: " + SingleNumberVariants.singleNumberI(singleNums1) + " (Expected: 4)");

        int[] singleNums2 = {2, 2, 3, 2};
        System.out.println("Single Number II: " + SingleNumberVariants.singleNumberII(singleNums2) + " (Expected: 3)");

        int[] singleNums3 = {1, 2, 1, 3, 2, 5};
        System.out.println("Single Number III: " + Arrays.toString(SingleNumberVariants.singleNumberIII(singleNums3)) + " (Expected: [3, 5])");
        System.out.println("All BitmaskAndCombinatorics tests passed successfully!");
    }
}
