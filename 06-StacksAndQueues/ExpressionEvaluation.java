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

    /**
     * Infix to Postfix Conversion using Dijkstra's Shunting-yard Algorithm.
     * Supports operators +, -, *, /, ^ and parentheses.
     *
     * Time Complexity: O(N)
     * Space Complexity: O(N)
     */
    public static String infixToPostfix(String expression) {
        StringBuilder result = new StringBuilder();
        Deque<Character> stack = new ArrayDeque<>();

        for (int i = 0; i < expression.length(); i++) {
            char ch = expression.charAt(i);
            if (Character.isWhitespace(ch)) continue;

            if (Character.isLetterOrDigit(ch)) {
                result.append(ch);
            } else if (ch == '(') {
                stack.push(ch);
            } else if (ch == ')') {
                while (!stack.isEmpty() && stack.peek() != '(') {
                    result.append(stack.pop());
                }
                if (!stack.isEmpty() && stack.peek() == '(') stack.pop();
            } else {
                while (!stack.isEmpty() && precedence(ch) <= precedence(stack.peek()) && ch != '^') {
                    result.append(stack.pop());
                }
                stack.push(ch);
            }
        }

        while (!stack.isEmpty()) {
            result.append(stack.pop());
        }
        return result.toString();
    }

    private static int precedence(char op) {
        switch (op) {
            case '+': case '-': return 1;
            case '*': case '/': return 2;
            case '^': return 3;
            default: return -1;
        }
    }

    /**
     * Evaluate Reverse Polish Notation (LeetCode 150).
     * Supports +, -, *, / with truncation towards zero.
     *
     * Time Complexity: O(N)
     * Space Complexity: O(N)
     */
    public static int evalRPN(String[] tokens) {
        Deque<Integer> stack = new ArrayDeque<>();
        for (String token : tokens) {
            switch (token) {
                case "+":
                    stack.push(stack.pop() + stack.pop());
                    break;
                case "-":
                    int b = stack.pop();
                    int a = stack.pop();
                    stack.push(a - b);
                    break;
                case "*":
                    stack.push(stack.pop() * stack.pop());
                    break;
                case "/":
                    int divisor = stack.pop();
                    int dividend = stack.pop();
                    stack.push(dividend / divisor);
                    break;
                default:
                    stack.push(Integer.parseInt(token));
                    break;
            }
        }
        return stack.pop();
    }
}
