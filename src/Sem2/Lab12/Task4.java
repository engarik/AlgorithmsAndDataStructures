/* Kornilov Nikita, M3102, 18.04.2021 */

package Sem2.Lab12;

import java.io.*;
import java.util.*;

public class Task4 {
    BufferedReader br;
    StringTokenizer in;
    PrintWriter out;

    int[][] field;
    int n, m;

    public static void main(String[] args) {
        String fileName = "knight2";
        new Task4().run(String.format("%s.in", fileName), String.format("%s.out", fileName));
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

    public void solve() throws IOException {
        n = nextInt();
        m = nextInt();
        field = new int[n][m];

        field[0][0] = 1;

        for (int i = m - 1; i >= -(n - 1); i--) {
            for (int j = 0; j < n; j++) {
                if (i + j >= 0 && i + j < m) {
                    if (field[j][m - (i + j) - 1] > 0) {
                        modify(j, m - (i + j) - 1);
                    }
                }
            }
        }

        out.println(field[n - 1][m - 1]);
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

    private void modify(int x, int y) {
        increaseIfInBounds(field[x][y], x + 2, y - 1);
        increaseIfInBounds(field[x][y],x + 2, y + 1);
        increaseIfInBounds(field[x][y], x - 1, y + 2);
        increaseIfInBounds(field[x][y], x + 1, y + 2);
    }

    private void increaseIfInBounds(int value, int x, int y) {
        if (inBounds(x, y)) {
            field[x][y] += value;
        }
    }

    private boolean inBounds(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < m;
    }
}