/* Kornilov Nikita, M3102, 28.09.2020 */
package Lab2;

import java.io.*;
import java.util.*;

public class Task2_TreeMap {
    BufferedReader br;
    StringTokenizer in;
    PrintWriter out;

    public static void main(String[] args) {
        new Task2_TreeMap().run("race.in", "race.out");
    }

    public String nextToken() throws IOException {
        while (in == null || !in.hasMoreTokens())
            in = new StringTokenizer(br.readLine());
        return in.nextToken();
    }

    public int nextInt() throws IOException {
        return Integer.parseInt(nextToken());
    }

    public void solve() throws IOException {
        int n = nextInt();
        TreeMap<String, ArrayDeque<String>> countries = new TreeMap<>(String::compareTo);


        for (int i = 0; i < n; i++) {
            String country = nextToken();
            if (!countries.containsKey(country)) {
                ArrayDeque<String> sportsmen = new ArrayDeque<>();
                sportsmen.add(nextToken());
                countries.put(country, sportsmen);
            } else
                countries.get(country).add(nextToken());
        }

        for (String country : countries.keySet()) {
            out.write(String.format("=== %s ===\n", country));
            for (String sportsman : countries.get(country))
                out.write(String.format("%s\n", sportsman));
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
}