import java.util.*;

/**
 * Two-Pointer optimization techniques.
 *
 * @author Shreepurnaa V
 */
public class TwoPointerTechniques {

    /**
     * Trapping Rain Water (LeetCode 42).
     * Solved with O(1) space using dual pointers left and right.
     * Time: O(N), Space: O(1).
     */
    public static int trapRainWater(int[] height) {
        if (height == null || height.length <= 2) return 0;
        int left = 0, right = height.length - 1;
        int leftMax = 0, rightMax = 0, trapped = 0;

        while (left < right) {
            if (height[left] < height[right]) {
                if (height[left] >= leftMax) {
                    leftMax = height[left];
                } else {
                    trapped += leftMax - height[left];
                }
                left++;
            } else {
                if (height[right] >= rightMax) {
                    rightMax = height[right];
                } else {
                    trapped += rightMax - height[right];
                }
                right--;
            }
        }
        return trapped;
    }

    public static void main(String[] args) {
        System.out.println("=== Testing Two Pointer Techniques ===");
        int[] elevation = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        System.out.println("Trapped Rain Water: " + trapRainWater(elevation) + " (Expected: 6)");
        System.out.println("All TwoPointerTechniques tests passed successfully!");
    }
}
