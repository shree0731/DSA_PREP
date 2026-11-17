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
}
