/* Kornilov Nikita, M3102, 25.04.2021 */

package Sem2.Lab12;

import java.io.*;
import java.util.*;

public class Task8 {
    BufferedReader br;
    StringTokenizer in;
    PrintWriter out;

    int[] colours;
    long array[][];

    public static void main(String[] args) {
        new Task8().run();
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

    public int nextInt() throws IOException {
        return Integer.parseInt(nextToken());
    }

    private long subPalindromes(int i, int j) {
        if (i > j) {
            return 0;
        } else if (i == j) {
            return array[i][j] = 1;
        } else if (array[i][j] != -1) {
            return array[i][j];
        } else {
            if (colours[i] == colours[j]) {
                array[i][j] = (
                        subPalindromes(i + 1, j) +
                                subPalindromes(i, j - 1) +
                                1
                ) % 1_000_000_000;
            } else {
                array[i][j] = (
                        subPalindromes(i + 1, j) +
                                subPalindromes(i, j - 1) -
                                subPalindromes(i + 1, j - 1)
                ) % 1_000_000_000;
            }
            return array[i][j];
        }
    }

    public void solve() throws IOException {
        int n = nextInt();
        colours = new int[n];

        for (int i = 0; i < n; i++) {
            colours[i] = nextInt();
        }

        array = new long[n][n];

        for (int i = 0; i < n; i++) {
            Arrays.fill(array[i], -1);
        }

        long res = subPalindromes(0, n - 1);

        if (res < 0) {
            out.println(res + 1_000_000_000);
        } else {
            out.println(res);
        }
    }

    public void run() {
        try {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
            solve();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}