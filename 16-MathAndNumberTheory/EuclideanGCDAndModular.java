import java.util.*;

/**
 * Euclidean algorithms and modular arithmetic.
 *
 * @author Shreepurnaa V
 */
public class EuclideanGCDAndModular {

    public static long gcd(long a, long b) {
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    public static long lcm(long a, long b) {
        return (a / gcd(a, b)) * b;
    }

    /**
     * Extended Euclidean Algorithm.
     * Finds x, y such that a*x + b*y = gcd(a, b).
     * Returns array [gcd, x, y].
     * Time: O(log(min(a, b))), Space: O(log(min(a, b))).
     */
    public static long[] extendedGCD(long a, long b) {
        if (b == 0) {
            return new long[]{a, 1, 0};
        }
        long[] next = extendedGCD(b, a % b);
        long g = next[0];
        long x1 = next[1];
        long y1 = next[2];

        long x = y1;
        long y = x1 - (a / b) * y1;
        return new long[]{g, x, y};
    }

    /**
     * Fast Modular Exponentiation: (base^exp) % mod.
     * Time: O(log exp), Space: O(1).
     */
    public static long modPow(long base, long exp, long mod) {
        long res = 1;
        base %= mod;
        while (exp > 0) {
            if ((exp & 1) == 1) res = (res * base) % mod;
            base = (base * base) % mod;
            exp >>= 1;
        }
        return res;
    }

    /**
     * Modular Multiplicative Inverse via Fermat's Little Theorem (mod must be prime).
     * a^(mod - 2) % mod.
     */
    public static long modInverse(long a, long mod) {
        return modPow(a, mod - 2, mod);
    }
}
