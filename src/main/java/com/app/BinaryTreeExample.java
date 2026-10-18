package com.app;

public class BinaryTreeExample {

    public static void main(String[] args) {

        BinaryTree bt = new BinaryTree();
        bt.insert(10);
        bt.insert(20);
        bt.insert(5);
        bt.insert(30);
        bt.insert(15);

        bt.traverse();
        bt.search(30);

    }

    static class Node{
        int data;
        Node left,right;


    }

    static class BinaryTree{
        Node root;

        void insert(int data){
            root = insertRecursive(root, data);
        }

        private Node insertRecursive(Node root, int data) {
            if(root==null){
                Node n = new Node();
                n.data = data;
                root= n;
            }
            else if(data < root.data ){
                root.left=insertRecursive(root.left, data);
            } else if(data > root.data) {
                root.right=insertRecursive(root.right, data);
            }
            return root;
        }

        public Node search(int data){
            if(root== null){
                return null;
            }
            return searchRecursive(root, data);

        }

        private Node searchRecursive(Node node, int data) {
             if(node.data == data){
                return node;
            } else if (data < node.data) {
                return searchRecursive(node.left, data);
            } else {
                return searchRecursive(node.right, data);
            }
        }

        public void traverse() {
            traverseRecursive(root);
        }

        private void traverseRecursive(Node left) {
            if(left != null){
                traverseRecursive(left.left);
                System.out.println(left.data);
                traverseRecursive(left.right);
            }
        }
    }
}
