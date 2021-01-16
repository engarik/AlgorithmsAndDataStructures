/* Kornilov Nikita, M3102, 04.10.2020 */
package Lab2;

import java.io.*;
import java.util.*;

public class Task4 {
    BufferedReader br;
    StringTokenizer in;
    PrintWriter out;
    int k;
    int array[];

    public static void main(String[] args) {
        new Task4().run("antiqs.in", "antiqs.out");
    }

    public String nextToken() throws IOException {
        while (in == null || !in.hasMoreTokens())
            in = new StringTokenizer(br.readLine());
        return in.nextToken();
    }

    public int nextInt() throws IOException {
        return Integer.parseInt(nextToken());
    }

    public void solve() throws IOException {
        int n = nextInt();
        array = new int[n];
        k = n;
        half(0, n - 1);
        array[n - 1] = 1;

        for (int i = 0; i < array.length - 1; i++) {
            out.print(array[i]);
            out.print(" ");
        }
        out.print(array[array.length - 1]);


    }

    public void half(int leftIndex, int rightIndex) {
        if (leftIndex != rightIndex) {
            int mid = (leftIndex + rightIndex) / 2;
            //System.out.printf("l:%d r:%d m:%d\n", leftIndex, rightIndex, mid);
            array[mid] = k--;
            half(leftIndex, mid);
            half(mid + 1, rightIndex);
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