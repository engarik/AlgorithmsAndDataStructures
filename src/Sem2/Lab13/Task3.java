/* Kornilov Nikita, M3102, 09.05.2021 */

package Sem2.Lab13;

import java.io.*;
import java.util.*;

public class Task3 {
    BufferedReader br;
    StringTokenizer in;
    PrintWriter out;

    public static void main(String[] args) {
        String fileName = "prefix";
        new Task3().run(String.format("%s.in", fileName), String.format("%s.out", fileName));
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

    public void solve() throws IOException {
        char[] s = nextToken().toCharArray();
        int[] prefix = new int[s.length + 1];
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

        for (int k = 0; k < s.length; k++) {
            out.print(prefix[k + 1] + " ");
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