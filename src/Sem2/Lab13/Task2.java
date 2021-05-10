/* Kornilov Nikita, M3102, 09.05.2021 */

package Sem2.Lab13;

import java.io.*;
import java.util.*;

public class Task2 {
    BufferedReader br;
    StringTokenizer in;
    PrintWriter out;

    final long X = 239;

    long[] xPow;

    int[] p, t;
    int m, n;

    public static void main(String[] args) {
        String fileName = "search2";
        new Task2().run(String.format("%s.in", fileName), String.format("%s.out", fileName));
    }

    public String nextToken() throws IOException {
        while (in == null || !in.hasMoreTokens()) {
            String inputString = br.readLine();
            if (inputString != null) {
                in = new StringTokenizer(inputString);
            }
            else {
                return null;
            }
        }
        return in.nextToken();
    }

    public void solve() throws IOException {
        ArrayList<Integer> pos = new ArrayList<>();
        init(nextToken(), nextToken());

        if (n >= m) {
            xPow = new long[m + 1];

            for (int i = 0; i < m + 1; i++) {
                xPow[i] = pow(X, i);
            }

            long pHash = polynomialHash(p);
            long[] tHash = buildHash();

            System.out.println(pHash);
            System.out.println(Arrays.toString(tHash));

            out: for (int i = 0; i < n - m + 1; i++) {
                if (tHash[i] != pHash)
                    continue;
                for (int j = 0; j < m; j++) {
                    if (t[i + j] != p[j])
                        continue out;
                }
                pos.add(i + 1);
            }
        }

        out.println(pos.size());
        for (int i : pos)
            out.print(i + " ");

    }

    public long[] buildHash() {
        long[] resHash = new long[n - m + 1];

        resHash[0] = polynomialHash(t, 0, m);

        for (int i = 0; i < n - m; i++) {
            resHash[i + 1] = (X * (resHash[i] - t[i] * xPow[m - 1]) + t[i + m]);
        }

        return resHash;
    }

    public long polynomialHash(int[] array) {
        return polynomialHash(array, 0, array.length);
    }

    public long polynomialHash(int[] array, int begin, int end) {
        long resHash = 0;

        for (int i = begin; i < end; i++) {
            resHash += (array[i] * xPow[end - i - 1]);
        }

        return resHash;
    }

    public void init(String p, String t) {
        m = p.length();
        n = t.length();
        this.p = new int[m];
        this.t = new int[n];

        for (int i = 0; i < m; i++) {
            this.p[i] = p.charAt(i) - 'A' + 1;
        }
        for (int i = 0; i < n; i++) {
            this.t[i] = t.charAt(i) - 'A' + 1;
        }
    }

    public long pow(long value, int pow) {
        long res = 1;

        for (int i = 0; i < pow; i++) {
            res *= value;
        }

        return res;
    }

    public void run(String inputFile, String outputFile) {
        try {
            br = new BufferedReader(new FileReader(inputFile));
            out = new PrintWriter(outputFile);
            solve();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}