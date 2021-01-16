/* Kornilov Nikita, M3102, 16.11.2020 */

package Lab5;

import Lab7.Task5;

import java.io.*;
import java.util.*;

public class Task3 {
    BufferedReader br;
    StringTokenizer in;
    PrintWriter out;

    public static void main(String[] args) {
        String inputFile = "bstsimple.in";
        String outputFile = "bstsimple.out";
        new Task3().run(inputFile, outputFile);
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
        Tree tree = new Tree();
        boolean working = true;
        String input;
        int value;
        TreeNode tmp;

        while (working) {
            try {
                input = nextToken();
                value = nextInt();

                switch (input) {
                    case "insert": {
                        tree.add(value);
                        break;
                    }
                    case "delete": {
                        tree.remove(value);
                        break;
                    }
                    case "exists": {
                        out.println(tree.exists(value));
                        break;
                    }
                    case "next": {
                        tmp = tree.next(value);
                        out.println(t != null ? res : "none");
                        break;
                    }
                    case "prev": {
                        Integer res = tree.prev(value);
                        out.println(res != null ? res : "none");
                        break;
                    }
                }
            } catch (NullPointerException ignored) {
                working = false;
            }

        }

    }

    private static class Tree {
        TreeNode head;

        public Tree() {
            this.head = null;
        }

        private void add(int value) {
            head = addNode(head, value);
        }

        private void remove(int value) {
            head = removeNode(head, value);
        }

        private boolean exists(int value) {
            return findNodeByValue(head, value) != null;
        }

        private int next(int value) {
            return findMin(findNodeByValue(head, value).rightChild) != null ?;
        }

        private int prev(int value) {
            return findMax(findNodeByValue(head, value).leftChild) != null;
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
            return node;
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
                return max;
            }
            return node;
        }

        private TreeNode findMax(TreeNode node) {
            return node.rightChild != null ? findMax(node.rightChild) : node;
        }

        private TreeNode findMin(TreeNode node) {
            return node.leftChild != null ? findMax(node.leftChild) : node;
        }

        private TreeNode removeMax(TreeNode node) {
            if (node.rightChild == null) {
                return node.leftChild;
            }
            node.rightChild = removeMax(node.rightChild);
            return node;
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
    }

    private static class TreeNode {
        int value;
        TreeNode leftChild;
        TreeNode rightChild;

        public TreeNode(int value) {
            this.value = value;
            leftChild = null;
            rightChild = null;
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