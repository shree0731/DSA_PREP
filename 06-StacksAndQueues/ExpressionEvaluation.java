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
}
