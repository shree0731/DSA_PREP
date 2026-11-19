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

    /**
     * Non-overlapping Intervals (LeetCode 435).
     * Minimum number of intervals to remove to make the rest non-overlapping.
     * Greedily keeps interval with earliest end time.
     * Time: O(N log N), Space: O(1).
     */
    public static int eraseOverlapIntervals(int[][] intervals) {
        if (intervals.length <= 1) return 0;
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[1]));

        int removals = 0;
        int prevEnd = intervals[0][1];

        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] < prevEnd) {
                removals++; // Overlap detected; remove interval with later end time
            } else {
                prevEnd = intervals[i][1];
            }
        }
        return removals;
    }

    /**
     * Minimum Number of Arrows to Burst Balloons (LeetCode 452).
     * Time: O(N log N), Space: O(1).
     */
    public static int findMinArrowShots(int[][] points) {
        if (points.length == 0) return 0;
        // Use Integer.compare to avoid 32-bit subtraction overflow
        Arrays.sort(points, (a, b) -> Integer.compare(a[1], b[1]));

        int arrows = 1;
        int arrowPos = points[0][1];

        for (int i = 1; i < points.length; i++) {
            if (points[i][0] > arrowPos) {
                arrows++;
                arrowPos = points[i][1];
            }
        }
        return arrows;
    }
}
