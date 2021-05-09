/* Kornilov Nikita, M3102, 09.05.2021 */

package Sem2.Lab13;

import java.io.*;
import java.util.*;

public class Task1 {
    BufferedReader br;
    StringTokenizer in;
    PrintWriter out;

    public static void main(String[] args) {
        String fileName = "search1";
        new Task1().run(String.format("%s.in", fileName), String.format("%s.out", fileName));
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
        char[] p = nextToken().toCharArray(), t = nextToken().toCharArray();

        ArrayList<Integer> pos = new ArrayList<>();

        out : for (int i = 0; i <= t.length - p.length; i++) {
            for (int j = 0; j < p.length; j++) {
                if (t[i + j] != p[j]) {
                    continue out;
                }
            }
            pos.add(i + 1);
        }

        out.println(pos.size());

        for (int i : pos) {
            out.print(i + " ");
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