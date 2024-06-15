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

        //Pre order traversal
        public static void preOrder(Node root) {
            if (root == null) {
//                System.out.print("-1" + " ");
                return;
            }
            System.out.print(root.data + " ");
            preOrder(root.left);
            preOrder(root.right);
        }

        //In order traversal
        public static void inOrder(Node root) {
            if (root == null) {
                return;
            }

            inOrder(root.left);
            System.out.print(root.data + " ");
            inOrder(root.right);
        }

        //Post order traversal
        public static void postOrder(Node root) {
            if (root == null) {
                return;
            }

            postOrder(root.left);
            postOrder(root.right);
            System.out.print(root.data + " ");
        }

        //Level order traversal
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

        //Height of the tree
        public static int height(Node root) {
            if (root == null) {
                return 0;
            }

            int lh = height(root.left);
            int rh = height(root.right);
            return Math.max(lh,rh) + 1;
        }

        //Counting the no of nodes
        public static int countNodes(Node root) {
            if (root == null) {
                return 0;
            }

            int countLeft = countNodes(root.left);
            int countRight = countNodes(root.right);
            return countLeft + countRight + 1;
        }

        //Sum of nodes
        public static int sum(Node root) {
            if (root == null) {
                return 0;
            }

            int leftSum = sum(root.left);
            int rightSum = sum(root.right);
            return leftSum + rightSum + root.data;
        }

        //Diameter of a tree -> Approach 1
        public static int diameterOfTree1(Node root) {   // O(n2) -> Time Complexity
            if (root == null) {
                return 0;
            }

            int leftDiameter = diameterOfTree1(root.left);
            int heightLeft = height(root.left);
            int rightDiameter = diameterOfTree1(root.right);
            int heightRight = height(root.right);
            int selfDiameter = heightLeft + heightRight + 1;

            return Math.max(selfDiameter, Math.max(leftDiameter, rightDiameter));
        }

        //Diameter of a tree -> Approach 2
        static class Info {
            int diam;
            int ht;

            public Info(int diam, int ht) {
                this.diam = diam;
                this.ht = ht;
            }
        }

        public static Info diameterOfTree2(Node root) {
            if (root == null) {
                return new Info(0, 0);
            }

            Info leftInfo = diameterOfTree2(root.left);
            Info rightInfo = diameterOfTree2(root.right);
            int diam = Math.max(Math.max(leftInfo.diam, rightInfo.diam), leftInfo.ht + rightInfo.ht + 1);
            int ht = Math.max(leftInfo.ht, rightInfo.ht) + 1;

            return new Info(diam, ht);
        }

        //Check whether subtree belongs to main tree or not
        public static boolean isIdentical(Node node, Node subRoot) {
            if (node == null && subRoot == null) {
                return true;
            } else if (node == null || subRoot == null || node.data != subRoot.data) {
                return false;
            }
            if (!isIdentical(node.left, subRoot.left)) {
                return false;
            }
            if (!isIdentical(node.right, subRoot.right)) {
                return false;
            }
            return true;
        }


        public static boolean isSubtree(Node root, Node subRoot) {
            if (root == null) {
                return false;
            }

            if (root.data == subRoot.data) {
                if (isIdentical(root, subRoot)) {
                    return true;
                }
            }

            return isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
        }

    }
    public static void main(String[] args) {
//        int nodes[] = {1,2,4,-1,-1,5,-1,-1,3,-1,6,-1,-1};
//        BinaryTree tree  = new BinaryTree();
//        Node root = tree.buildTree(nodes);
//        tree.preOrder(root);
//        tree.inOrder(root);
//        tree.postOrder(root);
//        tree.levelOrder(root);
//        System.out.println(tree.height(root));
//        System.out.println(tree.countNodes(root));
//        System.out.println(tree.sum(root));

        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);

//        System.out.println(BinaryTree.diameterOfTree1(root));
//        System.out.println(BinaryTree.diameterOfTree2(root).diam);

//        Node subRoot = new Node(2);
//        subRoot.left = new Node(4);
//        subRoot.right = new Node(5);

//        System.out.println(BinaryTree.isSubtree(root, subRoot));
    }
}
