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
        if (s == null) return null;
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
}
