/**
 * @author Shreepurnaa V
 * @version 1.0
 */
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

    /**
     * Prepends an element at head.
     * Time Complexity: O(1)
     */
    public void insertHead(int val) {
        Node newNode = new Node(val, null, head);
        if (head == null) {
            head = tail = newNode;
        } else {
            head.prev = newNode;
            head = newNode;
        }
        size++;
    }

    /**
     * Appends an element at tail.
     * Time Complexity: O(1)
     */
    public void insertTail(int val) {
        Node newNode = new Node(val, tail, null);
        if (tail == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    /**
     * Removes and returns the head node.
     * Time Complexity: O(1)
     */
    public int deleteHead() {
        if (head == null) throw new NoSuchElementException("Empty list");
        int removed = head.val;
        if (head == tail) {
            head = tail = null;
        } else {
            head = head.next;
            head.prev = null;
        }
        size--;
        return removed;
    }

    /**
     * Removes and returns the tail node.
     * Time Complexity: O(1)
     */
    public int deleteTail() {
        if (tail == null) throw new NoSuchElementException("Empty list");
        int removed = tail.val;
        if (head == tail) {
            head = tail = null;
        } else {
            tail = tail.prev;
            tail.next = null;
        }
        size--;
        return removed;
    }

    /**
     * Deletes a given node directly without traversing from head.
     * Time Complexity: O(1)
     */
    public void deleteNode(Node node) {
        if (node == null) return;
        if (node == head) {
            deleteHead();
            return;
        }
        if (node == tail) {
            deleteTail();
            return;
        }
        node.prev.next = node.next;
        node.next.prev = node.prev;
        node.prev = null;
        node.next = null;
        size--;
    }

    /**
     * In-place reversal of Doubly Linked List by swapping next and prev pointers for every node.
     * Time Complexity: O(N)
     * Space Complexity: O(1)
     */
    public void reverse() {
        if (head == null || head == tail) return;
        Node curr = head;
        Node temp = null;

        tail = head; // Former head becomes new tail

        while (curr != null) {
            temp = curr.prev;
            curr.prev = curr.next;
            curr.next = temp;
            curr = curr.prev; // Since prev and next were swapped, curr.prev moves to original next
        }

        if (temp != null) {
            head = temp.prev; // Final node becomes new head
        }
    }

    public void displayForward() {
        StringBuilder sb = new StringBuilder("Forward:  null <-> ");
        Node curr = head;
        while (curr != null) {
            sb.append(curr.val).append(" <-> ");
            curr = curr.next;
        }
        sb.append("null");
        System.out.println(sb.toString());
    }

    public void displayBackward() {
        StringBuilder sb = new StringBuilder("Backward: null <-> ");
        Node curr = tail;
        while (curr != null) {
            sb.append(curr.val).append(" <-> ");
            curr = curr.prev;
        }
        sb.append("null");
        System.out.println(sb.toString());
    }

    /**
     * Clears all elements from the list to assist garbage collection.
     */
    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    public static void main(String[] args) {
        System.out.println("=== Testing Doubly Linked List ===");
        DoublyLinkedList dll = new DoublyLinkedList();
        dll.insertTail(10);
        dll.insertTail(20);
        dll.insertTail(30);
        dll.insertHead(5);
        dll.displayForward();
        dll.displayBackward();

        System.out.println("\nReversing Doubly Linked List...");
        dll.reverse();
        dll.displayForward();
        dll.displayBackward();

        System.out.println("\nDeleting Head (val=" + dll.deleteHead() + ")...");
        System.out.println("Deleting Tail (val=" + dll.deleteTail() + ")...");
        dll.displayForward();
    }
}
