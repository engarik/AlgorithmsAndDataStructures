/* Kornilov Nikita, M3102, 18.04.2021 */

package Sem2.Lab12;

import java.io.*;
import java.util.*;

public class Task7 {
    BufferedReader br;
    StringTokenizer in;
    PrintWriter out;

    public static void main(String[] args) {
        String fileName = "knapsack";
        new Task7().run(String.format("%s.in", fileName), String.format("%s.out", fileName));
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
        int s = nextInt(), n = nextInt();
        int[] maxWeight = new int[s + 1];
        maxWeight[0] = 1;

        for (int i = 0; i < n; i++) {
            int nextWeight = nextInt();
            for (int j = s; j >= nextWeight; j--) {
                if (maxWeight[j - nextWeight] == 1) maxWeight[j] = 1;
            }
        }

        for (int i = s; i >= 0; i--) {
            if (maxWeight[i] == 1) {
                out.println(i);
                break;
            }
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