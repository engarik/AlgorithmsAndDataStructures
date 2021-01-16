/* Kornilov Nikita, M3102, 29.10.2020 */

package Lab4;

import java.io.*;
import java.util.*;

public class Task1 {
    BufferedReader br;
    StringTokenizer in;
    PrintWriter out;

    public static void main(String[] args) {
        String inputFile = "stack.in";
        String outputFile = "stack.out";
        new Task1().run(inputFile, outputFile);
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
        int m = nextInt();

        Stack stack = new Stack(m);
        StringBuilder res = new StringBuilder();
        String tmp;

        for (int i = 0; i < m; i++) {
            tmp = nextToken();

            switch (tmp.charAt(0)) {
                case '+': {
                    tmp = nextToken();
                    stack.push(Integer.parseInt(tmp));
                    break;
                }
                case '-': {
                    res.append(stack.pop());
                    res.append('\n');
                    break;
                }
            }
        }
        out.print(res);
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

    private static class Stack {
        int[] array;
        int size;

        public Stack(int length) {
            array = new int[length];
            size = 0;
        }

        public void push(int element) {
            array[size++] = element;
        }

        public int pop() {
            return array[--size];
        }
    }
}


