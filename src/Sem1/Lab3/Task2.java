/* Kornilov Nikita, M3102, 18.10.2020 */

package Sem1.Lab3;

import java.io.*;
import java.util.*;

public class Task2 {
    BufferedReader br;
    StringTokenizer in;
    PrintWriter out;

    static class Heap {
        int size;
        int length;

        int[] heap;

        public Heap(int n) {
            heap = new int[n + 1];
            length = n;
            size = n;
        }

        public void put(int pos, int value) {
            heap[pos] = value;
        }

        public int get(int pos) {
            return heap[pos];
        }

        @Override
        public String toString() {
            StringBuilder res = new StringBuilder();
            for (int i = 1; i <= length; i++) {
                res.append(heap[i]);
                res.append(' ');
            }
            res.deleteCharAt(res.length() - 1);
            return res.toString();
        }
    }

    public static void main(String[] args) {
        String inputFile = "sort.in";
        String outputFile = "sort.out";
        new Task2().run(inputFile, outputFile);
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
        int n = nextInt();
        Heap heap = new Heap(n);

        for (int i = 1; i <= n; i++) {
            heap.put(i, nextInt());
        }

        heapSort(heap);

        out.print(heap.toString());
    }

    public void heapSort(Heap heap) {
        buildMaxHeap(heap);
        for (int i = heap.length; i > 0; i--) {
            int tmp = heap.get(1);
            heap.put(1, heap.get(i));
            heap.put(i, tmp);
            heap.size--;
            maxHeapify(heap, 1);
        }
    }

    public void buildMaxHeap(Heap heap) {
        heap.size = heap.length;
        for (int i = heap.length / 2; i > 0; i--) {
            maxHeapify(heap, i);
        }
    }

    public void maxHeapify(Heap heap, int i) {
        int left = 2 * i, right = 2 * i + 1, largest;

        if (left <= heap.size && heap.get(left) >= heap.get(i)) {
            largest = left;
        } else {
            largest = i;
        }
        if (right <= heap.size && heap.get(right) >= heap.get(largest)) {
            largest = right;
        }
        if (largest != i) {
            int tmp = heap.get(i);
            heap.put(i, heap.get(largest));
            heap.put(largest, tmp);
            maxHeapify(heap, largest);
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
