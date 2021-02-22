/* Kornilov Nikita, M3102, 29.10.2020 */

package Sem1.Lab4;

import java.io.*;
import java.util.*;

public class Task6 {
    BufferedReader br;
    StringTokenizer in;
    PrintWriter out;

    public static void main(String[] args) {
        String inputFile = "garland.in";
        String outputFile = "garland.out";
        new Task6().run(inputFile, outputFile);
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

    public double nextDouble() throws IOException {
        return Double.parseDouble(nextToken());
    }

    public void solve() throws IOException {
        int n = nextInt();
        double A = nextDouble();
        double[] array = new double[n];
        array[0] = A;

        double B = binarySearch(array, n);
        out.print(String.format(Locale.US, "%.2f", B));
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

    double binarySearch(double[] array, int n) {
        double left = 0, right = array[0];

        while (right - left > 0.0000000001) {
            array[1] = (left + right) / 2;
            boolean flag = true;
            for (int i = 2; i < n; i++) {
                array[i] = 2 * array[i - 1] - array[i - 2] + 2;
                if (array[i] < 0) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                right = array[1];
            }
            else {
                left = array[1];
            }
        }
        return array[n - 1];

    }
}


