/* Kornilov Nikita, M3102, 18.10.2020 */

package Lab3;

import java.io.*;
import java.util.*;

public class Task4 {
    BufferedReader br;
    StringTokenizer in;
    PrintWriter out;

    static class Heap {
        int size;
        int length;

        int[] heap;

        public Heap(int length, int size) {
            heap = new int[length];
            this.length = length;
            this.size = size;
        }

        public void put(int pos, int value) {
            heap[pos] = value;
        }

        public void push(int value) {
            size++;
            heap[size - 1] = value;
            siftUp(size - 1);
        }

        public int extractMin() {
            if (size == 0)
                return Integer.MAX_VALUE;
            int min = heap[0];
            heap[0] = heap[size - 1];
            size--;
            siftDown(0);
            return min;
        }

        private void siftDown(int i) {
            while (2 * i + 1 < size) {
                int left = 2 * i + 1;
                int right = 2 * i + 2;
                int j = left;
                if (right < size && heap[right] < heap[left])
                    j = right;
                if (heap[i] <= heap[j]) {
                    break;
                }
                int tmp = heap[i];
                heap[i] = heap[j];
                heap[j] = tmp;
                i = j;
            }
        }

        private void siftUp(int i) {
            while (heap[i] < heap[(i - 1) / 2]) {
                int tmp = heap[i];
                heap[i] = heap[(i - 1) / 2];
                heap[(i - 1) / 2] = tmp;
                i = (i - 1) / 2;
            }
        }

        private int findIndexByValue(int value) {
            int index = 0;
            while (heap[index] != value) {
                index++;
            }
            return index;
        }

        public void decreaseKey(int x, int y) {
            int index = findIndexByValue(x);
            heap[index] = y;
            siftUp(index);
        }

        @Override
        public String toString() {
            StringBuilder res = new StringBuilder();
            for (int i = 0; i < size; i++) {
                res.append(heap[i]);
                res.append(' ');
            }
            res.deleteCharAt(res.length() - 1);
            return res.toString();
        }
    }


    public static void main(String[] args) {
        String inputFile = "priorityqueue.in";
        String outputFile = "priorityqueue.out";
        new Task4().run(inputFile, outputFile);
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
        ArrayList<String> commands = new ArrayList<>();
        String tmpString;
        int tmpInt1, tmpInt2, tmpInt3;
        Heap heap = null;

        while (true) {
            try {
                tmpString = nextToken();
                switch (tmpString) {
                    case "push": {
                        tmpInt1 = nextInt();
                        tmpString += " " + tmpInt1;
                        commands.add(tmpString);

                        if (heap == null) {
                            heap = new Heap(1000000, 1);
                            heap.put(0, tmpInt1);
                        } else {
                            heap.push(tmpInt1);
                        }
                        break;
                    }
                    case "decrease-key": {
                        String newString = "push ";
                        tmpInt1 = nextInt();
                        tmpInt2 = nextInt();
                        tmpString += " " + tmpInt1 + " " + tmpInt2;
                        commands.add(tmpString);

                        tmpInt3 = Integer.parseInt(commands.get(tmpInt1 - 1).split(" ")[1]);

                        newString += tmpInt2;
                        commands.set(tmpInt1 - 1, newString);
                        //System.out.println();


                        assert heap != null;
                        heap.decreaseKey(tmpInt3, tmpInt2);
                        break;
                    }
                    case "extract-min": {
                        commands.add(tmpString);

                        if (heap == null) {
                            out.println("*");
                        } else {
                            tmpInt3 = heap.extractMin();
                            if (tmpInt3 == Integer.MAX_VALUE) {
                                out.println("*");
                            } else {
                                out.println(tmpInt3);
                            }
                        }
                        break;
                    }
                }
            } catch (NullPointerException e) {
                //System.out.println(commands.toString());
                break;
            }
        }

        //System.out.println(heap);

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