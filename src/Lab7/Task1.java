/* Kornilov Nikita, M3102, 11.12.2020 */

package Lab7;

import java.io.*;
import java.util.*;

public class Task1 {
    BufferedReader br;
    StringTokenizer in;
    PrintWriter out;

    int[][] inputArray;


    public static void main(String[] args) {
        String fileName = "balance";
        new Task1().run(String.format("%s.in", fileName), String.format("%s.out", fileName));
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

    public void solve() throws IOException {
        int n = nextInt();
        inputArray = new int[n][3];

        for (int i = 0; i < n; i++) {
            inputArray[i] = new int[]{Integer.parseInt(nextToken()), Integer.parseInt(nextToken()), Integer.parseInt(nextToken())};
        }
        Tree tree = new Tree(init(null, 0), n);
        tree.updateTreeNodeHeights();
        for (int i = 0; i < n; i++) {
            out.println(tree.balanceArray[i]);
        }

    }

    private TreeNode init(TreeNode parent, int index) {
        TreeNode node = new TreeNode(inputArray[index][0], index);
        node.parent = parent;
        if (inputArray[index][1] != 0) {
            node.leftChild = init(node, inputArray[index][1] - 1);
        }
        if (inputArray[index][2] != 0) {
            node.rightChild = init(node, inputArray[index][2] - 1);
        }
        return node;
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

    private static class Tree {
        int[] balanceArray;
        TreeNode head;

        public Tree(TreeNode head, int n) {
            this.head = head;
            balanceArray = new int[n];
        }

        private void updateTreeNodeHeights() {
            head.updateHeight(balanceArray);
        }
    }

    private static class TreeNode {
        int index;
        int value;
        int height;
        int leftChildHeight;
        int rightChildHeight;

        TreeNode parent;
        TreeNode leftChild;
        TreeNode rightChild;

        public TreeNode(int value, int index) {
            this.index = index;
            this.value = value;
            height = 0;
            leftChildHeight = 0;
            rightChildHeight = 0;
            parent = null;
            leftChild = null;
            rightChild = null;
        }

        public int balance() {
            return rightChildHeight - leftChildHeight;
        }

        public int updateHeight(int[] balanceArray) {
            if (leftChild != null)
                leftChildHeight = leftChild.updateHeight(balanceArray);
            if (rightChild != null)
                rightChildHeight = rightChild.updateHeight(balanceArray);
            height = Math.max(leftChildHeight, rightChildHeight) + 1;
            balanceArray[index] = balance();
            return height;
        }
    }
}