/* Kornilov Nikita, M3102, 22.02.2021 */

package Sem2.Lab8;

import java.io.*;
import java.util.*;

public class Task3 {
    BufferedReader br;
    StringTokenizer in;
    PrintWriter out;

    public static void main(String[] args) {
        String inputFileName = "input.txt", outFileName = "output.txt";
        new Task3().run(String.format("%s", inputFileName), String.format("%s", outFileName));
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
        int vertices = nextInt(), edges = nextInt(), row, column;
        int[][] matrix = new int[vertices][vertices];
        boolean parallelEdges = false;

        for (int i = 0; i < edges; i++) {
            column = nextInt() - 1;
            row = nextInt() - 1;
            if (matrix[column][row] == 1) {
                parallelEdges = true;
                break;
            } else {
                matrix[column][row] = 1;
            }
        }

        if (!parallelEdges) {
            out:
            for (int i = 0; i < vertices; i++) {
                for (int j = i; j < vertices; j++) {
                    if (matrix[i][j] == matrix[j][i] && matrix[i][j] == 1) {
                        parallelEdges = true;
                        break out;
                    }
                }
            }
        }

        out.print(parallelEdges ? "YES" : "NO");
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