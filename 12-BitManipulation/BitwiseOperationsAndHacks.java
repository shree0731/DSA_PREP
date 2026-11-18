import java.util.*;

/**
 * Fundamental bitwise operations, manipulation hacks, and fast bit operations.
 *
 * @author Shreepurnaa V
 */
public class BitwiseOperationsAndHacks {

    public static boolean getBit(int num, int i) {
        return (num & (1 << i)) != 0;
    }

    public static int setBit(int num, int i) {
        return num | (1 << i);
    }

    public static int clearBit(int num, int i) {
        return num & ~(1 << i);
    }

    public static int updateBit(int num, int i, boolean bitValue) {
        int value = bitValue ? 1 : 0;
        int mask = ~(1 << i);
        return (num & mask) | (value << i);
    }

    /**
     * Brian Kernighan's Algorithm (LeetCode 191).
     * Counts set bits by clearing the lowest set bit in each iteration.
     * Time: O(Number of set bits), Space: O(1).
     */
    public static int countSetBits(int n) {
        int count = 0;
        while (n != 0) {
            n &= (n - 1); // Clears the lowest set bit
            count++;
        }
        return count;
    }

    /**
     * Determines whether n is a power of two.
     * A power of two has exactly one set bit.
     */
    public static boolean isPowerOfTwo(int n) {
        return n > 0 && (n & (n - 1)) == 0;
    }

    /**
     * Isolates the lowest set bit using two's complement identity: n & (-n).
     */
    public static int isolateLowestSetBit(int n) {
        return n & (-n);
    }
}
