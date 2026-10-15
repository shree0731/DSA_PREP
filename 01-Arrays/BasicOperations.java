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

    /**
     * Default constructor initializing with default capacity.
     */
    public BasicOperations() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * Parameterized constructor initializing with given initial capacity.
     *
     * @param initialCapacity initial size of internal buffer
     */
    public BasicOperations(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Capacity cannot be negative: " + initialCapacity);
        }
        this.capacity = initialCapacity;
        this.data = new int[capacity];
        this.size = 0;
    }
}
