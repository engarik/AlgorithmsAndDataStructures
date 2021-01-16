/* Kornilov Nikita, M3102, 29.10.2020 */

package Lab4;

import java.io.*;
import java.util.*;

public class Task5 {
    BufferedReader br;
    StringTokenizer in;
    PrintWriter out;

    public static void main(String[] args) {
        String inputFile = "binsearch.in";
        String outputFile = "binsearch.out";
        new Task5().run(inputFile, outputFile);
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
        int[] array = new int[n];

        for (int i = 0; i < n; i++) {
            array[i] = nextInt();
        }

        int m = nextInt();
        int[] requests = new int[m];

        for (int i = 0; i < m; i++) {
            requests[i] = nextInt();
        }

        for (int i : requests) {
            int left = binarySearch(array, i, false) + 1;
            int right = binarySearch(array, i, true) + 1;
            if (left > right) {
                out.print(-1);
                out.print(" ");
                out.println(-1);
            } else {
                out.print(left);
                out.print(" ");
                out.println(right);
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

    int binarySearch(int[] a, int key, boolean last) {
        int left = 0;
        int right = a.length - 1;

        while (left <= right) {
            int mid = (left + right) / 2;
            int midVal = a[mid];

            if (midVal < key || (last && midVal == key))
                left = mid + 1;
            else if (midVal > key || !last)
                right = mid - 1;
        }

        return last ? right : left;
    }
}


