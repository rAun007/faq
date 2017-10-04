package com.spark;

/**
 * Created by raunak.agrawal on 10/4/17.
 */
public class RLL {

    public static void main(String[] args) {

        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);
        head.next.next.next.next.next = new Node(6);
        head.next.next.next.next.next.next = new Node(7);
        head.next.next.next.next.next.next.next = new Node(8);
        head.next.next.next.next.next.next.next.next = new Node(9);
        head.next.next.next.next.next.next.next.next.next = new Node(10);

        System.out.println(head);
        Node n = reverse(head, 3);
        System.out.println(n);
    }

    private static Node reverse(Node head, int k) {

        Node prev = null;
        Node curr = head;
        Node next = null;

        int count = 0;
        while (curr != null && count < k) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
            ++count;
        }

        if(next != null){
            head.next = reverse(next, k);
        }
        return prev;
    }

    static class Node {
        Node next;
        int  data;

        Node(int data) {
            this.data = data;
        }

        @Override
        public String toString() {
            Node n = this;
            String s = null;
            while (n != null) {

                if(s == null){
                    s = String.valueOf(n.data);
                }else{
                    s = s + String.valueOf(n.data);
                }

                if (n.next != null) s = s + " -> ";
                n = n.next;
            }
            return s;
        }
    }

}
