import java.util.*;

public class Hillcipher {
    private static int[][] key = new int[3][3];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                key[i][j] = sc.nextInt();
        sc.nextLine();
        String pt = sc.nextLine().replaceAll("\\s", "").toUpperCase();
        String original = sc.nextLine();
        while (pt.length() % 3 != 0)
            pt += "X";
        String ct = crypt(pt, key);
        System.out.println("Encrypted: " + ct);
        int[][] invKey = inverse(key);
        String dt = crypt(ct, invKey);
        System.out.println("Decrypted: " + restore(original, dt));
    }

    static String crypt(String text, int[][] matrix) {
        StringBuilder out = new StringBuilder();
        for (int i = 0; i < text.length(); i += 3) {
            int[] vec = { text.charAt(i) - 'A', text.charAt(i + 1) - 'A', text.charAt(i + 2) - 'A' };
            for (int j = 0; j < 3; j++) {
                int val = 0;
                for (int k = 0; k < 3; k++)
                    val += matrix[j][k] * vec[k];
                out.append((char) ((val % 26 + 26) % 26 + 'A'));
            }
        }
        return out.toString();
    }

    static int[][] inverse(int[][] m) {
        int d = det(m), invD = modInv(d, 26);
        int[][] adj = {
                { (m[1][1] * m[2][2] - m[1][2] * m[2][1]), (m[0][2] * m[2][1] - m[0][1] * m[2][2]),
                        (m[0][1] * m[1][2] - m[0][2] * m[1][1]) },
                { (m[1][2] * m[2][0] - m[1][0] * m[2][2]), (m[0][0] * m[2][2] - m[0][2] * m[2][0]),
                        (m[0][2] * m[1][0] - m[0][0] * m[1][2]) },
                { (m[1][0] * m[2][1] - m[1][1] * m[2][0]), (m[0][1] * m[2][0] - m[0][0] * m[2][1]),
                        (m[0][0] * m[1][1] - m[0][1] * m[1][0]) }
        };
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                adj[i][j] = ((adj[i][j] * invD) % 26 + 26) % 26;
        return adj;
    }

    static int det(int[][] m) {
        return ((m[0][0] * (m[1][1] * m[2][2] - m[1][2] * m[2][1])
                - m[0][1] * (m[1][0] * m[2][2] - m[1][2] * m[2][0])
                + m[0][2] * (m[1][0] * m[2][1] - m[1][1] * m[2][0])) % 26 + 26) % 26;
    }

    static int modInv(int a, int m) {
        a %= m;
        for (int x = 1; x < m; x++)
            if ((a * x) % m == 1)
                return x;
        return 1;
    }

    static String restore(String original, String decrypted) {
        StringBuilder res = new StringBuilder();
        int idx = 0;
        for (char c : original.toCharArray())
            res.append(c == ' ' ? ' ' : decrypted.charAt(idx++));
        return res.toString();
    }
}
