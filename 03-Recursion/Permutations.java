/**
 * @author Shreepurnaa V
 * @version 1.0
 */
import java.util.*;

/**
 * Problem: Permutations I (LeetCode #46) & Permutations II (LeetCode #47)
 * 
 * Given an array nums of distinct integers, return all the possible permutations.
 * A permutation is an arrangement of all elements into a sequence or order.
 * Total permutations for N elements: N!
 */
public class Permutations {

    public Permutations() {
        // Utility class
    }

    /**
     * Permutations I using visited boolean array.
     * Time Complexity: O(N! * N)
     * Space Complexity: O(N) visited array + recursion stack
     * 
     * @param nums array of distinct integers
     * @return all possible permutations
     */
    public static List<List<Integer>> permuteVisited(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length == 0) return result;
        boolean[] visited = new boolean[nums.length];
        backtrackPermuteVisited(nums, visited, new ArrayList<>(), result);
        return result;
    }

    private static void backtrackPermuteVisited(int[] nums, boolean[] visited, List<Integer> current, List<List<Integer>> result) {
        if (current.size() == nums.length) {
            result.add(new ArrayList<>(current));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (visited[i]) continue;

            visited[i] = true;
            current.add(nums[i]);
            backtrackPermuteVisited(nums, visited, current, result);
            current.remove(current.size() - 1);
            visited[i] = false;
        }
    }

    /**
     * Permutations I using In-Place Swapping.
     * Eliminates auxiliary boolean visited array and extra list allocations during descent.
     * Time Complexity: O(N! * N)
     * Space Complexity: O(N) stack frames
     * 
     * @param nums array of distinct integers
     * @return all permutations
     */
    public static List<List<Integer>> permuteSwap(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length == 0) return result;
        backtrackSwap(nums, 0, result);
        return result;
    }

    private static void backtrackSwap(int[] nums, int index, List<List<Integer>> result) {
        if (index == nums.length) {
            List<Integer> perm = new ArrayList<>();
            for (int n : nums) perm.add(n);
            result.add(perm);
            return;
        }

        for (int i = index; i < nums.length; i++) {
            swap(nums, index, i);             // Choose: swap element i into current slot
            backtrackSwap(nums, index + 1, result); // Explore
            swap(nums, index, i);             // Unchoose: backtrack swap to restore original array
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * Permutations II (LeetCode #47: May contain duplicates)
     * Sorts array and ensures identical elements are not reused at the same position.
     * Condition: if (visited[i] || (i > 0 && nums[i] == nums[i - 1] && !visited[i - 1])) continue;
     * 
     * Time Complexity: O(N! * N)
     * Space Complexity: O(N)
     * 
     * @param nums array with potential duplicates
     * @return all unique permutations
     */
    public static List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length == 0) return result;
        Arrays.sort(nums); // Group duplicates
        boolean[] visited = new boolean[nums.length];
        backtrackPermuteUnique(nums, visited, new ArrayList<>(), result);
        return result;
    }

    private static void backtrackPermuteUnique(int[] nums, boolean[] visited, List<Integer> current, List<List<Integer>> result) {
        if (current.size() == nums.length) {
            result.add(new ArrayList<>(current));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (visited[i]) continue;
            // If duplicate has not been visited in the current branch, skip it to prevent duplicate branch
            if (i > 0 && nums[i] == nums[i - 1] && !visited[i - 1]) continue;

            visited[i] = true;
            current.add(nums[i]);
            backtrackPermuteUnique(nums, visited, current, result);
            current.remove(current.size() - 1);
            visited[i] = false;
        }
    }

    /**
     * Generates all permutations of a string in lexicographical order.
     */
    public static List<String> stringPermutations(String str) {
        List<String> result = new ArrayList<>();
        if (str == null || str.length() == 0) return result;
        char[] chars = str.toCharArray();
        Arrays.sort(chars);
        boolean[] visited = new boolean[chars.length];
        backtrackString(chars, visited, new StringBuilder(), result);
        return result;
    }

    private static void backtrackString(char[] chars, boolean[] visited, StringBuilder current, List<String> result) {
        if (current.length() == chars.length) {
            result.add(current.toString());
            return;
        }

        for (int i = 0; i < chars.length; i++) {
            if (visited[i]) continue;
            if (i > 0 && chars[i] == chars[i - 1] && !visited[i - 1]) continue;

            visited[i] = true;
            current.append(chars[i]);
            backtrackString(chars, visited, current, result);
            current.deleteCharAt(current.length() - 1);
            visited[i] = false;
        }
    }

    /**
     * Next Permutation (LeetCode #31).
     * Rearranges numbers into the lexicographically next greater permutation of numbers.
     * Time Complexity: O(N)
     * Space Complexity: O(1)
     */
    public static void nextPermutation(int[] nums) {
        if (nums == null || nums.length <= 1) return;
        int i = nums.length - 2;

        // Step 1: Find first decreasing element from right
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }

        // Step 2: If found, find element just larger than nums[i] from right
        if (i >= 0) {
            int j = nums.length - 1;
            while (nums[j] <= nums[i]) {
                j--;
            }
            swap(nums, i, j);
        }

        // Step 3: Reverse the descending suffix
        reverseRange(nums, i + 1, nums.length - 1);
    }

    private static void reverseRange(int[] nums, int start, int end) {
        while (start < end) {
            swap(nums, start++, end--);
        }
    }

    /**
     * Returns total expected count of permutations for distinct n: n!
     */
    public static long totalPermutationsCount(int n) {
        if (n < 0 || n > 20) throw new IllegalArgumentException("n out of range [0..20]");
        long count = 1;
        for (int i = 2; i <= n; i++) count *= i;
        return count;
    }

    public static void main(String[] args) {
        System.out.println("=== Testing Permutations I (Distinct Elements) ===");
        int[] nums1 = {1, 2, 3};
        System.out.println("Input: " + Arrays.toString(nums1));
        List<List<Integer>> p1 = permuteVisited(nums1);
        System.out.println("Total (Visited approach): " + p1.size());
        System.out.println("Permutations: " + p1);

        System.out.println("\nSwap-based Permutations: " + permuteSwap(nums1));

        System.out.println("\n=== Testing Permutations II (With Duplicates) ===");
        int[] nums2 = {1, 1, 2};
        System.out.println("Input: " + Arrays.toString(nums2));
        List<List<Integer>> p2 = permuteUnique(nums2);
        System.out.println("Total unique: " + p2.size());
        System.out.println("Permutations: " + p2);

        System.out.println("\n=== Testing String Permutations ===");
        System.out.println("ABC: " + stringPermutations("ABC"));

        System.out.println("\n=== Testing Next Permutation ===");
        int[] np = {1, 2, 3};
        System.out.println("Original: " + Arrays.toString(np));
        nextPermutation(np);
        System.out.println("Next: " + Arrays.toString(np));
    }
}
