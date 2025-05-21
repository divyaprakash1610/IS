import java.security.*;
import java.util.*;

public class MD5 {
    public static String hash(String input) throws Exception {

        byte[] bytes = MessageDigest.getInstance("MD5").digest(input.getBytes());
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes)
            sb.append(String.format("%02x", b));
        return sb.toString();

    }

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a string to hash with MD5: ");
        System.out.println("MD5 hash:\n" + hash(sc.nextLine()));
        sc.close();
    }
}