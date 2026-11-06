import java.util.*;

/**
 * SinglyLinkedList provides a complete educational implementation of a singly linked list
 * with standard operations, pointer reversals, and fast/slow pointer algorithms.
 */
public class SinglyLinkedList {

    /**
     * Node represents an individual element containing an integer value and a next pointer.
     */
    public static class Node {
        public int val;
        public Node next;

        public Node(int val) {
            this.val = val;
            this.next = null;
        }

        public Node(int val, Node next) {
            this.val = val;
            this.next = next;
        }

        @Override
        public String toString() {
            return String.valueOf(val);
        }
    }

    private Node head;
    private int size;

    public SinglyLinkedList() {
        this.head = null;
        this.size = 0;
    }

    public Node getHead() {
        return head;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return head == null;
    }

    /**
     * Inserts a new node at the beginning of the list.
     * Time Complexity: O(1)
     */
    public void insertAtHead(int val) {
        Node newNode = new Node(val, head);
        head = newNode;
        size++;
    }

    /**
     * Appends a new node to the end of the list.
     * Time Complexity: O(N) without tail pointer
     */
    public void insertAtTail(int val) {
        Node newNode = new Node(val);
        if (head == null) {
            head = newNode;
        } else {
            Node curr = head;
            while (curr.next != null) {
                curr = curr.next;
            }
            curr.next = newNode;
        }
        size++;
    }

    /**
     * Inserts an element at a specified 0-based index.
     * Time Complexity: O(N)
     */
    public void insertAt(int index, int val) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        if (index == 0) {
            insertAtHead(val);
            return;
        }
        Node prev = head;
        for (int i = 0; i < index - 1; i++) {
            prev = prev.next;
        }
        Node newNode = new Node(val, prev.next);
        prev.next = newNode;
        size++;
    }

    /**
     * Deletes and returns the element at the head of the list.
     * Time Complexity: O(1)
     */
    public int deleteAtHead() {
        if (head == null) {
            throw new NoSuchElementException("Cannot delete from an empty list");
        }
        int removed = head.val;
        head = head.next;
        size--;
        return removed;
    }

    /**
     * Deletes and returns the element at the tail of the list.
     * Time Complexity: O(N)
     */
    public int deleteAtTail() {
        if (head == null) {
            throw new NoSuchElementException("Cannot delete from an empty list");
        }
        if (head.next == null) {
            return deleteAtHead();
        }
        Node curr = head;
        while (curr.next.next != null) {
            curr = curr.next;
        }
        int removed = curr.next.val;
        curr.next = null;
        size--;
        return removed;
    }

    /**
     * Deletes the node at the specified 0-based index.
     * Time Complexity: O(N)
     */
    public int deleteAt(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        if (index == 0) return deleteAtHead();

        Node prev = head;
        for (int i = 0; i < index - 1; i++) {
            prev = prev.next;
        }
        int removed = prev.next.val;
        prev.next = prev.next.next;
        size--;
        return removed;
    }

    /**
     * Deletes the first occurrence of the specified value.
     * Time Complexity: O(N)
     */
    public boolean deleteValue(int val) {
        if (head == null) return false;
        if (head.val == val) {
            deleteAtHead();
            return true;
        }
        Node curr = head;
        while (curr.next != null && curr.next.val != val) {
            curr = curr.next;
        }
        if (curr.next != null) {
            curr.next = curr.next.next;
            size--;
            return true;
        }
        return false;
    }

    /**
     * In-place iterative linked list reversal (LeetCode #206).
     * Uses 3 pointers: prev, curr, next.
     * Time Complexity: O(N)
     * Space Complexity: O(1)
     */
    public void reverse() {
        head = reverseList(head);
    }

    public static Node reverseList(Node head) {
        Node prev = null;
        Node curr = head;

        while (curr != null) {
            Node nextTemp = curr.next; // Store next
            curr.next = prev;          // Reverse pointer
            prev = curr;               // Advance prev
            curr = nextTemp;           // Advance curr
        }
        return prev; // New head
    }
}
