/* Kornilov Nikita, M3102, 11.12.2020 */

package Sem1.Lab7;

import java.io.*;
import java.util.*;

public class Task5 {
    BufferedReader br;
    StringTokenizer in;
    PrintWriter out;

    public static void main(String[] args) {
        String fileName = "avlset";
        new Task5().run(String.format("%s.in", fileName), String.format("%s.out", fileName));
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
        Tree tree = new Tree();
        int n = nextInt();

        for (int i = 0; i < n; i++) {
            switch (nextToken()) {
                case "A" : {
                    out.println(tree.add(nextInt()));
                    break;
                }
                case "D" : {
                    out.println(tree.remove(nextInt()));
                    break;
                }
                case "C" : {
                    out.println(tree.exists(nextInt()) ? "Y" : "N");
                    break;
                }
            }
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

    private static class Tree {
        TreeNode head;

        public Tree() {
            this.head = null;
        }

        private int add(int value) {
            head = addNode(head, value);
            return headBalance();
        }

        private int remove(int value) {
            head = removeNode(head, value);
            return headBalance();
        }

        private boolean exists(int value) {
            return findNodeByValue(head, value) != null;
        }

        private int headBalance() {
            return head != null ? head.balance() : 0;
        }

        private TreeNode addNode(TreeNode node, int value) {
            if (node == null) {
                return new TreeNode(value);
            }
            if (value == node.value) {
                return node;
            }
            if (value < node.value) {
                node.leftChild = addNode(node.leftChild, value);
            } else {
                node.rightChild = addNode(node.rightChild, value);
            }
            return balance(node);
        }

        private TreeNode removeNode(TreeNode node, int value) {
            if (node == null) {
                return null;
            }
            if (value < node.value) {
                node.leftChild = removeNode(node.leftChild, value);
            } else if (value > node.value) {
                node.rightChild = removeNode(node.rightChild, value);
            } else {
                TreeNode tmp1 = node.leftChild;
                TreeNode tmp2 = node.rightChild;
                if (tmp1 == null) {
                    return tmp2;
                }
                TreeNode max = findMax(tmp1);
                max.leftChild = removeMax(tmp1);
                max.rightChild = tmp2;
                return balance(max);
            }
            return balance(node);
        }

        private TreeNode findNodeByValue(TreeNode node, int value) {
            if (node == null) {
                return null;
            }
            if (value < node.value) {
                return findNodeByValue(node.leftChild, value);
            } else if (value > node.value) {
                return findNodeByValue(node.rightChild, value);
            } else {
                return node;
            }
        }

        private TreeNode findMax(TreeNode node) {
            return node.rightChild != null ? findMax(node.rightChild) : node;
        }

        private TreeNode removeMax(TreeNode node) {
            if (node.rightChild == null) {
                return node.leftChild;
            }
            node.rightChild = removeMax(node.rightChild);
            return balance(node);
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

    }

    private static class TreeNode {
        int value;
        int height;

        TreeNode leftChild;
        TreeNode rightChild;

        public TreeNode(int value) {
            this.value = value;
            height = 1;
            leftChild = null;
            rightChild = null;
        }

        public int balance() {
            return (rightChild != null ? rightChild.height : 0) - (leftChild != null ? leftChild.height : 0);
        }

        public void updateHeight() {
            int leftChildHeight = 0;
            int rightChildHeight = 0;
            if (leftChild != null)
                leftChildHeight = leftChild.height;
            if (rightChild != null)
                rightChildHeight = rightChild.height;
            height = Math.max(leftChildHeight, rightChildHeight) + 1;
        }

    }
}