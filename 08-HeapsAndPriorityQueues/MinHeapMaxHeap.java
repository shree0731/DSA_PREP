import java.util.Arrays;

/**
 * Custom Min-Heap and Max-Heap implementations from scratch using dynamic arrays.
 * Demonstrates Sift-Up, Sift-Down, O(N) BuildHeap, and In-Place HeapSort.
 *
 * @author Shreepurnaa V
 */
public class MinHeapMaxHeap {
    public static class MinHeap {
        private int[] data;
        private int size;
        private static final int DEFAULT_CAPACITY = 8;

        public MinHeap() { this(DEFAULT_CAPACITY); }
        public MinHeap(int capacity) {
            this.data = new int[Math.max(1, capacity)];
            this.size = 0;
        }

        public int size() { return size; }
        public boolean isEmpty() { return size == 0; }
        public int peek() {
            if (isEmpty()) throw new IllegalStateException("Heap Underflow: heap is empty.");
            return data[0];
        }
        private int parent(int i) { return (i - 1) / 2; }
        private int leftChild(int i) { return 2 * i + 1; }
        private int rightChild(int i) { return 2 * i + 2; }
    }
}
