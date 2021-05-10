/* Kornilov Nikita, M3102, 10.05.2021 */

package Sem2.Lab13;

import java.io.*;
import java.util.*;

public class Task4 {
    BufferedReader br;
    StringTokenizer in;
    PrintWriter out;

    int[] s, prefix;

    public static void main(String[] args) {
        new Task4().run();
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
        convert(nextToken().toCharArray());
        initPrefix();

        // KMP-automaton

        int[][] automaton = new int[s.length + 1][n];

        for (int i = 0; i < s.length + 1; i++) {
            for (int j = 0; j < n; j++) {
                if (i != s.length && s[i] == j) {
                    automaton[i][j] = i + 1;
                } else {
                    automaton[i][j] = automaton[prefix[i]][j];
                }
            }
        }

        for (int i = 0; i < s.length + 1; i++) {
            for (int j = 0; j < n; j++) {
                out.print(automaton[i][j] + " ");
            }
            out.println();
        }

    }

    public void initPrefix() {
        prefix = new int[s.length + 1];
        int i = 1, j = 0;

        while (i < s.length) {
            if (s[i] == s[j]) {
                prefix[i + 1] = j + 1;
                i++; j++;
            } else {
                if (j > 0) {
                    j = prefix[j];
                } else {
                    prefix[i + 1] = 0;
                    i++;
                }
            }
        }
    }

    public void convert(char[] array) {
        s = new int[array.length];
        for (int i = 0; i < s.length; i++) {
            s[i] = array[i] - 'a';
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