/* Kornilov Nikita, M3102, 14.03.2021 */

package Sem2.Lab9;

import java.io.*;
import java.util.*;

public class Task2 {
    BufferedReader br;
    StringTokenizer in;
    PrintWriter out;

    enum nodeColor {
        WHITE,
        GRAY,
        BLACK
    }

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
            if (v != w)
                adj[v].add(w);
            E++;
        }

        public Iterable<Integer> adj(int v) {
            return adj[v];
        }
    }

    class Cycle {
        private nodeColor[] color;
        private boolean hasCycle;
        private boolean end;
        private int endNode;

        public Cycle(Graph graph) {
            color = new nodeColor[graph.getV()];
            hasCycle = false;
            Arrays.fill(color, nodeColor.WHITE);
            ArrayList<Integer> list = new ArrayList<>();

            for (int s = 0; s < graph.getV(); s++) {
                if (!hasCycle) {
                    list.clear();
                    if (color[s] == nodeColor.WHITE) {
                        dfs(graph, s, list);
                    }
                } else break;
            }

            if (hasCycle) {
                out.println("YES");
                for (int i : list) {
                    out.print((i + 1) + " ");
                }
            } else {
                out.print("NO");
            }
        }

        private void dfs(Graph graph, int v, ArrayList<Integer> list) {
            list.add(v);
            color[v] = nodeColor.GRAY;
            out : for (int w : graph.adj(v)) {
                switch (color[w]) {
                    case WHITE: {
                        dfs(graph, w, list);
                        if (hasCycle) {
                            if (end) {
                                if (endNode == w) {
                                    end = false;
                                } else list.add(w);
                            }
                            break out;
                        }
                        break;
                    }
                    case GRAY: {
                        hasCycle = true;
                        endNode = w;
                        end = true;
                        list.add(w);
                        break out;
                    }
                    case BLACK: {
                    }
                }
            }
            color[v] = nodeColor.BLACK;
        }
    }

    public static void main(String[] args) {
        String fileName = "cycle";
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
        Graph graph = new Graph();

        Cycle cycle = new Cycle(graph);
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
