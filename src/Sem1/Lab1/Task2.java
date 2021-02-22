/* Kornilov Nikita, M3102, 10.09.2020 */
package Sem1.Lab1;

import java.io.*;
import java.util.*;

public class Task2 {
    BufferedReader br;
    StringTokenizer in;
    PrintWriter out;

    public static void main(String[] args) {
        new Task2().run("Sem1/Lab1/files/aplusbb.in", "Sem1/Lab1/files/aplusbb.out");
    }

    public String nextToken() throws IOException {
        while (in == null || !in.hasMoreTokens()) {
            in = new StringTokenizer(br.readLine());
        }
        return in.nextToken();
    }

    public long nextLong() throws IOException {
        return Long.parseLong(nextToken());
    }

    public void solve() throws IOException {
        long a = nextLong(), b = nextLong();
        out.print(a + b * b);
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