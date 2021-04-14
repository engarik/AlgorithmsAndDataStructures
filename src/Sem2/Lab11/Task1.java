/* Kornilov Nikita, M3102, 06.04.2021 */
package Sem2.Lab11;

import java.io.*;
import java.util.*;

public class Task1 {
    BufferedReader br;
    StringTokenizer in;
    PrintWriter out;

    boolean[] marked;
    double[] distances;

    public static void main(String[] args) {
        String fileName = "pathmgep";
        new Task1().run(String.format("%s.in", fileName), String.format("%s.out", fileName));
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

    public short nextShort() throws IOException {
        return Short.parseShort(nextToken());
    }

    public int nextInt() throws IOException {
        return Integer.parseInt(nextToken());
    }

    public void solve() throws IOException {
        short n = nextShort(), s = (short) (nextShort() - 1), f = (short) (nextShort() - 1), curIndex;
        int m = 0, dist;

        int[][] matrix = new int[n][n];
        for (short from = 0; from < n; from++) {
            for (short to = 0; to < n; to++) {
                dist = nextInt();
                matrix[from][to] = dist;
                if (dist != -1 && dist != 0) {
                    m++;
                }
            }
        }

        double d = 2.0 * m / (n * (n - 1));
        distances = new double[n];
        Arrays.fill(distances, Double.POSITIVE_INFINITY);
        distances[s] = 0;

        if (d > 0.7) { // Для плотных графов на массивчике
            marked = new boolean[n];

            for (short i = 0; i < n; i++) {
                curIndex = findMinIndex();
                marked[curIndex] = true;
                for (short j = 0; j < n; j++) {
                    if (matrix[curIndex][j] != -1 && matrix[curIndex][j] != 0)
                        if (distances[j] > distances[curIndex] + matrix[curIndex][j])
                            distances[j] = distances[curIndex] + matrix[curIndex][j];
                }
            }
        } else { // Для разряженных графов на приоритетной очереди
            PriorityQueue<Vertex> vertices = new PriorityQueue<>(Comparator.comparingDouble(o -> o.dist));
            vertices.add(new Vertex(s, 0));

            Vertex curVertex;

            while (!vertices.isEmpty()) {
                curVertex = vertices.poll();
                curIndex = curVertex.id;

                if (curVertex.dist != distances[curIndex])
                    continue;
                for (short j = 0; j < n; j++) {
                    if (matrix[curIndex][j] != -1 && matrix[curIndex][j] != 0)
                        if (distances[j] > distances[curIndex] + matrix[curIndex][j]) {
                            distances[j] = distances[curIndex] + matrix[curIndex][j];
                            vertices.add(new Vertex(j, distances[j]));
                        }
                }

            }

        }

        out.println(distances[f] == Double.POSITIVE_INFINITY ? -1 : (long) distances[f]);
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

    private class Vertex {
        private short id;
        private double dist;

        public Vertex(short id, double dist) {
            this.id = id;
            this.dist = dist;
        }
    }

    public short findMinIndex() {
        double minValue = Double.POSITIVE_INFINITY;
        short minIndex = 0;
        for (short i = 0; i < distances.length; i++) {
            if ((distances[i] < minValue) && !marked[i]) {
                minValue = distances[i];
                minIndex = i;
            }
        }
        return minIndex;
    }
}