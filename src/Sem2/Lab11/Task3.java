/* Kornilov Nikita, M3102, 06.04.2021 */

package Sem2.Lab11;

import java.io.*;
import java.util.*;

public class Task3 {
    BufferedReader br;
    StringTokenizer in;
    PrintWriter out;

    public static void main(String[] args) {
        String fileName = "pathbgep";
        new Task3().run(String.format("%s.in", fileName), String.format("%s.out", fileName));
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
        int n = nextInt(), m = nextInt(), curIndex;
        double d = 2.0 * m / (n * (n - 1));

        TreeMap<Integer, LinkedList<Edge>> adj = new TreeMap<>();
        for (int i = 0; i < n; i++) adj.put(i, new LinkedList<>());
        for (int i = 0; i < m; i++) {
            int from = nextInt() - 1, to = nextInt() - 1, dist = nextInt();
            adj.get(from).add(new Edge(to, dist));
            adj.get(to).add(new Edge(from, dist));
        }

        int[] distances = new int[n];
        Arrays.fill(distances, 2000000000);
        distances[0] = 0;

        if (d > 0.7) { // Для плотных графов на массивчике
            boolean[] marked = new boolean[n];

            for (int i = 0; i < n; i++) {
                curIndex = findMinIndex(distances, marked);
                marked[curIndex] = true;
                for (Edge e : adj.get(curIndex)) {
                    if (distances[e.to] > distances[curIndex] + e.dist)
                        distances[e.to] = distances[curIndex] + e.dist;
                }
            }
        } else { // Для разряженных графов на приоритетной очереди
            PriorityQueue<Vertex> vertices = new PriorityQueue<>(Comparator.comparingDouble(o -> o.dist));
            vertices.add(new Vertex(0, 0));

            Vertex curVertex;

            while (!vertices.isEmpty()) {
                curVertex = vertices.poll();
                curIndex = curVertex.id;

                if (curVertex.dist != distances[curIndex])
                    continue;

                for (Edge e : adj.get(curIndex)) {
                    if (distances[e.to] > distances[curIndex] + e.dist) {
                        distances[e.to] = distances[curIndex] + e.dist;
                        vertices.add(new Vertex(e.to, distances[e.to]));
                    }
                }
            }

        }

        for (int i = 0; i < n; i++) {
            out.print(distances[i] + " ");
        }

    }

    public int findMinIndex(int[] distances, boolean[] marked) {
        int minValue = 200000000, minIndex = 0;
        for (int i = 0; i < distances.length; i++) {
            if ((distances[i] < minValue) && !marked[i]) {
                minValue = distances[i];
                minIndex = i;
            }
        }
        return minIndex;
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
        int to;
        int dist;

        public Edge(int to, int dist) {
            this.to = to;
            this.dist = dist;
        }
    }

    private class Vertex {
        private int id;
        private double dist;

        public Vertex(int id) {
            this.id = id;
            dist = Double.POSITIVE_INFINITY;
        }

        public Vertex(int id, int dist) {
            this.id = id;
            this.dist = dist;
        }
    }
}