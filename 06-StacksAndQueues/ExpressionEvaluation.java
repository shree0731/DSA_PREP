import java.util.*;

/**
 * Demonstrates expression parsing, syntax validation, and evaluation using Stacks.
 * Includes Valid Parentheses, Longest Valid Parentheses, Infix-to-Postfix, RPN, and Basic Calculator.
 *
 * @author Shreepurnaa V
 */
public class ExpressionEvaluation {


    /**
     * Valid Parentheses (LeetCode 20).
     * Validates whether string containing '(', ')', '{', '}', '[' and ']' is balanced.
     *
     * Time Complexity: O(N)
     * Space Complexity: O(N)
     */
    public static boolean isValidParentheses(String s) {
        if (s == null || s.length() % 2 != 0) return false;
        Deque<Character> stack = new ArrayDeque<>();

        for (char ch : s.toCharArray()) {
            if (ch == '(') stack.push(')');
            else if (ch == '{') stack.push('}');
            else if (ch == '[') stack.push(']');
            else {
                if (stack.isEmpty() || stack.pop() != ch) return false;
            }
        }
        return stack.isEmpty();
    }

    /**
     * Longest Valid Parentheses (LeetCode 32).
     * Stack-based approach tracking last unmatched boundary index.
     *
     * Time Complexity: O(N)
     * Space Complexity: O(N)
     */
    public static int longestValidParenthesesStack(String s) {
        if (s == null || s.length() < 2) return 0;
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(-1);
        int maxLen = 0;

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '(') {
                stack.push(i);
            } else {
                stack.pop();
                if (stack.isEmpty()) {
                    stack.push(i);
                } else {
                    maxLen = Math.max(maxLen, i - stack.peek());
                }
            }
        }
        return maxLen;
    }

    /**
     * Longest Valid Parentheses - O(1) Auxiliary Space (LeetCode 32).
     * Two-pass scan (left-to-right then right-to-left) counting open and close brackets.
     *
     * Time Complexity: O(N)
     * Space Complexity: O(1)
     */
    public static int longestValidParenthesesOptimal(String s) {
        if (s == null || s.length() < 2) return 0;
        int left = 0, right = 0, maxLen = 0;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') left++;
            else right++;

            if (left == right) maxLen = Math.max(maxLen, 2 * right);
            else if (right > left) { left = 0; right = 0; }
        }

        left = right = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == '(') left++;
            else right++;

            if (left == right) maxLen = Math.max(maxLen, 2 * left);
            else if (left > right) { left = 0; right = 0; }
        }
        return maxLen;
    }
}
