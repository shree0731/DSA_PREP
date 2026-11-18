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
}
