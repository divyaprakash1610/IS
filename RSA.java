import java.math.BigInteger;
import java.util.Scanner;

public class RSA {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input message to encrypt
        System.out.print("Enter the message (number) to encrypt: ");
        BigInteger msg = sc.nextBigInteger();

        // Input primes
        System.out.print("Enter first prime (p): ");
        BigInteger p = sc.nextBigInteger();

        System.out.print("Enter second prime (q): ");
        BigInteger q = sc.nextBigInteger();

        BigInteger n = p.multiply(q);
        BigInteger z = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));

        // Choose public exponent e such that gcd(e, z) = 1
        BigInteger e = BigInteger.valueOf(2);
        while (e.compareTo(z) < 0 && !e.gcd(z).equals(BigInteger.ONE)) {
            e = e.add(BigInteger.ONE);
        }

        // Calculate private exponent d (modular inverse of e mod z)
        BigInteger d = e.modInverse(z);

        System.out.println("\nPublic Key (e, n): (" + e + ", " + n + ")");
        System.out.println("Private Key (d, n): (" + d + ", " + n + ")");

        // Encrypt: c = msg^e mod n
        BigInteger encrypted = msg.modPow(e, n);
        System.out.println("Encrypted message: " + encrypted);

        // Decrypt: msg = c^d mod n
        BigInteger decrypted = encrypted.modPow(d, n);
        System.out.println("Decrypted message: " + decrypted);

        sc.close();
    }
}
