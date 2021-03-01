/* Kornilov Nikita, M3102, 22.02.2021 */

package Sem2.Lab8;

import java.io.*;
import java.util.*;

public class Task4 {
    BufferedReader br;
    StringTokenizer in;
    PrintWriter out;

    class Graph {
        private final int V; //
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

    class CC {
        private boolean[] marked;
        private int[] id;
        private int count;

        public CC(Graph G) {
            marked = new boolean[G.getV()];
            id = new int[G.getV()];
            for (int s = 0; s < G.getV(); s++) {
                if (!marked[s]) {
                    dfs(G, s);
                    count++;
                }
            }
        }

        private void dfs(Graph G, int v) {
            marked[v] = true;
            id[v] = count;
            for (int w :G.adj(v)) {
                if (!marked[w]) {
                    dfs(G, w);
                }
            }
        }

        public boolean isConnected(int v, int w) {
            return id[v] == id[w];
        }

        public int getId(int v) {
            return id[v];
        }

        public int getCount() {
            return count;
        }
    }

    public static void main(String[] args) {
        String fileName = "components";
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

    public void solve() throws IOException {
        Graph graph = new Graph();

        CC cc = new CC(graph);

        int M = cc.getCount();

        out.println(M);

        ArrayList<Integer>[] components;
        components = (ArrayList<Integer>[]) new ArrayList[M];

        for (int i = 0; i < M; i++) {
            components[i] = new ArrayList<>();
        }
        for (int v = 0; v < graph.getV(); v++) {
            components[cc.getId(v)].add(v);
        }

        for (int v = 0; v < graph.getV(); v++) {
            out.print(cc.getId(v) + 1 + " ");
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