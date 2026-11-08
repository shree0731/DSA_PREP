/**
 * Demonstrates custom Queue and Deque implementations and adapter patterns in Java.
 * Includes CircularArrayQueue, LinkedQueue, QueueUsingStacks, StackUsingQueues, and CircularDeque.
 *
 * @author Shreepurnaa V
 */
public class QueueImplementations {

    /**
     * Circular Array-backed FIFO Queue with automatic dynamic expansion.
     */
    public static class CircularArrayQueue {
        private int[] arr;
        private int front;
        private int rear;
        private int count;
        private static final int DEFAULT_CAPACITY = 4;

        public CircularArrayQueue() {
            this(DEFAULT_CAPACITY);
        }

        public CircularArrayQueue(int capacity) {
            this.arr = new int[Math.max(1, capacity)];
            this.front = 0;
            this.rear = 0;
            this.count = 0;
        }

        public boolean isEmpty() {
            return count == 0;
        }

        public boolean isFull() {
            return count == arr.length;
        }

        public int size() {
            return count;
        }

        public void enqueue(int val) {
            if (isFull()) {
                expandCapacity();
            }
            arr[rear] = val;
            rear = (rear + 1) % arr.length;
            count++;
        }

        public int dequeue() {
            if (isEmpty()) {
                throw new IllegalStateException("Queue Underflow: cannot dequeue empty queue.");
            }
            int val = arr[front];
            front = (front + 1) % arr.length;
            count--;
            return val;
        }

        public int peek() {
            if (isEmpty()) {
                throw new IllegalStateException("Queue Underflow: cannot peek empty queue.");
            }
            return arr[front];
        }

        private void expandCapacity() {
            int newCap = arr.length * 2;
            int[] newArr = new int[newCap];
            for (int i = 0; i < count; i++) {
                newArr[i] = arr[(front + i) % arr.length];
            }
            this.arr = newArr;
            this.front = 0;
            this.rear = count;
        }
    }

    /**
     * Linked List Queue maintaining head and tail pointers for true O(1) operations.
     */
    public static class LinkedQueue {
        private static class Node {
            int val;
            Node next;
            Node(int val) { this.val = val; }
        }

        private Node head;
        private Node tail;
        private int count;

        public LinkedQueue() {
            this.head = null;
            this.tail = null;
            this.count = 0;
        }

        public boolean isEmpty() {
            return count == 0;
        }

        public int size() {
            return count;
        }

        public void enqueue(int val) {
            Node newNode = new Node(val);
            if (isEmpty()) {
                head = tail = newNode;
            } else {
                tail.next = newNode;
                tail = newNode;
            }
            count++;
        }

        public int dequeue() {
            if (isEmpty()) {
                throw new IllegalStateException("Queue Underflow: linked queue is empty.");
            }
            int val = head.val;
            head = head.next;
            if (head == null) {
                tail = null;
            }
            count--;
            return val;
        }

        public int peek() {
            if (isEmpty()) {
                throw new IllegalStateException("Queue Underflow: linked queue is empty.");
            }
            return head.val;
        }
    }

    /**
     * Queue implemented using two Stacks with amortized O(1) time per operation (LeetCode 232).
     */
    public static class QueueUsingStacks {
        private final java.util.ArrayDeque<Integer> inStack = new java.util.ArrayDeque<>();
        private final java.util.ArrayDeque<Integer> outStack = new java.util.ArrayDeque<>();

        public void push(int x) {
            inStack.push(x);
        }

        public int pop() {
            moveIfNecessary();
            if (outStack.isEmpty()) {
                throw new IllegalStateException("Queue is empty.");
            }
            return outStack.pop();
        }

        public int peek() {
            moveIfNecessary();
            if (outStack.isEmpty()) {
                throw new IllegalStateException("Queue is empty.");
            }
            return outStack.peek();
        }

        public boolean empty() {
            return inStack.isEmpty() && outStack.isEmpty();
        }

        private void moveIfNecessary() {
            if (outStack.isEmpty()) {
                while (!inStack.isEmpty()) {
                    outStack.push(inStack.pop());
                }
            }
        }
    }

    /**
     * Stack implemented using a single Queue with O(N) push and O(1) pop (LeetCode 225).
     */
    public static class StackUsingQueues {
        private final java.util.Queue<Integer> queue = new java.util.LinkedList<>();

        public void push(int x) {
            queue.add(x);
            int rotations = queue.size() - 1;
            while (rotations-- > 0) {
                queue.add(queue.remove());
            }
        }

        public int pop() {
            if (queue.isEmpty()) {
                throw new IllegalStateException("Stack is empty.");
            }
            return queue.remove();
        }

        public int top() {
            if (queue.isEmpty()) {
                throw new IllegalStateException("Stack is empty.");
            }
            return queue.peek();
        }

        public boolean empty() {
            return queue.isEmpty();
        }
    }

    /**
     * Circular Deque supporting O(1) insertions and deletions at both ends (LeetCode 641).
     */
    public static class CircularDeque {
        private final int[] arr;
        private int front;
        private int rear;
        private int count;
        private final int capacity;

        public CircularDeque(int k) {
            this.capacity = k;
            this.arr = new int[k];
            this.front = 0;
            this.rear = 0;
            this.count = 0;
        }

        public boolean insertFront(int value) {
            if (isFull()) return false;
            front = (front - 1 + capacity) % capacity;
            arr[front] = value;
            count++;
            return true;
        }

        public boolean insertLast(int value) {
            if (isFull()) return false;
            arr[rear] = value;
            rear = (rear + 1) % capacity;
            count++;
            return true;
        }

        public boolean deleteFront() {
            if (isEmpty()) return false;
            front = (front + 1) % capacity;
            count--;
            return true;
        }

        public boolean deleteLast() {
            if (isEmpty()) return false;
            rear = (rear - 1 + capacity) % capacity;
            count--;
            return true;
        }

        public int getFront() {
            return isEmpty() ? -1 : arr[front];
        }

        public int getRear() {
            return isEmpty() ? -1 : arr[(rear - 1 + capacity) % capacity];
        }

        public boolean isEmpty() {
            return count == 0;
        }

        public boolean isFull() {
            return count == capacity;
        }
    }

        public void display() {
            System.out.print("Deque [Front: " + front + ", Rear: " + rear + ", Count: " + count + "]: ");
            for (int i = 0; i < count; i++) {
                System.out.print(arr[(front + i) % capacity] + " ");
            }
            System.out.println();
        }
    }
}
