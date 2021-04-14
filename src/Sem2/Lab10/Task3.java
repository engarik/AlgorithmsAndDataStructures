/* Kornilov Nikita, M3102, 29.03.2021 */

package Sem2.Lab10;

import java.io.*;
import java.util.*;

public class Task3 {
    BufferedReader br;
    StringTokenizer in;
    PrintWriter out;

    public static void main(String[] args) {
        String fileName = "spantree3";
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

        int minWeight = Kruskal(graph.edges, graph.vertices);

        out.println(minWeight);
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
        private final int E;
        private final Vertex[] vertices;
        private PriorityQueue<Edge> edges;

        public Graph() throws IOException {
            this.V = nextInt();
            this.E = nextInt();

            vertices = new Vertex[V];
            edges = new PriorityQueue<>((Comparator.comparingInt(Edge::getWeight)));

            int from, to, weight;
            for (int i = 0; i < E; i++) {
                from = nextInt() - 1;
                to = nextInt() - 1;
                weight = nextInt();
                edges.add(new Edge(from, to, weight));
                if (vertices[from] == null)
                    vertices[from] = new Vertex(from);
                if (vertices[to] == null)
                    vertices[to] = new Vertex(to);
            }
        }

    }

    private class Vertex {
        private final int index;
        public int color;

        public Vertex(int index) {
            this.index = index;
            this.color = index;
        }

        public int getIndex() {
            return index;
        }

    }

    private class Edge {
        private final int from;
        private final int to;
        private final int weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        public int getFrom() {
            return from;
        }

        public int getTo() {
            return to;
        }

        public int getWeight() {
            return weight;
        }

    }

    private int Kruskal(PriorityQueue<Edge> edgesQueue, Vertex[] vertices) {
        int minWeight = 0;

        int currentSize = 0; // Number of elements connected
        int fromColor, toColor;

        while (currentSize != vertices.length && !edgesQueue.isEmpty()) { // while not MST
            Edge edge = edgesQueue.poll(); // Get another edge

            // If edge doesn't make a cycle
            fromColor = vertices[edge.getFrom()].color;
            toColor = vertices[edge.getTo()].color;

            if (fromColor != toColor) {
                // Merge them
                int finalToColor = toColor;
                int finalFromColor = fromColor;
                Arrays.stream(vertices).filter(v -> v.color == finalToColor).forEach(v -> v.color = finalFromColor);
                // Add edge to mst
                minWeight += edge.getWeight();
                // Update number of connected
                currentSize = (int)Arrays.stream(vertices).filter(v -> v.color == finalFromColor).count();
            }
        }
        return minWeight;
    }
}