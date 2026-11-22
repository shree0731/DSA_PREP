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
}
