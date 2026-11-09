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
}
