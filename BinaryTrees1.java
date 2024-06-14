package com.nitin;

import java.util.LinkedList;
import java.util.Queue;

public class BinaryTrees1 {

    static class Node {
        int data;
        Node left;
        Node right;

        Node(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    static class BinaryTree {
        static int idx = -1;
        public static Node buildTree(int nodes[]) {
            idx++;
            if (nodes[idx] == -1) {
                return null;
            }

            Node newNode = new Node(nodes[idx]);
            newNode.left = buildTree(nodes);
            newNode.right = buildTree(nodes);

            return newNode;
        }

        public static void preOrder(Node root) {
            if (root == null) {
//                System.out.print("-1" + " ");
                return;
            }
            System.out.print(root.data + " ");
            preOrder(root.left);
            preOrder(root.right);
        }

        public static void inOrder(Node root) {
            if (root == null) {
                return;
            }

            inOrder(root.left);
            System.out.print(root.data + " ");
            inOrder(root.right);
        }

        public static void postOrder(Node root) {
            if (root == null) {
                return;
            }

            postOrder(root.left);
            postOrder(root.right);
            System.out.print(root.data + " ");
        }

        public static void levelOrder(Node root) {
            if (root == null) {
                return;
            }

            Queue<Node> q = new LinkedList<>();
            q.add(root);
            q.add(null);

            while (!q.isEmpty()) {
                Node currNode = q.remove();
                if (currNode == null) {
                    System.out.println(); // Print Next Line
                    if (q.isEmpty()) {
                        break;
                    } else {
                        q.add(null);
                    }
                } else { // Other Node Than Null Node
                    System.out.print(currNode.data + " ");
                    if (currNode.left != null) {
                        q.add(currNode.left);
                    }
                    if (currNode.right != null) {
                        q.add(currNode.right);
                    }
                }
            }
        }

        public static int height(Node root) {
            if (root == null) {
                return 0;
            }

            int lh = height(root.left);
            int rh = height(root.right);
            return Math.max(lh,rh) + 1;
        }

        public static int countNodes(Node root) {
            if (root == null) {
                return 0;
            }

            int countLeft = countNodes(root.left);
            int countRight = countNodes(root.right);
            return countLeft + countRight + 1;
        }

        public static int sum(Node root) {
            if (root == null) {
                return 0;
            }

            int leftSum = sum(root.left);
            int rightSum = sum(root.right);
            return leftSum + rightSum + root.data;
        }
    }
    public static void main(String[] args) {
        int nodes[] = {1,2,4,-1,-1,5,-1,-1,3,-1,6,-1,-1};
        BinaryTree tree  = new BinaryTree();
        Node root = tree.buildTree(nodes);
//        tree.preOrder(root);
//        tree.inOrder(root);
//        tree.postOrder(root);
//        tree.levelOrder(root);
//        System.out.println(tree.height(root));
//        System.out.println(tree.countNodes(root));
        System.out.println(tree.sum(root));
    }
}
