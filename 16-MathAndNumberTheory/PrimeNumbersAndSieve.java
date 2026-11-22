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

    /**
     * Segmented Sieve for finding primes in range [L, R].
     * Useful when R is large (up to 10^12) but (R - L + 1) is reasonably small (<= 10^6).
     * Time: O((R - L + 1) log log R), Space: O(R - L + 1).
     */
    public static List<Long> segmentedSieve(long L, long R) {
        int limit = (int) Math.sqrt(R);
        boolean[] basePrime = sieveOfEratosthenes(limit);
        List<Integer> primes = new ArrayList<>();
        for (int i = 2; i <= limit; i++) {
            if (basePrime[i]) primes.add(i);
        }

        int range = (int) (R - L + 1);
        boolean[] isPrimeRange = new boolean[range];
        Arrays.fill(isPrimeRange, true);

        for (int p : primes) {
            long start = Math.max((long) p * p, ((L + p - 1) / p) * p);
            for (long j = start; j <= R; j += p) {
                isPrimeRange[(int) (j - L)] = false;
            }
        }

        if (L == 1) isPrimeRange[0] = false;

        List<Long> result = new ArrayList<>();
        for (int i = 0; i < range; i++) {
            if (isPrimeRange[i]) result.add(L + i);
        }
        return result;
    }

    /**
     * Prime Factorization in O(sqrt(N)) time.
     */
    public static Map<Long, Integer> primeFactorization(long n) {
        Map<Long, Integer> factors = new LinkedHashMap<>();
        for (long d = 2; d * d <= n; d++) {
            if (n % d == 0) {
                int count = 0;
                while (n % d == 0) {
                    count++;
                    n /= d;
                }
                factors.put(d, count);
            }
        }
        if (n > 1) factors.put(n, 1);
        return factors;
    }
}
