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

    public static void main(String[] args) {
        System.out.println("=== Testing Bitwise Operations ===");
        int num = 13; // Binary: 1101
        System.out.println("Num 13 bit 2: " + getBit(num, 2) + " (Expected: true)");
        System.out.println("Num 13 bit 1: " + getBit(num, 1) + " (Expected: false)");
        System.out.println("Set bit 1: " + setBit(num, 1) + " (Expected: 15, binary 1111)");
        System.out.println("Clear bit 2: " + clearBit(num, 2) + " (Expected: 9, binary 1001)");

        System.out.println("Count Set Bits in 13 (1101): " + countSetBits(13) + " (Expected: 3)");
        System.out.println("Is 16 Power of Two? " + isPowerOfTwo(16) + " (Expected: true)");
        System.out.println("Is 18 Power of Two? " + isPowerOfTwo(18) + " (Expected: false)");
        System.out.println("Lowest Set Bit in 12 (1100): " + isolateLowestSetBit(12) + " (Expected: 4)");
        System.out.println("All BitwiseOperationsAndHacks tests passed successfully!");
    }
}
