/* Kornilov Nikita, M3102, 11.12.2020 */

package Sem1.Lab7;

import java.io.*;
import java.util.*;

public class Task3 {
    BufferedReader br;
    StringTokenizer in;
    PrintWriter out;

    public static void main(String[] args) {
        String fileName = "addition";
        new Task3().run(String.format("%s.in", fileName), String.format("%s.out", fileName));
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
        Tree tree = new Tree(n);
        tree.initTree(inputArray);
        tree.addNode(nextInt());
        out.println(tree.size);
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
        int size;
        TreeNode head;

        public Tree(int size) {
            this.size = size;
            this.head = null;
        }

        private void initTree(int[][] inputArray) {
            head = init(inputArray, 0);
            if (head != null) head.updateHeight();
        }

        private TreeNode init(int[][] inputArray, int index) {
            if (inputArray.length == 0) {
                return null;
            }
            TreeNode node = new TreeNode(inputArray[index][0], index);
            if (inputArray[index][1] != 0) {
                node.leftChild = init(inputArray, inputArray[index][1] - 1);
            }
            if (inputArray[index][2] != 0) {
                node.rightChild = init(inputArray, inputArray[index][2] - 1);
            }
            return node;
        }

        private void addNode(int value) {
            head = addNode(head, value);
            size++;
            updateTreeNodeIndices();
        }

        private TreeNode addNode(TreeNode node, int value) {
            if (node == null) {
                return new TreeNode(value, 0);
            }
            if (value < node.value) {
                node.leftChild = addNode(node.leftChild, value);
            } else {
                node.rightChild = addNode(node.rightChild, value);
            }
            return balance(node);
        }

        private void updateTreeNodeIndices() {
            head.reIndex(1);
        }

        private TreeNode smallLeftRotation(TreeNode node) {
            TreeNode tmp = node.rightChild;
            node.rightChild = tmp.leftChild;
            tmp.leftChild = node;
            node.updateHeight();
            tmp.updateHeight();
            return tmp;
        }

        private TreeNode smallRightRotation(TreeNode node) {
            TreeNode tmp = node.leftChild;
            node.leftChild = tmp.rightChild;
            tmp.rightChild = node;
            node.updateHeight();
            tmp.updateHeight();
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

        private TreeNode rightRotation(TreeNode node) {
            if (node.leftChild.balance() <= 0) {
                return smallRightRotation(node);
            } else {
                return bigRightRotation(node);
            }
        }

        private TreeNode balance(TreeNode node) {
            node.updateHeight();
            if (node.balance() == 2) {
                return leftRotation(node);
            } else if (node.balance() == -2) {
                return rightRotation(node);
            } else {
                return node;
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

        TreeNode leftChild;
        TreeNode rightChild;

        public TreeNode(int value, int index) {
            this.index = index;
            this.value = value;
            height = 1;
            leftChild = null;
            rightChild = null;
        }

        public int balance() {
            return (rightChild != null ? rightChild.height : 0) - (leftChild != null ? leftChild.height : 0);
        }

        public int updateHeight() {
            int leftChildHeight = 0;
            int rightChildHeight = 0;
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