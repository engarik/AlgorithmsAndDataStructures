/* Kornilov Nikita, M3102, 11.12.2020 */

package Lab7;

import java.io.*;
import java.util.*;

public class Task2 {
    BufferedReader br;
    StringTokenizer in;
    PrintWriter out;

    public static void main(String[] args) {
        String fileName = "rotation";
        new Task2().run(String.format("%s.in", fileName), String.format("%s.out", fileName));
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
        int[][] inputArray = new int[n][3];

        for (int i = 0; i < n; i++) {
            inputArray[i] = new int[]{Integer.parseInt(nextToken()), Integer.parseInt(nextToken()), Integer.parseInt(nextToken())};
        }
        Tree tree = new Tree();
        tree.initTree(inputArray);
        tree.updateTreeNodeHeights();
        tree.head = tree.leftRotation(tree.head);
        tree.updateTreeNodeIndices();
        out.println(n);
        tree.preorderHead(out);
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
        TreeNode head;

        public Tree() {
            this.head = null;
        }

        private void initTree(int[][] inputArray) {
            head = init(inputArray, 0);
        }

        private TreeNode init(int[][] inputArray, int index) {
            TreeNode node = new TreeNode(inputArray[index][0], index);
            if (inputArray[index][1] != 0) {
                node.leftChild = init(inputArray, inputArray[index][1] - 1);
            }
            if (inputArray[index][2] != 0) {
                node.rightChild = init(inputArray, inputArray[index][2] - 1);
            }
            return node;
        }

        private void updateTreeNodeHeights() {
            head.updateHeight();
        }

        private void updateTreeNodeIndices() {
            head.reIndex(1);
        }

        private TreeNode smallLeftRotation(TreeNode node) {
            TreeNode tmp = node.rightChild;
            node.rightChild = tmp.leftChild;
            tmp.leftChild = node;
            return tmp;
        }

        private TreeNode smallRightRotation(TreeNode node) {
            TreeNode tmp = node.leftChild;
            node.leftChild = tmp.rightChild;
            tmp.rightChild = node;
            return tmp;
        }

        private TreeNode bigLeftRotation(TreeNode node) {
            node.rightChild = smallRightRotation(node.rightChild);
            return smallLeftRotation(node);
        }

        private TreeNode bigRightRotation(TreeNode node) {
            node.leftChild = smallLeftRotation(node.leftChild);
            return smallRightRotation(node);
        }

        private TreeNode leftRotation(TreeNode node) {
            if (node.rightChild.balance() >= 0) {
                return smallLeftRotation(node);
            } else {
                return bigLeftRotation(node);
            }
        }

        private void preorderHead(PrintWriter out) {
            preorder(out, head);
        }

        private void preorder(PrintWriter out, TreeNode node) {
            if (node != null) {
                out.print(node.value);
                out.print(" ");
                out.print(node.leftChild != null ? node.leftChild.index : 0);
                out.print(" ");
                out.print(node.rightChild != null ? node.rightChild.index : 0);
                out.println();
                preorder(out, node.leftChild);
                preorder(out, node.rightChild);
            }
        }
    }

    private static class TreeNode {
        int index;
        int value;
        int height;
        int leftChildHeight;
        int rightChildHeight;

        TreeNode leftChild;
        TreeNode rightChild;

        public TreeNode(int value, int index) {
            this.index = index;
            this.value = value;
            height = 0;
            leftChildHeight = 0;
            rightChildHeight = 0;
            leftChild = null;
            rightChild = null;
        }

        public int balance() {
            return rightChildHeight - leftChildHeight;
        }

        public int updateHeight() {
            if (leftChild != null)
                leftChildHeight = leftChild.updateHeight();
            if (rightChild != null)
                rightChildHeight = rightChild.updateHeight();
            height = Math.max(leftChildHeight, rightChildHeight) + 1;
            return height;
        }

        public int reIndex(int index) {
            this.index = index;
            if (leftChild != null)
                index = leftChild.reIndex(index + 1);
            if (rightChild != null)
                index = rightChild.reIndex(index + 1);
            return index;
        }
    }
}