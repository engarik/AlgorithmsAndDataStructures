/* Kornilov Nikita, M3102, 18.10.2020 */

package Lab3;

import java.io.*;
import java.util.*;

public class Task1 {
    BufferedReader br;
    StringTokenizer in;
    PrintWriter out;

    public static void main(String[] args) {
        String inputFile = "isheap.in";
        String outputFile = "isheap.out";
        new Task1().run(inputFile, outputFile);
    }

    public String nextToken() throws IOException {
        while (in == null || !in.hasMoreTokens()) {
            in = new StringTokenizer(br.readLine());
        }
        return in.nextToken();
    }

    public int nextInt() throws IOException {
        return Integer.parseInt(nextToken());
    }

    public void solve() throws IOException {
        int n = nextInt();
        int[] heap = new int[n + 1];
        boolean flag = true;

        for (int i = 1; i <= n; i++) {
            heap[i] = nextInt();
        }

        int i = 1;
        while (2 * i <= n) {
            if (heap[i] <= heap[2 * i]) {
                i++;
            } else {
                flag = false;
                break;
            }

        }
        i = 1;
        while (2 * i + 1 <= n) {
            if (heap[i] <= heap[2 * i + 1]) {
                i++;
            } else {
                flag = false;
                break;
            }

        }

        if (flag) {
            out.print("YES");
        } else {
            out.print("NO");
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