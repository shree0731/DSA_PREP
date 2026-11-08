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
}
