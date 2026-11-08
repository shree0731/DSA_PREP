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

    /**
     * MinStack supporting push, pop, top, and getMin in O(1) time (LeetCode 155).
     * Approach 1: Dual Stacks (main stack + minimum tracking stack).
     */
    public static class DualMinStack {
        private final java.util.ArrayDeque<Integer> stack = new java.util.ArrayDeque<>();
        private final java.util.ArrayDeque<Integer> minStack = new java.util.ArrayDeque<>();

        public void push(int val) {
            stack.push(val);
            if (minStack.isEmpty() || val <= minStack.peek()) {
                minStack.push(val);
            }
        }

        public int pop() {
            if (stack.isEmpty()) {
                throw new IllegalStateException("Stack is empty.");
            }
            int val = stack.pop();
            if (val == minStack.peek()) {
                minStack.pop();
            }
            return val;
        }

        public int top() {
            if (stack.isEmpty()) {
                throw new IllegalStateException("Stack is empty.");
            }
            return stack.peek();
        }

        public int getMin() {
            if (minStack.isEmpty()) {
                throw new IllegalStateException("Stack is empty.");
            }
            return minStack.peek();
        }
    }

    /**
     * MinStack supporting push, pop, top, and getMin in O(1) time and O(1) auxiliary space (LeetCode 155).
     * Approach 2: Mathematical Value Encoding.
     * When a new minimum x is pushed, store: encoded = 2 * x - currentMin.
     */
    public static class EncodedMinStack {
        private final java.util.ArrayDeque<Long> stack = new java.util.ArrayDeque<>();
        private long min;

        public void push(int val) {
            if (stack.isEmpty()) {
                stack.push((long) val);
                min = val;
            } else if (val >= min) {
                stack.push((long) val);
            } else {
                stack.push(2L * val - min);
                min = val;
            }
        }

        public int pop() {
            if (stack.isEmpty()) {
                throw new IllegalStateException("Stack is empty.");
            }
            long top = stack.pop();
            if (top >= min) {
                return (int) top;
            } else {
                int actualVal = (int) min;
                min = 2 * min - top;
                return actualVal;
            }
        }

        public int top() {
            if (stack.isEmpty()) {
                throw new IllegalStateException("Stack is empty.");
            }
            long top = stack.peek();
            if (top >= min) {
                return (int) top;
            } else {
                return (int) min;
            }
        }

        public int getMin() {
            if (stack.isEmpty()) {
                throw new IllegalStateException("Stack is empty.");
            }
            return (int) min;
        }
    }

    /**
     * Implements two stacks within a single array to maximize memory efficiency.
     * Stack 1 grows left-to-right from index 0; Stack 2 grows right-to-left from index N-1.
     */
    public static class TwoStacksInOneArray {
        private final int[] arr;
        private int top1;
        private int top2;

        public TwoStacksInOneArray(int capacity) {
            this.arr = new int[capacity];
            this.top1 = -1;
            this.top2 = capacity;
        }

        public void push1(int val) {
            if (top1 + 1 == top2) {
                throw new IllegalStateException("Stack Overflow: No space remaining between stacks.");
            }
            arr[++top1] = val;
        }

        public void push2(int val) {
            if (top1 + 1 == top2) {
                throw new IllegalStateException("Stack Overflow: No space remaining between stacks.");
            }
            arr[--top2] = val;
        }

        public int pop1() {
            if (top1 == -1) {
                throw new IllegalStateException("Stack 1 Underflow: stack 1 is empty.");
            }
            return arr[top1--];
        }

        public int pop2() {
            if (top2 == arr.length) {
                throw new IllegalStateException("Stack 2 Underflow: stack 2 is empty.");
            }
            return arr[top2++];
        }

        public int peek1() {
            if (top1 == -1) throw new IllegalStateException("Stack 1 is empty.");
            return arr[top1];
        }

        public int peek2() {
            if (top2 == arr.length) throw new IllegalStateException("Stack 2 is empty.");
            return arr[top2];
        }
    }

        public void display() {
            System.out.print("Stack 1: ");
            for (int i = 0; i <= top1; i++) System.out.print(arr[i] + " ");
            System.out.print("| Stack 2: ");
            for (int i = arr.length - 1; i >= top2; i--) System.out.print(arr[i] + " ");
            System.out.println();
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Testing ArrayStack with Dynamic Resizing ===");
        ArrayStack arrayStack = new ArrayStack(2);
        for (int i = 1; i <= 6; i++) {
            arrayStack.push(i * 10);
        }
        System.out.println("ArrayStack Size: " + arrayStack.size());
        System.out.println("ArrayStack Peek: " + arrayStack.peek());
        System.out.print("ArrayStack Popped: ");
        while (!arrayStack.isEmpty()) {
            System.out.print(arrayStack.pop() + " ");
        }
        System.out.println();

        System.out.println("\n=== Testing LinkedStack ===");
        LinkedStack linkedStack = new LinkedStack();
        linkedStack.push(100);
        linkedStack.push(200);
        linkedStack.push(300);
        System.out.println("LinkedStack Top: " + linkedStack.peek());
        System.out.println("LinkedStack Pop: " + linkedStack.pop());
        System.out.println("LinkedStack Size: " + linkedStack.size());

        System.out.println("\n=== Testing Dual & Encoded MinStack ===");
        DualMinStack dualMin = new DualMinStack();
        EncodedMinStack encMin = new EncodedMinStack();
        int[] vals = {5, 3, 7, 2, 8, 2, 1};
        for (int v : vals) {
            dualMin.push(v);
            encMin.push(v);
        }
        System.out.println("Dual MinStack Min: " + dualMin.getMin() + " (Expected: 1)");
        System.out.println("Encoded MinStack Min: " + encMin.getMin() + " (Expected: 1)");
        dualMin.pop();
        encMin.pop();
        System.out.println("After pop -> Dual Min: " + dualMin.getMin() + ", Encoded Min: " + encMin.getMin() + " (Expected: 2)");

        System.out.println("\n=== Testing TwoStacksInOneArray ===");
        TwoStacksInOneArray dual = new TwoStacksInOneArray(6);
        dual.push1(11);
        dual.push1(22);
        dual.push2(99);
        dual.push2(88);
        dual.push1(33);
        dual.display();
        System.out.println("Stack 1 Top: " + dual.peek1() + ", Stack 2 Top: " + dual.peek2());
        System.out.println("Pop Stack 1: " + dual.pop1() + ", Pop Stack 2: " + dual.pop2());
        System.out.println("All StackImplementations tests completed successfully!");
    }
}
