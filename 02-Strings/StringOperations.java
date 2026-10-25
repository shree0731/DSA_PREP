import java.util.*;

/**
 * StringOperations provides foundational string manipulation techniques,
 * memory experiments (String Pool), and algorithmic string utilities.
 */
public class StringOperations {

    /**
     * Demonstrates the difference between reference equality (==) and value equality (.equals()),
     * as well as the effect of String.intern().
     */
    public static void demonstrateStringPool() {
        String s1 = "hello";
        String s2 = "hello";
        String s3 = new String("hello");
        String s4 = s3.intern();

        System.out.println("--- String Pool & Equality Demonstration ---");
        System.out.println("s1 == s2 (both literals): " + (s1 == s2));           // true (same pool object)
        System.out.println("s1 == s3 (literal vs heap): " + (s1 == s3));         // false (different references)
        System.out.println("s1.equals(s3) (value check): " + s1.equals(s3));     // true (identical content)
        System.out.println("s1 == s4 (literal vs interned): " + (s1 == s4));     // true (intern returns pool ref)
    }

    /**
     * Reverses a string using a two-pointer approach on a char array.
     * Time Complexity: O(N)
     * Space Complexity: O(N) for char array
     * 
     * @param str input string
     * @return reversed string
     */
    public static String reverseString(String str) {
        if (str == null || str.length() <= 1) return str;
        char[] chars = str.toCharArray();
        int left = 0;
        int right = chars.length - 1;

        while (left < right) {
            char temp = chars[left];
            chars[left] = chars[right];
            chars[right] = temp;
            left++;
            right--;
        }
        return new String(chars);
    }

    /**
     * Reverses the order of words in a given string, trimming leading/trailing
     * and reducing multiple spaces to single spaces (LeetCode #151).
     * Time Complexity: O(N)
     * Space Complexity: O(N)
     * 
     * @param s input string containing words
     * @return string with words in reversed order
     */
    public static String reverseWords(String s) {
        if (s == null) throw new IllegalArgumentException("Input string cannot be null");
        String[] words = s.trim().split("\\s+");
        StringBuilder sb = new StringBuilder();

        for (int i = words.length - 1; i >= 0; i--) {
            sb.append(words[i]);
            if (i > 0) sb.append(" ");
        }
        return sb.toString();
    }

    /**
     * Checks if s2 is a rotation of s1.
     * Property: If s2 is a rotation of s1, then s2 must be a substring of (s1 + s1).
     * Time Complexity: O(N)
     * Space Complexity: O(N)
     * 
     * @param s1 base string
     * @param s2 candidate rotation string
     * @return true if s2 is rotation of s1, false otherwise
     */
    public static boolean isRotation(String s1, String s2) {
        if (s1 == null || s2 == null) return false;
        if (s1.length() != s2.length()) return false;
        String doubled = s1 + s1;
        return doubled.contains(s2);
    }

    /**
     * Counts vowels, consonants, digits, and special characters in a string.
     * Time Complexity: O(N)
     * Space Complexity: O(1)
     * 
     * @param str input string
     * @return 4-element array [vowels, consonants, digits, others]
     */
    public static int[] countCharacters(String str) {
        int vowels = 0, consonants = 0, digits = 0, others = 0;
        if (str == null) return new int[]{0, 0, 0, 0};

        String lower = str.toLowerCase();
        for (int i = 0; i < lower.length(); i++) {
            char ch = lower.charAt(i);
            if (ch >= 'a' && ch <= 'z') {
                if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
                    vowels++;
                } else {
                    consonants++;
                }
            } else if (ch >= '0' && ch <= '9') {
                digits++;
            } else {
                others++;
            }
        }
        return new int[]{vowels, consonants, digits, others};
    }

    /**
     * Performs basic string compression using the counts of repeated characters (Run-Length Encoding).
     * If compressed string is not smaller than original, returns original string.
     * Time Complexity: O(N)
     * Space Complexity: O(N)
     * 
     * @param str input string
     * @return compressed string or original if compressed is longer/equal
     */
    public static String compress(String str) {
        if (str == null || str.length() <= 2) return str;

        StringBuilder sb = new StringBuilder();
        int count = 1;

        for (int i = 0; i < str.length(); i++) {
            if (i + 1 < str.length() && str.charAt(i) == str.charAt(i + 1)) {
                count++;
            } else {
                sb.append(str.charAt(i)).append(count);
                count = 1;
            }
        }

        String compressed = sb.toString();
        return compressed.length() < str.length() ? compressed : str;
    }

    /**
     * Determines if two strings are isomorphic (LeetCode #205).
     * Two strings s and t are isomorphic if characters in s can be replaced to get t.
     * Time Complexity: O(N)
     * Space Complexity: O(1) auxiliary (fixed ASCII 256 arrays)
     * 
     * @param s first string
     * @param t second string
     * @return true if isomorphic, false otherwise
     */
    public static boolean areIsomorphic(String s, String t) {
        if (s == null || t == null) return false;
        if (s.length() != t.length()) return false;

        int[] mapS = new int[256];
        int[] mapT = new int[256];
        Arrays.fill(mapS, -1);
        Arrays.fill(mapT, -1);

        for (int i = 0; i < s.length(); i++) {
            char c1 = s.charAt(i);
            char c2 = t.charAt(i);

            if (mapS[c1] == -1 && mapT[c2] == -1) {
                mapS[c1] = c2;
                mapT[c2] = c1;
            } else if (mapS[c1] != c2 || mapT[c2] != c1) {
                return false;
            }
        }
        return true;
    }

    /**
     * Finds the index of the first non-repeating character in a string (LeetCode #387).
     * Time Complexity: O(N)
     * Space Complexity: O(1) auxiliary (26 lower-case letter frequency array)
     * 
     * @param s input string
     * @return 0-based index of first unique character, or -1 if none exists
     */
    public static int firstUniqChar(String s) {
        if (s == null || s.length() == 0) return -1;
        int[] freq = new int[26];

        for (int i = 0; i < s.length(); i++) {
            freq[s.charAt(i) - 'a']++;
        }

        for (int i = 0; i < s.length(); i++) {
            if (freq[s.charAt(i) - 'a'] == 1) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Checks if a string is null, empty, or consists only of whitespace.
     * 
     * @param str input string
     * @return true if blank, false otherwise
     */
    public static boolean isBlank(String str) {
        return str == null || str.trim().isEmpty();
    }

    public static void main(String[] args) {
        System.out.println("=== Testing StringOperations ===");
        demonstrateStringPool();

        System.out.println("\n--- String Reversal ---");
        String original = "DataStructures";
        System.out.println("Original: " + original);
        System.out.println("Reversed: " + reverseString(original));

        System.out.println("\n--- Reverse Words ---");
        String sentence = "  the sky   is blue  ";
        System.out.println("Input sentence : '" + sentence + "'");
        System.out.println("Reversed words : '" + reverseWords(sentence) + "'");

        System.out.println("\n--- Rotation Check ---");
        System.out.println("isRotation('waterbottle', 'erbottlewat'): " + isRotation("waterbottle", "erbottlewat"));
        System.out.println("isRotation('hello', 'world'): " + isRotation("hello", "world"));

        System.out.println("\n--- Character Counting ---");
        int[] counts = countCharacters("Hello World 2026!");
        System.out.println("[Vowels, Consonants, Digits, Others]: " + Arrays.toString(counts));

        System.out.println("\n--- String Compression ---");
        System.out.println("aabcccccaaa -> " + compress("aabcccccaaa"));
        System.out.println("abcdef -> " + compress("abcdef"));

        System.out.println("\n--- Isomorphic Strings ---");
        System.out.println("egg & add: " + areIsomorphic("egg", "add"));
        System.out.println("foo & bar: " + areIsomorphic("foo", "bar"));

        System.out.println("\n--- First Unique Character ---");
        System.out.println("leetcode: idx=" + firstUniqChar("leetcode"));
        System.out.println("loveleetcode: idx=" + firstUniqChar("loveleetcode"));
    }
}
