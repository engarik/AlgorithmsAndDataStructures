/* Kornilov Nikita, M3102, 14.03.2021 */

//package Sem2.Lab9;

import java.io.*;
import java.util.*;

public class Task3 {
    BufferedReader br;
    StringTokenizer in;
    PrintWriter out;

    class Graph {
        private final int V;
        private int E;
        private ArrayList<Integer>[] adj;

        public Graph(int V) {
            this.V = V; this.E = 0;
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
            if (v != w && !adj[v].contains(w) && !adj[w].contains(w)) {
                adj[v].add(w);
                E++;
            }

        }

        public Iterable<Integer> adj(int v) {
            return adj[v];
        }
    }

    class Bipartite {
        private boolean[] marked;
        private boolean[] color;
        private boolean isBipartite = true;

        public Bipartite(Graph graph) {
            marked = new boolean[graph.getV()];
            color = new boolean[graph.getV()];

            for (int s = 0; s < graph.getV(); s++) {
                if (!marked[s]) {
                    dfs(graph, s);
                }
            }
        }

        private void dfs(Graph graph, int v) {
            marked[v] = true;
            for (int w : graph.adj(v)) {
                if (!marked[w]) {
                    color[w] = !color[v];
                    dfs(graph, w);
                } else if (color[w] == color[v]) {
                    isBipartite = false;
                    break;
                }
            }
        }

        public boolean isBipartite() {
            return isBipartite;
        }
    }

    public static void main(String[] args) {
        String fileName = "bipartite";
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
        Graph graph = new Graph();

        Bipartite bipartite = new Bipartite(graph);

        out.println(bipartite.isBipartite() ? "YES" : "NO");
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
