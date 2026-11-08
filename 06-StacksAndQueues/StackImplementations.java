/**
 * Demonstrates custom Stack implementations and patterns in Java.
 * Includes ArrayStack, LinkedStack, MinStack (dual and encoded), and TwoStacks.
 *
 * @author Shreepurnaa V
 */
public class StackImplementations {

    /**
     * Custom Array-backed Stack with initial capacity and pointer tracking.
     */
    public static class ArrayStack {
        private int[] data;
        private int top;
        private static final int DEFAULT_CAPACITY = 10;

        public ArrayStack() {
            this(DEFAULT_CAPACITY);
        }

        public ArrayStack(int capacity) {
            this.data = new int[Math.max(1, capacity)];
            this.top = -1;
        }

        public boolean isEmpty() {
            return top == -1;
        }

        public int size() {
            return top + 1;
        }

        public int peek() {
            if (isEmpty()) {
                throw new IllegalStateException("Stack Underflow: Stack is empty.");
            }
            return data[top];
        }
    }
}
