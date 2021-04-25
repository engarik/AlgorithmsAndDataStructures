/* Kornilov Nikita, M3102, 25.04.2021 */

package Sem2.Lab12;

import java.io.*;
import java.util.*;

public class Task5 {
    BufferedReader br;
    StringTokenizer in;
    PrintWriter out;

    ArrayList<ArrayList<Integer>> children;
    boolean[] visited;
    int[] maxSet;

    public static void main(String[] args) {
        new Task5().run();
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

            maxSet[node] = Math.max(1 + grandchildrenSum, childrenSum);

        }
        return maxSet[node];
    }

    public void run() {
        try {
            br = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(System.out);
            solve();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}