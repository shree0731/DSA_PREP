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
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
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
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
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

    /**
     * Reverses an entire array in-place using two pointers.
     * Time Complexity: O(N)
     * Space Complexity: O(1)
     *
     * @param arr the array to reverse
     */
    public static void reverse(int[] arr) {
        if (arr == null || arr.length <= 1) return;
        reverseRange(arr, 0, arr.length - 1);
    }

    /**
     * Reverses a subsegment of an array from start to end inclusive.
     *
     * @param arr array to modify
     * @param start start index
     * @param end end index
     */
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

    public int get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        return data[index];
    }
}
