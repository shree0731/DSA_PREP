/**
 * @author Shreepurnaa V
 * @version 1.0
 */
import java.util.Arrays;

/**
 * BasicOperations provides an educational implementation of a dynamic array
 * alongside standard algorithmic operations on arrays.
 */
public class BasicOperations {
    private int[] data;
    private int size;
    private int capacity;

    private static final int DEFAULT_CAPACITY = 10;

    public BasicOperations() {
        this(DEFAULT_CAPACITY);
    }

    public BasicOperations(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Capacity cannot be negative: " + initialCapacity);
        }
        this.capacity = initialCapacity;
        this.data = new int[capacity];
        this.size = 0;
    }

    private void ensureCapacity(int minCapacity) {
        if (minCapacity > capacity) {
            int newCapacity = capacity == 0 ? DEFAULT_CAPACITY : capacity * 2;
            if (newCapacity < minCapacity) {
                newCapacity = minCapacity;
            }
            int[] newData = new int[newCapacity];
            System.arraycopy(data, 0, newData, 0, size);
            this.data = newData;
            this.capacity = newCapacity;
        }
    }

    public void insert(int element) {
        ensureCapacity(size + 1);
        data[size++] = element;
    }

    public void insertAt(int index, int element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Invalid array index: " + index + ", current size is: " + size);
        }
        ensureCapacity(size + 1);
        for (int i = size; i > index; i--) {
            data[i] = data[i - 1];
        }
        data[index] = element;
        size++;
    }

    public int deleteAt(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Invalid array index: " + index + ", current size is: " + size);
        }
        int removed = data[index];
        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }
        data[size - 1] = 0;
        size--;
        return removed;
    }

    public boolean deleteValue(int element) {
        for (int i = 0; i < size; i++) {
            if (data[i] == element) {
                deleteAt(i);
                return true;
            }
        }
        return false;
    }

    public int linearSearch(int target) {
        for (int i = 0; i < size; i++) {
            if (data[i] == target) {
                return i;
            }
        }
        return -1;
    }

    public boolean contains(int target) {
        return linearSearch(target) != -1;
    }

    public static int binarySearchIterative(int[] arr, int target) {
        if (arr == null) return -1;
        int low = 0;
        int high = arr.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

    public static int binarySearchRecursive(int[] arr, int target) {
        if (arr == null) return -1;
        return binarySearchRecursiveHelper(arr, target, 0, arr.length - 1);
    }

    private static int binarySearchRecursiveHelper(int[] arr, int target, int low, int high) {
        if (low > high) return -1;
        int mid = low + (high - low) / 2;
        if (arr[mid] == target) {
            return mid;
        } else if (arr[mid] < target) {
            return binarySearchRecursiveHelper(arr, target, mid + 1, high);
        } else {
            return binarySearchRecursiveHelper(arr, target, low, mid - 1);
        }
    }

    public static void reverse(int[] arr) {
        if (arr == null || arr.length <= 1) return;
        reverseRange(arr, 0, arr.length - 1);
    }

    public static void reverseRange(int[] arr, int start, int end) {
        if (arr == null) return;
        while (start < end) {
            int temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start++;
            end--;
        }
    }

    public static void rotateLeft(int[] arr, int d) {
        if (arr == null || arr.length <= 1) return;
        int n = arr.length;
        d = d % n;
        if (d < 0) d += n;
        if (d == 0) return;

        reverseRange(arr, 0, d - 1);
        reverseRange(arr, d, n - 1);
        reverseRange(arr, 0, n - 1);
    }

    public static void rotateRight(int[] arr, int d) {
        if (arr == null || arr.length <= 1) return;
        int n = arr.length;
        d = d % n;
        if (d < 0) d += n;
        if (d == 0) return;

        reverseRange(arr, 0, n - 1);
        reverseRange(arr, 0, d - 1);
        reverseRange(arr, d, n - 1);
    }

    public int get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Invalid array index: " + index + ", current size is: " + size);
        }
        return data[index];
    }

    public int size() {
        return size;
    }

    public int getCapacity() {
        return capacity;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void display() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            sb.append(data[i]);
            if (i < size - 1) sb.append(", ");
        }
        sb.append("]");
        System.out.println("Array (size=" + size + ", cap=" + capacity + "): " + sb.toString());
    }

    public static void main(String[] args) {
        System.out.println("=== Testing Dynamic Array Operations ===");
        BasicOperations list = new BasicOperations(3);
        list.insert(10);
        list.insert(20);
        list.insert(30);
        list.display();

        System.out.println("Triggering dynamic resize...");
        list.insert(40);
        list.display();

        System.out.println("Inserting 15 at index 1...");
        list.insertAt(1, 15);
        list.display();

        System.out.println("Deleting element at index 2 (val=" + list.deleteAt(2) + ")...");
        list.display();

        System.out.println("Linear search for 40: index = " + list.linearSearch(40));
        System.out.println("Contains 99: " + list.contains(99));

        System.out.println("\n=== Testing Binary Search ===");
        int[] sorted = {2, 5, 8, 12, 16, 23, 38, 56, 72, 91};
        System.out.println("Array: " + Arrays.toString(sorted));
        System.out.println("Binary Search Iterative (target=23): " + binarySearchIterative(sorted, 23));
        System.out.println("Binary Search Recursive (target=23): " + binarySearchRecursive(sorted, 23));
        System.out.println("Binary Search (target=100, absent): " + binarySearchIterative(sorted, 100));

        System.out.println("\n=== Testing Reversal & Rotations ===");
        int[] nums = {1, 2, 3, 4, 5, 6, 7};
        System.out.println("Original: " + Arrays.toString(nums));
        reverse(nums);
        System.out.println("Reversed: " + Arrays.toString(nums));

        int[] rot = {1, 2, 3, 4, 5, 6, 7};
        rotateLeft(rot, 2);
        System.out.println("Left rotate by 2: " + Arrays.toString(rot));

        rot = new int[]{1, 2, 3, 4, 5, 6, 7};
        rotateRight(rot, 2);
        System.out.println("Right rotate by 2: " + Arrays.toString(rot));
    }
}
