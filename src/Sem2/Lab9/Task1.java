/* Kornilov Nikita, M3102, 14.03.2021 */

//package Sem2.Lab9;

import java.io.*;
import java.util.*;

public class Task1 {
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
            E++;
        }

        public Iterable<Integer> adj(int v) {
            return adj[v];
        }
    }

    class TopSort {
        private byte[] color; // 0 - white, 1 - gray, 2 - black

        public TopSort(Graph graph) {
            boolean cycle = false;
            color = new byte[graph.getV()];
            ArrayList<Integer> list = new ArrayList<>();

            for (int s = 0; s < graph.getV(); s++) {
                if (!dfs(graph, s, list)) {
                    cycle = true;
                    break;
                }
            }

            if (!cycle) {
                Collections.reverse(list);
                for (var i : list)
                    out.print((i + 1) + " ");
            } else {
                out.println(-1);
            }
        }

        private boolean dfs(Graph graph, int v, ArrayList<Integer> list) {
            switch (color[v]) {
                case 0 : {
                    color[v] = 1;

                    for (int w : graph.adj(v)) {
                        if (!dfs(graph, w, list)) return false;
                    }
                    color[v] = 2;
                    list.add(v);
                    return true;
                }
                case 1 : {
                    return false;
                }
                default:
                    return true;
            }
        }
    }
    
    public static void main(String[] args) {
        String fileName = "topsort";
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

    public int nextInt() throws IOException {
        return Integer.parseInt(nextToken());
    }

    public void solve() throws IOException {
        Graph graph = new Graph();

        TopSort topSort = new TopSort(graph);
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
