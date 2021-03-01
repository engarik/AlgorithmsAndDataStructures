/* Kornilov Nikita, M3102, 28.02.2021 */

package Sem2.Lab8;

import java.io.*;
import java.util.*;

public class Task5 {
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

    class BreadthFirstClass {
        private boolean[] marked;
        private int[] path;

        public BreadthFirstClass(Graph G) {
            marked = new boolean[G.getV()];
            path = new int[G.getV()];
            bfs(G, 0);
        }

        private void bfs(Graph G, int s) {
            Queue<Integer> queue = new ArrayDeque<>();
            marked[s] = true;
            path[0] = 0;
            queue.add(s);
            while (!queue.isEmpty()) {
                int v = queue.poll();
                for (int w : G.adj(v)) {
                    if (!marked[w]) {
                        path[w] = path[v] + 1;
                        marked[w] = true;
                        queue.add(w);
                    }
                }

            }
        }

        public String printPathLen() {
            StringBuilder builder = new StringBuilder();
            for (int len : path) {
                builder.append(len);
                builder.append(' ');
            }
            return builder.toString();
        }
    }

    public static void main(String[] args) {
        String fileName = "pathbge1";
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
        Graph graph = new Graph();

        BreadthFirstClass bfs = new BreadthFirstClass(graph);

        out.println(bfs.printPathLen());
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
