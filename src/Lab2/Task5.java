/* Kornilov Nikita, M3102, 04.10.2020 */
package Lab2;

import java.io.*;
import java.util.*;

public class Task5 {
    BufferedReader br;
    StringTokenizer in;
    PrintWriter out;
    Random random;
    int tmp, i, j, k, pivot;

    public static void main(String[] args) {
        new Task5().run("kth.in", "kth.out");
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
        k = nextInt() - 1;
        int[] array = new int[n];

        int a = nextInt(), b = nextInt(), c = nextInt();
        array[0] = nextInt();
        array[1] = nextInt();
        random = new Random();

        for (int i = 2; i < n; i++)
            array[i] = a * array[i - 2] + b * array[i - 1] + c;

        out.print(kthStat(array, 0, array.length - 1));
    }

    public int kthStat(int[] array, int left, int right) {
        i = left;
        j = right;
        pivot = array[left + random.nextInt(right - left + 1)];

        while (i <= j) {
            while (array[i] < pivot)
                i++;
            while (array[j] > pivot)
                j--;
            if (i <= j) {
                tmp = array[i];
                array[i] = array[j];
                array[j] = tmp;
                i++;
                j--;
            }
        }
        if (left <= k && k <= j)
            return kthStat(array, left, j);
        if (i <= k && k <= right)
            return kthStat(array, i, right);
        return array[k];
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