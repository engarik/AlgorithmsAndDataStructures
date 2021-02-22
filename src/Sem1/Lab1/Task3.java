/* Kornilov Nikita, M3102, 10.09.2020 */
package Sem1.Lab1;

import java.io.*;
import java.util.*;

public class Task3 {
    BufferedReader br;
    StringTokenizer in;
    PrintWriter out;

    public static void main(String[] args) {
        new Task3().run("turtle.in", "turtle.out");
    }

    public String nextToken() throws IOException {
        while (in == null || !in.hasMoreTokens()) {
            in = new StringTokenizer(br.readLine());
        }
        return in.nextToken();
    }

    public int nextInt() throws IOException {
        return Integer.parseInt(nextToken());
    }

    public void solve() throws IOException {
        int h = nextInt(), w = nextInt();
        int[][] field = new int[h][w];

        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                field[i][j] = nextInt();
            }
        }

        for (int i = 1; i < w; i++) {
            field[h - 1][i] += field[h - 1][i - 1];
        }

        for (int i = h - 2; i > -1; i--) {
            field[i][0] += field[i + 1][0];
        }

        for (int i = h - 2; i > -1 ; i--) {
            for (int j = 1; j < w; j++) {
                field[i][j] += Math.max(field[i][j - 1], field[i + 1][j]);
            }
        }

        out.println(field[0][w - 1]);
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