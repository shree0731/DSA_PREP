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

    /**
     * Insert Interval (LeetCode 57).
     * Inserts newInterval into sorted non-overlapping intervals and merges if necessary.
     * Time: O(N), Space: O(N).
     */
    public static int[][] insertInterval(int[][] intervals, int[] newInterval) {
        List<int[]> result = new ArrayList<>();
        int i = 0, n = intervals.length;

        // 1. Add all intervals ending before newInterval starts
        while (i < n && intervals[i][1] < newInterval[0]) {
            result.add(intervals[i++]);
        }

        // 2. Merge all overlapping intervals
        while (i < n && intervals[i][0] <= newInterval[1]) {
            newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
            newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
            i++;
        }
        result.add(newInterval);

        // 3. Add remaining intervals
        while (i < n) {
            result.add(intervals[i++]);
        }
        return result.toArray(new int[result.size()][]);
    }
}
