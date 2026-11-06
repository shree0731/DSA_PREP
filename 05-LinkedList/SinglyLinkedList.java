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
}
