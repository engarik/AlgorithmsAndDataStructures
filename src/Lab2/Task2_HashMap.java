/* Kornilov Nikita, M3102, 28.09.2020 */
package Lab2;

import java.io.*;
import java.util.*;

public class Task2_HashMap {
    BufferedReader br;
    StringTokenizer in;
    PrintWriter out;

    public static void main(String[] args) {
        new Task2_HashMap().run("race.in", "race.out");
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
        HashMap<String, ArrayDeque<String>> countries = new HashMap<>();


        for (int i = 0; i < n; i++) {
            String country = nextToken();
            if (!countries.containsKey(country)) {
                ArrayDeque<String> sportsmen = new ArrayDeque<>();
                sportsmen.add(nextToken());
                countries.put(country, sportsmen);
            } else
                countries.get(country).add(nextToken());
        }

        String[] sortedCountryNames = new String[(countries.keySet()).size()];
        int i = 0;
        for (String key : countries.keySet()) {
            sortedCountryNames[i++] = key;
        }

        quickSort(sortedCountryNames, 0, sortedCountryNames.length - 1);

        for (String country : sortedCountryNames) {
            out.write(String.format("=== %s ===\n", country));
            for (String sportsman : countries.get(country))
                out.write(String.format("%s\n", sportsman));
        }
    }

    public void quickSort(String[] array, int leftIndex, int rightIndex) {
        if (leftIndex < rightIndex) {
            int pivotIndex = partition(array, leftIndex, rightIndex);
            quickSort(array, leftIndex, pivotIndex - 1);
            quickSort(array, pivotIndex, rightIndex);
        }
    }

    private int partition(String[] array, int leftIndex, int rightIndex) {
        Random random = new Random();
        String pivotIndex = array[leftIndex + random.nextInt(rightIndex - leftIndex)];

        while (leftIndex <= rightIndex) {
            while (array[leftIndex].compareTo(pivotIndex) < 0)
                leftIndex++;
            while (array[rightIndex].compareTo(pivotIndex) > 0)
                rightIndex--;
            if (leftIndex <= rightIndex) {
                String tmp = array[leftIndex];
                array[leftIndex] = array[rightIndex];
                array[rightIndex] = tmp;
                leftIndex++;
                rightIndex--;
            }
        }
        return leftIndex;
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