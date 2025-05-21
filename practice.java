import java.util.*;
import java.util.stream.*;

public class practice {
    static int[] getKeyOrder(String key) {
        return IntStream.range(0, key.length())
                .boxed()
                .sorted(Comparator.comparingInt(i -> key.charAt(i)))
                .mapToInt(i -> i).toArray();

    }

    public static String encrypt(String text, String key) {
        StringBuilder result = new StringBuilder();
        int order[] = getKeyOrder(key);
        for (int i : order) {
            result.append(text.charAt(i));
        }
        return result.toString();
    }

    public static String decrypt(String text, String key) {
        int[] order = getKeyOrder(key);
        int col = key.length();
        char[] result = new char[col];
        int ind = 0;
        for (int i : order) {
            result[i] = text.charAt(ind++);
        }
        return new String(result);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter plaintext: ");
        String text = sc.nextLine();
        System.out.print("Enter key: ");
        String key = sc.nextLine().toUpperCase();

        String enc = encrypt(text, key);
        System.out.println("Encrypted: " + enc);

        String dec = decrypt(enc, key);
        System.out.println("Decrypted: " + dec);
        sc.close();
    }
}
