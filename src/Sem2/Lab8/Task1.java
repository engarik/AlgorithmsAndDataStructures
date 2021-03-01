/* Kornilov Nikita, M3102, 22.02.2021 */

package Sem2.Lab8;

import java.io.*;
import java.util.*;

public class Task1 {
    BufferedReader br;
    StringTokenizer in;
    PrintWriter out;

    public static void main(String[] args) {
        String inputFileName = "input.txt", outFileName = "output.txt";
        new Task1().run(String.format("%s", inputFileName), String.format("%s", outFileName));
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
        int vertices = nextInt(), edges = nextInt(), row, column;
        int[][] matrix = new int[vertices][vertices];

        for (int i = 0; i < edges; i++) {
            column = nextInt() - 1;
            row = nextInt() - 1;
            matrix[column][row] = 1;
        }

        printArray(matrix);
    }

    public void printArray(int[][] matrix) {
        for (int[] row : matrix) {
            for (int value : row) {
                out.print(value + " ");
            }
            out.println();
        }
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