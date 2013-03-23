package com.raunak.linkedlist;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Given a singly linked list of size ‘n’ and a number ‘k’, write a program to reverse every ‘k’ nodes.
 * 
 * Eg:
 * 
 * Input: 1->2->3->4->5->6 and k=3
 * 
 * Output: 3->2->1->6->5->4
 * 
 * @author raunak
 * 
 */

public class ReverseLinkedList {

    /**
     * Reverses the linked list in groups of size k and returns the pointer to the new head node.
     */
    public Node reverse(Node headNode, int k) {

        Node currNode = headNode;
        Node next = null;
        Node prev = null;

        int count = 0;

        /* reverse first k nodes of the linked list */
        while (currNode != null && count < k) {
            next = currNode.getNextNode();
            currNode.setNextNode(prev);
            prev = currNode;
            currNode = next;
            count++;
        }
        if (next != null) {
            headNode.setNextNode(reverse(next, k));
        }

        return prev;
    }

    public static void main(String[] args) throws IOException {

        // Node n6 = new Node(6, null);
        // Node n5 = new Node(5, n6);
        // Node n4 = new Node(4, n5);
        // Node n3 = new Node(3, n4);
        // Node n2 = new Node(2, n3);
        // Node n1 = new Node(1, n2);

        InputStreamReader in = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(in);
        String s = br.readLine();

        // 1->2->3->4->5->63

        String[] input = (s.split(" and k="))[0].split("->");

        int k = Integer.parseInt(s.split(" and k=")[1]);

        Node[] nodes = new Node[input.length];

        for (int i = 0; i < input.length; i++) {
            Node n = new Node(Integer.parseInt(input[i]), null);
            nodes[i] = n;
        }

        for (int i = 0; i < nodes.length - 1; i++) {
            nodes[i].setNextNode(nodes[i + 1]);
        }

        ReverseLinkedList linkedList = new ReverseLinkedList();
        Node result = linkedList.reverse(nodes[0], 3);

        System.out.println(result);

    }
}

class Node {

    private int data;

    private Node nextNode;

    public Node(int data, Node nextNode) {

        this.data = data;
        this.nextNode = nextNode;
    }

    public int getData() {

        return data;
    }

    public void setData(int data) {

        this.data = data;
    }

    public Node getNextNode() {

        return nextNode;
    }

    public void setNextNode(Node nextNode) {

        this.nextNode = nextNode;
    }

    @Override
    public String toString() {

        return data + (nextNode != null ? "->" + nextNode : "");
    }
}