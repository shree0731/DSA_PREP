import java.util.*;

/**
 * Single Number problem variants solved via bit manipulation:
 * 1. Single Number I (LeetCode 136): all elements appear twice except one.
 * 2. Single Number II (LeetCode 137): all elements appear three times except one.
 * 3. Single Number III (LeetCode 260): all elements appear twice except two distinct ones.
 *
 * @author Shreepurnaa V
 */
public class SingleNumberVariants {

    /**
     * Single Number I (LeetCode 136).
     * Solved via XOR: x ^ x = 0, x ^ 0 = x.
     * Time: O(N), Space: O(1).
     */
    public static int singleNumberI(int[] nums) {
        int xor = 0;
        for (int x : nums) xor ^= x;
        return xor;
    }

    /**
     * Single Number II (LeetCode 137).
     * Counts bits across all numbers at each position modulo 3.
     * Time: O(32 * N) = O(N), Space: O(1).
     */
    public static int singleNumberII(int[] nums) {
        int result = 0;
        for (int i = 0; i < 32; i++) {
            int sum = 0;
            for (int x : nums) {
                sum += (x >>> i) & 1;
            }
            if (sum % 3 != 0) {
                result |= (1 << i);
            }
        }
        return result;
    }

    /**
     * Single Number III (LeetCode 260).
     * XORs all numbers to get a ^ b. Then partitions numbers into two groups
     * based on any set bit in a ^ b (e.g. lowest set bit diff = xor & (-xor)).
     * Time: O(N), Space: O(1).
     */
    public static int[] singleNumberIII(int[] nums) {
        int xor = 0;
        for (int x : nums) xor ^= x;

        // Isolate lowest differing bit between the two unique numbers
        int diff = xor & (-xor);

        int a = 0, b = 0;
        for (int x : nums) {
            if ((x & diff) != 0) {
                a ^= x;
            } else {
                b ^= x;
            }
        }
        return new int[]{a, b};
    }
}
