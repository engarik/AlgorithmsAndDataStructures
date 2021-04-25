/* Kornilov Nikita, M3102, 25.04.2021 */

package Sem2.Lab12;

import java.io.*;
import java.util.*;

public class Task6 {
    BufferedReader br;
    StringTokenizer in;
    PrintWriter out;

    ArrayList<ArrayList<Integer>> children;
    boolean[] visited;
    int[] maxSet;
    int[] weight;

    public static void main(String[] args) {
        String fileName = "selectw";
        new Task6().run(String.format("%s.in", fileName), String.format("%s.out", fileName));
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
        int n = nextInt(), root = 0;

        children = new ArrayList<>();
        visited = new boolean[n];
        maxSet = new int[n];
        weight = new int[n];

        for (int i = 0; i < n; i++) {
            children.add(new ArrayList<>());
        }

        for (int i = 0; i < n; i++) {
            int tmp = nextInt();
            if (tmp == 0) {
                root = i;
            } else {
                children.get(tmp - 1).add(i);
            }
            weight[i] = nextInt();
        }

        out.println(getMaxSet(root));

    }

    private int getMaxSet(int node) {
        if (!visited[node]) {
            visited[node] = true;
            int childrenSum = 0, grandchildrenSum = 0;

            for (int i : children.get(node)) {
                childrenSum += getMaxSet(i);
            }

            for (int i : children.get(node)) {
                for (int j : children.get(i)) {
                    grandchildrenSum += getMaxSet(j);
                }
            }

            maxSet[node] = Math.max(weight[node] + grandchildrenSum, childrenSum);

        }
        return maxSet[node];
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