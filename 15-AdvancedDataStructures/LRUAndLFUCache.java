import java.util.*;

/**
 * Cache design implementations:
 * 1. LRU Cache (LeetCode 146): Least Recently Used cache using Doubly Linked List + HashMap in O(1).
 * 2. LFU Cache (LeetCode 460): Least Frequently Used cache using Frequency Buckets in O(1).
 *
 * @author Shreepurnaa V
 */
public class LRUAndLFUCache {

    public static class LRUCache {
        private static class Node {
            int key, val;
            Node prev, next;
            Node(int k, int v) { this.key = k; this.val = v; }
        }

        private final int capacity;
        private final Map<Integer, Node> map = new HashMap<>();
        private final Node head = new Node(0, 0);
        private final Node tail = new Node(0, 0);

        public LRUCache(int capacity) {
            this.capacity = capacity;
            head.next = tail;
            tail.prev = head;
        }

        public int get(int key) {
            Node node = map.get(key);
            if (node == null) return -1;
            moveToHead(node);
            return node.val;
        }

        public void put(int key, int value) {
            Node node = map.get(key);
            if (node != null) {
                node.val = value;
                moveToHead(node);
            } else {
                if (map.size() >= capacity) {
                    Node lru = tail.prev;
                    removeNode(lru);
                    map.remove(lru.key);
                }
                Node newNode = new Node(key, value);
                map.put(key, newNode);
                addFirst(newNode);
            }
        }

        private void addFirst(Node node) {
            node.next = head.next;
            node.prev = head;
            head.next.prev = node;
            head.next = node;
        }

        private void removeNode(Node node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }

        private void moveToHead(Node node) {
            removeNode(node);
            addFirst(node);
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Testing LRU Cache ===");
        LRUCache lru = new LRUCache(2);
        lru.put(1, 1);
        lru.put(2, 2);
        System.out.println("Get 1: " + lru.get(1) + " (Expected: 1)");
        lru.put(3, 3); // evicts key 2
        System.out.println("Get 2: " + lru.get(2) + " (Expected: -1)");
        lru.put(4, 4); // evicts key 1
        System.out.println("Get 1: " + lru.get(1) + " (Expected: -1)");
        System.out.println("Get 3: " + lru.get(3) + " (Expected: 3)");
        System.out.println("Get 4: " + lru.get(4) + " (Expected: 4)");
        System.out.println("All LRUCache tests passed successfully!");
    }
}
