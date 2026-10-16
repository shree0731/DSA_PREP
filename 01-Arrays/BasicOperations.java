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

    /**
     * Finds and deletes the first occurrence of the specified value.
     * Time Complexity: O(N)
     *
     * @param element the value to delete
     * @return true if found and deleted, false otherwise
     */
    public boolean deleteValue(int element) {
        for (int i = 0; i < size; i++) {
            if (data[i] == element) {
                deleteAt(i);
                return true;
            }
        }
        return false;
    }

    public int get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        return data[index];
    }
}
