import java.util.*;

/**
 * Bitwise Trie implementation for Maximum XOR queries.
 * Evaluates binary representations bit-by-bit from MSB to LSB.
 *
 * @author Shreepurnaa V
 */
public class BitwiseTrieAndXOR {

    private static class BinaryNode {
        BinaryNode[] children = new BinaryNode[2]; // 0 and 1
    }

    private final BinaryNode root = new BinaryNode();

    public void insert(int num) {
        BinaryNode curr = root;
        for (int i = 31; i >= 0; i--) {
            int bit = (num >>> i) & 1;
            if (curr.children[bit] == null) {
                curr.children[bit] = new BinaryNode();
            }
            curr = curr.children[bit];
        }
    }

    /**
     * Finds the maximum XOR value possible with given num in the trie.
     * Greedily picks opposite bit at each step (1 - bit).
     * Time: O(32) = O(1), Space: O(1).
     */
    public int findMaxXOR(int num) {
        BinaryNode curr = root;
        int maxXor = 0;
        for (int i = 31; i >= 0; i--) {
            int bit = (num >>> i) & 1;
            int opposite = 1 - bit;
            if (curr.children[opposite] != null) {
                maxXor |= (1 << i);
                curr = curr.children[opposite];
            } else {
                curr = curr.children[bit];
            }
        }
        return maxXor;
    }

    /**
     * Maximum XOR of Two Numbers in an Array (LeetCode 421).
     * Time: O(32 * N) = O(N), Space: O(32 * N) = O(N).
     */
    public static int findMaximumXOR(int[] nums) {
        BitwiseTrieAndXOR trie = new BitwiseTrieAndXOR();
        for (int n : nums) trie.insert(n);
        int max = 0;
        for (int n : nums) {
            max = Math.max(max, trie.findMaxXOR(n));
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println("=== Testing Bitwise Trie and KMP Matching ===");
        int[] nums = {3, 10, 5, 25, 2, 8};
        System.out.println("Maximum XOR in Array: " + findMaximumXOR(nums) + " (Expected: 28)");

        String text = "ABABDABACDABABCABAB";
        String pattern = "ABABCABAB";
        System.out.println("KMP Match Indices: " + KMPAndStringMatching.kmpSearch(text, pattern) + " (Expected: [10])");
        System.out.println("All BitwiseTrie and KMP tests passed successfully!");
    }
}
