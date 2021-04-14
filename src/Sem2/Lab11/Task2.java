/* Kornilov Nikita, M3102, 08.04.2021 */
package Sem2.Lab11;

import java.io.*;
import java.util.*;

public class Task2 {
    BufferedReader br;
    StringTokenizer in;
    PrintWriter out;

    public static void main(String[] args) {
        String fileName = "pathsg";
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

    public int nextInt() throws IOException {
        return Integer.parseInt(nextToken());
    }

    public void solve() throws IOException {
        int n = nextInt(), m = nextInt();

        double[][] distances = new double[n][n];

        for (int i = 0; i < n; i++) { // Инициализируем матрицу смежности
            Arrays.fill(distances[i], Double.POSITIVE_INFINITY);
        }

        for (int i = 0; i < n; i++) { // Расстояния от вершин до самих себя - 0
            distances[i][i] = 0;
        }

        int from, to, dist;
        for (int i = 0; i < m; i++) { // Считываем данную матрицу смежности
            from = nextInt() - 1;
            to = nextInt() - 1;
            dist = nextInt();
            distances[from][to] = dist;
        }

        for (int k = 0; k < n; k++) { // Алгоритм Флойда-Уоршелла O(v^3)
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (distances[i][j] > distances[i][k] + distances[k][j])
                        distances[i][j] = distances[i][k] + distances[k][j];
                }
            }
        }

        for (int i = 0; i < n; i++) { // Выводим результат
            for (int j = 0; j < n; j++) {
                out.print((int)distances[i][j] + " ");
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