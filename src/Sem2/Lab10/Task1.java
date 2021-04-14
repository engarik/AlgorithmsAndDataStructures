/* Kornilov Nikita, M3102, 29.03.2021 */

package Sem2.Lab10;

import java.io.*;
import java.util.*;

public class Task1 {
    BufferedReader br;
    StringTokenizer in;
    PrintWriter out;

    public static void main(String[] args) {
        new Task1().run("input.txt", "output.txt");
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
        Graph graph = new Graph();
        for (int i = 0; i < graph.getV(); i++) {
            out.print(graph.adj[i].size() + " ");
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

    private class Graph {
        private final int V;
        private int E;
        private ArrayList<Integer>[] adj;

        public Graph(int V) {
            this.V = V;
            this.E = 0;
            adj = (ArrayList<Integer>[]) new ArrayList[V];
            for (int v = 0; v < V; v++) {
                adj[v] = new ArrayList<>();
            }
        }

        public Graph() throws IOException {
            this(nextInt());
            int E = nextInt();

            for (int i = 0; i < E; i++) {
                int v = nextInt() - 1, w = nextInt() - 1;
                addEdge(v, w);
            }
        }


        public int getV() {
            return V;
        }

        public int getE() {
            return E;
        }

        public void addEdge(int v, int w) {
            adj[v].add(w);
            adj[w].add(v);
            E++;
        }

        public Iterable<Integer> adj(int v) {
            return adj[v];
        }

        @Override
        public String toString() {
            StringBuilder s = new StringBuilder(String.format("%d vertices, %d edges\n", V, E));
            for (int v = 0; v < V; v++) {
                s.append(v).append(": ");
                for (int w : this.adj(v)) {
                    s.append(w).append(" ");
                }
                s.append("\n");
            }
            return s.toString();
        }
    }
}