/* Kornilov Nikita, M3102, 22.02.2021 */

package Sem2.Lab8;

import java.io.*;
import java.util.*;

public class Task2 {
    BufferedReader br;
    StringTokenizer in;
    PrintWriter out;

    public static void main(String[] args) {
        String inputFileName = "input.txt", outFileName = "output.txt";
        new Task2().run(String.format("%s", inputFileName), String.format("%s", outFileName));
    }

    public String nextToken() throws IOException {
        while (in == null || !in.hasMoreTokens()) {
            String inputString = br.readLine();
            if (inputString != null) {
                in = new StringTokenizer(inputString);
            } else {
                return null;
            }
        }
        return in.nextToken();
    }

    public int nextInt() throws IOException {
        return Integer.parseInt(nextToken());
    }

    public void solve() throws IOException {
        int n = nextInt();
        int[][] matrix = new int[n][n];
        boolean undirected = true;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = nextInt();
            }
        }

        out: for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                if (matrix[i][j] != matrix[j][i] || (i == j) & matrix[i][j] == 1) {
                    undirected = false;
                    break out;
                }
            }
        }

        out.println(undirected ? "YES" : "NO");

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