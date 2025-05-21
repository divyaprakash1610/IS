import java.nio.charset.*;
import java.security.*;
import java.util.*;

public class SHA {
    public static String hash(String input, String algo) throws Exception {

        return HexFormat.of().formatHex(
                MessageDigest.getInstance(algo)
                        .digest(input.getBytes(StandardCharsets.UTF_8)));

    }

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter string to hash: ");
        String input = sc.nextLine();

        System.out.println("SHA-256: " + hash(input, "SHA-256"));
        System.out.println("SHA-512: " + hash(input, "SHA-512"));
        sc.close();
    }
}
