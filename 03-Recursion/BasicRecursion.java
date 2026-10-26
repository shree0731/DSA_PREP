import java.util.*;

/**
 * BasicRecursion explores recursive execution flow, call stack mechanics,
 * mathematical recursion, and tail recursion optimizations.
 */
public class BasicRecursion {

    public BasicRecursion() {
        // Utility class
    }

    /**
     * Standard recursive factorial calculation.
     * Time Complexity: O(N)
     * Space Complexity: O(N) call stack
     * 
     * @param n non-negative integer
     * @return n!
     */
    public static long factorial(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Factorial is undefined for negative numbers: " + n);
        }
        if (n <= 1) return 1;
        return n * factorial(n - 1);
    }

    /**
     * Tail-recursive factorial with accumulator.
     * In languages supporting Tail Call Optimization (TCO), this executes in O(1) space.
     * 
     * @param n non-negative integer
     * @return n!
     */
    public static long factorialTailRecursive(int n) {
        return factorialAccumulator(n, 1);
    }

    private static long factorialAccumulator(int n, long acc) {
        if (n < 0) throw new IllegalArgumentException("Negative input: " + n);
        if (n <= 1) return acc;
        return factorialAccumulator(n - 1, n * acc);
    }
}
