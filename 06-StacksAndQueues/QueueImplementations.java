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
}
