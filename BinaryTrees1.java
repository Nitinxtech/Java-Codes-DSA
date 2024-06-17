package com.nitin;

import java.util.*;
import java.util.LinkedList;

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


        //Top View Of A Tree
        static class Information {
            Node node;
            int hd;

            public Information(Node node, int hd) {
                this.node = node;
                this.hd = hd;
            }
        }

        public static void topViewOfTree(Node root) {
            //Level Order
            Queue<Information> q = new LinkedList<>();
            HashMap<Integer, Node> map = new HashMap<>();

            int min=0 , max = 0;
            q.add(new Information(root, 0));

            while (!q.isEmpty()) {
                Information curr = q.remove();
                if (curr == null) {
                    if (q.isEmpty()) {
                        break;
                    } else {
                        q.add(null);
                    }
                } else {
                    if (!map.containsKey(curr.hd)) { //first time my horizontal distance is occuring
                        map.put(curr.hd, curr.node);
                    }

                    if (curr.node.left != null) {
                        q.add(new Information(curr.node.left, curr.hd-1));
                        min = Math.min(min,curr.hd-1);
                    }

                    if (curr.node.right != null) {
                        q.add(new Information(curr.node.right, curr.hd+1));
                        max = Math.max(max, curr.hd+1);
                    }
                }
            }
            for (int i=min; i<=max; i++) {
                System.out.print(map.get(i).data + " ");
            }
            System.out.println();
        }

        //Print Kth Level Nodes O(n) -> TC
        public static void printKthLevel(Node root, int level, int k) {
            if (root == null) {
                return;
            }
            if (level == k) {
                System.out.print(root.data + " ");
                return;
            }

            printKthLevel(root.left, level+1, k);
            printKthLevel(root.right, level+1, k);
        }

        //Lowest Common Ancestors -> Approach 1 -> O(n) Tc & O(n) Sc
        public static boolean getPath(Node root, int n, ArrayList<Node> path) {
            if (root == null) {
                return false;
            }

            path.add(root);
            if (root.data == n) {
                return true;
            }

            boolean foundLeft = getPath(root.left, n, path);
            boolean foundRight = getPath(root.right, n, path);

            if (foundLeft || foundRight) {
                return true;
            }
            path.remove(path.size()-1);
            return false;
        }

        public static Node lca(Node root, int n1, int n2) {
            ArrayList<Node> path1 = new ArrayList<>();
            ArrayList<Node> path2 = new ArrayList<>();

            getPath(root, n1, path1);
            getPath(root, n2, path2);

            //Last Common Ancestor
            int i=0;
            for (; i<path1.size() && i<path2.size(); i++) {
                if (path1.get(i) != path2.get(i)) {
                    break;
                }
            }

            //Last Equal Node -> i-1th
            Node lca = path1.get(i-1);
            return lca;
        }

        //Lowest Common Ancestor -> Approach 2 -> Tc O(n) & Sc O(1)[if we don't consider the recursion]
        public static Node lca2(Node root, int n1, int n2) {
            if (root == null || root.data == n1 || root.data == n2) {
                return root;
            }
            Node leftLca = lca2(root.left, n1, n2);
            Node rightLca = lca2(root.right, n1, n2);

            //leftLca = val & rightLca = null
            if (rightLca == null) {
                return leftLca;
            }
            //rightLca = val & leftLca = null
            if (leftLca == null) {
                return rightLca;
            }
            return root;
        }

        //Minimum Distance Between Nodes -> O(n) Tc
        public static int lcaDist(Node root, int n) {
            if (root == null) {
                return -1;
            }
            if (root.data == n) {
                return 0;
            }

            int leftDist = lcaDist(root.left, n);
            int rightDist = lcaDist(root.right, n);

            if (leftDist == -1 && rightDist == -1) {
                return -1;
            } else if (leftDist == -1) {
                return rightDist + 1;
            } else {
                return leftDist + 1;
            }
        }
        public static int minDistBwNodes(Node root, int n1, int n2) {
            Node lca = lca2(root, n1, n2);

            int distance1 = lcaDist(lca, n1);
            int distance2 = lcaDist(lca, n2);

            return distance1 + distance2;
        }

        //Kth Ancestor -> O(n) Tc
        public static int kAncestor(Node root, int n, int k) {
            if (root == null) {
                return -1;
            }

            if (root.data == n) {
                return 0;
            }

            int leftDist = kAncestor(root.left, n, k);
            int rightDist = kAncestor(root.right, n, k);

            if (leftDist == -1 && rightDist == -1) {
                return -1;
            }

            int max = Math.max(leftDist, rightDist);
            if (max+1 == k) {
                System.out.println(root.data);
            }
            return max+1;
        }

        //Transform To Sum Tree -> O(n)
        public static int transform(Node root) {
            if (root == null) {
                return 0;
            }

            int leftChild = transform(root.left);
            int rightChild = transform(root.right);

            int data = root.data;

            int newLeft = root.left == null ? 0 : root.left.data;
            int newRight = root.right == null ? 0 : root.right.data;

            root.data = newLeft + leftChild + newRight + rightChild;
            return data;
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
//        BinaryTree.topViewOfTree(root);

//        BinaryTree.printKthLevel(root, 1, 2);

//        int n1 = 4, n2 = 6;
//        System.out.println(BinaryTree.lca(root, n1, n2).data);
//        System.out.println(BinaryTree.lca2(root, n1, n2).data);
//        System.out.println(BinaryTree.minDistBwNodes(root, n1, n2));

//        int n = 5, k = 2;
//        BinaryTree.kAncestor(root, n, k);

        BinaryTree.transform(root);
        BinaryTree.preOrder(root);
    }
}
