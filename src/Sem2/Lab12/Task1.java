/* Kornilov Nikita, M3102, 16.04.2021 */

package Sem2.Lab12;

import java.io.*;
import java.util.*;

public class Task1 {
    BufferedReader br;
    StringTokenizer in;
    PrintWriter out;

    public static void main(String[] args) {
        new Task1().run();
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
        int n = nextInt();
        int[] inputArray = new int[n];

        for (int i = 0; i < n; i++) {
            inputArray[i] = nextInt();
        }

        List<List<Integer>> LIS = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            LIS.add(new ArrayList<>());
        }

        LIS.get(0).add(inputArray[0]);

        for (int i = 1; i < n; i++) { // O(n^2)
            for (int j = 0; j < i; j++) {
                if (inputArray[j] < inputArray[i] && LIS.get(j).size() > LIS.get(i).size()) {
                    LIS.set(i, new ArrayList<>(LIS.get(j)));
                }
            }
            LIS.get(i).add(inputArray[i]);
        }

        List<Integer> maxLIS = LIS.stream().max(Comparator.comparing(List::size)).get();

        out.println(maxLIS.size());
        for (Integer maxLI : maxLIS) {
            out.print(maxLI + " ");
        }

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