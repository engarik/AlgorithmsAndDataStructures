/* Kornilov Nikita, M3102, 29.11.2020 */

package Lab6;

import java.io.*;
import java.util.*;

public class Task4 {
    BufferedReader br;
    StringTokenizer in;
    PrintWriter out;

    public static void main(String[] args) {
        String fileName = "multimap";
        new Task4().run(String.format("%s.in", fileName), String.format("%s.out", fileName));
    }

    public void solve() throws IOException {
        String inputString = nextToken();
        String inputValue1, inputValue2;
        MultiMap map = new MultiMap();

        while (inputString != null) {
            inputValue1 = nextToken();
            switch (inputString) {
                case "put": {
                    inputValue2 = nextToken();
                    map.put(inputValue1, inputValue2);
                    break;
                }
                case "delete": {
                    inputValue2 = nextToken();
                    map.delete(inputValue1, inputValue2);
                    break;
                }
                case "get": {
                    LinkedSet res = map.get(inputValue1);
                    if (res == null) {
                        out.println("0");
                    } else {
                        out.print(res.size);
                        out.print(" ");
                        LinkedSetNode node = res.head;
                        while (node != null) {
                            out.print(node.value);
                            out.print(" ");
                            node = node.next;
                        }
                        out.println();
                    }
                    break;
                }
                case "deleteall": {
                    map.deleteAll(inputValue1);
                    break;
                }
            }
            inputString = nextToken();
        }
    }

    private class MultiMap {
        private int capacity;

        private LinkedList<LinkedSet>[] multiMap;

        private MultiMap() {
            capacity = 1009;
            multiMap = new LinkedList[capacity];
        }

        private void put(String key, String value) {
            int hash = getHash(key);
            if (multiMap[hash] == null) {
                multiMap[hash] = new LinkedList<>();
            }
            if (multiMap[hash].stream().noneMatch(node1 -> node1.key.equals(key))) {
                multiMap[hash].add(new LinkedSet(key));
            }
            multiMap[hash].stream().filter(set -> set.key.equals(key)).findAny().get().push(value);
        }

        private void delete(String key, String value) {
            int hash = getHash(key);
            if (!(multiMap[hash] == null || multiMap[hash].stream().noneMatch(set -> set.key.equals(key)))) {
                multiMap[hash].stream().filter(set -> set.key.equals(key)).findAny().get().delete(value);
            }
        }

        private void deleteAll(String key) {
            int hash = getHash(key);
            if (!(multiMap[hash] == null || multiMap[hash].stream().noneMatch(set -> set.key.equals(key)))) {
                multiMap[hash].stream().filter(set -> set.key.equals(key)).findAny().get().deleteAll();
            }
        }

        public LinkedSet get(String key) {
            int hash = getHash(key);
            if (!(multiMap[hash] == null || multiMap[hash].stream().noneMatch(node -> node.key.equals(key)))) {
                return multiMap[hash].stream().filter(node -> node.key.equals(key)).findAny().get();
            } else {
                return null;
            }
        }

        private int getHash(String value) {
            int hash = 0, helper = 1;
            for (char c : value.toLowerCase().toCharArray()) {
                hash += (c - 'a') * helper % capacity;
                hash %= capacity;
                helper *= 239;
                helper %= capacity;
            }
            return Math.abs(hash);
        }

    }

    private class LinkedSet {
        private final String key;
        private final int capacity = 100003;
        public int size;

        private LinkedList<LinkedSetNode>[] values;
        public LinkedSetNode head;
        private LinkedSetNode tail;


        public LinkedSet(String key) {
            this.key = key;
            values = new LinkedList[capacity];
            head = null;
            tail = null;
            size = 0;
        }

        public void push(String value) {
            int hash = getHash(key);
            LinkedSetNode node = new LinkedSetNode(value, tail);
            if (values[hash] == null)
                values[hash] = new LinkedList<>();
            if (values[hash].stream().noneMatch(node1 -> node1.value.equals(node.value))) {
                values[hash].add(node);
                size++;
                if (tail != null) {
                    tail.next = node;
                }
                tail = node;
                if (head == null) {
                    head = node;
                }
            }

        }

        public void delete(String value) {
            int hash = getHash(key);
            if (values[hash].stream().anyMatch(node -> node.value.equals(value))) {
                LinkedSetNode node = values[hash].stream().filter(node1 -> node1.value.equals(value)).findAny().get();
                size--;
                values[hash].removeIf(node1 -> node1.value.equals(value));

                if (node.prev != null) {
                    node.prev.next = node.next;
                }

                if (node.next != null) {
                    node.next.prev = node.prev;
                }

                if (Objects.equals(node.value, tail.value)) {
                    tail = node.prev;
                }

                if (Objects.equals(node.value, head.value)) {
                    head = node.next;
                }
            }
        }

        public void deleteAll() {
            values = new LinkedList[capacity];
            head = null;
            tail = null;
            size = 0;
        }

        private int getHash(String value) {
            int hash = 0, helper = 1;
            for (char c : value.toLowerCase().toCharArray()) {
                hash += (c - 'a') * helper % capacity;
                hash %= capacity;
                helper *= 239;
                helper %= capacity;
            }
            return Math.abs(hash);
        }
    }

    private class LinkedSetNode {
        private final String value;
        private LinkedSetNode prev;
        private LinkedSetNode next;

        private LinkedSetNode(String value, LinkedSetNode prev) {
            this.value = value;
            this.prev = prev;
            this.next = null;
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