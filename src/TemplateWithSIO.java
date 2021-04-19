/* Kornilov Nikita, M3102, xx.xx.2020 */

import java.io.*;
import java.util.*;

public class TemplateWithSIO {
    BufferedReader br;
    StringTokenizer in;
    PrintWriter out;

    public static void main(String[] args) {
        new TemplateWithSIO().run();
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

    public long nextLong() throws IOException {
        return Long.parseLong(nextToken());
    }

    public float nextFloat() throws IOException {
        return Float.parseFloat(nextToken());
    }

    public double nextDouble() throws IOException {
        return Double.parseDouble(nextToken());
    }


    public void solve() throws IOException {
        int n = nextInt();
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