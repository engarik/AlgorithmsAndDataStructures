/* Kornilov Nikita, M3102, 29.11.2020 */

package Lab6;

import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class Task1 {
    BufferedReader br;
    StringTokenizer in;
    PrintWriter out;

    public static void main(String[] args) {
        String fileName = "set";
        new Task1().run(String.format("%s.in", fileName), String.format("%s.out", fileName));
    }

    public void solve() throws IOException {
        String inputString = nextToken();
        int inputValue;
        MySet set = new MySet();

        while (inputString != null) {
            inputValue = nextInt();
            switch (inputString) {
                case "insert": {
                    set.insert(inputValue);
                    break;
                }
                case "delete": {
                    set.delete(inputValue);
                    break;
                }
                case "exists": {
                    out.print(set.exists(inputValue) ? "true\n" : "false\n");
                    break;
                }
            }
            inputString = nextToken();
        }
    }

    private class MySet {
        private int capacity;

        private LinkedList<Integer>[] hashTable;

        private MySet() {
            capacity = 9973;
            hashTable = new LinkedList[capacity];
        }

        private void insert(Integer value) {
            if (hashTable[getHash(value)] == null) {
                hashTable[getHash(value)] = new LinkedList<>();
            }
            if (!hashTable[getHash(value)].contains(value)) {
                hashTable[getHash(value)].add(value);
            }

        }

        private void delete(Integer value) {
            if (hashTable[getHash(value)] != null) {
                hashTable[getHash(value)].remove(value);
            }
        }

        private boolean exists(Integer value) {
            if (hashTable[getHash(value)] != null) {
                return hashTable[getHash(value)].contains(value);
            }
            return false;
        }

        private int getHash(Integer value) {
            return Math.abs(value) % capacity;
        }
    }

    public String nextToken() throws IOException {
        while (in == null || !in.hasMoreTokens()) {
            String inputString = br.readLine();
            if (inputString != null) {
                in = new StringTokenizer(inputString);
            } else {
                return null;
            }
        }
        return in.nextToken();
    }

    public int nextInt() throws IOException {
        return Integer.parseInt(nextToken());
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