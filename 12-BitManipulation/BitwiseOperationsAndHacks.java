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
}
