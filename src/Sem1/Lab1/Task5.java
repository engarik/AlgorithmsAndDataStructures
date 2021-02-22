/* Kornilov Nikita, M3102, 10.09.2020 */
package Sem1.Lab1;

import java.io.*;
import java.util.*;

public class Task5 {
    BufferedReader br;
    StringTokenizer in;
    PrintWriter out;

    public static void main(String[] args) {
        new Task5().run("sortland.in", "sortland.out");
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

    public float nextFloat() throws IOException {
        return Float.parseFloat(nextToken());
    }

    public void solve() throws IOException {
        int n = nextInt();
        int minId = -1, midId = -1, maxId = -1;

        float min, mid, max;

        float[] array = new float[n], sortedArray = new float[n];

        for (int i = 0; i < n; i++) {
            array[i] = nextFloat();
            sortedArray[i] = array[i];
        }

        for (int i = sortedArray.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (sortedArray[j] > sortedArray[j + 1]) {
                    float tmp = sortedArray[j];
                    sortedArray[j] = sortedArray[j + 1];
                    sortedArray[j + 1] = tmp;
                }
            }
        }

        min = sortedArray[0];
        mid = sortedArray[n / 2];
        max = sortedArray[n - 1];

        for (int i = 0; i < array.length; i++) {
            if (minId == -1 && array[i] == min) {
                minId = i;
            }
            if (midId == -1 && array[i] == mid) {
                midId = i;
            }
            if (maxId == -1 && array[i] == max) {
                maxId = i;
            }
        }

        out.printf("%d %d %d", minId + 1, midId + 1, maxId + 1);
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