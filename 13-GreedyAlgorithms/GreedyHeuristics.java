import java.util.*;

/**
 * Greedy heuristics and ratio-based optimizations:
 * 1. Fractional Knapsack
 * 2. Assign Cookies (LeetCode 455)
 *
 * @author Shreepurnaa V
 */
public class GreedyHeuristics {

    public static class Item {
        public final int weight, value;
        public final double ratio;
        public Item(int w, int v) {
            this.weight = w;
            this.value = v;
            this.ratio = (double) v / w;
        }
    }

    /**
     * Fractional Knapsack.
     * Greedily picks items with highest value-to-weight ratio.
     * Time: O(N log N), Space: O(N).
     */
    public static double fractionalKnapsack(int[] weights, int[] values, int capacity) {
        int n = weights.length;
        Item[] items = new Item[n];
        for (int i = 0; i < n; i++) items[i] = new Item(weights[i], values[i]);

        Arrays.sort(items, (a, b) -> Double.compare(b.ratio, a.ratio)); // Descending ratio

        double totalValue = 0.0;
        int currentCap = capacity;

        for (Item item : items) {
            if (currentCap >= item.weight) {
                totalValue += item.value;
                currentCap -= item.weight;
            } else {
                totalValue += item.ratio * currentCap;
                break;
            }
        }
        return totalValue;
    }
}
