import java.util.*;

class DiffieHellman {
    private static long power(long base, long exponent, long mod) {
        long result = 1;

        while (exponent > 0) {
            if ((exponent % 2) == 1)
                result = (result * base) % mod;

            base = (base * base) % mod;
            exponent >>= 1;
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        System.out.print("Enter value of P: ");
        long P = s.nextLong();

        System.out.print("Enter value of G: ");
        long G = s.nextLong();

        System.out.print("Enter private key a for Sender: ");
        long a = s.nextLong();

        System.out.print("Enter private key b for Receiver: ");
        long b = s.nextLong();

        long x = power(G, a, P);
        long y = power(G, b, P);

        long ka = power(y, a, P);
        long kb = power(x, b, P);

        System.out.println("Secret key for the Sender is: " + ka);
        System.out.println("Secret key for the Receiver is: " + kb);

        s.close();
    }
}
