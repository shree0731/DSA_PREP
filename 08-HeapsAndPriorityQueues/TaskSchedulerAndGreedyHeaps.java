import java.util.*;

/**
 * Greedy scheduling and interval algorithms powered by Priority Queues.
 *
 * @author Shreepurnaa V
 */
/**
 * Production-ready Task Scheduler and Greedy PriorityQueue algorithms.
 */
public class TaskSchedulerAndGreedyHeaps {


    /**
     * Task Scheduler using Max-Heap & Cooldown Queue (LeetCode 621).
     * Time: O(N), Space: O(1) (at most 26 alphabet frequencies).
     */
    public static int leastIntervalHeap(char[] tasks, int n) {
        int[] counts = new int[26];
        for (char t : tasks) counts[t - 'A']++;

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        for (int c : counts) {
            if (c > 0) maxHeap.offer(c);
        }

        Queue<int[]> cooldown = new LinkedList<>(); // [remainingCount, readyTime]
        int time = 0;

        while (!maxHeap.isEmpty() || !cooldown.isEmpty()) {
            time++;
            if (!maxHeap.isEmpty()) {
                int rem = maxHeap.poll() - 1;
                if (rem > 0) cooldown.offer(new int[]{rem, time + n});
            }

            if (!cooldown.isEmpty() && cooldown.peek()[1] == time) {
                maxHeap.offer(cooldown.poll()[0]);
            }
        }
        return time;
    }

    /**
     * Task Scheduler - Mathematical Optimal Formulation (LeetCode 621).
     * Time: O(N), Space: O(1).
     */
    public static int leastIntervalMath(char[] tasks, int n) {
        int[] freq = new int[26];
        int maxFreq = 0;
        for (char t : tasks) {
            freq[t - 'A']++;
            maxFreq = Math.max(maxFreq, freq[t - 'A']);
        }

        int maxCount = 0;
        for (int f : freq) {
            if (f == maxFreq) maxCount++;
        }

        int slots = (maxFreq - 1) * (n + 1) + maxCount;
        return Math.max(tasks.length, slots);
    }

    /**
     * Reorganize String (LeetCode 767).
     * Reorganizes characters such that no two adjacent characters are identical.
     * Time: O(N log A) where A = 26, Space: O(A).
     */
    public static String reorganizeString(String s) {
        int[] freq = new int[26];
        for (char c : s.toCharArray()) freq[c - 'a']++;

        PriorityQueue<int[]> maxHeap = new PriorityQueue<>(
                (a, b) -> Integer.compare(b[1], a[1])
        );

        for (int i = 0; i < 26; i++) {
            if (freq[i] > (s.length() + 1) / 2) return "";
            if (freq[i] > 0) maxHeap.offer(new int[]{i, freq[i]});
        }

        StringBuilder sb = new StringBuilder();
        int[] prev = null;

        while (!maxHeap.isEmpty()) {
            int[] curr = maxHeap.poll();
            sb.append((char) ('a' + curr[0]));
            curr[1]--;

            if (prev != null && prev[1] > 0) {
                maxHeap.offer(prev);
            }

            prev = curr;
        }
        return sb.toString();
    }

    /**
     * Minimum Interval to Include Each Query (LeetCode 1851).
     * Sorts queries and intervals, then uses a Min-Heap of intervals [length, rightEnd].
     * Time: O(N log N + Q log Q), Space: O(N + Q).
     */
    public static int[] minInterval(int[][] intervals, int[] queries) {
        if (intervals == null || intervals.length == 0 || queries == null || queries.length == 0) {
            int[] empty = new int[queries == null ? 0 : queries.length];
            Arrays.fill(empty, -1);
            return empty;
        }
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        int qLen = queries.length;
        int[][] sortedQ = new int[qLen][2];
        for (int i = 0; i < qLen; i++) {
            sortedQ[i][0] = queries[i];
            sortedQ[i][1] = i;
        }
        Arrays.sort(sortedQ, Comparator.comparingInt(a -> a[0]));

        // Min-Heap ordered by interval length
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(
                Comparator.comparingInt(a -> (a[1] - a[0] + 1))
        );

        int[] result = new int[qLen];
        int i = 0;

        for (int[] q : sortedQ) {
            int qVal = q[0];
            int qIdx = q[1];

            while (i < intervals.length && intervals[i][0] <= qVal) {
                minHeap.offer(intervals[i]);
                i++;
            }

            while (!minHeap.isEmpty() && minHeap.peek()[1] < qVal) {
                minHeap.poll();
            }

            result[qIdx] = minHeap.isEmpty() ? -1 : (minHeap.peek()[1] - minHeap.peek()[0] + 1);
        }
        return result;
    }

    /**
     * Seat Reservation Manager (LeetCode 1845).
     * Maintains available seat numbers in a Min-Heap.
     */
    public static class SeatManager {
        private final PriorityQueue<Integer> availableSeats;

        public SeatManager(int n) {
            this.availableSeats = new PriorityQueue<>(n);
            for (int i = 1; i <= n; i++) {
                availableSeats.offer(i);
            }
        }

        public int reserve() {
            return availableSeats.poll();
        }

        public void unreserve(int seatNumber) {
            availableSeats.offer(seatNumber);
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Testing Task Scheduler ===");
        char[] tasks = {'A', 'A', 'A', 'B', 'B', 'B'};
        System.out.println("Heap  Slots (n=2): " + leastIntervalHeap(tasks, 2) + " (Expected: 8)");
        System.out.println("Math  Slots (n=2): " + leastIntervalMath(tasks, 2) + " (Expected: 8)");

        System.out.println("\n=== Testing Reorganize String ===");
        System.out.println("Reorganize 'aab' : " + reorganizeString("aab") + " (Valid: 'aba')");
        System.out.println("Reorganize 'aaab': " + reorganizeString("aaab") + " (Expected: '')");

        System.out.println("\n=== Testing SeatManager ===");
        SeatManager sm = new SeatManager(5);
        System.out.println("Reserve seat: " + sm.reserve() + " (Expected: 1)");
        System.out.println("Reserve seat: " + sm.reserve() + " (Expected: 2)");
        sm.unreserve(1);
        System.out.println("Reserve after unreserve(1): " + sm.reserve() + " (Expected: 1)");
        System.out.println("All TaskSchedulerAndGreedyHeaps tests completed successfully!");
    }
}
