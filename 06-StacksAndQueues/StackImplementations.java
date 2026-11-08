import java.util.Arrays;

/**
 * Demonstrates custom Stack implementations and patterns in Java.
 * Includes ArrayStack, LinkedStack, MinStack (dual and encoded), and TwoStacks.
 *
 * @author Shreepurnaa V
 */
public class StackImplementations {

    /**
     * Custom Array-backed Stack with dynamic capacity resizing.
     */
    public static class ArrayStack {
        private int[] data;
        private int top;
        private static final int DEFAULT_CAPACITY = 4;

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

        public void push(int val) {
            if (top + 1 == data.length) {
                resize(data.length * 2);
            }
            data[++top] = val;
        }

        public int pop() {
            if (isEmpty()) {
                throw new IllegalStateException("Stack Underflow: cannot pop from empty stack.");
            }
            int val = data[top--];
            if (size() > 0 && size() == data.length / 4 && data.length / 2 >= DEFAULT_CAPACITY) {
                resize(data.length / 2);
            }
            return val;
        }

        public int peek() {
            if (isEmpty()) {
                throw new IllegalStateException("Stack Underflow: cannot peek empty stack.");
            }
            return data[top];
        }

        private void resize(int newCapacity) {
            data = Arrays.copyOf(data, newCapacity);
        }
    }

    /**
     * Singly Linked List-backed Stack ensuring true O(1) operations without reallocation.
     */
    public static class LinkedStack {
        private static class Node {
            int val;
            Node next;
            Node(int val, Node next) {
                this.val = val;
                this.next = next;
            }
        }

        private Node head;
        private int size;

        public LinkedStack() {
            this.head = null;
            this.size = 0;
        }

        public boolean isEmpty() {
            return head == null;
        }

        public int size() {
            return size;
        }

        public void push(int val) {
            head = new Node(val, head);
            size++;
        }

        public int pop() {
            if (isEmpty()) {
                throw new IllegalStateException("Stack Underflow: linked stack is empty.");
            }
            int val = head.val;
            head = head.next;
            size--;
            return val;
        }

        public int peek() {
            if (isEmpty()) {
                throw new IllegalStateException("Stack Underflow: linked stack is empty.");
            }
            return head.val;
        }
    }
}
