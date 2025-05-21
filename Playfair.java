import java.util.*;

public class Playfair {
    private char[][] mat = new char[5][5];

    public Playfair(String key) {
        key = key.toUpperCase().replace("J", "I");
        Set<Character> set = new LinkedHashSet<>();
        for (char c : key.toCharArray())
            if (Character.isLetter(c))
                set.add(c);
        for (char c = 'A'; c <= 'Z'; c++)
            if (c != 'J')
                set.add(c);
        Iterator<Character> it = set.iterator();
        for (int i = 0; i < 5; i++)
            for (int j = 0; j < 5; j++)
                mat[i][j] = it.next();
    }

    private String format(String text) {
        text = text.toUpperCase().replace("J", "I").replaceAll("[^A-Z]", "");
        StringBuilder sb = new StringBuilder(text);
        for (int i = 0; i < sb.length() - 1; i += 2)
            if (sb.charAt(i) == sb.charAt(i + 1))
                sb.insert(i + 1, 'X');
        if (sb.length() % 2 != 0)
            sb.append('X');
        return sb.toString();
    }

    private int[] pos(char c) {
        for (int i = 0; i < 5; i++)
            for (int j = 0; j < 5; j++)
                if (mat[i][j] == c)
                    return new int[] { i, j };
        return null;
    }

    private String run(String txt, boolean enc) {
        txt = format(txt);
        StringBuilder res = new StringBuilder();
        int d = enc ? 1 : -1;
        for (int i = 0; i < txt.length(); i += 2) {
            int[] a = pos(txt.charAt(i)), b = pos(txt.charAt(i + 1));
            if (a[0] == b[0])
                res.append(mat[a[0]][(a[1] + d + 5) % 5]).append(mat[b[0]][(b[1] + d + 5) % 5]);
            else if (a[1] == b[1])
                res.append(mat[(a[0] + d + 5) % 5][a[1]]).append(mat[(b[0] + d + 5) % 5][b[1]]);
            else
                res.append(mat[a[0]][b[1]]).append(mat[b[0]][a[1]]);
        }
        return enc ? res.toString() : clean(res.toString());
    }

    private String clean(String text) {
        StringBuilder sb = new StringBuilder(text);
        for (int i = 1; i < sb.length() - 1; i++)
            if (sb.charAt(i) == 'X' && sb.charAt(i - 1) == sb.charAt(i + 1))
                sb.deleteCharAt(i--);
        if (sb.length() > 0 && sb.charAt(sb.length() - 1) == 'X')
            sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    public String encrypt(String pt) {
        return run(pt, true);
    }

    public String decrypt(String ct) {
        return run(ct, false);
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.print("Key: ");
        Playfair pf = new Playfair(s.nextLine());
        System.out.print("Plaintext: ");
        String ct = pf.encrypt(s.nextLine());
        System.out.println("Encrypted: " + ct);
        System.out.println("Decrypted: " + pf.decrypt(ct));
    }
}
