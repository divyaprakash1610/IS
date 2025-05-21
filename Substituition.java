import java.util.*;

public class Substituition {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Text:");
        String text = sc.nextLine();
        System.out.println("Enter Key:");
        int key = sc.nextInt();

        String encrypted = encrypt(text, key);
        System.out.println(encrypted);
        String decrypted = encrypt(encrypted, -key);
        System.out.println(decrypted);
        sc.close();
    }

    public static String encrypt(String text, int key) {
        StringBuilder result = new StringBuilder();
        for (Character c : text.toCharArray()) {
            if (Character.isLowerCase(c)) {
                result.append((char) ((c - 'a' + key + 26) % 26 + 'a'));
            } else if (Character.isUpperCase(c)) {
                result.append((char) ((c - 'A' + key + 26) % 26 + 'A'));
            } else {
                result.append(c);
            }
        }
        return result.toString();
    }
}