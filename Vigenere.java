import java.util.*;

public class Vigenere {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Text:");
        String text = sc.nextLine().toUpperCase();
        System.out.println("Enter Key:");
        String key = sc.nextLine().toUpperCase();
        String encrypted = encrypt(text, key);
        System.out.println(encrypted);
        String decrypted = decrypt(encrypted, key);
        System.out.println(decrypted);
        sc.close();
    }

    public static String encrypt(String text, String key) {
        StringBuilder result = new StringBuilder();
        int keylen = key.length();

        for (int i = 0; i < text.length(); i++) {
            int p = text.charAt(i);
            int k = key.charAt(i % keylen);
            char c = (char) ((p + k - 2 * 'A') % 26 + 'A');
            result.append(c);
        }
        return result.toString();
    }

    public static String decrypt(String text, String key) {
        StringBuilder result = new StringBuilder();
        int keylen = key.length();

        for (int i = 0; i < text.length(); i++) {
            int p = text.charAt(i);
            int k = key.charAt(i % keylen);
            char c = (char) ((p - k + 26) % 26 + 'A');
            result.append(c);
        }
        return result.toString();

    }
}
