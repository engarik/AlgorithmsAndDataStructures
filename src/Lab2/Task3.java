/* Kornilov Nikita, M3102, 28.09.2020 */
package Lab2;

import java.io.*;
import java.util.*;

public class Task3 {
    BufferedReader br;
    StringTokenizer in;
    PrintWriter out;
    long inversions;

    public static void main(String[] args) {
        new Task3().run("inversions.in", "inversions.out");
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

        for (int i = 1; i < n; i++) {
            int temp = array[i];
            int j;
            for (j = i - 1; j >= 0 && temp < array[j]; j--) {
                array[j + 1] = array[j];
                inversions++;
            }
            array[j + 1] = temp;
        }

        //mergeSort(array, n);
        //System.out.printf("MERGED ARRAY %s \n %d\n", Arrays.toString(array), inversions);


        out.print(inversions);
    }

    public void mergeSort(int[] array, int n) {
        if (n < 2)
            return;

        int mid = n / 2;
        int[] leftArray = new int[mid];
        System.arraycopy(array, 0, leftArray, 0, mid);
        int[] rightArray = new int[n - mid];
        System.arraycopy(array, mid, rightArray, 0, n - mid);

        mergeSort(leftArray, mid);
        mergeSort(rightArray, n - mid);

        merge(array, leftArray, rightArray, mid, n - mid);
        //System.out.println(Arrays.toString(array));

    }

    public void merge(int[] mergedArray, int[] leftArray, int[] rightArray, int left, int right) {
        int i = 0, j = 0, k = 0;
        while (i < left && j < right) {
            if (leftArray[i] <= rightArray[j]) {
                mergedArray[k++] = leftArray[i++];
            } else {
                mergedArray[k++] = rightArray[j++];
                inversions += leftArray.length - i;
            }
        }
        while (i < left) {
            mergedArray[k++] = leftArray[i++];
        }
        while (j < right) {
            mergedArray[k++] = rightArray[j++];
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