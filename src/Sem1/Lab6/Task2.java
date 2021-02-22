/* Kornilov Nikita, M3102, 29.11.2020 */

package Sem1.Lab6;

import java.io.*;
import java.util.*;

public class Task2 {
    BufferedReader br;
    StringTokenizer in;
    PrintWriter out;

    public static void main(String[] args) {
        String fileName = "map";
        new Task2().run(String.format("%s.in", fileName), String.format("%s.out", fileName));
    }

    public void solve() throws IOException {
        String inputString = nextToken();
        String inputValue1, inputValue2;
        MyMap map = new MyMap();

        while (inputString != null) {
            inputValue1 = nextToken();
            switch (inputString) {
                case "put": {
                    inputValue2 = nextToken();
                    map.put(new Pair(inputValue1, inputValue2));
                    break;
                }
                case "delete": {
                    map.delete(inputValue1);
                    break;
                }
                case "get": {
                    out.print(String.format("%s\n", map.get(inputValue1)));
                    break;
                }
            }
            inputString = nextToken();
        }
    }

    private class MyMap {
        private int capacity;

        private LinkedList<Pair>[] hashMap;

        private MyMap() {
            capacity = 100003;
            hashMap = new LinkedList[capacity];
        }

        private void put(Pair pair) {
            int hash = getHash(pair.key);
            if (hashMap[hash] == null) {
                hashMap[hash] = new LinkedList<>();
            } else {
                hashMap[hash].removeIf(pair1 -> pair1.key.equals(pair.key));
            }
            hashMap[hash].add(pair);
        }

        private void delete(String key) {
            int hash = getHash(key);
            if (hashMap[hash] != null) {
                hashMap[hash].removeIf(pair -> pair.key.equals(key));
            }
        }

        private String get(String key) {
            int hash = getHash(key);
            if (hashMap[hash] != null) {
                for (Pair p : hashMap[hash]) {
                    if (p.key.equals(key))
                        return p.value;
                }
            }
            return "none";
        }

        private int getHash(String value) {
            int hash = 0, helper = 1;
            for (char c : value.toLowerCase().toCharArray()) {
                hash += (c - 'a') * helper % capacity;
                hash %= capacity;
                helper *= 239;
                helper %= capacity;
            }
            return hash % capacity;
        }
    }

    private class Pair {
        private String key;
        private String value;

        private Pair(String key, String value) {
            this.key = key;
            this.value = value;
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