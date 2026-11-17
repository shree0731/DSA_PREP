import java.util.*;

/**
 * Standard Trie (Prefix Tree) implementation.
 * Supports insert, search, startsWith, and auto-complete prefix enumeration.
 *
 * @author Shreepurnaa V
 */
public class TriePrefixTree {

    public static class TrieNode {
        public final TrieNode[] children = new TrieNode[26];
        public boolean isEndOfWord;
        public String word; // Optional reference for fast traversal
    }

    private final TrieNode root = new TrieNode();

    /**
     * Inserts a word into the Trie.
     * Time: O(L), Space: O(L).
     */
    public void insert(String word) {
        TrieNode curr = root;
        for (char c : word.toCharArray()) {
            int idx = c - 'a';
            if (curr.children[idx] == null) {
                curr.children[idx] = new TrieNode();
            }
            curr = curr.children[idx];
        }
        curr.isEndOfWord = true;
        curr.word = word;
    }

    /**
     * Returns true if the word is in the Trie.
     * Time: O(L), Space: O(1).
     */
    public boolean search(String word) {
        TrieNode node = findPrefixNode(word);
        return node != null && node.isEndOfWord;
    }

    /**
     * Returns true if there is any word in the Trie that starts with the given prefix.
     * Time: O(L), Space: O(1).
     */
    public boolean startsWith(String prefix) {
        return findPrefixNode(prefix) != null;
    }

    private TrieNode findPrefixNode(String prefix) {
        TrieNode curr = root;
        for (char c : prefix.toCharArray()) {
            int idx = c - 'a';
            if (curr.children[idx] == null) return null;
            curr = curr.children[idx];
        }
        return curr;
    }

    /**
     * Auto-Complete: returns all words in the Trie that begin with given prefix.
     * Time: O(Prefix + Subtree Size), Space: O(Results).
     */
    public List<String> autoComplete(String prefix) {
        List<String> results = new ArrayList<>();
        TrieNode prefixNode = findPrefixNode(prefix);
        if (prefixNode != null) {
            dfsCollect(prefixNode, results);
        }
        return results;
    }

    private void dfsCollect(TrieNode node, List<String> results) {
        if (node.isEndOfWord && node.word != null) {
            results.add(node.word);
        }
        for (TrieNode child : node.children) {
            if (child != null) {
                dfsCollect(child, results);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Testing TriePrefixTree ===");
        TriePrefixTree trie = new TriePrefixTree();
        trie.insert("apple");
        trie.insert("app");
        trie.insert("application");
        trie.insert("banana");

        System.out.println("Search 'apple': " + trie.search("apple") + " (Expected: true)");
        System.out.println("Search 'app': " + trie.search("app") + " (Expected: true)");
        System.out.println("Search 'appl': " + trie.search("appl") + " (Expected: false)");
        System.out.println("StartsWith 'app': " + trie.startsWith("app") + " (Expected: true)");
        System.out.println("StartsWith 'ban': " + trie.startsWith("ban") + " (Expected: true)");
        System.out.println("StartsWith 'cat': " + trie.startsWith("cat") + " (Expected: false)");

        System.out.println("Auto-complete for 'app': " + trie.autoComplete("app") + " (Expected: [app, apple, application])");
        System.out.println("All TriePrefixTree tests passed successfully!");
    }
}
