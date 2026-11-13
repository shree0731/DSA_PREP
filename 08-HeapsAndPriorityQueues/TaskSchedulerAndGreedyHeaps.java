import java.util.*;

/**
 * Greedy scheduling and interval algorithms powered by Priority Queues.
 *
 * @author Shreepurnaa V
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
}
