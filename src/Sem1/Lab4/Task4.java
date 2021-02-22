/* Kornilov Nikita, M3102, 29.10.2020 */

package Sem1.Lab4;

import java.io.*;
import java.util.*;

public class Task4 {
    BufferedReader br;
    StringTokenizer in;
    PrintWriter out;

    public static void main(String[] args) {
        String inputFile = "postfix.in";
        String outputFile = "postfix.out";
        new Task4().run(inputFile, outputFile);
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
        StringBuilder tmp = new StringBuilder();
        boolean working = true;
        while (working){
            try {
                tmp.append(nextToken());
            } catch (NullPointerException ignored) {
                working = false;
            }
        }
        tmp = new StringBuilder(tmp.toString().replaceAll(" ", ""));

        Stack stack = new Stack(tmp.length());
        int operand, a, b;

        for (char c : tmp.toString().toCharArray()) {
            if (c == '+' || c == '-' || c == '*') {
                a = stack.pop();
                b = stack.pop();
                switch (c) {
                    case '+': {
                        stack.push(b + a);
                        break;
                    }
                    case '-': {
                        stack.push(b - a);
                        break;
                    }
                    case '*': {
                        stack.push(b * a);
                        break;
                    }
                }
            } else {
                operand = Integer.parseInt(c + "");
                stack.push(operand);
            }
        }
        out.print(stack.pop());

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
            if (size != 0)
                return array[--size];
            else
                return Integer.MAX_VALUE;
        }
    }
}


