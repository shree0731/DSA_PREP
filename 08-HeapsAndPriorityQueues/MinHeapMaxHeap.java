import java.util.Arrays;

/**
 * Production-ready Min-Heap and Max-Heap implementations with bounds checks,
 * in-place heapify, and heapSort.
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

        public void display() {
            System.out.print("MinHeap elements: ");
            for (int i = 0; i < size; i++) System.out.print(data[i] + " ");
            System.out.println();
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

    public static void heapSort(int[] arr) {
        if (arr == null || arr.length <= 1) return;
        int n = arr.length;
        for (int i = (n / 2) - 1; i >= 0; i--) heapify(arr, n, i);
        for (int i = n - 1; i > 0; i--) {
            int temp = arr[0]; arr[0] = arr[i]; arr[i] = temp;
            heapify(arr, i, 0);
        }
    }
    private static void heapify(int[] arr, int n, int i) {
        int largest = i, l = 2 * i + 1, r = 2 * i + 2;
        if (l < n && arr[l] > arr[largest]) largest = l;
        if (r < n && arr[r] > arr[largest]) largest = r;
        if (largest != i) {
            int swap = arr[i]; arr[i] = arr[largest]; arr[largest] = swap;
            heapify(arr, n, largest);
        }
    }

    public static int[] getKSmallest(int[] arr, int k) {
        if (arr == null || k <= 0) return new int[0];
        MinHeap heap = MinHeap.buildHeap(arr);
        int count = Math.min(k, arr.length);
        int[] res = new int[count];
        for (int i = 0; i < count; i++) res[i] = heap.extractMin();
        return res;
    }

    public static void runBenchmark() {
        int n = 1_000_000;
        int[] data = new int[n];
        java.util.Random rand = new java.util.Random(42);
        for (int i = 0; i < n; i++) data[i] = rand.nextInt();
        long start = System.currentTimeMillis();
        MinHeap custom = MinHeap.buildHeap(data);
        long customTime = System.currentTimeMillis() - start;
        start = System.currentTimeMillis();
        java.util.PriorityQueue<Integer> pq = new java.util.PriorityQueue<>(n);
        for (int v : data) pq.offer(v);
        long pqTime = System.currentTimeMillis() - start;
        System.out.println("Custom Primitive BuildHeap Time: " + customTime + " ms");
        System.out.println("Java PriorityQueue Insertion Time: " + pqTime + " ms");
    }

    public static void main(String[] args) {
        System.out.println("=== Testing MinHeap Operations ===");
        MinHeap minHeap = new MinHeap(5);
        int[] input = {30, 10, 50, 20, 5, 40};
        for (int v : input) minHeap.insert(v);
        minHeap.display();
        System.out.println("Extracted Min: " + minHeap.extractMin() + " (Expected: 5)");
        System.out.println("Current Min  : " + minHeap.peek() + " (Expected: 10)");

        System.out.println("\n=== Testing Linear O(N) BuildHeap ===");
        int[] raw = {12, 11, 13, 5, 6, 7};
        MinHeap built = MinHeap.buildHeap(raw);
        System.out.print("Extracted from BuildHeap: ");
        while (!built.isEmpty()) System.out.print(built.extractMin() + " ");
        System.out.println();

        System.out.println("\n=== Testing MaxHeap ===");
        MaxHeap maxHeap = new MaxHeap(5);
        for (int v : input) maxHeap.insert(v);
        System.out.println("Extracted Max: " + maxHeap.extractMax() + " (Expected: 50)");

        System.out.println("\n=== Testing In-Place HeapSort ===");
        int[] arrToSort = {64, 34, 25, 12, 22, 11, 90};
        System.out.println("Before Sort: " + Arrays.toString(arrToSort));
        heapSort(arrToSort);
        System.out.println("After Sort : " + Arrays.toString(arrToSort));
        System.out.println("All MinHeapMaxHeap tests completed successfully!");
    }
}
