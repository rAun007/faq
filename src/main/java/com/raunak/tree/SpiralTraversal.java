package com.raunak.tree;

public class SpiralTraversal {
    class DLLNode {
        int value;

        DLLNode previous;

        DLLNode next;

        public DLLNode(int v) {

            this.value = v;
            this.previous = null;
            this.next = null;
        }
    }

    class TreeNode {
        public int value;

        public TreeNode left;

        public TreeNode right;
    }

    boolean printLeftFirst = true;

    Stack<TreeNode> leftStack = new Stack<SpiralTraversal.TreeNode>();

    Stack<TreeNode> rightStack = new Stack<SpiralTraversal.TreeNode>();

    DLLNode getSpiralDLLFromBST(TreeNode root) throws Exception {

        if (root == null) {
            return null;
        } else {
            DLLNode headNode = new DLLNode(root.value);

            DLLNode prev = headNode;

            if (root.right != null) {
                leftStack.push(root.right);
            }
            if (root.left != null) {
                leftStack.push(root.left);
            }

            while (!leftStack.isEmpty() && !rightStack.isEmpty()) {
                if (printLeftFirst) {
                    printLeftFirst = false;
                    while (!leftStack.isEmpty()) {
                        TreeNode treeNode = leftStack.pop();
                        DLLNode node = new DLLNode(treeNode.value);

                        node.previous = prev;
                        prev = node;

                        if (treeNode.left != null) {
                            rightStack.push(treeNode.left);
                        }

                        if (treeNode.right != null) {
                            rightStack.push(treeNode.right);
                        }
                    }
                } else {
                    printLeftFirst = true;
                    while (!rightStack.isEmpty()) {
                        TreeNode treeNode = rightStack.pop();
                        DLLNode node = new DLLNode(treeNode.value);

                        node.previous = prev;
                        prev = node;

                        if (treeNode.right != null) {
                            leftStack.push(treeNode.right);
                        }

                        if (treeNode.left != null) {
                            leftStack.push(treeNode.left);
                        }
                    }
                }
            }
            return headNode;
        }
    }

}

class Stack<T> {
    private Node<T> head = null;

    public boolean isEmpty() {

        return head == null;
    }

    public void push(T element) {

        head = new Node<T>(element, head);
    }

    public T pop() throws Exception {

        if (isEmpty()) {
            throw new Exception();
        }

        T element = head.element;
        head = head.next;
        return element;
    }
}

class Node<T> {
    T element;

    Node<T> next;

    public Node(T element, Node<T> next) {

        this.element = element;
        this.next = next;
    }

    @Override
    public String toString() {

        return element.toString();
    }
}