import java.util.*;

/**
 * Interval scheduling and overlap resolution algorithms.
 *
 * @author Shreepurnaa V
 */
public class IntervalScheduling {

    public static class Interval {
        public int start, end;
        public Interval(int s, int e) { this.start = s; this.end = e; }
    }

    /**
     * Merge Intervals (LeetCode 56).
     * Sorts intervals by start time and merges overlapping contiguous intervals.
     * Time: O(N log N), Space: O(N).
     */
    public static int[][] mergeIntervals(int[][] intervals) {
        if (intervals.length <= 1) return intervals;
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));

        List<int[]> merged = new ArrayList<>();
        int[] curr = intervals[0];
        merged.add(curr);

        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] <= curr[1]) {
                curr[1] = Math.max(curr[1], intervals[i][1]); // Extend current interval
            } else {
                curr = intervals[i];
                merged.add(curr);
            }
        }
        return merged.toArray(new int[merged.size()][]);
    }
}
