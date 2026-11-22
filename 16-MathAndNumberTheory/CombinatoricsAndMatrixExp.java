import java.util.*;

/**
 * Matrix exponentiation for linear recurrences and modular combinatorics.
 *
 * @author Shreepurnaa V
 */
public class CombinatoricsAndMatrixExp {

    public static final long MOD = 1_000_000_007L;

    /**
     * Multiplies two 2x2 matrices modulo MOD.
     */
    public static long[][] multiply(long[][] A, long[][] B) {
        long[][] C = new long[2][2];
        C[0][0] = (A[0][0] * B[0][0] + A[0][1] * B[1][0]) % MOD;
        C[0][1] = (A[0][0] * B[0][1] + A[0][1] * B[1][1]) % MOD;
        C[1][0] = (A[1][0] * B[0][0] + A[1][1] * B[1][0]) % MOD;
        C[1][1] = (A[1][0] * B[0][1] + A[1][1] * B[1][1]) % MOD;
        return C;
    }

    /**
     * Computes N-th Fibonacci number in O(log N) time via Matrix Exponentiation.
     * [[1, 1], [1, 0]]^N
     */
    public static long fibonacci(long n) {
        if (n <= 0) return 0;
        if (n == 1) return 1;

        long[][] res = {{1, 0}, {0, 1}}; // Identity matrix
        long[][] base = {{1, 1}, {1, 0}};
        long p = n - 1;

        while (p > 0) {
            if ((p & 1) == 1) res = multiply(res, base);
            base = multiply(base, base);
            p >>= 1;
        }
        return res[0][0];
    }
}
