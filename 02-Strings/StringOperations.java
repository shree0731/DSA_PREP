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
}
