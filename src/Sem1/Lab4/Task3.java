/* Kornilov Nikita, M3102, 29.10.2020 */

package Sem1.Lab4;

import java.io.*;
import java.util.*;

public class Task3 {
    BufferedReader br;
    StringTokenizer in;
    PrintWriter out;

    public static void main(String[] args) {
        String inputFile = "brackets.in";
        String outputFile = "brackets.out";
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
        String tmp;
        Stack stack;
        boolean flag, working = true;
        while (working) {
            try {
                flag = true;
                stack = new Stack(10000);
                tmp = nextToken();
                for (char c : tmp.toCharArray()) {
                    switch (c) {
                        case '(':
                        case '[': {
                            stack.push(c);
                            break;
                        }
                        case ')': {
                            char ex = stack.pop();
                            if (ex != '(') {
                                flag = false;
                            }
                            break;
                        }
                        case ']': {
                            char ex = stack.pop();
                            if (ex != '[') {
                                flag = false;
                            }
                            break;
                        }
                    }

                }
                if (flag)
                    flag = stack.size == 0;
                if (flag)
                    out.write("YES\n");
                else out.write("NO\n");
            } catch (NullPointerException ignored) {
                working = false;
            }
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

    private static class Stack {
        char[] array;
        int size;

        public Stack(int length) {
            array = new char[length];
            size = 0;
        }

        public void push(char element) {
            array[size++] = element;
        }

        public char pop() {
            if (size != 0)
                return array[--size];
            else
                return '0';
        }
    }
}


