import java.util.*;
import java.util.stream.*;

public class Columnar {

    static int[] getKeyOrder(String key) {
        return IntStream.range(0, key.length())
                .boxed()
                .sorted(Comparator.comparingInt(i -> key.charAt(i)))
                .mapToInt(i -> i)
                .toArray();
    }

    public static String encrypt(String text, String key) {
        text = text.replaceAll("\\s+", "");
        int cols = key.length(), rows = (int) Math.ceil((double) text.length() / cols);
        while (text.length() < rows * cols)
            text += "X";

        int[] order = getKeyOrder(key);
        StringBuilder res = new StringBuilder();

        for (int col : order)
            for (int row = 0; row < rows; row++)
                res.append(text.charAt(row * cols + col));

        return res.toString();
    }

    public static String decrypt(String cipher, String key) {
        int cols = key.length(), rows = cipher.length() / cols;
        char[] plain = new char[rows * cols];
        int[] order = getKeyOrder(key);

        int idx = 0;
        for (int col : order)
            for (int row = 0; row < rows; row++)
                plain[row * cols + col] = cipher.charAt(idx++);

        return new String(plain).replaceAll("X+$", "");
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
