import java.util.*;

/**
 * Prime number generation, primality testing, and prime factorization.
 *
 * @author Shreepurnaa V
 */
public class PrimeNumbersAndSieve {

    /**
     * Sieve of Eratosthenes.
     * Generates all prime numbers up to n.
     * Time: O(N log log N), Space: O(N).
     */
    public static boolean[] sieveOfEratosthenes(int n) {
        boolean[] isPrime = new boolean[n + 1];
        Arrays.fill(isPrime, true);
        if (n >= 0) isPrime[0] = false;
        if (n >= 1) isPrime[1] = false;

        for (int p = 2; p * p <= n; p++) {
            if (isPrime[p]) {
                for (int i = p * p; i <= n; i += p) {
                    isPrime[i] = false;
                }
            }
        }
        return isPrime;
    }
}
