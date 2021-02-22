/* Kornilov Nikita, M3102, 25.09.2020 */
package Sem1.Lab2;

import java.io.*;
import java.util.*;

public class Task1 {
    BufferedReader br;
    StringTokenizer in;
    PrintWriter out;

    public static void main(String[] args) {
        new Task1().run("sort.in", "sort.out");
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
        int[] array = new int[n];

        for (int i = 0; i < n; i++)
            array[i] = nextInt();

        if (isNotSorted(array))
            quickSort(array, 0, array.length - 1);

        for (int i = 0; i < array.length - 1; i++) {
            out.print(array[i]);
            out.print(" ");
        }
        out.print(array[array.length - 1]);
    }

    public void quickSort(int[] array, int leftIndex, int rightIndex) {
        if (leftIndex < rightIndex) {
            int pivotIndex = partition(array, leftIndex, rightIndex);
            quickSort(array, leftIndex, pivotIndex - 1);
            quickSort(array, pivotIndex, rightIndex);
        }
    }

    public boolean isNotSorted(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++)
            if (arr[i] >= arr[i + 1])
                return true;
        return false;
    }

    private int partition(int[] array, int leftIndex, int rightIndex) {
        Random random = new Random();
        int pivotIndex = array[leftIndex + random.nextInt(rightIndex - leftIndex)];

        while (leftIndex <= rightIndex) {
            while (array[leftIndex] < pivotIndex)
                leftIndex++;
            while (array[rightIndex] > pivotIndex)
                rightIndex--;
            if (leftIndex <= rightIndex) {
                int tmp = array[leftIndex];
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