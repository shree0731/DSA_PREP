import java.util.*;

/**
 * Problem: Two Sum (LeetCode #1)
 */
public class TwoSum {

    public static int[] twoSumBruteForce(int[] nums, int target) {
        if (nums == null || nums.length < 2) return new int[0];
        int n = nums.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (nums[i] + nums[j] == target) return new int[]{i, j};
            }
        }
        return new int[0];
    }

    public static int[] twoSumTwoPass(int[] nums, int target) {
        if (nums == null || nums.length < 2) return new int[0];
        int initialCap = (int) Math.ceil(nums.length / 0.75f) + 1;
        Map<Integer, Integer> map = new HashMap<>(initialCap);
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement) && map.get(complement) != i) {
                return new int[]{i, map.get(complement)};
            }
        }
        return new int[0];
    }

    public static int[] twoSumOptimal(int[] nums, int target) {
        if (nums == null || nums.length < 2) return new int[0];
        // Pre-allocate capacity based on default 0.75 load factor to eliminate dynamic rehashing
        int initialCap = (int) Math.ceil(nums.length / 0.75f) + 1;
        Map<Integer, Integer> map = new HashMap<>(initialCap);

        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[]{map.get(complement), i};
            }
            map.put(nums[i], i);
        }
        return new int[0];
    }

    /**
     * Approach 4: Two-Pointer (Two Sum II - Input array is sorted)
     * Time Complexity: O(N)
     * Space Complexity: O(1)
     * 
     * Uses two converging pointers from the extremes of a sorted array.
     * 
     * @param sortedNums sorted array of integers
     * @param target sum to match
     * @return 1-based indices [left + 1, right + 1] as per LeetCode #167 convention
     */
    public static int[] twoSumSorted(int[] sortedNums, int target) {
        if (sortedNums == null || sortedNums.length < 2) return new int[0];
        int left = 0;
        int right = sortedNums.length - 1;

        while (left < right) {
            int sum = sortedNums[left] + sortedNums[right];
            if (sum == target) {
                return new int[]{left + 1, right + 1}; // 1-based index
            } else if (sum < target) {
                left++;
            } else {
                right--;
            }
        }
        return new int[0];
    }

    /**
     * Finds all unique value pairs (a, b) in an array that add up to target.
     * Handles duplicate array elements gracefully.
     * Time Complexity: O(N log N) due to sorting
     * Space Complexity: O(1) auxiliary (excluding output)
     * 
     * @param nums array of integers
     * @param target sum to match
     * @return list of unique pairs
     */
    public static List<List<Integer>> findAllUniquePairs(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length < 2) return result;

        int[] copy = nums.clone();
        Arrays.sort(copy);

        int left = 0;
        int right = copy.length - 1;

        while (left < right) {
            int sum = copy[left] + copy[right];
            if (sum == target) {
                result.add(Arrays.asList(copy[left], copy[right]));
                left++;
                right--;

                // Skip duplicates
                while (left < right && copy[left] == copy[left - 1]) left++;
                while (left < right && copy[right] == copy[right + 1]) right--;
            } else if (sum < target) {
                left++;
            } else {
                right--;
            }
        }
        return result;
    }

    /**
     * Checks if any pair in the array adds up to target.
     * 
     * @param nums array of integers
     * @param target target sum
     * @return true if pair exists, false otherwise
     */
    public static boolean hasPairWithSum(int[] nums, int target) {
        return twoSumOptimal(nums, target).length == 2;
    }

    public static void main(String[] args) {
        System.out.println("=== Testing Two Sum Implementations ===");

        int[] nums1 = {2, 7, 11, 15};
        int target1 = 9;
        System.out.println("Test 1: nums = " + Arrays.toString(nums1) + ", target = " + target1);
        System.out.println("  Brute Force : " + Arrays.toString(twoSumBruteForce(nums1, target1)));
        System.out.println("  Two Pass    : " + Arrays.toString(twoSumTwoPass(nums1, target1)));
        System.out.println("  Optimal 1-P : " + Arrays.toString(twoSumOptimal(nums1, target1)));

        int[] nums2 = {3, 2, 4};
        int target2 = 6;
        System.out.println("\nTest 2: nums = " + Arrays.toString(nums2) + ", target = " + target2);
        System.out.println("  Optimal 1-P : " + Arrays.toString(twoSumOptimal(nums2, target2)));

        int[] nums3 = {-3, 4, 3, 90};
        int target3 = 0;
        System.out.println("\nTest 3 (Negative numbers): nums = " + Arrays.toString(nums3) + ", target = " + target3);
        System.out.println("  Optimal 1-P : " + Arrays.toString(twoSumOptimal(nums3, target3)));

        int[] sortedNums = {2, 7, 11, 15};
        System.out.println("\nTest 4 (Two Sum II Sorted): nums = " + Arrays.toString(sortedNums) + ", target = 9");
        System.out.println("  Two Pointer : " + Arrays.toString(twoSumSorted(sortedNums, 9)));

        int[] dupNums = {1, 2, 2, 3, 4, 3, 5, 0};
        System.out.println("\nTest 5 (Find All Unique Pairs for target=5): nums = " + Arrays.toString(dupNums));
        System.out.println("  All Pairs   : " + findAllUniquePairs(dupNums, 5));
    }
}
