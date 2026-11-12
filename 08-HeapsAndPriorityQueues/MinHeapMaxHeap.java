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

        public void insert(int val) {
            if (size == data.length) data = Arrays.copyOf(data, data.length * 2);
            data[size] = val;
            siftUp(size);
            size++;
        }

        public int extractMin() {
            if (isEmpty()) throw new IllegalStateException("Heap Underflow: cannot extract from empty heap.");
            int minVal = data[0];
            data[0] = data[size - 1];
            size--;
            if (size > 0) siftDown(0);
            return minVal;
        }

        private void siftUp(int i) {
            while (i > 0 && data[i] < data[parent(i)]) {
                swap(i, parent(i));
                i = parent(i);
            }
        }

        private void siftDown(int i) {
            int minIdx = i, l = leftChild(i), r = rightChild(i);
            if (l < size && data[l] < data[minIdx]) minIdx = l;
            if (r < size && data[r] < data[minIdx]) minIdx = r;
            if (minIdx != i) {
                swap(i, minIdx);
                siftDown(minIdx);
            }
        }

        private void swap(int i, int j) {
            int temp = data[i]; data[i] = data[j]; data[j] = temp;
        }

        public static MinHeap buildHeap(int[] arr) {
            MinHeap heap = new MinHeap(arr.length);
            heap.data = Arrays.copyOf(arr, arr.length);
            heap.size = arr.length;
            for (int i = (heap.size / 2) - 1; i >= 0; i--) heap.siftDown(i);
            return heap;
        }
    }

    public static class MaxHeap {
        private int[] data;
        private int size;
        public MaxHeap(int capacity) {
            this.data = new int[Math.max(1, capacity)];
            this.size = 0;
        }
        public int size() { return size; }
        public boolean isEmpty() { return size == 0; }
        public int peek() {
            if (isEmpty()) throw new IllegalStateException("Heap is empty.");
            return data[0];
        }
        public void insert(int val) {
            if (size == data.length) data = Arrays.copyOf(data, data.length * 2);
            data[size] = val;
            siftUp(size);
            size++;
        }
        public int extractMax() {
            if (isEmpty()) throw new IllegalStateException("Heap is empty.");
            int maxVal = data[0];
            data[0] = data[size - 1];
            size--;
            if (size > 0) siftDown(0);
            return maxVal;
        }
        private void siftUp(int i) {
            while (i > 0 && data[i] > data[(i - 1) / 2]) {
                int p = (i - 1) / 2;
                int temp = data[i]; data[i] = data[p]; data[p] = temp;
                i = p;
            }
        }
        private void siftDown(int i) {
            int maxIdx = i, l = 2 * i + 1, r = 2 * i + 2;
            if (l < size && data[l] > data[maxIdx]) maxIdx = l;
            if (r < size && data[r] > data[maxIdx]) maxIdx = r;
            if (maxIdx != i) {
                int temp = data[i]; data[i] = data[maxIdx]; data[maxIdx] = temp;
                siftDown(maxIdx);
            }
        }
    }
}
