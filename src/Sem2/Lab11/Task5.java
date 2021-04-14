/* Kornilov Nikita, M3102, 08.04.2021 */
package Sem2.Lab11;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class Task5 {
    BufferedReader br;
    StringTokenizer in;
    PrintWriter out;

    public static void main(String[] args) {
        String fileName = "negcycle";
        new Task5().run(String.format("%s.in", fileName), String.format("%s.out", fileName));
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
        int[] parents = new int[n];
        double[] distances = new double[n];
        boolean[] visited = new boolean[n];

        ArrayList<Edge> edges = new ArrayList<>();

        for (int from = 0; from < n; from++) {
            for (int to = 0; to < n; to++) {
                int dist = nextInt();
                if (dist != 1_000_000_000) {
                    edges.add(new Edge(from, to, dist));
                    parents[to] = from;
                }
            }
        }

        boolean foundCycle = false;
        int lastVertex = -1;

        for (int i = 0; i < n && !foundCycle; i++) { // Запускаем БФ из всех вершин, пока не найдем цикл
            if (visited[i])
                continue;

            Arrays.fill(distances, Double.POSITIVE_INFINITY);
            distances[i] = 0;

            for (int j = 0; j < n; j++) {
                lastVertex = -1;
                for (Edge e : edges) {
                    if (distances[e.toVertex] > distances[e.fromVertex] + e.weight) {
                        distances[e.toVertex] = distances[e.fromVertex] + e.weight;
                        lastVertex = e.toVertex;
                        parents[e.toVertex] = e.fromVertex;
                        visited[e.toVertex] = visited[e.fromVertex] = true;
                    }
                }
            }

            foundCycle = lastVertex != -1;
        }

        if (lastVertex == -1) {
            out.println("NO");
        } else {
            ArrayList<Integer> cycle = new ArrayList<>();
            int y = lastVertex;

            for (int i = 0; i < n; i++) { // Пытаемся вернутся в цикл
                y = parents[y];
            }

            for (int curVertex = y; ; curVertex = parents[curVertex]) { // Проходимся по цикл и записываем
                cycle.add(curVertex);
                if (curVertex == y && cycle.size() > 1) break;
            }

            Collections.reverse(cycle);
            out.println("YES");
            out.println(cycle.size());
            for (int i : cycle) {
                out.print(i + 1 + " ");
            }
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

    private class Edge {
        private final int fromVertex;
        private final int toVertex;
        private final int weight;

        public Edge(int fromVertex, int toVertex, int weight) {
            this.fromVertex = fromVertex;
            this.toVertex = toVertex;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "Edge{" +
                    "fromVertex=" + fromVertex +
                    ", toVertex=" + toVertex +
                    ", weight=" + weight +
                    '}';
        }
    }
}