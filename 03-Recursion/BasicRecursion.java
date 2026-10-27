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

    /**
     * Naive recursive Fibonacci computation (LeetCode #509).
     * Time Complexity: O(2^N) exponential
     * Space Complexity: O(N) stack depth
     * 
     * @param n index in Fibonacci sequence
     * @return nth Fibonacci number
     */
    public static int fibonacci(int n) {
        if (n < 0) throw new IllegalArgumentException("Fibonacci undefined for negative: " + n);
        if (n <= 1) return n;
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    /**
     * Memoized Fibonacci (Top-Down Dynamic Programming).
     * Time Complexity: O(N)
     * Space Complexity: O(N) memo array + call stack
     * 
     * @param n index
     * @return nth Fibonacci number
     */
    public static long fibonacciMemo(int n) {
        if (n < 0) throw new IllegalArgumentException("Fibonacci undefined for negative: " + n);
        long[] memo = new long[n + 1];
        Arrays.fill(memo, -1);
        return fibMemoHelper(n, memo);
    }

    private static long fibMemoHelper(int n, long[] memo) {
        if (n <= 1) return n;
        if (memo[n] != -1) return memo[n];
        memo[n] = fibMemoHelper(n - 1, memo) + fibMemoHelper(n - 2, memo);
        return memo[n];
    }

    /**
     * Reverses an array in-place recursively.
     * Time Complexity: O(N)
     * Space Complexity: O(N) call stack
     * 
     * @param arr array to reverse
     */
    public static void reverseArray(int[] arr) {
        if (arr == null || arr.length <= 1) return;
        reverseHelper(arr, 0, arr.length - 1);
    }

    private static void reverseHelper(int[] arr, int left, int right) {
        if (left >= right) return;
        int temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;
        reverseHelper(arr, left + 1, right - 1);
    }

    /**
     * Checks if a string is a palindrome recursively.
     * Time Complexity: O(N)
     * Space Complexity: O(N) call stack
     * 
     * @param str input string
     * @return true if palindrome
     */
    public static boolean isPalindromeRecursive(String str) {
        if (str == null) return false;
        return isPalindromeHelper(str, 0, str.length() - 1);
    }

    private static boolean isPalindromeHelper(String s, int left, int right) {
        if (left >= right) return true;
        if (s.charAt(left) != s.charAt(right)) return false;
        return isPalindromeHelper(s, left + 1, right - 1);
    }

    /**
     * Linear power computation x^n.
     * Time Complexity: O(N)
     * Space Complexity: O(N)
     */
    public static double powerLinear(double x, int n) {
        if (n == 0) return 1.0;
        if (n < 0) return 1.0 / powerLinear(x, -n);
        return x * powerLinear(x, n - 1);
    }

    /**
     * Fast Power - Binary Exponentiation (LeetCode #50: Pow(x, n)).
     * Time Complexity: O(log N)
     * Space Complexity: O(log N) stack frames
     * 
     * Formula:
     * x^n = (x^(n/2))^2        (if n is even)
     * x^n = x * (x^(n/2))^2    (if n is odd)
     * 
     * @param x base
     * @param n exponent
     * @return x^n
     */
    public static double myPow(double x, int n) {
        long exp = n;
        if (exp < 0) {
            x = 1.0 / x;
            exp = -exp;
        }
        return fastPowHelper(x, exp);
    }

    private static double fastPowHelper(double x, long n) {
        if (n == 0) return 1.0;
        double half = fastPowHelper(x, n / 2);
        if (n % 2 == 0) {
            return half * half;
        } else {
            return half * half * x;
        }
    }

    /**
     * Solves the Tower of Hanoi puzzle with n disks.
     * Total moves required: 2^n - 1
     * Time Complexity: O(2^N)
     * Space Complexity: O(N) call stack
     * 
     * @param n number of disks
     * @param fromRod source peg
     * @param toRod destination peg
     * @param auxRod auxiliary peg
     * @param moves record of moves
     */
    public static void towerOfHanoi(int n, char fromRod, char toRod, char auxRod, List<String> moves) {
        if (n == 0) return;
        towerOfHanoi(n - 1, fromRod, auxRod, toRod, moves);
        moves.add("Move disk " + n + " from " + fromRod + " to " + toRod);
        towerOfHanoi(n - 1, auxRod, toRod, fromRod, moves);
    }

    /**
     * Computes sum of first N natural numbers recursively.
     * Formula: N * (N + 1) / 2
     */
    public static long sumOfN(int n) {
        if (n < 0) throw new IllegalArgumentException("N must be non-negative: " + n);
        if (n == 0) return 0;
        return n + sumOfN(n - 1);
    }

    public static void main(String[] args) {
        System.out.println("=== Testing Basic Recursion ===");
        System.out.println("Factorial(5)          : " + factorial(5));
        System.out.println("FactorialTail(5)      : " + factorialTailRecursive(5));
        System.out.println("Fibonacci(7) Naive    : " + fibonacci(7));
        System.out.println("Fibonacci(50) Memo    : " + fibonacciMemo(50));
        System.out.println("Sum of first 100 nums : " + sumOfN(100));

        System.out.println("\n--- Fast Exponentiation (Pow) ---");
        System.out.println("2.0 ^ 10 = " + myPow(2.0, 10));
        System.out.println("2.0 ^ -2 = " + myPow(2.0, -2));

        System.out.println("\n--- Array Reversal ---");
        int[] arr = {1, 2, 3, 4, 5};
        System.out.println("Original: " + Arrays.toString(arr));
        reverseArray(arr);
        System.out.println("Reversed: " + Arrays.toString(arr));

        System.out.println("\n--- Palindrome Check ---");
        System.out.println("racecar: " + isPalindromeRecursive("racecar"));
        System.out.println("hello  : " + isPalindromeRecursive("hello"));

        System.out.println("\n--- Tower of Hanoi (3 Disks) ---");
        List<String> moves = new ArrayList<>();
        towerOfHanoi(3, 'A', 'C', 'B', moves);
        System.out.println("Total moves: " + moves.size());
        for (String m : moves) System.out.println("  " + m);
    }
}
