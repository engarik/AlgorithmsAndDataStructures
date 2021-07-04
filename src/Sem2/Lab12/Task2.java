/* Kornilov Nikita, M3102, 16.04.2021 */

package Sem2.Lab12;

import java.io.*;
import java.util.*;

public class Task2 {
    BufferedReader br;
    StringTokenizer in;
    PrintWriter out;

    public static void main(String[] args) {
        new Task2().run();
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

    public void solve() throws IOException {
        int n = nextInt();
        int[] inputArray = new int[n];
        int[] parent = new int[n];

        for (int i = 0; i < n; i++) {
            inputArray[i] = nextInt();
        }

        TreeMap<Integer, Integer> map = new TreeMap<>(); // key - value, value - index

        for (int i = 0; i < n; i++) {
            if (map.containsKey(inputArray[i])) {
                continue;
            }

            map.put(inputArray[i], i);

            if (map.lastKey() != inputArray[i]) {
                map.remove(map.higherKey(inputArray[i]));
            }

            if (map.lowerKey(inputArray[i]) != null) {
                parent[i] = map.lowerEntry(inputArray[i]).getValue();
            }
            else {
                parent[i] = map.get(inputArray[i]);
            }
        }

        Stack<Integer> lis = new Stack<>();

        Iterator<Integer> iterator = map.descendingKeySet().iterator();

        int index = map.lastEntry().getValue();

        while (iterator.hasNext()) {
            lis.push(inputArray[index]);
            index = parent[index];
            iterator.next();
        }

        out.println(lis.size());
        while (!lis.isEmpty()) {
            out.print(lis.pop() + " ");
        }

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