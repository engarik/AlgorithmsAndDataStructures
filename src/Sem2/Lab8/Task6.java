/* Kornilov Nikita, M3102, 01.03.2021 */

package Sem2.Lab8;

import java.io.*;
import java.util.*;

public class Task6 {
    BufferedReader br;
    StringTokenizer in;
    PrintWriter out;

    class Graph {
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

        public Graph(ArrayList<int[]> edges) {
            this(edges.size() * 2);
            int E = edges.size();

            for (var v : edges) {
                addEdge(v[0], v[1]);
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
    }

    class BreadthFirstPaths {
        private boolean[] marked;
        private int[] edgeTo;
        private final int s;

        public BreadthFirstPaths(Graph G, int s) {
            marked = new boolean[G.getV()];
            edgeTo = new int[G.getV()];
            this.s = s;
            bfs(G, s);
        }

        private void bfs(Graph G, int s) {
            Queue<Integer> queue = new ArrayDeque<>();
            marked[s] = true;
            queue.add(s);
            while (!queue.isEmpty()) {
                int v = queue.poll();
                for (int w : G.adj(v)) {
                    if (!marked[w]) {
                        edgeTo[w] = v;
                        marked[w] = true;
                        queue.add(w);
                    }
                }

            }
        }

        public boolean hasPathTo(int v) {
            return marked[v];
        }

        public ArrayList<Integer> pathTo(int v) {
            if (!hasPathTo(v)) return null;
            ArrayList<Integer> path = new ArrayList<>();
            for (int x = v; x != s; x = edgeTo[x]) path.add(x);
            path.add(s);
            return path;
        }
    }

    public static void main(String[] args) {
        String inputFileName = "input.txt", outFileName = "output.txt";
        new Task6().run(String.format("%s", inputFileName), String.format("%s", outFileName));
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

    public int[] nextLine() throws IOException {
        char[] chars = nextToken().toCharArray();
        int[] res = new int[chars.length];
        for (int i = 0; i < chars.length; i++) {
            res[i] = chars[i];
        }
        return res;
    }

    public void solve() throws IOException {
        int n = nextInt(), m = nextInt();
        int[][] map = new int[n][m];

        for (int i = 0; i < n; i++) map[i] = nextLine();

        int ver = 1, start = 0, finish = 0;

        HashMap<Integer, int[]> pos = new HashMap<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == '#') map[i][j] = -1;
                else {
                    if (map[i][j] == 'S') start = ver;
                    else if (map[i][j] == 'T') finish = ver;
                    map[i][j] = ver;
                    pos.put(ver++, new int[]{j, i});
                }
            }
        }

        ArrayList<int[]> edges = new ArrayList<>();

        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++)
                if (map[i][j] != -1) {
                    if (i + 1 < n && map[i + 1][j] != -1) edges.add(new int[]{map[i][j], map[i + 1][j]});
                    if (j + 1 < m && map[i][j + 1] != -1) edges.add(new int[]{map[i][j], map[i][j + 1]});
                }

        BreadthFirstPaths breadthFirstPaths = new BreadthFirstPaths(new Graph(edges), start);

        if (breadthFirstPaths.hasPathTo(finish)) {
            var path = breadthFirstPaths.pathTo(finish);
            Collections.reverse(path);

            out.println(path.size() - 1);

            int prevX = -2, prevY = -2, diffX = -2, diffY = -2;

            for (var v : path) {
                if (prevX != -2) diffX = pos.get(v)[0] - prevX;
                if (prevY != -2) diffY = pos.get(v)[1] - prevY;

                prevX = pos.get(v)[0];
                prevY = pos.get(v)[1];

                if (diffX == 1) out.print("R");
                else if (diffX == -1) out.print("L");

                if (diffY == 1) out.print("D");
                else if (diffY == -1) out.print("U");
            }
        } else {
            out.println(-1);
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
