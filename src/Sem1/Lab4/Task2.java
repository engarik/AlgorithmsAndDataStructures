/* Kornilov Nikita, M3102, 29.10.2020 */

package Sem1.Lab4;

import java.io.*;
import java.util.*;

public class Task2 {
    BufferedReader br;
    StringTokenizer in;
    PrintWriter out;

    public static void main(String[] args) {
        String inputFile = "queue.in";
        String outputFile = "queue.out";
        new Task2().run(inputFile, outputFile);
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

        Queue queue = new Queue(m);
        StringBuilder res = new StringBuilder();
        String tmp;

        for (int i = 0; i < m; i++) {
            tmp = nextToken();

            switch (tmp.charAt(0)) {
                case '+': {
                    tmp = nextToken();
                    queue.push(Integer.parseInt(tmp));
                    break;
                }
                case '-': {
                    res.append(queue.pop());
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

    private static class Queue {
        int[] array;
        int head, tail, length;

        public Queue(int length) {
            this.length = length;
            array = new int[length];
            head = 0;
            tail = 0;
        }

        public void push(int element) {
            array[tail++] = element;
        }

        public int pop() {
            return array[head++];
        }
    }
}


