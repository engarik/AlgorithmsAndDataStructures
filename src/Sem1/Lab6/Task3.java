/* Kornilov Nikita, M3102, 29.11.2020 */

package Sem1.Lab6;

import java.io.*;
import java.util.*;

public class Task3 {
    BufferedReader br;
    StringTokenizer in;
    PrintWriter out;

    public static void main(String[] args) {
        String fileName = "linkedmap";
        new Task3().run(String.format("%s.in", fileName), String.format("%s.out", fileName));
    }

    public void solve() throws IOException {
        String inputString = nextToken();
        String inputValue1, inputValue2;
        LinkedMap map = new LinkedMap();

        LinkedMapNode lastNode = null;
        LinkedMapNode removed;

        while (inputString != null) {
            inputValue1 = nextToken();

            switch (inputString) {
                case "put": {
                    inputValue2 = nextToken();
                    lastNode = map.put(new LinkedMapNode(inputValue1, inputValue2, lastNode));
                    break;
                }
                case "delete": {
                    removed = map.delete(inputValue1);
                    if (removed != null && lastNode != null && removed.key.equals(lastNode.key)) {
                        lastNode = removed.prev;
                    }
                    break;
                }
                case "get": {
                    LinkedMapNode get = map.get(inputValue1);
                    out.print(String.format("%s\n", get != null ? get.value : "none"));
                    break;
                }
                case "prev": {
                    out.print(String.format("%s\n", map.prev(inputValue1)));
                    break;
                }
                case "next": {
                    out.print(String.format("%s\n", map.next(inputValue1)));
                    break;
                }
            }
            inputString = nextToken();
        }
    }

    private class LinkedMap {
        private int capacity;

        private LinkedList<LinkedMapNode>[] hashMap;

        private LinkedMap() {
            capacity = 100003;
            hashMap = new LinkedList[capacity];
        }

        private LinkedMapNode put(LinkedMapNode node) {
            int hash = getHash(node.key);
            if (hashMap[hash] == null) {
                hashMap[hash] = new LinkedList<>();
            }
            if (hashMap[hash].stream().noneMatch(node1 -> node1.key.equals(node.key))) {
                hashMap[hash].add(node);
                if (node.prev != null) {
                    node.prev.next = node;
                }
                return node;
            } else {
                LinkedMapNode toChange = hashMap[hash].stream().filter(node1 -> node1.key.equals(node.key)).findAny().stream().findFirst().get();
                toChange.value = node.value;
                return node.prev;
            }
        }

        private LinkedMapNode delete(String key) {
            int hash = getHash(key);
            if (hashMap[hash] == null || hashMap[hash].stream().noneMatch(node1 -> node1.key.equals(key))) {
                return null;
            }
            for (LinkedMapNode node : hashMap[hash]) {
                if (node.key.equals(key)) {
                    if (node.prev != null) {
                        node.prev.next = node.next;
                    }
                    if (node.next != null) {
                        node.next.prev = node.prev;
                    }
                    hashMap[hash].remove(node);
                    return node;
                }
            }
            return null;
        }

        private LinkedMapNode get(String key) {
            int hash = getHash(key);
            if (hashMap[hash] != null && hashMap[hash].stream().anyMatch(node1 -> node1.key.equals(key))) {
                return hashMap[hash].stream().filter(node1 -> node1.key.equals(key)).findAny().get();
            }
            return null;
        }

        public String prev(String inputValue1) {
            LinkedMapNode node = get(inputValue1);
            if (node != null)
                return node.prev != null ? node.prev.value : "none";
            return "none";
        }

        public String next(String inputValue1) {
            LinkedMapNode node = get(inputValue1);
            if (node != null)
                return node.next != null ? node.next.value : "none";
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

    private class LinkedMapNode {
        private String key;
        private String value;
        private LinkedMapNode prev;
        private LinkedMapNode next;

        private LinkedMapNode(String key, String value, LinkedMapNode prev) {
            this.key = key;
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