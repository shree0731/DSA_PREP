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
}
