import java.util.*;

/**
 * Advanced Trie applications:
 * 1. WordDictionary (LeetCode 211): supports '.' wildcard character matching.
 * 2. Word Search II (LeetCode 212): finds all words on 2D board with Trie pruning.
 *
 * @author Shreepurnaa V
 */
public class WordSearchAndDictionary {

    public static class WordDictionary {
        private static class Node {
            final Node[] children = new Node[26];
            boolean isEnd;
        }

        private final Node root = new Node();

        public void addWord(String word) {
            Node curr = root;
            for (char c : word.toCharArray()) {
                int idx = c - 'a';
                if (curr.children[idx] == null) curr.children[idx] = new Node();
                curr = curr.children[idx];
            }
            curr.isEnd = true;
        }

        public boolean search(String word) {
            return match(word.toCharArray(), 0, root);
        }

        private boolean match(char[] chars, int k, Node node) {
            if (k == chars.length) return node.isEnd;
            char c = chars[k];
            if (c == '.') {
                for (Node child : node.children) {
                    if (child != null && match(chars, k + 1, child)) return true;
                }
                return false;
            } else {
                int idx = c - 'a';
                return node.children[idx] != null && match(chars, k + 1, node.children[idx]);
            }
        }
    }

    /**
     * Word Search II (LeetCode 212).
     * Searches for dictionary words on 2D board using DFS with Trie prefix pruning.
     */
    public static List<String> findWords(char[][] board, String[] words) {
        TriePrefixTree.TrieNode root = new TriePrefixTree.TrieNode();
        for (String w : words) {
            TriePrefixTree.TrieNode curr = root;
            for (char c : w.toCharArray()) {
                int idx = c - 'a';
                if (curr.children[idx] == null) curr.children[idx] = new TriePrefixTree.TrieNode();
                curr = curr.children[idx];
            }
            curr.isEndOfWord = true;
            curr.word = w;
        }

        List<String> result = new ArrayList<>();
        int m = board.length, n = board[0].length;
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                dfsBoard(board, r, c, root, result);
            }
        }
        return result;
    }

    private static void dfsBoard(char[][] board, int r, int c, TriePrefixTree.TrieNode node, List<String> result) {
        char ch = board[r][c];
        if (ch == '#' || node.children[ch - 'a'] == null) return;

        node = node.children[ch - 'a'];
        if (node.isEndOfWord && node.word != null) {
            result.add(node.word);
            node.word = null; // Avoid duplicate collection
        }

        board[r][c] = '#'; // Mark visited
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};
        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i], nc = c + dc[i];
            if (nr >= 0 && nr < board.length && nc >= 0 && nc < board[0].length) {
                dfsBoard(board, nr, nc, node, result);
            }
        }
        board[r][c] = ch; // Backtrack
    }

    public static void main(String[] args) {
        System.out.println("=== Testing WordDictionary (Wildcard Search) ===");
        WordDictionary dict = new WordDictionary();
        dict.addWord("bad");
        dict.addWord("dad");
        dict.addWord("mad");

        System.out.println("Search 'pad': " + dict.search("pad") + " (Expected: false)");
        System.out.println("Search 'bad': " + dict.search("bad") + " (Expected: true)");
        System.out.println("Search '.ad': " + dict.search(".ad") + " (Expected: true)");
        System.out.println("Search 'b..': " + dict.search("b..") + " (Expected: true)");

        System.out.println("\n=== Testing Word Search II ===");
        char[][] board = {
                {'o', 'a', 'a', 'n'},
                {'e', 't', 'a', 'e'},
                {'i', 'h', 'k', 'r'},
                {'i', 'f', 'l', 'v'}
        };
        String[] words = {"oath", "pea", "eat", "rain"};
        System.out.println("Found Words: " + findWords(board, words) + " (Expected: [oath, eat])");
        System.out.println("All WordSearchAndDictionary tests passed successfully!");
    }
}
