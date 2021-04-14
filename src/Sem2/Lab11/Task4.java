/* Kornilov Nikita, M3102, 08.04.2021 */
package Sem2.Lab11;

import java.io.*;
import java.util.*;

public class Task4 {
    BufferedReader br;
    StringTokenizer in;
    PrintWriter out;

    private boolean[] cycled;
    private ArrayList<Integer>[] adj;

    public static void main(String[] args) {
        String fileName = "path";
        new Task4().run(String.format("%s.in", fileName), String.format("%s.out", fileName));
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

    public long nextLong() throws IOException {
        return Long.parseLong(nextToken());
    }

    public void solve() throws IOException {
        int n = nextInt(), m = nextInt(), s = nextInt() - 1;

        HashMap<Edge, Long> edges = new HashMap<>();
        adj = (ArrayList<Integer>[]) new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            int from = nextInt() - 1, to = nextInt() - 1;
            long weight = nextLong();

            Edge edge = new Edge(from, to);

            if (edges.containsKey(edge)) {
                if (edges.get(edge) > weight) {
                    edges.replace(edge, weight);
                }
            } else {
                edges.put(edge, weight);
                adj[from].add(to);
            }
        }

        double[] distances = new double[n];
        int[] parents = new int[n];

        Arrays.fill(distances, Double.POSITIVE_INFINITY);
        distances[s] = 0;

        for (int i = 0; i < n - 1; i++) {
            for (Edge e : edges.keySet()) {
                if (distances[e.to] > distances[e.from] + edges.get(e)) {
                    distances[e.to] = distances[e.from] + edges.get(e);
                    parents[e.to] = e.from;
                }
            }
        }

        cycled = new boolean[n];

        for (Edge e : edges.keySet()) {
            if (distances[e.to] > distances[e.from] + edges.get(e)) {
                dfs(e.to);
            }
        }

        for (int i = 0; i < n; i++) {
            if (cycled[i]) {
                out.println("-");
            } else if (distances[i] == Double.POSITIVE_INFINITY) {
                out.println("*");
            } else {
                out.println((long) distances[i]);
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

    public class Edge {
        private final int from;
        private final int to;

        public Edge(int from, int to) {
            this.from = from;
            this.to = to;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Edge edge = (Edge) o;
            return to == edge.to && from == edge.from;
        }

        @Override
        public int hashCode() {
            return Objects.hash(to, from);
        }

        @Override
        public String toString() {
            return String.format("(%d:%d)", to, from);
        }
    }

    public void dfs(int vertex) {
        cycled[vertex] = true;
        for (int to : adj[vertex]) {
            if (!cycled[to])
                dfs(to);
        }
    }
}