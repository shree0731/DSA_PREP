import java.util.*;

/**
 * DoublyLinkedList provides bidirectional linked list operations,
 * O(1) arbitrary node deletion, and bidirectional traversals.
 */
public class DoublyLinkedList {

    public static class Node {
        public int val;
        public Node prev;
        public Node next;

        public Node(int val) {
            this.val = val;
            this.prev = null;
            this.next = null;
        }

        public Node(int val, Node prev, Node next) {
            this.val = val;
            this.prev = prev;
            this.next = next;
        }

        @Override
        public String toString() {
            return String.valueOf(val);
        }
    }

    private Node head;
    private Node tail;
    private int size;

    public DoublyLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public Node getHead() { return head; }
    public Node getTail() { return tail; }
    public int size() { return size; }
    public boolean isEmpty() { return size == 0; }
}
