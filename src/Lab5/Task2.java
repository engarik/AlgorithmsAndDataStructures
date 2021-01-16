/* Kornilov Nikita, M3102, 16.11.2020 */

package Lab5;

import java.io.*;
import java.util.*;

public class Task2 {
    BufferedReader br;
    StringTokenizer in;
    PrintWriter out;
    int[][] input;

    public static void main(String[] args) {
        String inputFile = "check.in";
        String outputFile = "check.out";
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
        Tree tree = new Tree();
        input = new int[n][3];
        for (int i = 0; i < n; i++) {
            input[i][0] = nextInt();
            input[i][1] = nextInt();
            input[i][2] = nextInt();
        }

        if (n != 0) {
            tree.head = build(input[0]);

            out.println(
                    isCorrect(tree.head.leftChild, Integer.MIN_VALUE, tree.head.value) &&
                            isCorrect(tree.head.rightChild, tree.head.value, Integer.MAX_VALUE) ? "YES" : "NO"
            );
        }
        else out.println("YES");
    }

    private TreeNode build(int[] pars) {
        TreeNode node = new TreeNode(pars[0]);
        if (pars[1] != 0) {
            node.leftChild = build(input[pars[1] - 1]);
        }
        if (pars[2] != 0) {
            node.rightChild = build(input[pars[2] - 1]);
        }

        return node;
    }

    private class TreeNode {
        int value;
        TreeNode leftChild = null;
        TreeNode rightChild = null;

        public TreeNode(int value) {
            this.value = value;
        }
    }

    private class Tree {
        TreeNode head = null;

        public Tree() {
        }

        public void add(int value) {
            if (head == null) {
                head = new TreeNode(value);
            } else {
                addTo(head, value);
            }
        }

        public void addTo(TreeNode node, int value) {
            if (value < node.value) {
                if (node.leftChild == null) {
                    node.leftChild = new TreeNode(value);
                } else {
                    addTo(node.leftChild, value);
                }
            } else {
                if (node.rightChild == null) {
                    node.rightChild = new TreeNode(value);
                } else {
                    addTo(node.rightChild, value);
                }
            }
        }
    }

    private boolean isCorrect(TreeNode node, int min, int max) {
        if (node == null)
            return true;
        if (node.value > min && node.value < max) {
            boolean left = isCorrect(node.leftChild, min, node.value);
            boolean right = isCorrect(node.rightChild, node.value, max);

            return left && right;
        } else
            return false;
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