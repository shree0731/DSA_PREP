/**
 * Demonstrates custom Queue and Deque implementations and adapter patterns in Java.
 * Includes CircularArrayQueue, LinkedQueue, QueueUsingStacks, StackUsingQueues, and CircularDeque.
 *
 * @author Shreepurnaa V
 */
public class QueueImplementations {

    /**
     * Circular Array-backed FIFO Queue with modular arithmetic.
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
    }
}
