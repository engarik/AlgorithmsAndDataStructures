/* Kornilov Nikita, M3102, 29.03.2021 */

package Sem2.Lab10;

import java.io.*;
import java.util.*;

public class Task2 {
    BufferedReader br;
    StringTokenizer in;
    PrintWriter out;

    public static void main(String[] args) {
        String fileName = "spantree";
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

    public short nextShort() throws IOException {
        return (short)Integer.parseInt(nextToken());
    }

    public void solve() throws IOException {
        Graph graph = new Graph();

        double minWeight = Kruskal(graph.edges, graph.vertices);

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
        private final short V;
        private Vertex[] vertices;
        private final PriorityQueue<Edge> edges;

        public Graph() throws IOException {
            this.V = nextShort();

            vertices = new Vertex[V];
            edges = new PriorityQueue<>((Comparator.comparingDouble(Edge::getWeight))); // Contains all possible edges from every vertex

            for (short i = 0; i < V; i++) {
                vertices[i] = new Vertex(nextShort(), nextShort(), i);
            }
            TreeMap<Short, Double> map = new TreeMap<>();
            for (int i = 0; i < V; i++) {
                for (int j = i + 1; j < V; j++) {
                    map.put(vertices[j].getIndex(), getDistanceBetweenTwoPoints(vertices[i], vertices[j]));
//                    edges.add(new Edge(vertices[i].getIndex(), vertices[j].getIndex(),getDistanceBetweenTwoPoints(vertices[i], vertices[j]) )); // Adding all possible edges in complete graph
                }
                int j = 0;
                for (short key : map.keySet()) {
                    if (j < map.keySet().size() * 0.975) {
                        edges.add(new Edge(vertices[i].getIndex(), key, map.get(key)));
                        j++;
                    } else break;
                }

                map.clear();
            }

        }
        private double getDistanceBetweenTwoPoints(Vertex from, Vertex to) {
            return Math.sqrt(Math.pow(from.getX() - to.getX(), 2) + Math.pow(from.getY() - to.getY(), 2));
        }

    }

    private class Vertex {
        private final short x;
        private final short y;
        private final short index;
        public short color;

        public Vertex(short x, short y, short index) {
            this.x = x;
            this.y = y;
            this.index = index;
            this.color = index;
        }

        public short getX() {
            return x;
        }

        public short getY() {
            return y;
        }

        public short getIndex() {
            return index;
        }

        @Override
        public String toString() {
            return String.format("(%d,%d)", x, y);
        }
    }

    private class Edge {
        private final short from;
        private final short to;
        private final double weight;

        public Edge(short from, short to, double weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        public double getWeight() {
            return weight;
        }

        public short getFrom() {
            return from;
        }

        public short getTo() {
            return to;
        }

    }

    private double Kruskal(PriorityQueue<Edge> edgesQueue, Vertex[] vertices) {
        double minWeight = 0;

        int currentSize = 0; // Number of elements connected
        short fromColor, toColor;

        while (currentSize != vertices.length && !edgesQueue.isEmpty()) { // while not MST
            Edge edge = edgesQueue.poll(); // Get another edge

            // If edge doesn't make a cycle
            fromColor = vertices[edge.getFrom()].color;
            toColor = vertices[edge.getTo()].color;

            if (fromColor != toColor) {
                // Merge them
                short finalToColor = toColor;
                short finalFromColor = fromColor;
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