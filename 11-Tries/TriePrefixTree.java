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
}
