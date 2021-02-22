package Sem1.Lab3;/* Kornilov Nikita, M3102, xx.xx.2020 */

import java.io.*;
import java.util.*;

public class Task3 {
    BufferedReader br;
    StringTokenizer in;
    PrintWriter out;

    public static void main(String[] args) {
        String inputFile = "radixsort.in";
        String outputFile = "radixsort.out";
        new Task3().run(inputFile, outputFile);
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
        int n = nextInt(), m = nextInt(), k = nextInt();

        String[] input = new String[n], sorted;

        for (int i = 0; i < n; i++) {
            input[i] = nextToken();
        }

        sorted = radixSort(input, m, k);

        for (String s : sorted) {
            out.print(s);
            out.print('\n');
        }
    }

    public String[] countingSort(String[] unsorted, int length, int charPos) {
        int maxChar = 'z' - 'a';

        int[] tmp = new int[maxChar + 1];
        String[] sorted = new String[unsorted.length];

        for (String j : unsorted) {
            tmp[j.charAt(length - charPos - 1) - 'a']++;
        }

        for (int i = 1; i < maxChar + 1; i++) {
            tmp[i] += tmp[i - 1];
        }

        for (int i = unsorted.length - 1; i > -1; i--) {
            int tmpChar = unsorted[i].charAt(length - charPos - 1) - 'a';
            sorted[tmp[tmpChar] - 1] = unsorted[i];
            tmp[tmpChar]--;
        }

        return sorted;
    }

    public String[] radixSort(String[] unsorted, int length, int numberOfSortPhases) {
        String[] sorted = unsorted;
        int charAt = 0;
        while (numberOfSortPhases-- > 0) {
            sorted = countingSort(sorted, length, charAt);
            charAt++;
        }

        return sorted;
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